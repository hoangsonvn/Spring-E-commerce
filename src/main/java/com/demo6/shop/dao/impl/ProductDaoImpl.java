package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.ProductDao;
import com.demo6.shop.entity.Product;
import com.demo6.shop.model.StatsByYearDTO;
import com.demo6.shop.model.StatsDTO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {


    @Autowired
    private SessionFactory sessionFactory;
    private static Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

    @Override
    public List<Integer> listYears() {
        String sql = " select year(o.buyDate) from Order o order by o.buyDate asc";
        TypedQuery<Integer> typedQuery = sessionFactory.getCurrentSession().createQuery(sql);
        return typedQuery.getResultList();
    }

    @Override
    public Double totalOrderPricebyMonthAndYear(Integer month, Integer year) {// tính tổng order theo tháng theo năm hoặc cả 2
        logger.info("tính tổng giá Order theo tháng hoặc năm hoặc cả 2");
        String sql = "select sum(o.priceTotal) from Order  o WHERE (:month is null or month(o.buyDate)= :month)" +
                "and (:year is null or year(o.buyDate)=:year) and o.status='SUCCESS'";
        TypedQuery typedQuery = sessionFactory.getCurrentSession().createQuery(sql)
                .setParameter("month", month)
                .setParameter("year", year);
        Double sum = (Double) typedQuery.getSingleResult();
        return  sum;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public int coutStats(Integer month, Integer year) {
        logger.info("tính số lượng sản phẩm đã bán ");
        String sql = "select count(distinct i.product.productId) from Item i where i.order.status='SUCCESS'" +
                " and (:month is null or month(i.order.buyDate)= :month)" +
                " and (:year is null or year(i.order.buyDate)= :year)";
        TypedQuery typedQuery = sessionFactory.getCurrentSession().createQuery(sql)
                .setParameter("month", month)
                .setParameter("year", year);
        long obj = (long) typedQuery.getSingleResult();
        return (int) obj;
    }

    @org.springframework.transaction.annotation.Transactional
    @Override
    public List<StatsByYearDTO> totalEachYear() {
        logger.info("tính tổng giá theo mỗi năm toan bộ mặt hàng");
        String sql = "select sum(o.priceTotal),year(o.buyDate) from Order o group by year(o.buyDate) order by o.buyDate asc ";
        TypedQuery typedQuery = sessionFactory.getCurrentSession().createQuery(sql);
        List<Object[]> list = typedQuery.getResultList();
        List<StatsByYearDTO> statsByYearDTOS = new ArrayList<>();
        for (Object[] obj : list
        ) {
            StatsByYearDTO statsByYearDTO = new StatsByYearDTO();
            statsByYearDTO.setYear((Integer) obj[1]);
            statsByYearDTO.setTotal((Double) obj[0]);
            statsByYearDTOS.add(statsByYearDTO);
        }
        return statsByYearDTOS;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public List listStats(Integer month, Integer year, Integer pageIndex, Integer pageSize) {
        logger.info("lấy ra danh sách doanh thu phân trang");
 /*       pageIndex= Optional.ofNullable(pageIndex).orElse(0);
        pageSize= Optional.ofNullable(pageSize).orElse(0);*/
        Integer first = pageIndex == null & pageSize == null ? 0 : pageIndex * pageSize;

        pageSize = Optional.ofNullable(pageSize).orElse(coutStats(month, year));
       /* if (pageIndex == null & pageSize == null) {
            first = 0;
        }*/
        // first = Optional.ofNullable(pageIndex * pageSize).orElse(0);

        String sql = " select i.product.productName,sum(i.quantity),sum(i.unitPrice)" +
                " ,sum(i.unitPrice)/sum(i.quantity),min(i.unitPrice/i.quantity)" +
                " ,max(i.unitPrice/i.quantity) from Item i where i.order.status='SUCCESS'" +
                " and (:month is null or month(i.order.buyDate) = :month) and(:year is null or year(i.order.buyDate) = :year)" +
                " group by i.product.productId  ";
        logger.info("lẽ ra nhóm theo productname nhưng mình đang test nên để toàn trùng sản phẩm nen mới như vậy");
        TypedQuery typedQuery = sessionFactory.getCurrentSession().createQuery(sql)
                .setParameter("month", month)
                .setParameter("year", year)
                .setFirstResult(first)
                .setMaxResults(pageSize);
        List<Object[]> list = typedQuery.getResultList();
        List<StatsDTO> statsDTOList = new ArrayList<>();
        for (Object[] obj : list
        ) {
            StatsDTO statsDTO = new StatsDTO();
            statsDTO.setName((String) obj[0]);
            statsDTO.setQuantity((Long) obj[1]);
            statsDTO.setTotalprice((Double) obj[2]);
            statsDTO.setAvgprice((Double) obj[3]);
            statsDTO.setMinprice((Float) obj[4]);
            statsDTO.setMaxprice((Float) obj[5]);
            statsDTOList.add(statsDTO);
        }
        return statsDTOList;
    }

    @org.springframework.transaction.annotation.Transactional
    @Override
    public void insert(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @org.springframework.transaction.annotation.Transactional
    @Override
    public void update(Product product) {
        sessionFactory.getCurrentSession().merge(product);
    }

    @Transactional
    @Override
    public void delete(long productId) {
        Product product = findById(productId);
        sessionFactory.getCurrentSession().delete(product);

    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public Product findById(long productId) {
        return sessionFactory.getCurrentSession().find(Product.class, productId);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public List<Product> findAll(int pageIndex, int pageSize) {
        int first = pageIndex * pageSize;
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class).setFirstResult(first).setMaxResults(pageSize);
        return criteria.list();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public List<Product> findAllByCategoryId(long catgoryId, int pageIndex, int pageSize) {
        String sql = "SELECT p FROM Product p WHERE p.category.categoryId = " + catgoryId;
        int first = pageIndex * pageSize;
        TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery(sql).setFirstResult(first).setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(p) FROM Product p";
        TypedQuery query = sessionFactory.getCurrentSession().createQuery(sql);
        long count = (long) query.getSingleResult();
        return (int) count;
    }

    @Override
    public int countByCategoryId(long categoryId) {
        String sql = "SELECT COUNT(p) FROM Product p where p.category.categoryId = :categoryId ";
        TypedQuery query = sessionFactory.getCurrentSession().createQuery(sql)
                .setParameter("categoryId", categoryId);

        long count = (long) query.getSingleResult();
        return (int) count;
    }


    @org.springframework.transaction.annotation.Transactional(readOnly = true)

    @Override
    public List<Product> search(Long categoryId, String pricing, float priceFrom, float priceTo, String sort, String text, int pageIndex,
                                int pageSize) {
        String sql = "SELECT p FROM Product p WHERE 1=1" ;
           /*     " and (:categoryId is null or p.category.categoryId = :categoryId)" +
                " and (:pricing is null or :pricing='default' or :pricing='' or  (p.price-p.price*p.sale.salePercent/100) > :priceFrom and (p.price-(p.price * p.sale.salePercent/100)) < :priceTo)" +
                " and (:text is null or :text = '' or ( p.productName  like concat(concat('%',:text),'%')) )" +
                " and ( :sort is null or :sort = '' or :sort='default'  order by p.productName asc  ";
*/
        //TypedQuery typedQuery = sessionFactory.getCurrentSession().createQuery(sql);
        if (categoryId != null) {
            sql+=" and p.category.categoryId = '" + categoryId + "'";
        }
        if (pricing != null && !pricing.equals("default") && !pricing.equals("")) {
            sql+=" and ((p.price - (p.price * p.sale.salePercent / 100)) >= " + priceFrom + " and (p.price - (p.price * p.sale.salePercent / 100)) <= " + priceTo + ")";
        }

        if (text != null) {
           sql+=" and CONCAT(p.productName,' ',p.price,' ',p.category.categoryName,' ',p.price - (p.price * p.sale.salePercent / 100))  like '%" + text + "%'";
        }

        if (sort != null && !sort.equals("default")) {
           sql+=" ORDER BY (p.price - (p.price * p.sale.salePercent / 100)) " + sort;
        }

        int first = pageIndex * pageSize;
        TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery(sql.toString())
                .setFirstResult(first).setMaxResults(pageSize);
        return query.getResultList();


    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public int countBySearch(Long categoryId, String pricing, float priceFrom, float priceTo, String text) {

        StringBuilder sb = new StringBuilder("SELECT COUNT(p) FROM Product p where 1=1 ");
        sb.append(" and (:categoryId is null or p.category.categoryId = :categoryId)");
        sb.append(" and (:pricing is null or :pricing ='default' or :pricing =''  or  (p.price-p.price*p.sale.salePercent/100) > :priceFrom and (p.price-(p.price * p.sale.salePercent/100)) < :priceTo)");// chỉ ra rằng mệnh đề này là đúng
        logger.info("tức thằng thằng is null đúng thằng sau sai thì vẫn đúng vì toán tử or, nếu thằng sau = default đúng, mấy thằng còn lại sai mệnh đề vẫn đúng vẫn đúng");
        sb.append(" and (:text is null or ( CONCAT(p.productName,' ',p.price,' ',p.category.categoryName,' ',p.price - (p.price * p.sale.salePercent / 100))  like concat(concat('%',:text), '%')))");
        TypedQuery typedQuery = sessionFactory.getCurrentSession().createQuery(sb.toString())
                .setParameter("categoryId", categoryId)
                .setParameter("pricing", pricing)
                .setParameter("priceFrom", priceFrom)
                .setParameter("priceTo", priceTo)
                .setParameter("text", text);

        long count = (long) typedQuery.getSingleResult();
        return (int) count;
    }


}

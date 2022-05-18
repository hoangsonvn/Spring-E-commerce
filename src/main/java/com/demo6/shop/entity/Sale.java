package com.demo6.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {


	@Id
	@Column(name = "sale_id", length =4)
	private String saleId;
	@Column(name = "sale_percent")
	private int salePercent;
	@OneToMany(mappedBy = "sale")
	private List<Product> products;


}

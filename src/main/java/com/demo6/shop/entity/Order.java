package com.demo6.shop.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_user")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long orderId;
	
	@Column(name = "buy_date")
	private Date buyDate;
	
	@Column(name = "status")
	private String status;
	@NotNull
	@Column(name = "price_total")
	private float priceTotal;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User buyer;
	@OneToMany(mappedBy = "order",cascade = {CascadeType.REMOVE})
	private List<Item> items;
	
}

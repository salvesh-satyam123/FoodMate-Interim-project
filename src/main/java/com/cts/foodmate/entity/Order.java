package com.cts.foodmate.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cts.foodmate.utils.OrderDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_table")
@Builder
@ToString
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long oid;
	
	private long userId;
	
	private String summary;
	
	private String address;
	
	private Date calendarDate;
	
	private long price;
	
	@Transient
	private List<OrderDetails> description=new ArrayList<>();

}

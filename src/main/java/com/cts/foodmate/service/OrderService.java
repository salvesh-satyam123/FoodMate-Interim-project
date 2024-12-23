package com.cts.foodmate.service;

import java.util.Date;
import java.util.List;

import com.cts.foodmate.entity.Order;
import com.cts.foodmate.entity.Cart;
import com.cts.foodmate.utils.Revenue;

public interface OrderService {
		
	
	public void addOrder(long userId,String Address);
	public List<Order>getAllOrderOfUser(long userId);
	public Order getOrderByOrderId(long oid);
	
 	public Revenue getRevenue(Date date);
	public Revenue getRevenueMonthly(Date date1,Date date2);
//	public List<Order>getAllOrders();



	

}

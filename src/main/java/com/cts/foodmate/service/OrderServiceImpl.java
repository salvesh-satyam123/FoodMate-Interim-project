package com.cts.foodmate.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.foodmate.entity.Cart;
import com.cts.foodmate.entity.Order;
import com.cts.foodmate.entity.UserInfo;
import com.cts.foodmate.exception.OrderNotFoundException;
import com.cts.foodmate.exception.UserNotExistException;
import com.cts.foodmate.repository.OrderDao;
import com.cts.foodmate.repository.UserInfoRepository;
import com.cts.foodmate.utils.OrderDetails;
import com.cts.foodmate.utils.Revenue;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderdao;
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	CartService cartService;

	private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

	@Override
	@Transactional
	public void addOrder(long userId, String Address) {
		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);

		if (!userInfo.isPresent()) {
			throw new UserNotExistException("User not found");
		}

		List<Cart> cart = cartService.getCart(userId);

		logger.info("{}", cart);

		Date today = Calendar.getInstance().getTime();
		Order order = new Order();
		order.setUserId(userId);
		order.setCalendarDate(today);
		order.setAddress(Address);
		String summary = "";

		long price = cart.stream().map(p -> p.getQuantity() * p.getProduct().getPrice()).collect(Collectors.toList())
				.stream().reduce(0L, (ans, i) -> ans + i);

		for (Cart c : cart) {
			summary += c.getProduct().getName() + "," + c.getQuantity() + "," + c.getProduct().getPrice() + ";";
		}
		logger.info("{}", summary);

		order.setSummary(summary);
		order.setPrice(price);
		logger.info("{}", order);
		orderdao.save(order);
		cartService.moveFromCartToOrder(userId);
		

	}

	@Override
	public List<Order> getAllOrderOfUser(long userId) throws UserNotExistException {

		logger.info("In Get all Orders of User Service");

		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);

		if (!userInfo.isPresent()) {
			throw new UserNotExistException("User not found");
		}

		List<Order> res = new ArrayList<>();

		orderdao.findByUserId(userId).stream().forEach(order -> {
			String summary = order.getSummary();

			String[] subSummary = summary.split(";");
			for (String s : subSummary) {
				OrderDetails o = new OrderDetails();

				String[] subs = s.split(",");

				o.setName(subs[0]);
				o.setPrice(Long.parseLong(subs[1]));
				o.setQuantity(Integer.parseInt(subs[2]));

				order.getDescription().add(o);

			}
			res.add(order);
		});
		return res;
	}

	@Override
	public Order getOrderByOrderId(long oid) throws OrderNotFoundException {

		logger.info("In Get Orders Service");

		if (!orderdao.findById(oid).isPresent()) {

			logger.warn("Invalid Order ID");
			throw new OrderNotFoundException("Invalid Order ID");
		}

		Order order = orderdao.findById(oid).get();

		String summary = order.getSummary();

		String[] subSummary = summary.split(";");

		for (String s : subSummary) {
			String[] subs = s.split(",");
			order.getDescription().add(new OrderDetails(subs[0], Integer.parseInt(subs[2]), Long.parseLong(subs[1])));
		}

		return order;
	}

	public Revenue getRevenue(Date date) {
		long sum = 0;
		List<Order> orders = new ArrayList<>();
		orderdao.getAllOrderBydate(date).forEach(order -> {
			orders.add(order);
		});

		for (Order o : orders) {
			sum += o.getPrice();
		}

		Revenue revenue = new Revenue();
		revenue.setFromDate(date);
		revenue.setToDate(date);
		revenue.setPrice(sum);

		return revenue;

	}

	public Revenue getRevenueMonthly(Date date1, Date date2) {
		long sum = 0;
		List<Order> orders = new ArrayList<>();
		orderdao.getAllOrderBetweendate(date1, date2).forEach(order -> {
			orders.add(order);
		});

		for (Order o : orders) {
			sum += o.getPrice();
		}

		Revenue revenue = new Revenue();
		revenue.setFromDate(date1);
		revenue.setToDate(date2);
		revenue.setPrice(sum);

		return revenue;

	}
//
//	@Override
//	public void addOrder(List<Cart> cart, String address) throws OrderNotFoundException {
//		// TODO Auto-generated method
//
//		if (cart.size() == 0) {
//			
//			logger.warn("Invalid Order Call");
//			throw new OrderNotFoundException("Invalid Order");
//		}
//
//		Date today = Calendar.getInstance().getTime();
//
//		long id = cart.get(0).getUserId();
//		Order order = new Order();
//		order.setUserId(id);
//		order.setCalendarDate(today);
//		order.setAddress(address);
//		String summary = "";
//
//		for (Cart cartitem : cart) {
//			summary += cartitem.toString() + ";";
//		}
//
////		long price = cart.stream().map(p -> p.getQuantity() * p.getProduct().getPrice())
////				.collect(Collectors.toList()).stream().reduce(0L, (ans, i) -> ans + i);
//
////		System.out.println(price);
////		order.setDescription(null);
////
////		order.setSummary(summary);
////		order.setPrice(price);
//		//Order o=orderdao.save(order);
//		orderdao.save(order);
//
//	}
//

//
//	
//	public List<Order>getAllOrders()
//	{
//		return orderdao.findAll();
//	}

}

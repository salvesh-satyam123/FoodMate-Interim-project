package com.cts.foodmate.repository;


import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.foodmate.entity.Order;

@Repository
public interface OrderDao extends JpaRepository<Order,Long>{
 
	
	public List<Order> findByUserId(long userId);
	
	@Query("Select o from Order o where o.calendarDate=?1")
     public List<Order> getAllOrderBydate(Date date);
//	
//	
	@Query("Select o from Order o where o.calendarDate between ?1 and ?2 ")
    public List<Order> getAllOrderBetweendate(Date date1,Date date2);

}

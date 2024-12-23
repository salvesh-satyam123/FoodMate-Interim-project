package com.cts.foodmate.utils;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Revenue {
	
	private Date fromDate;
	private Date toDate;
    private long price;
}

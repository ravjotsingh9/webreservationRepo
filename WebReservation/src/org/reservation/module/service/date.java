package org.reservation.module.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;


public class date {
	public static void main(String srg[]) throws ParseException{
		java.util.Date curr_date = new java.util.Date();
        
        
		DateTime d= new DateTime(curr_date.getTime());
		
		System.out.println("d=" +d);
	}
}

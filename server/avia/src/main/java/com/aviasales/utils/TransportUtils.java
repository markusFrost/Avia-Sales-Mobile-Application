package com.aviasales.utils;

import java.util.ArrayList;
import java.util.List;

import com.avia.entities.City;
import com.avia.entities.FlightItem;
import com.aviasales.db.BookingDb;

public class TransportUtils {

	public static List<List<FlightItem>> formAnswerByFlightsIds(List<List<FlightItem>> mainList, City cityFrom, City cityTo) {
		
		BookingDb db = new BookingDb();
		
		for (int j = 0; j < mainList.size(); j++){
			
			List<FlightItem> list = mainList.get(j);
			
				
				for (int i = 0; i < list.size(); i++){
					
					FlightItem item = list.get(i);
					
					item = db.getFlight(item.getFlightId());
					
					list.set(i, item);
				}
			
			
			mainList.set(j, list);
		}
		
		return joinItemsInlistView(mainList,cityFrom, cityTo);
		
	}
	
	private static List<List<FlightItem>> joinItemsInlistView(List<List<FlightItem>> mainList, City cityFrom, City cityTo ){
		
		List<FlightItem> listItems = new ArrayList<FlightItem>();
		
		List<List<FlightItem>> resultList = new ArrayList<List<FlightItem>>();
		
		boolean startSearchFrom = true;
		boolean startSearchTo = false;
		
		for (List<FlightItem> list : mainList){
			
			if (list.size() == 1){
				
				FlightItem item = list.get(0);
				
				
				if (item.getCityFrom().getCityId().toString()
						.equalsIgnoreCase(cityFrom.getCityId().toString()) && startSearchFrom){
					
					startSearchFrom = false;
					startSearchTo = true;
					
					listItems = new ArrayList<FlightItem>();
					
					listItems.add(item);
				}
				
				else if (item.getCityTo().getCityId().toString()
						.equalsIgnoreCase(cityTo.getCityId().toString()) && startSearchTo){
					
					startSearchFrom = true;
					startSearchTo = false;
					listItems.add(item);
					
					resultList.add(listItems);
					
				}
								
				else if (startSearchTo){

					listItems.add(item);	
				}
				
				
			}
		}
		return resultList;
	}

}

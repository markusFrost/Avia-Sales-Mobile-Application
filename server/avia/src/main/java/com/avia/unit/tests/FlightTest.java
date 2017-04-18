package com.avia.unit.tests;

import java.util.UUID;

import com.avia.entities.City;
import com.avia.entities.FlightItem;
import com.avia.exchange.FlightRequest;
import com.aviasales.db.FlightDb;
import com.aviasales.utils.TImeUtils;

public class FlightTest {
	
	
	public static String testGetFlights(){
		
		String msg = "";
		
		//msg += addFlights() + "\n";
		
		FlightRequest request = new FlightRequest();

        request.setCityFrom("Kaliningrad");
        request.setCityTo("Murmansk");
        request.setDateDeparture(TImeUtils.convertStringWithTimeToMills("23.07.2017 11:05"));
        request.setRoundTrip(false);
        
        FlightDb db = new FlightDb();
        
       String json =  db.getFlights(request);
       
       msg += json + "\n";
		
		return msg;
	}
	
	private static String addFlights(){
		
		String msg = "";
		
		City cityFrom = generateCity("Kaliningrad");
		City cityTo = generateCity("Murmansk");
		
		FlightDb db = new FlightDb();
		
		msg += db.addCity(cityFrom) + "\n";
		msg += db.addCity(cityTo) + "\n";
		
		
		
		String dateDep = "23.07.2017 12:05";
		String dateArr = "23.07.2017 15:25";
		
		String [] arrayDatesDep = {"23.07.2017 12:05", "23.07.2017 18:55",
				"23.07.2017 23:05", "24.07.2017 01:05"};
		
		String [] arrayDatesArr = {"23.07.2017 15:25", "23.07.2017 21:15",
				"24.07.2017 01:05", "24.07.2017 05:05"};
		
		if (arrayDatesArr.length == arrayDatesDep.length){
			
			int size = arrayDatesArr.length;
			
			for (int i = 0; i < size; i++){
				
				long timeDep = TImeUtils.convertStringWithTimeToMills(arrayDatesDep[i]);
				long timeArr = TImeUtils.convertStringWithTimeToMills(arrayDatesArr[i]);
				
				UUID flightId = UUID.randomUUID();
				
				int freePlaceCount = 15 + i;
				int price = 100 + i * 10;
				
				
				FlightItem item = new FlightItem();
				
				item.setCityFrom(cityFrom);
				item.setCityTo(cityTo);
				
				item.setDateArr(timeArr);
				item.setDateDep(timeDep);
				item.setFlightId(flightId);
				item.setFreePlaceCount(freePlaceCount);
				item.setPricePerTicket(price);
				
				
				msg += db.addFlights(item) + "\n";
			}
		}
		
		return msg;
		
		
		
	}
	
	private static City generateCity(String name){
		
		City city = new City();
		
		UUID id = UUID.randomUUID();
		
		city.setCityId(id);	
		city.setName(name);	
		city.setCode(id.toString().toString().substring(0, 10));
		
		return city;
	}

}

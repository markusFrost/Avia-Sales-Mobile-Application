package avia.androi.innopolis.com.aviasales.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import avia.androi.innopolis.com.aviasales.models.Booking;
import avia.androi.innopolis.com.aviasales.models.City;
import avia.androi.innopolis.com.aviasales.models.Flight;

public class FakeHelper {

    public static List<Booking> generateBooking(){

        List<Booking> listBooking = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            List<Flight> listFlightsTo = new ArrayList<>();
            List<Flight> listFlightsBack = new ArrayList<>();

            UUID guid = HelpUtils.generateGUID();

            for (int j = 1; j <= 2; j++) {

                City cityFrom = new City();
                cityFrom.setName("CityFrom " + i);

                City cityTo = new City();
                cityTo.setName("CityTo " + i);


                Flight flight = new Flight();
                flight.setCityFrom(cityFrom);
                flight.setCityTo(cityTo);
                flight.setDateArr(i * 5);
                flight.setDateDep(i);
                flight.setFreePlaceCount(i);
                flight.setPricePerTicket(i * 50 + 10);

                listFlightsTo.add(flight);

                listFlightsBack.add(flight);
            }

            Booking booking = new Booking();

            booking.setDateBook(System.currentTimeMillis());
            booking.setListFlightsTo(listFlightsTo);
            booking.setListFlightsBack(listFlightsBack);

            booking.setBookingId(guid);

            listBooking.add(booking);
        }

        return listBooking;
    }
}

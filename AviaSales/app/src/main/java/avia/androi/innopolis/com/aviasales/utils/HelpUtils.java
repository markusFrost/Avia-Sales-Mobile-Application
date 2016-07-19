package avia.androi.innopolis.com.aviasales.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import avia.androi.innopolis.com.aviasales.models.Flight;

public class HelpUtils {

    public static List<UUID> getListIds (List<Flight> list){

        List<UUID> listIds = new ArrayList<>();

        for (Flight flight : list){

            listIds.add(flight.getFlightId());
        }

        return listIds;
    }

    public static UUID generateGUID(){

        UUID uuid = UUID.randomUUID();

        return uuid;
    }

    public static List<Flight> buildListFlightsFromOneSizeList(List<List<Flight>> array){

        List<Flight> listFlights = new ArrayList<>();

        for (List<Flight> list : array){

            if (list.size() == 1){

                listFlights.add(list.get(0));
            }
        }

        return listFlights;
    }
}

package avia.androi.innopolis.com.aviasales.utils;

import java.util.ArrayList;
import java.util.List;

import avia.androi.innopolis.com.aviasales.models.Flight;

public class HelpUtils {

    public static List<String> getListIds (List<Flight> list){

        List<String> listIds = new ArrayList<>();

        for (Flight flight : list){

            listIds.add(flight.getId());
        }

        return listIds;
    }
}

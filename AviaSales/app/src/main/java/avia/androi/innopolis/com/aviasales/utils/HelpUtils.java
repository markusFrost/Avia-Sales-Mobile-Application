package avia.androi.innopolis.com.aviasales.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import avia.androi.innopolis.com.aviasales.models.Flight;

public class HelpUtils {

    public static List<UUID> getListIds (List<Flight> list){

        List<UUID> listIds = new ArrayList<>();

        for (Flight flight : list){

            listIds.add(flight.getId());
        }

        return listIds;
    }

    public static UUID generateGUID(){

        UUID uuid = UUID.randomUUID();

        return uuid;
    }
}

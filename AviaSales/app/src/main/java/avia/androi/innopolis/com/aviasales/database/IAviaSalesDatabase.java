package avia.androi.innopolis.com.aviasales.database;

import java.util.List;

import avia.androi.innopolis.com.aviasales.models.Flight;
import avia.androi.innopolis.com.aviasales.models.User;

public interface IAviaSalesDatabase {

    void addUser (User user);

    void addFlight (Flight flight);

    void deleteFlight (Flight flight);

    void clearFlightsHistory();

    void bookFlight (Flight flight);

    List<Flight> getFlights();
}

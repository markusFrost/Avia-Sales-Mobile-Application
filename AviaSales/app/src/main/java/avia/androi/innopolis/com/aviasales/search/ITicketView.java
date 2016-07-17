package avia.androi.innopolis.com.aviasales.search;

import java.util.List;

import avia.androi.innopolis.com.aviasales.interfaces.UILoader;
import avia.androi.innopolis.com.aviasales.models.Flight;

public interface ITicketView extends UILoader{

    void displayFlightsList(List<Flight> list);

    void displayEmptyFlightsList();
}

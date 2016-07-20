package avia.androi.innopolis.com.aviasales.search;

import java.util.List;

import avia.androi.innopolis.com.aviasales.interfaces.UILoader;
import avia.androi.innopolis.com.aviasales.models.Booking;
import avia.androi.innopolis.com.aviasales.models.Flight;

public interface ITicketView extends UILoader{

    void displayFlightsListNoTranspher(List<Flight> list);

    void displayEmptyFlightsListNoTranspher();

    void displayFlightsListTransphers(List<Flight> list);

    void displayEmptyFlightsListTransphers();

    void goToBookingHistory(List<Booking> listBooking);

    void showAlertPlaceCount();
}

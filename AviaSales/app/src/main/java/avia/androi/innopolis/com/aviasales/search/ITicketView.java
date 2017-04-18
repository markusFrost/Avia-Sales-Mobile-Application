package avia.androi.innopolis.com.aviasales.search;

import java.util.List;

import avia.androi.innopolis.com.aviasales.interfaces.UILoader;
import avia.androi.innopolis.com.aviasales.models.Booking;
import avia.androi.innopolis.com.aviasales.models.Flight;

public interface ITicketView extends UILoader{

    void showNotTrasph(List<Flight> list);

    void showNoTrasphBack(List<Flight> listBackTranphers);

    void showWithTrasph (List<List<Flight>> listTo);

    void showWithTrasphBack (List<List<Flight>> listBack);

    void showEmptyFlights();

    void goToBookingHistory(List<Booking> listBooking);

    void showAlertPlaceCount();



}

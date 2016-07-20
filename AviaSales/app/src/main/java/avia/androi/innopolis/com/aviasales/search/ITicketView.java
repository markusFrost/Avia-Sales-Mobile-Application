package avia.androi.innopolis.com.aviasales.search;

import java.util.List;

import avia.androi.innopolis.com.aviasales.interfaces.UILoader;
import avia.androi.innopolis.com.aviasales.models.Booking;
import avia.androi.innopolis.com.aviasales.models.Flight;

public interface ITicketView extends UILoader{

    void display___Flights__No__Tranpher__With__Full___Straight(List<Flight> list);

    void display__Flights__NO___Transpher__Empty___Straight();

    void display__Flights__No___Transphers__Full___Back(List<Flight> list);

    void display__Flights__No___Transphers__Empty___Back();

    void goToBookingHistory(List<Booking> listBooking);

    void showAlertPlaceCount();

}

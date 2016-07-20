package avia.androi.innopolis.com.aviasales.history;

import java.util.List;

import avia.androi.innopolis.com.aviasales.interfaces.UILoader;
import avia.androi.innopolis.com.aviasales.models.Booking;

public interface IBookingHistoryView extends UILoader {

    void displayBookingHistoryList(List<Booking> listBooking);

    void displayEmptyBookingHistoryList();
}

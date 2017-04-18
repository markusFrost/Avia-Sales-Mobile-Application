package avia.androi.innopolis.com.aviasales.models;

import java.util.List;
import java.util.UUID;

public class Booking {

    private UUID bookingId;

    private long dateBook;

    private List<Flight> listFlightsTo;
    private List<Flight> listFlightsBack;

    public long getDateBook() {
        return dateBook;
    }

    public void setDateBook(long dateBook) {
        this.dateBook = dateBook;
    }

    public List<Flight> getListFlightsTo() {
        return listFlightsTo;
    }

    public void setListFlightsTo(List<Flight> listFlightsTo) {
        this.listFlightsTo = listFlightsTo;
    }

    public List<Flight> getListFlightsBack() {
        return listFlightsBack;
    }

    public void setListFlightsBack(List<Flight> listFlightsBack) {
        this.listFlightsBack = listFlightsBack;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }
}

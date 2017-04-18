package com.avia.entities;

import java.util.List;
import java.util.UUID;

public class Booking {

    private UUID bookingId;

    private long dateBook;

    private List<FlightItem> listFlightsTo;
    private List<FlightItem> listFlightsBack;

    public long getDateBook() {
        return dateBook;
    }

    public void setDateBook(long dateBook) {
        this.dateBook = dateBook;
    }

    public List<FlightItem> getListFlightsTo() {
        return listFlightsTo;
    }

    public void setListFlightsTo(List<FlightItem> listFlightsTo) {
        this.listFlightsTo = listFlightsTo;
    }

    public List<FlightItem> getListFlightsBack() {
        return listFlightsBack;
    }

    public void setListFlightsBack(List<FlightItem> listFlightsBack) {
        this.listFlightsBack = listFlightsBack;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }
}

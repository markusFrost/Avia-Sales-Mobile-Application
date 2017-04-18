package com.avia.exchange;

import java.util.List;
import java.util.UUID;

public class BookingRequest {

    private UUID userId;

    private int placeCount;

    private UUID bookingId;

    private List<UUID> listFlightIdsStraight;

    private List<UUID> listFlightIdsBack;

    public List<UUID> getListFlightIdsBack() {
        return listFlightIdsBack;
    }

    public void setListFlightIdsBack(List<UUID> listFlightIdsBack) {
        this.listFlightIdsBack = listFlightIdsBack;
    }

    private long dateBooking;

    public long getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(long dateBooking) {
        this.dateBooking = dateBooking;
    }


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<UUID> getListFlightIdsStraight() {
        return listFlightIdsStraight;
    }

    public void setListFlightIdsStraight(List<UUID> listFlightIdsStraight) {
        this.listFlightIdsStraight = listFlightIdsStraight;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }
}

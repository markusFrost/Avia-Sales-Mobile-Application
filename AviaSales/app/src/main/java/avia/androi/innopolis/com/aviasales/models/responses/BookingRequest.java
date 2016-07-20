package avia.androi.innopolis.com.aviasales.models.responses;

import java.util.List;

public class BookingRequest {

    private String userId;

    private int placeCount;

    private List<String> listFlightIds;

    private long dateBooking;

    public long getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(long dateBooking) {
        this.dateBooking = dateBooking;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getListFlightIds() {
        return listFlightIds;
    }

    public void setListFlightIds(List<String> listFlightIds) {
        this.listFlightIds = listFlightIds;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }
}

package avia.androi.innopolis.com.aviasales.models.responses;

import java.util.List;
import java.util.UUID;

public class BookingRequest {

    private UUID userId;

    private int placeCount;

    private List<UUID> listFlightIds;

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

    public List<UUID> getListFlightIds() {
        return listFlightIds;
    }

    public void setListFlightIds(List<UUID> listFlightIds) {
        this.listFlightIds = listFlightIds;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }
}

package avia.androi.innopolis.com.aviasales.models.responses;

import java.util.List;

import avia.androi.innopolis.com.aviasales.models.Booking;

public class BookingResponse {

    private int code;

    private String message;

    private List<Booking> listBooking;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Booking> getListBooking() {
        return listBooking;
    }

    public void setListBooking(List<Booking> listBooking) {
        this.listBooking = listBooking;
    }
}

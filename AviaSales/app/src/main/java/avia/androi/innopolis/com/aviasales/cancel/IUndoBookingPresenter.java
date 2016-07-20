package avia.androi.innopolis.com.aviasales.cancel;

import java.util.UUID;

import avia.androi.innopolis.com.aviasales.interfaces.IPresenter;

public interface IUndoBookingPresenter extends IPresenter{

    void undoBooking(UUID bookingId);
}

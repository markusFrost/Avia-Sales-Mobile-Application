package avia.androi.innopolis.com.aviasales.booking;

import java.util.List;
import java.util.UUID;

import avia.androi.innopolis.com.aviasales.interfaces.IPresenter;

public interface IBookingPresenter extends IPresenter {

    void book(List<UUID> listIdsStraight, List<UUID> listIds, int placeCount);
}

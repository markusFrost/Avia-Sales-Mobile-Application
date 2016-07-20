package avia.androi.innopolis.com.aviasales.search;

import avia.androi.innopolis.com.aviasales.interfaces.IPresenter;
import avia.androi.innopolis.com.aviasales.models.responses.FlightRequest;

public interface ISearchPresenter extends IPresenter {

    void search(FlightRequest request);
}

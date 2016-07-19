package avia.androi.innopolis.com.aviasales.search;

import avia.androi.innopolis.com.aviasales.interfaces.ILoader;
import avia.androi.innopolis.com.aviasales.models.Item;
import avia.androi.innopolis.com.aviasales.models.User;
import avia.androi.innopolis.com.aviasales.models.responses.FlightResponse;
import avia.androi.innopolis.com.aviasales.objects.AppContext;

public class SearchLoader implements ILoader<Item> {

    ISearchPresenter iPresenter;

    public SearchLoader(ISearchPresenter presenter) {

        this.iPresenter = presenter;
    }

    @Override
    public void load(User item) {

        String json = "{\"listTo\":[[{\"flightId\":\"c039581f-e70b-4a3e-83f6-82b253d091d5\",\"mCityFrom\":{\"cityId\":\"f3df9929-bd70-4658-9a4d-d523a3bf2a2c\",\"name\":\"Moscow\",\"code\":\"1\"},\"mCityTo\":{\"cityId\":\"a4ce48f3-25df-4d12-b0b9-50f25c920b27\",\"name\":\"Tokio\",\"code\":\"2\"},\"mDateDep\":1468947780000,\"mDateArr\":1469034180000,\"mPricePerTicket\":200.0,\"mFreePlaceCount\":20}],[{\"flightId\":\"4759b7a7-0aa2-427d-9ff8-1790019215d4\",\"mCityFrom\":{\"cityId\":\"f3df9929-bd70-4658-9a4d-d523a3bf2a2c\",\"name\":\"Moscow\",\"code\":\"1\"},\"mCityTo\":{\"cityId\":\"a4ce48f3-25df-4d12-b0b9-50f25c920b27\",\"name\":\"Tokio\",\"code\":\"2\"},\"mDateDep\":1468969500000,\"mDateArr\":1468980600000,\"mPricePerTicket\":250.0,\"mFreePlaceCount\":10}]],\"listBack\":[[{\"flightId\":\"5ae8c137-0c96-4eb5-8232-f2ce8872ae6a\",\"mCityFrom\":{\"cityId\":\"a4ce48f3-25df-4d12-b0b9-50f25c920b27\",\"name\":\"Tokio\",\"code\":\"2\"},\"mCityTo\":{\"cityId\":\"f3df9929-bd70-4658-9a4d-d523a3bf2a2c\",\"name\":\"Moscow\",\"code\":\"1\"},\"mDateDep\":1469205000000,\"mDateArr\":1469219400000,\"mPricePerTicket\":240.0,\"mFreePlaceCount\":25}]],\"code\":0}";

        FlightResponse response = AppContext.getGson().fromJson(json, FlightResponse.class);

        Object object = response;

        iPresenter.onServerSuccess(object);

    }
}

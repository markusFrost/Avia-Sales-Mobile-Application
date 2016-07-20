package avia.androi.innopolis.com.aviasales.search;

import java.util.List;

import avia.androi.innopolis.com.aviasales.models.Flight;
import avia.androi.innopolis.com.aviasales.models.responses.FlightRequest;
import avia.androi.innopolis.com.aviasales.models.responses.FlightResponse;
import avia.androi.innopolis.com.aviasales.utils.HelpUtils;

public class SearchPresenter implements ISearchPresenter {

    private ITicketView iView;

    private SearchLoader mSearchLoader;

    public SearchPresenter(ITicketView view) {

        this.iView = view;

        mSearchLoader = new SearchLoader(this);
    }

    @Override
    public void search(FlightRequest request) {

        mSearchLoader.load(request);

        iView.showProgressBar();
    }

    @Override
    public void onServerSuccess(Object object) {

        if (object instanceof FlightResponse) {

            FlightResponse response = (FlightResponse) object;

            List<List<Flight>> listTo = response.getListTo();

            List<List<Flight>> listBack = response.getListBack();

            List<Flight> listNoTranphers = HelpUtils.buildListFlightsFromOneSizeList(listTo);

            List<Flight> listBackTranphers = HelpUtils.buildListFlightsFromOneSizeList(listBack);

            if (listNoTranphers.isEmpty()) {

                iView.showEmptyFlights();
            } else {

                iView.showNotTrasph(listNoTranphers);
            }

            if (listBackTranphers.isEmpty()){

                iView.showEmptyFlights();
            }
            else{

                iView.showNoTrasphBack(listBackTranphers);
            }

            List<List<Flight>> listWithTo = HelpUtils.buildListFlightsFromManySizeList(listTo);

            List<List<Flight>> listWithBack = HelpUtils.buildListFlightsFromManySizeList(listBack);


            showWithTo(listWithTo);

            showWithBack(listWithBack);


        }

        iView.hideProgressBar();


    }

    private void showWithBack(List<List<Flight>> listWithBack) {

        if (listWithBack != null && !listWithBack.isEmpty()) {

            iView.showWithTrasphBack(listWithBack);
        }
    }

    private void showWithTo(List<List<Flight>> listWithTo) {

        if (listWithTo != null && !listWithTo.isEmpty()) {

            iView.showWithTrasph(listWithTo);
        }
    }

    @Override
    public void onServerFail() {

        iView.hideProgressBar();
    }

    @Override
    public void onConnectionFail() {

        iView.hideProgressBar();
    }
}

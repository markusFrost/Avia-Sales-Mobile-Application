package avia.androi.innopolis.com.aviasales.search;

import java.util.List;

import avia.androi.innopolis.com.aviasales.models.Flight;
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
    public void search() {

        mSearchLoader.load(null);
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

                iView.displayEmptyFlightsListNoTranspher();
            } else {

                iView.displayFlightsListNoTranspher(listNoTranphers);
            }

            if (listBackTranphers.isEmpty()){

                iView.displayEmptyFlightsListTransphers();
            }
            else{

                iView.displayFlightsListTransphers(listBackTranphers);
            }

        }


    }

    @Override
    public void onServerFail() {

    }

    @Override
    public void onConnectionFail() {

    }
}

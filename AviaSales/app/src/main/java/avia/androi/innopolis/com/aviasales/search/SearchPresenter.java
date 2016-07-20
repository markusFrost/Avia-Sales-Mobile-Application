package avia.androi.innopolis.com.aviasales.search;

import java.util.ArrayList;
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

                iView.display__Flights__NO___Transpher__Empty___Straight();
            } else {

               // iView.display___Flights__No__Tranpher__With__Full___Straight(listNoTranphers);

                List<List<Flight>> listMain = new ArrayList<>();

                listNoTranphers.add(listNoTranphers.get(listNoTranphers.size() - 1));
                for (int i = 1; i <= 3; i++){

                    listMain.add(listNoTranphers);

                }

                iView.display___Flights__WITH__Tranpher____Full___Straight(listMain);
            }

            /*if (listBackTranphers.isEmpty()){

                iView.display__Flights__No___Transphers__Empty___Back();
            }
            else{

                iView.display__Flights__No___Transphers__Full___Back(listBackTranphers);
            }*/

        }

        iView.hideProgressBar();


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

package avia.androi.innopolis.com.aviasales.view;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.Counter;
import avia.androi.innopolis.com.aviasales.utils.ViewUtils;

public class FlightsInBackDirectionLoader extends BaseFlightsLoader {

    private Activity activity;

    public FlightsInBackDirectionLoader(Activity activity){

        super(activity);
        this.activity = activity;
    }

    public void addTripsBackInfo(LinearLayout container, Counter index){

        View helpView = ViewUtils.createHelpView(activity);

        View viewTranspherType =  activity.getLayoutInflater().
                inflate(R.layout.view_transpher_type, null);

        TextView tv = (TextView)viewTranspherType.findViewById(R.id.search_tv_transfer_name);

        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
        tv.setTextColor(Color.GREEN);
        tv.setText(R.string.trips_back);


        container.addView(viewTranspherType, index.getCount());
        index.increment();
        container.addView(helpView, index.getCount());
    }
}

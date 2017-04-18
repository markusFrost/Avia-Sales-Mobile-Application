package avia.androi.innopolis.com.aviasales.models;

import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ViewItem {

    private List<UUID> listIds;

    private boolean isClicked;

    private List<View> listViews;

    public List<View> getListViews() {
        return listViews;
    }

    public void setListViews(List<View> listViews) {
        this.listViews = listViews;
    }

    private int placeCount;

    private int mTripType;

    public int getTripType() {
        return mTripType;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }

    public List<UUID> getListIds() {
        return listIds;
    }

    public void setListIds(List<UUID> listIds) {
        if (this.listIds == null){

            this.listIds = new ArrayList<>();
        }
        this.listIds.addAll(listIds);
    }

    public void addId(UUID id) {
        if (listIds == null){

            listIds = new ArrayList<>();
        }
        listIds.add(id);
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public void setTripType(int tripType) {
        mTripType = tripType;
    }
}

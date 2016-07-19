package avia.androi.innopolis.com.aviasales.models;

public class Flight extends BaseEntity {

    private City mCityFrom;

    private City mCityTo;

    private long mDateDep;

    private long mDateArr;

    private double mPricePerTicket;

    private int mFreePlaceCount;

    public Flight(){};

    public City getCityFrom() {
        return mCityFrom;
    }

    public void setCityFrom(City cityFrom) {
        mCityFrom = cityFrom;
    }

    public City getCityTo() {
        return mCityTo;
    }

    public void setCityTo(City cityTo) {
        mCityTo = cityTo;
    }

    public long getDateDep() {
        return mDateDep;
    }

    public void setDateDep(long dateDep) {
        mDateDep = dateDep;
    }

    public long getDateArr() {
        return mDateArr;
    }

    public void setDateArr(long dateArr) {
        mDateArr = dateArr;
    }

    public double getPricePerTicket() {
        return mPricePerTicket;
    }

    public void setPricePerTicket(double pricePerTicket) {
        mPricePerTicket = pricePerTicket;
    }

    public int getFreePlaceCount() {
        return mFreePlaceCount;
    }

    public void setFreePlaceCount(int freePlaceCount) {
        mFreePlaceCount = freePlaceCount;
    }
}

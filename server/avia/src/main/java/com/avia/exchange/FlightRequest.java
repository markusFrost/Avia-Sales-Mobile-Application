package com.avia.exchange;

public class FlightRequest {

    private String cityFrom;

    private String cityTo;

    private long dateDeparture;

    private boolean isRoundTrip;
    
    private long dateBackReturn;
    

	public long getDateBackReturn() {
		return dateBackReturn;
	}

	public void setDateBackReturn(long dateBackReturn) {
		this.dateBackReturn = dateBackReturn;
	}

	public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public long getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(long dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public void setRoundTrip(boolean roundTrip) {
        isRoundTrip = roundTrip;
    }
}

package avia.androi.innopolis.com.aviasales.models;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class City {

    @SerializedName("cityId")
    private UUID mCityId;

    @SerializedName("name")
    private String mName;

    @SerializedName("code")
    private String mCode;


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public UUID getCityId() {
        return mCityId;
    }

    public void setCityId(UUID cityId) {
        mCityId = cityId;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }
}

package avia.androi.innopolis.com.aviasales.models;

public class Counter {
    private int mCount;

    public Counter(){}

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public void increment(){
        mCount++;
    }
}

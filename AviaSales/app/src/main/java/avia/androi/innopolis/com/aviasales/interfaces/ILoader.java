package avia.androi.innopolis.com.aviasales.interfaces;

import avia.androi.innopolis.com.aviasales.models.User;

public interface ILoader<T> {

    void load(User item);
}

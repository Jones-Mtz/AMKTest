package com.amk.amktest.utils;

/**
 * Created by Jones on 29/09/17.
 */

public interface FragmentCommunication<T> {
    void fragmentNotification(String fragmentName, T data);
}

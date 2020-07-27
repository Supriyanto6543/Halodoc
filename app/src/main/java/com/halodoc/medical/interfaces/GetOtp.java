package com.halodoc.medical.interfaces;

/**
 * Created by Supriyanto on 7/1/2020.
 */
public interface GetOtp {

    void onOptReceived(String otp);
    void onTimeout();
}

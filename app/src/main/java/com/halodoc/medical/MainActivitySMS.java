package com.halodoc.medical;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.halodoc.medical.sms.SmsHelper;

/**
 * Created by Supriyanto on 7/1/2020.
 */
public class MainActivitySMS extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        SmsHelper smsHelper = new SmsHelper(this);
        smsHelper.getAppHashCode();
    }
}

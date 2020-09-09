package com.halodoc.medical;

import android.content.Context;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Supriyanto on 9/7/2020.
 * Copyright (c) 2020 Murottal Pro . All rights reserved.
 */
public class PaymentGateway {

    private Context context;

    public PaymentGateway(Context context) {
        this.context = context;
    }

    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, "{\n    \"payment_type\": \"bank_transfer\",\n    \"transaction_details\": {\n        \"gross_amount\": 44000,\n        \"order_id\": \"order-101q-1599621318\"\n    },\n    \"customer_details\": {\n        \"email\": \"noreply@example.com\",\n        \"first_name\": \"budi\",\n        \"last_name\": \"utomo\",\n        \"phone\": \"+6281 1234 1234\"\n    },\n    \"item_details\": [\n    {\n       \"id\": \"item01\",\n       \"price\": 21000,\n       \"quantity\": 1,\n       \"name\": \"Ayam Zozozo\"\n    },\n    {\n       \"id\": \"item02\",\n       \"price\": 23000,\n       \"quantity\": 1,\n       \"name\": \"Ayam Xoxoxo\"\n    }\n   ],\n   \"bank_transfer\":{\n     \"bank\": \"bni\",\n     \"va_number\": \"12345678\"\n  }\n}");
    Request request = new Request.Builder()
            .url("https://api.sandbox.midtrans.com/v2/charge")
            .method("POST", body)
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Basic U0ItTWlkLXNlcnZlci16M3ZCaFJsM1pPY0c4MkxsbXV4eFhCSHM6")
            .addHeader("Cookie", "__cfduid=dca479fbc49df8bd869ff38e27109fc0d1599472331")
            .build();
    Response response;

    {
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

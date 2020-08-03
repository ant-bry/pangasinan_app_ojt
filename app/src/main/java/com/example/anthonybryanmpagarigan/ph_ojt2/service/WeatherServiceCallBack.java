package com.example.anthonybryanmpagarigan.ph_ojt2.service;

import com.example.anthonybryanmpagarigan.ph_ojt2.data.Channel;

public interface WeatherServiceCallBack {
    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}

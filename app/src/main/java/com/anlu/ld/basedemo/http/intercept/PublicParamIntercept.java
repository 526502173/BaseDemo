package com.anlu.ld.basedemo.http.intercept;


import com.anlu.ld.basedemo.AndroidApplication;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by maoqi on 2018/7/12.
 */
public class PublicParamIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrl = request.url();
        HttpUrl.Builder urlBuilder = httpUrl.newBuilder();

        Map<String, String> hashMapParams = AndroidApplication.getInstance().getPublicParamMap();
        Set<String> set = hashMapParams.keySet();
        for (String key : set) {
            urlBuilder.addQueryParameter(key, hashMapParams.get(key));
        }

        HttpUrl newHttpUrl = urlBuilder.build();
        Request newRequest = request.newBuilder()
                .method(request.method(), request.body())
                .url(newHttpUrl).build();
        Response response = chain.proceed(newRequest);
        return response;
    }

}

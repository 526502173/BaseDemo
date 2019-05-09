package com.anlu.ld.basedemo.http.converter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by maoqi on 2017/8/1.
 */

public final class CustomStringConverter<T> implements Converter<ResponseBody, T> {
    CustomStringConverter() {
    }
    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String body = value.string();
            return (T) body;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }
}

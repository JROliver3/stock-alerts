package com.example.stockalerts.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

public class ObjectSerializer<T> {
    public T DeserializeJSONObject(JSONObject jsonObject, Builder<T> builder) throws JSONException,
            IllegalAccessException {
        T object = builder.build();
        for (Field field : object.getClass().getFields()) {
            if(jsonObject.isNull(field.getName())){ continue; }
            field.set(object, jsonObject.get(field.getName()));
        }
        return object;
    }
}

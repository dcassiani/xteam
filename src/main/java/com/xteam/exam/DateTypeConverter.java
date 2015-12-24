package com.xteam.exam;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateTypeConverter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    // No need for an InstanceCreator since DateTime provides a no-args constructor
    @Override
    public JsonElement serialize(Date src, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public Date deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

        DateFormat dateFormat = new SimpleDateFormat(ISO_DATE_FORMAT);
        Date date = null;

        try {
            date = dateFormat.parse(json.getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        return date;
    }
}
package com.discountasciiwarehouse.ecommerce.proxy.adapter;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CalendarTypeConverter implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {


    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @Override
    public JsonElement serialize(Calendar src, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public Calendar deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

        DateFormat dateFormat = new SimpleDateFormat(ISO_DATE_FORMAT);
        Date date = null;

        try {
            date = dateFormat.parse(json.getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }
}
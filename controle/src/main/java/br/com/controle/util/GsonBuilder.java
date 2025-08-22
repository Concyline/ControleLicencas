package br.com.controle.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GsonBuilder {

    private static Gson gson  = new com.google.gson.GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

    public static  <T> String toJson(T value) throws Exception{
        return gson.toJson(value);
    }

    public static  <T> T fromJson(String value, Class c) throws Exception{
        return (T) gson.fromJson(value, c);
    }

    public static  <T> List<T> fronJsomList(String value, Class c) throws Exception{
        Type typeOfT = TypeToken.getParameterized(List.class, c).getType();
        return (List<T>) gson.fromJson(value, typeOfT);
    }
}

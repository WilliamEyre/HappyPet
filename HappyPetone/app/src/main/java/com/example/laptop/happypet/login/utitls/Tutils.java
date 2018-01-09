package com.example.laptop.happypet.login.utitls;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2018/1/9.
 */

public class Tutils {
    private static Class<?> aClass;
    private static Type superclass;
    private static Type[] arguments;

    public static <T>T getT(Object o, int i){

        try {
            aClass = o.getClass();
            superclass = aClass.getGenericSuperclass();
            arguments = ((ParameterizedType) superclass).getActualTypeArguments();
            Type type = arguments[i];
            return ((Class<T>)type).newInstance();
        } catch (InstantiationException e) {


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

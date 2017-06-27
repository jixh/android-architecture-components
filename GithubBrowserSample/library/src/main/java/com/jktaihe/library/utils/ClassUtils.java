package com.jktaihe.library.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by jktaihe on 13/6/17.
 * blog: blog.jktaihe.com
 */

public class ClassUtils {

    public static <T> Class<T> getClass(Class clz,int index){
        ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();
        Type[] types = type.getActualTypeArguments();
        return  (Class<T>) types[index];
    }

}

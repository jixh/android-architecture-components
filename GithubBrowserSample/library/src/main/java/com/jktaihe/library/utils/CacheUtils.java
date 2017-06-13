package com.jktaihe.library.utils;

import com.jktaihe.library.Constant;

import java.io.File;

/**
 * Created by jktaihe on 10/6/17.
 * blog: blog.jktaihe.com
 */

public class CacheUtils {

    public static File getCacheDir(){
        return Constant.appContext.getCacheDir();
    }
}

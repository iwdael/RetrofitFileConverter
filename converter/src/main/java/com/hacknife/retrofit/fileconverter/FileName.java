package com.hacknife.retrofit.fileconverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface FileName {
    String value();
}

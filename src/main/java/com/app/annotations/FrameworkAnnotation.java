package com.app.annotations;


import com.app.enums.CategoryType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface FrameworkAnnotation {

    String[] author() default "";
    CategoryType[] category() ;
}

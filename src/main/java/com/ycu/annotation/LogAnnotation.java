package com.ycu.annotation;

import java.lang.annotation.*;

/**
 * Created by Yang on 2018/4/1.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface LogAnnotation {

    /**模块名称*/
    String moduleName() default "";
    /**操作内容*/
    String operate() default "";
}

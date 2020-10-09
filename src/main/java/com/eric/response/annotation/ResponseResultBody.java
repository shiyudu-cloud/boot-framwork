package com.eric.response.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * @author shiyu.du
 * @date 2020/9/8
 **/

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ResponseBody
public @interface ResponseResultBody {
}

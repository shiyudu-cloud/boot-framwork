package com.eric.response.config;

import com.eric.response.annotation.ResponseResultBody;
import com.eric.response.entity.Result;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * @author shiyu.du
 * @date 2020/9/8
 **/
@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;

    /**
     * 判断类或者方法是否使用了@ResponseReusltBody
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(),ANNOTATION_TYPE ) ||methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    /**
     * 当前的类或者方法使用了 @ResponseResultBody 进行逻辑处理
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof Result){
            return o;
        }
        return Result.success(o);
    }
}

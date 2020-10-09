package com.eric.response.exceptions;

import com.eric.response.entity.ResultStatus;

/**
 * @author shiyu.du
 * @date 2020/9/8
 **/
public class ResultException extends RuntimeException{

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public ResultStatus getResultStatus() {
        return ResultStatus.BAD_REQUEST;
    }
}

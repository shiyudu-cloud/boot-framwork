package com.eric.response.entity;

import lombok.Getter;
import lombok.ToString;

/**
 * @author shiyu.du
 * @date 2020/9/8
 **/
@ToString
@Getter
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    private Result(ResultStatus resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    /** 业务成功返回业务代码和描述信息 */
    public static Result<Void> success() {
        return new Result<Void>(ResultStatus.SUCCESS, null);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultStatus.SUCCESS, data);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> Result<T> success(ResultStatus resultStatus, T data) {
        if (resultStatus == null) {
            return success(data);
        }
        return new Result<T>(resultStatus, data);
    }

    /** 业务异常返回业务代码和描述信息 */
    public static <T> Result<T> failure() {
        return new Result<T>(ResultStatus.INTERNAL_SERVER_ERROR, null);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> Result<T> failure(ResultStatus resultStatus) {
        return failure(resultStatus, null);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> Result<T> failure(ResultStatus resultStatus, T data) {
        if (resultStatus == null) {
            return new Result<T>(ResultStatus.INTERNAL_SERVER_ERROR, null);
        }
        return new Result<T>(resultStatus, data);
    }

}

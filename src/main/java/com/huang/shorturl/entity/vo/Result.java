package com.huang.shorturl.entity.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.huang.shorturl.enums.StatusEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


/**
 * @author yaohui.huang
 * @className Result
 * @date 2023/1/1 13:06
 * @description: 共用，返回结果
 */
@Setter
@Getter
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "状态编码")
    private int status = StatusEnum.OK.getCode();
    @Schema(description = "错误代码")
    private int code = 0;
    @Schema(description = "信息")
    private String message = "操作成功";
    @Schema(description = "结果数据")
    private T data;

    public boolean isSuccess() {
        return status == StatusEnum.OK.getCode();
    }


    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(StatusEnum.OK.getCode());
        result.setData(data);
        return result;
    }



    public static <T> Result<T> error(int code, String message) {
        Result<T> result = new Result<>();
        result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setCode(code);
        result.setMessage(message);
        return result;
    }


   
}

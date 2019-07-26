package com.zyj.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author by zyj
 * @version V1.0
 * @Description:
 * @Date 2019/7/25 9:39
 */
@ApiModel("统一返回")
public class Result {
    @ApiModelProperty(name = "code",value = "代码")
    private int code;
    @ApiModelProperty(name = "msg",value = "信息")
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }




}

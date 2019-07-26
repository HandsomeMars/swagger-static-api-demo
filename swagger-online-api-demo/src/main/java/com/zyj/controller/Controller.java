package com.zyj.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by zyj
 * @version V1.0
 * @Description:
 * @Date 2019/7/25 9:23
 */
@RestController
@Api(tags = "test",description = "用于测试swagger")
public class Controller {

    @RequestMapping(value = "/hello",method = RequestMethod.POST)
   /**
    * 此配置适用于单独api参数配置
    * 会覆盖对象api参数配置
    * @ApiImplicitParams(
    *  {
    *  @ApiImplicitParam(name = "result", value = "测试result", required = true, paramType = "com.zyj.controller.Result")
    * }
    *)
    */
    @ApiOperation(
            value = "hello-swagger", notes = "用于测试swagger在线文档",
            response = Result.class
           )
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 404, message = "未找到资源")}
    )
    public Result hello(@RequestBody Result result){
        return result;
    }
}

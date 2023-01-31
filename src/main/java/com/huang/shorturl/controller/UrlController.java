package com.huang.shorturl.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huang.shorturl.entity.vo.Result;
import com.huang.shorturl.enums.StatusEnum;
import com.huang.shorturl.service.ISmartUrlService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * @author yaohui.huang
 * @className SmartUrlController
 * @date 2023/1/1 13:06
 * @description: 短链接服务：短链接生成、重定向
 */
@Tag(name = "短链接服务")
@RestController
@AllArgsConstructor
public class UrlController {
    @Autowired
    private ISmartUrlService smartUrlService;

    /**
     * 生成短链接
     * @param url 长域名： 如https://github.com/scdt-china/interview-assignments/tree/master/java
     * @return 短链接：localhost:8080/r/43CIew1
     */
    @Operation(summary = "生成短链接")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "url", value = "长连接URL", required =true, paramType = "query", dataType = "String", defaultValue = ""),
     })
    @PostMapping("/createCodeByUrl")
    public Result<String> createCodeByUrl(@RequestParam("url") String url) {
        if (StringUtils.isEmpty(url))
        {
        	return Result.error(StatusEnum.ILLEGAL_PARAMETER.getCode(),StatusEnum.ILLEGAL_PARAMETER.getMsg());
        }
        return Result.ok(smartUrlService.saveShortUrl(url));
    }
    
    
    /**
     * 获取长链接
     *
     * @param urlCode 短链接编码 ，如：43CIew1
     * @return 重定向
     */
    @Operation(summary = "获取长链接")
    @GetMapping("/getOriginUrl")
    public Result<String> getOriginUrl(@RequestParam("urlCode") String urlCode) {
    	 if (StringUtils.isEmpty(urlCode))
         {
         	return Result.error(StatusEnum.ILLEGAL_PARAMETER.getCode(),StatusEnum.ILLEGAL_PARAMETER.getMsg());
         }
        String originUrl = smartUrlService.decode(urlCode);
        return Result.ok(originUrl);
    }

   
}

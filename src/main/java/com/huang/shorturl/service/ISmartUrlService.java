package com.huang.shorturl.service;

import com.huang.shorturl.entity.vo.Result;

/**
 * @author yaohui.huang
 * @className ISmartUrlService
 * @date 2023/1/1 14:24
 * @description: 服务层接口
 */
public interface ISmartUrlService {
    
    
    /**短域名存储接口：接受长域名信息
     * @param originUrl 原始链接（长链接）
     * @return 短域名信息
     */
	String saveShortUrl(String originUrl);

    /**
     * 短域名读取接口：接受短域名信息
     * @param urlCode 短链接编码
     * @return 长域名信息
     */
    String decode(String urlCode);
}

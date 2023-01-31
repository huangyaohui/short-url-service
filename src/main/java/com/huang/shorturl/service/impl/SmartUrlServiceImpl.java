package com.huang.shorturl.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson2.JSON;
import com.huang.shorturl.entity.po.ShortUrlInfo;
import com.huang.shorturl.entity.vo.Result;
import com.huang.shorturl.enums.StatusEnum;
import com.huang.shorturl.service.ISmartUrlService;
import com.huang.shorturl.util.BaseConvertUtil;
import com.huang.shorturl.util.CommonUtils;

/**
 * @author yaohui.huang
 * @className SmartUrlService
 * @date 2023/1/1 19:06
 * @description: 服务层实现
 */
@Service
public class SmartUrlServiceImpl implements ISmartUrlService {

    /**
     * 短链接解码失败，重定
     */
    @Value("${smartUrl.errorPage}")
    private String smartUrlErrorPage;

    /**
     * 服务域名
     */
    @Value("${smartUrl.domainName}")
    private String smartUrlDomainName;

   
    
    /**
     * 长链接编码成短链接
     *
     * @param originUrl 原始链接（长链接）
     * @return 短链接
     */
    @Override
    public String saveShortUrl(String originUrl) {
    	
    	ShortUrlInfo shortUrlInfo=new ShortUrlInfo();
    	shortUrlInfo.setOriginUrl(originUrl);
        String str=CommonUtils.parseDateToStr(CommonUtils.YYYYMMDDHHMMSS, new Date());
        Long id=Long.valueOf(str);
        shortUrlInfo.setId(id);
        // 依据当前时间作为发号器，转化为62进制（只包含数字、大小写字母）
        String smartCode = BaseConvertUtil.encode10to62(shortUrlInfo.getId(), 6);
        shortUrlInfo.setSmartCode(smartCode);
        CommonUtils.urlList.add(shortUrlInfo);
        List<ShortUrlInfo> list= CommonUtils.getUrlList();
        System.out.println("-->"+JSON.toJSONString(list));
        return smartUrlDomainName + "/r/" + smartCode;
    }
    
    

    /**
     * 短链接解码码短链接
     *
     * @param smartUrl 短链接
     * @return 原始链接（长链接）
     */
    @Override
    public String decode(String urlCode) {
        // 根据62进制编码（只包含数字、大小写字母）查询原始url信息
        // 将62进制转换成十进制,根据主键直接查询
        long id = BaseConvertUtil.encode62to10(urlCode);
        List<ShortUrlInfo> list= CommonUtils.getUrlList();
        ShortUrlInfo urlinfo=null;
        if(list!=null)
        {
        	for(ShortUrlInfo vo:list)
        	{
        		long urlid=vo.getId();
        		if(id==urlid)
        		{
        			urlinfo=vo;
        		}
        	}
        }
        String originUrl = smartUrlErrorPage;
        if(urlinfo!=null)
        {
        	  originUrl=urlinfo.getOriginUrl();
        }
        return originUrl;
        
    }
}


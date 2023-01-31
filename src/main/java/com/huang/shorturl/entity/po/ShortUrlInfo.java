package com.huang.shorturl.entity.po;



/**
 * @author yaohui.huang
 * @className SmartUrlInfo
 * @date 2023/1/1 15:11
 * @description: 短链接实体类
 */
public class ShortUrlInfo {
	
    /**
     * id
     */
    private Long id;
    /**
     * 短链接编码62进制
     */
    private String smartCode;
    /**
     * 原始链接
     */
    private String originUrl;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSmartCode() {
		return smartCode;
	}
	public void setSmartCode(String smartCode) {
		this.smartCode = smartCode;
	}
	public String getOriginUrl() {
		return originUrl;
	}
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
    
    
}

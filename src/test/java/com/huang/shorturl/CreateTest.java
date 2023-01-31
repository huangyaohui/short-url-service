package com.huang.shorturl;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.huang.shorturl.entity.vo.Result;
import com.huang.shorturl.service.impl.SmartUrlServiceImpl;

/**
 * @author yaohui.huang
 * @className CreateTest
 * @date 2023/1/1 13:06
 * @description: 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShortUrlApplication.class)
@Service
public class CreateTest {
	MockMvc mockMvc;
	@Resource
    private SmartUrlServiceImpl smartUrlServiceImpl;
	

    /**初始化MockMvc
     * @param wac
     */
    @BeforeEach
    void setUp(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    
    
	/**生成短链接 controller 有参测试
	 * @throws Exception
	 */
	@Test
	void createCodeByUrl() throws Exception {
	    MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/createCodeByUrl")
	            .param("url", "https://github.com/scdt-china/interview-assignments/tree/master/java")
	            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .accept(MediaType.APPLICATION_JSON))
	            .andReturn();
	    
	    
	}
	/**生成短链接 controller 无参测试
	 * @throws Exception
	 */
	@Test
	void createCodeByUrlTest() throws Exception {
	    
	    MvcResult createCodeByUrl = this.mockMvc.perform(MockMvcRequestBuilders.post("/createCodeByUrl")
	            .param("url", "")
	            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .accept(MediaType.APPLICATION_JSON))
	            .andReturn();
	}
	
	/**获取长链接 controller 无参测试
	 * @throws Exception
	 */
	@Test
	void getOriginUrl2() throws Exception {
	    
	    //生成短链接 controller 无参测试
	    MvcResult getOriginUrl = this.mockMvc.perform(MockMvcRequestBuilders.get("/getOriginUrl")
	            .param("urlCode", "")
	            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .accept(MediaType.APPLICATION_JSON))
	            .andReturn();
	}
	
	/**获取长链接 controller 无参测试
	 * @throws Exception
	 */
	@Test
	void createCodeByUrlTest2() throws Exception {
		String shortUrl=smartUrlServiceImpl.saveShortUrl("https://github.com/scdt-china/interview-assignments/tree/master/java");
		String urlCode=shortUrl.replace("localhost:8080/r/", "");
		//生成短链接 controller 有参测试
	    MvcResult getOriginUrl = this.mockMvc.perform(MockMvcRequestBuilders.get("/getOriginUrl")
	            .param("urlCode", urlCode)
	            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .accept(MediaType.APPLICATION_JSON))
	            .andReturn();
	}
	    
		
	/**获取长链接 服务层 有参测试
	 * @throws Exception
	 */
	@Test
	void decode() throws Exception {
		smartUrlServiceImpl.decode("abc");
	}
	
	
	
	
   
}

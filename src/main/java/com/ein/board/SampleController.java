package com.ein.board;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(value = "/sample")
public class SampleController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @RequestMapping(value = "/getSample")
    public void getSample(HttpServletRequest request, HttpServletResponse reponse) throws Exception {        
    	logger.debug("=========================== getSample =================================");
    }
}
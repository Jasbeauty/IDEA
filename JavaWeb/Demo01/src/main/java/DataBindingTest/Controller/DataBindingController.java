package DataBindingTest.Controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/DataBindingController")
public class DataBindingController {
    private static final Log logger = LogFactory.getLog(DataBindingController.class);

    @RequestMapping
    public String DataBindingTest() {
        return "DataBindingTest";
    }

    // @PathVariable：获得请求URL中的动态参数，但只支持一个String类型的属性value
    @RequestMapping(value = "/pathVariableTest/{userId}")
    public void pathVariableTest(@PathVariable Integer userId) {
        logger.info("通过 @PathVariable 获得数据： " + userId);
    }

    // @RequestHeader：用于将请求的头信息区数据映射到功能处理方法的参数上
    @RequestMapping(value = "/requestHeaderTest")
    public void requestHeaderTest(
            @RequestHeader("User-Agent") String userAgent,
            @RequestHeader(value = "Accept") String[] accepts) {
        logger.info("通过 @RequestHeader 获得数据： " + userAgent);
        for (String accept :
                accepts) {
            logger.info(accept);
        }
    }

    // @CookieValue：用于将请求的Cookie数据映射到功能处理方法的参数上
    @RequestMapping(value = "/cookieValueTest")
    public void cookieValueTest(
            @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {
        logger.info("通过 @CookieValue 获得数据： " + sessionId);
    }
}

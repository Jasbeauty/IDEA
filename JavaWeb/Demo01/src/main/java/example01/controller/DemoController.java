package example01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 注解 @Controller 表示这是一个控制器，当请求来时将会扫描是否有匹配的RequestMapping
@Controller
//注解 @RequestMapping 表示映射的路由，这里表示的是 /home/index
@RequestMapping("/home")
public class DemoController {
    @RequestMapping(value = "/index")
    public String index() {
//        return index 表示使用的模版是index，也就是 webapp/index.jsp
        return "index";
    }



}

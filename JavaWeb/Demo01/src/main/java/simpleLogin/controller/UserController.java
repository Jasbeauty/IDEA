package simpleLogin.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import simpleLogin.model.User;

import java.util.ArrayList;
import java.util.List;

// @Controller 注解用于指示该类是一个控制器，可以同时处理多个请求动作
@Controller
// @RequestMapping 可以用来注释一个控制器类，此时所有方法都将映射为相对于类级别的请求
// value = "..." 表示该处理器处理的所有请求都被映射到value属性所指示的路径下
@RequestMapping(value = "/user")

// @SessionAttributes：使得有选择的指定Model中的哪些属性需要转存到HttpSession对象中
// @SessionAttributes：只能声明在类上，而不能声明在方法上
@SessionAttributes("user")
public class UserController {
    // 此处使用userList代替数据库用来保存注册的用户信息
    private static List<User> userList;

    public UserController() {
        super();
        userList = new ArrayList<>();
    }

    // 静态的日志类
    private static final Log logger = LogFactory.getLog(UserController.class);

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    // 跳转到注册页面
    public String registerForm() {
        logger.info("register GET 方法被调用...");
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    // 获取前台传递的参数
    public String register(
            @RequestParam("loginName") String loginName,
            @RequestParam("password") String password,
            @RequestParam("userName") String userName) {

        logger.info("register POST 方法被调用...");
        // 创建User对象
        User user = new User();
        user.setLoginName(loginName);
        user.setPassword(password);
        user.setUserName(userName);
        // 模拟数据库存储User信息
        userList.add(user);
        return "loginForm";
    }

    @RequestMapping(value = "/login")
    public String login(
            @RequestParam("loginName") String loginName,
            @RequestParam("password") String password,
            Model model) {
        logger.info("登录名：" + loginName + "密码：" + password);

        // 到userList查找用户是否存在，此处用来模拟数据库验证
        for (User user : userList
                ) {
            if (user.getLoginName().equals(loginName) && user.getPassword().equals(password)) {
                // 将user对象添加到Model中
                // addAttribute() 可以将数据添加到 request 中
                model.addAttribute("user", user);
                return "welcome";
            }
        }
        return "registerForm";
    }
}

package JsonRequestTest.controller;

import JsonRequestTest.model.Book;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

// @RestController是@ResponseBody和@Controller的组合注解
@Controller
@RequestMapping("/json")
public class BookController {
    private static final Log logger = LogFactory.getLog(BookController.class);
    static Gson gson = new Gson();

    @RequestMapping(value = "/toPage")
    public String jsonRequestTestPage() {
        return "jsonRequestTest";
    }


    @PostMapping(value = "/testRequestBody")
    @ResponseBody
    public String setJson(@RequestBody Book book, HttpServletResponse response) {
//        System.out.println("当前http请求方式为:" + request.getMethod());
        response.setContentType("application/json;charset=UTF-8");
        System.out.println(response);
        System.out.println("bookId=" + book.getId() + ", name=" + book.getName());
        Book postData = new Book();
        postData.setId(book.getId());
        postData.setName(book.getName());
        return gson.toJson(postData);
    }


    @GetMapping(value = "/testGetBody")
    // @RequestBody：根据json数据，转换为对应的Object，并将其返回给客户端
    // 这里@ResponseBody代表该方法接受请求后不是跳转页面，而是直接返回json数据
    @ResponseBody
    public static String getJson(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        List<Book> list = new ArrayList<Book>();

        Book a = new Book();
        a.setId(1);
        a.setName("算法");
        a.setAuthor("fortunate");
        list.add(a);
        return gson.toJson(list);
    }

//    public static String object2json(Object obj) {
//        Gson gson = new Gson();
//        return gson.toJson(obj);
//    }
//    gson.fromJson(String json, Class<T> classOfT)
}

package JsonRequestTest.controller;

import JsonRequestTest.model.Book;
import JsonRequestTest.model.BookByxml;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
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
    public Book setJson(@RequestBody Book book) {
//        System.out.println("当前http请求方式为:" + request.getMethod());
//        response.setContentType("application/json;charset=UTF-8");
        System.out.println("bookId=" + book.getId() + ", name=" + book.getName());
        Book postData = new Book();
        postData.setId(book.getId());
        postData.setName(book.getName());
        return postData;
    }


    @GetMapping(value = "/testGetBody")
    // @RequestBody：根据json数据，转换为对应的Object，并将其返回给客户端
    // 这里@ResponseBody代表该方法接受请求后不是跳转页面，而是直接返回json数据
    @ResponseBody
    public static List<Book> getJson() {
        List<Book> list = new ArrayList<Book>();

        Book a = new Book();
        a.setId(1);
        a.setName("算法");
        a.setAuthor("fortunate");
        list.add(a);
        return list;
    }

    @PostMapping(value = "/sendxml")
    @ResponseBody
    public void sendxml(@RequestBody BookByxml bookByxml){
        System.out.println(bookByxml);
        logger.info(bookByxml);
        logger.info("接收xml数据成功");
    }

    @GetMapping(value = "/readxml")
    @ResponseBody
    public BookByxml readxml() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(BookByxml.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream is = this.getClass().getResourceAsStream("/book.xml");
        BookByxml bookByxml = (BookByxml) unmarshaller.unmarshal(is);
        logger.info(bookByxml);
        System.out.println(new String(readFileFromResource("book.xml")));
        return bookByxml;
    }

    public static byte[] readFileFromResource(String fileName) throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
        RandomAccessFile accessFile = new RandomAccessFile(resource.getFile(), "r");
        byte[] bytes = new byte[(int) accessFile.length()];
        accessFile.read(bytes);
        accessFile.close();
        return bytes;
    }

//    public static String object2json(Object obj) {
//        Gson gson = new Gson();
//        return gson.toJson(obj);
//    }
//    gson.fromJson(String json, Class<T> classOfT)
}

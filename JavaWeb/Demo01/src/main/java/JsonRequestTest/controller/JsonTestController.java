package JsonRequestTest.controller;

import JsonRequestTest.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class JsonTestController {
    @RequestMapping(value = "/acceptJsonByEntity", method = RequestMethod.GET)
    public String jsonTestPage() {
        return "jsonTest";
    }

    @RequestMapping(value = "/acceptJsonByEntity/json", method = RequestMethod.POST)
    @ResponseBody
    public Book acceptJsonByEntity(@RequestBody Book book, HttpServletRequest request) {
        System.out.println("当前http请求方式为:" + request.getMethod());
        System.out.println("bookId=" + book.getId() + ", author=" + book.getAuthor());
        return book;

    }
}

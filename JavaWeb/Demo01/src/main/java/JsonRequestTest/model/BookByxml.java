package JsonRequestTest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// @XmlRootElement表示xml文档的根元素
@XmlRootElement
public class BookByxml {
    private Integer id;
    private String name;
    private String author;

    public BookByxml() {
        super();
    }

    public BookByxml(Integer id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }


    public Integer getId() {
        return id;
    }
    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }
    @XmlElement
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BookByxml{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

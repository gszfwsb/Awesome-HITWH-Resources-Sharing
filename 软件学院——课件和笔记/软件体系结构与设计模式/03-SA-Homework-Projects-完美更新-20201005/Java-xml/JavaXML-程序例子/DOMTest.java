import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOMTest {
    /*生成xml*/
    public void createXML() throws Exception{
        DocumentBuilder db=getDocumentBuilder();
        Document document=db.newDocument();
        Element bookstore=document.createElement("bookStore");
        //向bookstore根节点中添加字节点book
        Element book=document.createElement("book");
        Element name=document.createElement("name");
        book.appendChild(name);
        name.setTextContent("小王子");
        book.setAttribute("id", "1");
        //将book节点添加到bookstore根节点中
        bookstore.appendChild(book);
        //将bookstore节点（已经包含了book）,添加到dom树中
        document.appendChild(bookstore);
        //创建TransformerFactory对象
        TransformerFactory tff=TransformerFactory.newInstance();
        //创建Transformer对象
        Transformer tf=tff.newTransformer();
        //换行文件内容
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(document), new StreamResult(new File("boos1.xml")));
    }
    public static void main(String []args) throws Exception{
        DOMTest test=new DOMTest();
        test .createXML();
    }

}


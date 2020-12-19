/**
 * 添加XML数据
 * @author 郑清
 */
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

public class AddXMLDataDemo {

	//static File file = new File("E:/eclipse-workspace/JavaEE_workspace/Day34XML/src/Persons.xml");//Persons.xml文件绝对路径
	static File file = new File("E:/Java-xml/AgentInfo.xml");//Persons.xml文件绝对路径

	public static void main(String[] args) throws Exception {
		//①获得解析器DocumentBuilder的工厂实例DocumentBuilderFactory  然后拿到DocumentBuilder对象
		DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		//②获取一个与磁盘文件关联的非空Document对象
		Document doc = newDocumentBuilder.parse(file);
		//③通过文档对象获得该文档对象的根节点
		Element root = doc.getDocumentElement();

		//创建一个新的person节点
		Element person = doc.createElement("person");
		//创建person的几个子节点
		Element name = doc.createElement("name");
		Element age = doc.createElement("age");
		Element sex = doc.createElement("sex");
		//给子节点设置值
		name.setTextContent("王二麻子");
		age.setTextContent("56");
		sex.setTextContent("男");
		//将子节点追加到person
		person.appendChild(name);
		person.appendChild(age);
		person.appendChild(sex);
		//给person的id设置值
		person.setAttribute("id", "4");
		//将person追加到根节点
		root.appendChild(person);

		//注意：XML文件是被加载到内存中 修改也是在内存中 ==》因此需要将内存中的数据同步到磁盘中
		/*
		 * static TransformerFactory newInstance():获取 TransformerFactory 的新实例。
		 * abstract  Transformer newTransformer():创建执行从 Source 到 Result 的复制的新 Transformer。
		 * abstract  void transform(Source xmlSource, Result outputTarget):将 XML Source 转换为 Result。
		 */
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(doc);
		//Source source = new DOMSource(doc);
		//StreamResult result = new StreamResult();

		StreamResult result = new StreamResult(file);

		//Result result = new StreamResult(file);
		transformer.transform(source, result);//将 XML==>Source 转换为 Result
	}

}

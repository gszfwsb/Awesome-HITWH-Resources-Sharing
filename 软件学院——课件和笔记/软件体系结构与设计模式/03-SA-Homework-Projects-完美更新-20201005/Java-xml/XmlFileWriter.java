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


 public class XmlFileWriter {
     static File file = new File("E:/Java-xml/AgentInfo.xml");// AgentInfo.xml文件绝对路径

  	 public void logMsg(String lastNmStr, String firstNmStr, String codeStr) throws Exception{

		//①获得解析器DocumentBuilder的工厂实例DocumentBuilderFactory  然后拿到DocumentBuilder对象
		DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		//②获取一个与磁盘文件关联的非空Document对象
		Document doc = newDocumentBuilder.parse(file);
		//③通过文档对象获得该文档对象的根节点
		Element root = doc.getDocumentElement();

		//创建一个新的employee节点
		Element employee = doc.createElement("employee");

		//创建employee节点的几个子节点
		Element lname = doc.createElement("LastName");
		Element fname = doc.createElement("FirstName");
		Element code = doc.createElement("Code");

		lname.setTextContent(lastNmStr);
        fname.setTextContent(firstNmStr);
        code.setTextContent(codeStr);


		//将子节点追加到employee
		employee.appendChild(lname);
		employee.appendChild(fname);
		employee.appendChild(code);
		//给employee的id设置值
		employee.setAttribute("id", "4");
		//将employee追加到根节点
		root.appendChild(employee);

		//注意：XML文件是被加载到内存中 修改也是在内存中 ==》因此需要将内存中的数据同步到磁盘中
		/*
		* static TransformerFactory newInstance():获取 TransformerFactory 的新实例。
		* abstract  Transformer newTransformer():创建执行从 Source 到 Result 的复制的新 Transformer。
		* abstract  void transform(Source xmlSource, Result outputTarget):将 XML Source 转换为 Result。
		*/
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);//将 XML==>Source 转换为 Result
		}
}
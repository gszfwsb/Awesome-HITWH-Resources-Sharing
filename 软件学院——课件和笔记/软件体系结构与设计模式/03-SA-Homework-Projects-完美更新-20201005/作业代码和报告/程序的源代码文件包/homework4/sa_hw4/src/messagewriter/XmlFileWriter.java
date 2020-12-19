package messagewriter;
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


 public class XmlFileWriter implements MessageWriter {

     //static File file = new File("E:/Java-xml/AgentInfo.xml");// һ������£���ҪAgentInfo.xml�ļ�����·����
                                                                // ԭ����xml�ļ���Ϊ���ݿ��Ǳ�������ض����ļ����е�
     static File file = new File("AgentInfo.xml");

  	 public void logMsg(String lastNmStr, String firstNmStr, String codeStr) throws Exception{

		//�ٻ�ý�����DocumentBuilder�Ĺ���ʵ��DocumentBuilderFactory  Ȼ���õ�DocumentBuilder����
		DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		//�ڻ�ȡһ��������ļ������ķǿ�Document����
		Document doc = newDocumentBuilder.parse(file);
		//��ͨ���ĵ������ø��ĵ�����ĸ��ڵ�
		Element root = doc.getDocumentElement();

		//����һ���µ�employee�ڵ�
		Element employee = doc.createElement("employee");

		//����employee�ڵ�ļ����ӽڵ�
		Element lname = doc.createElement("LastName");
		Element fname = doc.createElement("FirstName");
		Element code = doc.createElement("Code");

		lname.setTextContent(lastNmStr);
        fname.setTextContent(firstNmStr);
        code.setTextContent(codeStr);


		//���ӽڵ�׷�ӵ�employee
		employee.appendChild(lname);
		employee.appendChild(fname);
		employee.appendChild(code);
		//��employee��id����ֵ
		//employee.setAttribute("id", "4");
		//��employee׷�ӵ����ڵ�
		root.appendChild(employee);

		//ע�⣺XML�ļ��Ǳ����ص��ڴ��� �޸�Ҳ�����ڴ��� ==�������Ҫ���ڴ��е�����ͬ����������
		/*
		* static TransformerFactory newInstance():��ȡ TransformerFactory ����ʵ����
		* abstract  Transformer newTransformer():����ִ�д� Source �� Result �ĸ��Ƶ��� Transformer��
		* abstract  void transform(Source xmlSource, Result outputTarget):�� XML Source ת��Ϊ Result��
		*/
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);//�� XML==>Source ת��Ϊ Result
		}
}
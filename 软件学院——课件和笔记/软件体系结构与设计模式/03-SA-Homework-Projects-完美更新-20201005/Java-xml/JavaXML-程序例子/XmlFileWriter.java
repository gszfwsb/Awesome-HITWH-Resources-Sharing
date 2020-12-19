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

    static File file = new File("/AgentInfo.xml");
    static StreamResult stream = new StreamResult(file);

    public static void main(String[] args) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement("Agent-info-Encryption");
            root.setAttribute("Encrypted-agent-name", "Encrypted-agent-code");

            Element name = document.createElement("Name");
            name.setTextContent("Wolf");
            Element code = document.createElement("Code");
            code.setTextContent("6666666666 ");

            Element name2 = document.createElement("Name");
            name2.setTextContent("Tiger");
            Element code2 = document.createElement("Code");
            code2.setTextContent("777777 ");

            root.appendChild(name);
            root.appendChild(code);
            root.appendChild(name2);
            root.appendChild(code2);

            document.appendChild(root);

            //创建转换工厂，然后将创建的document转换输出到文件中或控制台
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //transformer.transform(new DOMSource(document), new StreamResult(new File("AgentInfo.xml")));
            transformer.transform(new DOMSource(document), stream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
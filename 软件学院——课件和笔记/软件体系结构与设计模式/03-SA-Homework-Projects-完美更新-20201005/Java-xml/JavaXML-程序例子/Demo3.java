//package org.xml.example;

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

public class Demo3 {
    public static void main(String[] args) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement("CustomerFile");

            //root.setAttribute("cat", "it");
            Element lastName = document.createElement("LastName");
            Element firstName = document.createElement("FirstName");
            Element id = document.createElement("ID");
            Element nation = document.createElement("Nationality");

            lastName.setTextContent("Sun");
            firstName.setTextContent("Mike");
            id.setTextContent("999888666555");
            nation.setTextContent("China");


           // = document.createElement("LastName");
			//            Element firstName = document.createElement("FirstName");
			//            Element id = document.createElement("ID");
           // Element nation = document.createElement("Nationality");


            //lan1.setAttribute("id" , "1");
            //Element name1 = document.createElement("name");

            //name1.setTextContent("Mike Sun, 123456789abc; java");
            //Element ide1 = document.createElement("ide");
            //ide1.setTextContent("myeclipse");

            //lan1.appendChild(name1);
            //lan1.appendChild(ide1);
            root.appendChild(lastName);
            root.appendChild(firstName);
            root.appendChild(id);
            root.appendChild(nation);

            document.appendChild(root);

            //创建转换工厂，然后将创建的document转换输出到文件中或控制台
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //transformer.transform(new DOMSource(document), new StreamResult(new File("newXml.xml")));
            transformer.transform(new DOMSource(document), new StreamResult(new File("Customer.xml")));

            //transform info in document into string and output it to Consol
            //StringWriter stringWriter = new StringWriter();
            //transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            //System.out.println(stringWriter.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
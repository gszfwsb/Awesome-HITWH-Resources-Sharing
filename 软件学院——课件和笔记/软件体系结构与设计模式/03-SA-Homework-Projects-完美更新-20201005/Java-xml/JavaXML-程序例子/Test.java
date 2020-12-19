//package com.lhx.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class Test {
    public static void main(String[] args) {
        DocumentBuilderFactory  fct=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder bui=fct.newDocumentBuilder();
            Document doc=bui.newDocument();
            Element ps=doc.createElement("persons");
            Element p1=doc.createElement("person");
            Element p2=doc.createElement("person");
            Attr id1=doc.createAttribute("id");
            Attr id2=doc.createAttribute("id");
            id1.setNodeValue("1");
            id2.setNodeValue("2");
            Element name1=doc.createElement("name");
            Text na1=doc.createTextNode("Áú´ó¸ç");
            Element name2=doc.createElement("name");
            Text na2=doc.createTextNode("Áú´óÒ¯");
            Element sex1=doc.createElement("sex");
            Text se1=doc.createTextNode("Ë§¸ç");
            Element sex2=doc.createElement("sex");
            Text se2=doc.createTextNode("ÃÃ×Ó");

            doc.appendChild(ps);
                ps.appendChild(p1);
                    p1.appendChild(name1);
                        p1.setAttributeNode(id1);
                        name1.appendChild(na1);
                    p1.appendChild(sex1);
                        sex1.appendChild(se1);
            ps.appendChild(p2);
                    p2.appendChild(name2);
                        p2.setAttributeNode(id2);
                        name2.appendChild(na2);
                    p2.appendChild(sex2);
                        sex2.appendChild(se2);
            try {
                FileOutputStream fos=new FileOutputStream(new File("E:/longdada.xml"));

                try {
                    //((org.apache.crimson.tree.XmlDocument)doc).write(fos);
                     //((org.apache.crimson.tree.XmlDocument)doc).write(fos);
                      doc.write(fos);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    fos.flush();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }





        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
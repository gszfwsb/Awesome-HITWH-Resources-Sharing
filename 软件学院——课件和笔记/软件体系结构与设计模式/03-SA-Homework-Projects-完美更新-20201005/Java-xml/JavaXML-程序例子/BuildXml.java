//package com.buildxml;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.crimson.tree.XmlDocument;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * 生成XML文件
 * @author Administrator
 */
public class BuildXml {


	/**
	 * 将数据生成xml文件
	 * @param listmap 　XML数据参数
	 * @param whole_Element　父节点
	 * @param first_Element  一级节点
	 * @return  返回字符串(true or false)
	 */
	public static String buildWriteToXml(List<Map<String,Object>> listmap,String whole_Element,String first_Element){
		String flag="false";
		DocumentBuilderFactory  fct=DocumentBuilderFactory.newInstance();
		try {
			    DocumentBuilder bui=fct.newDocumentBuilder();
	            Document doc=bui.newDocument();
	            Element ps=doc.createElement(whole_Element);
	            doc.appendChild(ps);
	            for(int i=0;i<listmap.size();i++){
	            	Map<String,Object> map=listmap.get(i);
	            	Element p1=doc.createElement(first_Element);
	            	Attr id1=doc.createAttribute("id");
	            	id1.setNodeValue(UUID.randomUUID().toString());
	            	ps.appendChild(p1);
	            	p1.setAttributeNode(id1);
	            	for(String key:map.keySet()){
	            		Element name1=doc.createElement(key);
	            		Text na1=doc.createTextNode((String)map.get(key));

	            		p1.appendChild(name1);
	                    name1.appendChild(na1);
	            	}

	            }


                        FileOutputStream fos=new FileOutputStream(new File("E:/longdada.xml"));
                        //((org.apache.crimson.tree.XmlDocument)doc).write(fos);
                        XmlDocument.write(fos);
                        fos.flush();
                        fos.close();

                        flag="true";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}


	/**
	 * 读取xml文件中的数据
	 * @param mapParms 要读取的节点参数，其中父节点为"firstElement";
	 * @return
	 */
	public static List<Map<String,Object>> queryFromXml(Map<String,Object> mapParms){
		List<Map<String,Object>> resultMaplist=new ArrayList<Map<String,Object>>();//返回结果
		try {
			 //得到DOM解析器的工厂实例
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //从DOM工厂中获得DOM解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //把要解析的xml文档读入DOM解析器
            Document doc = dbBuilder.parse("E:/longdada.xml");
            //得到文档名称为per的元素的节点列表
            //==================
            String firstElement=(String)mapParms.get("firstElement");
            NodeList nList = doc.getElementsByTagName(firstElement);

              for(String key:mapParms.keySet()){
            		  if(!"firstElement".equals(key)){
            			//遍历该集合，显示结合中的元素及其子元素的名字
            			  Map<String,Object> m=new HashMap<String, Object>();
            			  m=(Map)mapParms.get(key);
            			  for(int j = 0; j< nList.getLength();j++){
            				  Map<String,Object> resultMap=new HashMap<String, Object>();
            				  Element node = (Element)nList.item(j);
            				  String id=node.getAttribute("id")==null?"":node.getAttribute("id");
            				  resultMap.put("id",id);
            			    for(String parmkey:m.keySet()){
            			      String keyValue=node.getElementsByTagName(parmkey).item(0).getFirstChild().getNodeValue();
                              System.out.println(parmkey+":"+ keyValue);
                              resultMap.put(parmkey, keyValue);
            			    }
            			    resultMaplist.add(resultMap);
            		    }
                	  }
              }
            //==================

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMaplist;
	}


	/**
	 * 向已存在的xml文件中插入元素
	 */
	public static String insertXml(Map<String,Object> map){
		String flage="false";

		Element persons = null;
		Element person = null;
        Element name = null;
        Element sex = null;


		try {
			//得到DOM解析器的工厂实例
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //从DOM工厂中获得DOM解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //把要解析的xml文档读入DOM解析器
            Document doc = dbBuilder.parse("E:/longdada.xml");
            //得到文档名称为Student的元素的节点列表
            NodeList nList = doc.getElementsByTagName("persons");
            persons = (Element)nList.item(0);
            //创建名称为person的元素
            person = doc.createElement("person");
            //设置元素Student的属性值为231
            person.setAttribute("id", "3");

            //创建名称为name的元素
            name = doc.createElement("name");
            //创建名称为 香香 的文本节点并作为子节点添加到name元素中
            name.appendChild(doc.createTextNode("香香"));

            //创建名称为Name的元素
            sex = doc.createElement("sex");
            //创建名称为 大美女 的文本节点并作为子节点添加到sex元素中
            sex.appendChild(doc.createTextNode("大美女"));
            //将name子元素添加到student中
            person.appendChild(name);
            person.appendChild(sex);


            //将person作为子元素添加到树的根节点persons
            persons.appendChild(person);
            //将内存中的文档通过文件流生成insertSchool.xml,XmlDocument位于crison.jar下
           // ((XmlDocument)doc).write(new FileOutputStream("E:/longdada.xml"));
            System.out.println("成功");

            flage="true";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flage;
	}


	public static void main(String[] args) {
		//===========测试将数据生成xml文件中
//		List<Map<String,Object>> maplist=new ArrayList<Map<String,Object>>();
//		for(int i=0;i<3;i++){
//			Map map=new HashMap<String,Object>();
//			map.put("name", "小花"+i);
//			map.put("age", "12"+i);
//			map.put("sex", "女"+i);
//			maplist.add(map);
//		}
//		buildWriteToXml(maplist, "students", "per");
		//====================测试将数据从xml读出
		List<Map<String,Object>> maplist=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> parms=new HashMap<String,Object>();
		map.put("firstElement","per");

		parms.put("name","name");
		parms.put("age","age");
		parms.put("sex","sex");
		map.put("parms",parms);

		queryFromXml(map);
		//insertXml();
	}

}

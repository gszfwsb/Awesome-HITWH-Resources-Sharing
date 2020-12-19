import java.io.FileNotFoundException;

  import java.io.FileOutputStream;
  import java.io.IOException;

  import org.jdom.Attribute;
  import org.jdom.Comment;
  import org.jdom.Document;
  import org.jdom.Element;
  import org.jdom.output.Format;
  import org.jdom.output.XMLOutputter;

 public class JDomOutput
 {
     public static void main(String[] args) throws IOException
     {
         //创建文档
         Document document = new Document();
         //创建根元素
         Element people = new Element("people");
         //把根元素加入到document中
         document.addContent(people);

         //创建注释
         Comment rootComment = new Comment("将数据从程序输出到XML中！");
         people.addContent(rootComment);

         //创建父元素
         Element person1 = new Element("person");
         //把元素加入到根元素中
         people.addContent(person1);
         //设置person1元素属性
         person1.setAttribute("id", "001");

         Attribute person1_gender = new Attribute("gender", "male");
         person1.setAttribute(person1_gender);

         Element person1_name = new Element("name");
         person1_name.setText("刘德华");
         person1.addContent(person1_name);

         Element person1_address = new Element("address");
         person1_address.setText("香港");
         person1.addContent(person1_address);


         Element person2 = new Element("person");
         people.addContent(person2);

         person2.setAttribute("id", "002").setAttribute("gender","male");//添加属性，可以一次添加多个属性

         Element person2_name = new Element("name");
         person2_name.setText("林志颖");
         person2.addContent(person2_name);

         Element person2_address = new Element("address");
         person2_address.setText("台湾");
         person2.addContent(person2_address);


         //设置xml输出格式
         Format format = Format.getPrettyFormat();
         format.setEncoding("utf-8");//设置编码
         format.setIndent("    ");//设置缩进


         //得到xml输出流
         XMLOutputter out = new XMLOutputter(format);
         //把数据输出到xml中
         out.output(document, new FileOutputStream("jdom.xml"));//或者FileWriter

     }
 }
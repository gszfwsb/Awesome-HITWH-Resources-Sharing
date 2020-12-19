import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlCreateAndRead {

    public static void main(String[] args) {

        //从数据库读出数据写入xml文件
        boolean flag = addCompany();

        //从xml文件读出展示到页面
//      Map<String, Object> company = findByUnitcode("736350492");

        System.out.println(flag);

    }

    /**
     * 插入对象到xml中
     * @param form
     */
    public static boolean addCompany() {
        /*
         * 1. （创建）得到Document
         * 2. （创建）得到root元素
         * 3. 要把数据对象转换成Element元素
         * 4. 把对象的属性插入到root元素中
         * 5. 回写document
         */

        //获取要写入xml文件的数据，可以从数据库获取，这里直接用一个Map对象作为例子
        Map<String,Object> comMap = new HashMap<String,Object>();
        comMap.put("UNIT_ID", "89adf979asd");
        comMap.put("UNIT_CODE", "xiaoCreate");
        comMap.put("UNIT_NAME", "上市公司");
        comMap.put("DISPLAYNAME", "北京海淀");

        try {
            // 1. 创建Docuembnt
            Document doc = DocumentHelper.createDocument();
            // 2. 创建根元素
            Element root = doc.addElement("city");
            root.addComment("文档的根city已经创建。");// 添加注释

        /*  //以下三行为追加数据到 xml 文件时 使用
            SAXReader reader = new SAXReader(); // 创建解析器
            Document doc = reader.read("E://temptest//xmlTest.xml"); // 得到Document
            Element root = doc.getRootElement(); //得到根元素
        */
            /*
             * 3. 完成添加元素，并返回添加的元素！
             * 向root中添加一个名为company的元素！并返回这个元素
             */
            Element comElement = root.addElement("company");
            // 设置comElement的属性！
            comElement.addAttribute("unitId", (String) comMap.get("UNIT_ID"));
            comElement.addAttribute("unitCode", (String) comMap.get("UNIT_CODE"));
            comElement.addAttribute("unitName", (String) comMap.get("UNIT_NAME"));
            comElement.addAttribute("cityArea", (String) comMap.get("DISPLAYNAME"));
            // 添加comElement的子元素节点！
            comElement.addElement("unitId").addText((String) comMap.get("UNIT_ID"));
            comElement.addElement("unitCode").addText((String) comMap.get("UNIT_CODE"));
            comElement.addElement("unitName").addText((String) comMap.get("UNIT_NAME"));
            comElement.addElement("cityArea").addText((String) comMap.get("DISPLAYNAME"));

            // 创建输出流
            Writer out = new PrintWriter("E:\\temptest\\xmlTest.xml", "utf-8");
            // 格式化
            OutputFormat format = new OutputFormat("\t", true);
            format.setTrimText(true);//去掉原来的空白(\t和换行和空格)！

            XMLWriter writer = new XMLWriter(out, format);
            // 把document对象写到out流中。
            writer.write(doc);

            out.close();
            writer.close();

            return true;
        } catch(Exception e) {
            // 把编译异常转换成运行异常！
            throw new RuntimeException(e);
        }
    }
}

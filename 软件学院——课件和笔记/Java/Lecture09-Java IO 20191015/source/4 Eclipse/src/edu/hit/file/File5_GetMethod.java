package edu.hit.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class File5_GetMethod {

	/**
	 * * A:获取功能
		* public String getAbsolutePath()：获取绝对路径
		* public String getPath():获取路径
		* public String getName():获取名称
		* public long length():获取长度。字节数
		* public long lastModified():获取最后一次的修改时间，毫秒值
		* public String[] list():获取指定目录下的所有文件或者文件夹的名称数组
		* public File[] listFiles():获取指定目录下的所有文件或者文件夹的File数组 
	 */
	public static void main(String[] args) {
		//demo1();
		demo2();
 	}

	public static void demo2() {
		File dir = new File("d:\\source");
		String[] arr = dir.list();						//仅为了获取文件名
		
		for (String string : arr) {
			System.out.println(string);
		}
		
		File[] subFiles = dir.listFiles();
		
		for (File file : subFiles) {					//获取文件对象
			System.out.println(file);
		}
	}

	public static void demo1() {
		File file1 = new File("ccc.txt");
		File file2 = new File("d:\\source\\001.mp3");
		//System.out.println(file1.getAbsolutePath());			//获取绝对路径
		//System.out.println(file2.getAbsolutePath());
		
		//System.out.println(file1.getPath());					//获取构造方法中传入路径
		//System.out.println(file2.getPath());
		
//		System.out.println(file1.getName());					//获取文件或者文件的名字
//		System.out.println(file2.getName());
//		
//		System.out.println(file1.length());
		
		Date d = new Date(file1.lastModified());				//文件的最后修改时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		System.out.println(sdf.format(d));
	}

}

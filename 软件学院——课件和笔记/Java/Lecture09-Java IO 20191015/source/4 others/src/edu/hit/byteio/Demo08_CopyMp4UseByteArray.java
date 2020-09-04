package edu.hit.byteio;
import java.io.*;

/**
 * 利用数组拷贝的方式拷贝mp4
 * 采用数组拷贝，一次读入数据源所有的字节
 * 再一次写出所有字节，速度明显快，而且高效
 * 但此种读写文件方式不提倡，因为极易内存溢出
 * 可以发现本程序内存溢出
 * JVM是在OS上跑的，本来分配给JVM的内存就不是很多
 */
public class Demo08_CopyMp4UseByteArray {
   public static void main(String[] args) throws IOException {
      FileInputStream fis = new FileInputStream("Am.mp4"); 
      FileOutputStream fos = new FileOutputStream("AmCopy.mp4");  
      byte[] arr = new byte[fis.available()]; 	  
      fis.read(arr); // 将文件上的字节读取到内存中
      fos.write(arr); // 将字节数组中的字节数据写到文件上
      fis.close();
      fos.close();
   }
}

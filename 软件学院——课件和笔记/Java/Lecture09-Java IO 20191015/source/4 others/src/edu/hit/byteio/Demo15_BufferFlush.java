package edu.hit.byteio;
import java.io.*;

/**
 * close方法与flush方法 * 
 * 如果忘记关闭流，会发生拷贝的文件与源文件大小不一致的问题。原因是最后一次缓冲并没有写出。
 * close方法：
 * 具备刷新的功能,在关闭流之前,就会先刷新一次缓冲区,
 * 将缓冲区的字节全都刷新到文件上,再关闭,close方法刷完之后就能写出到源文件
 * 关闭流之后就不能再读写了。
 * flush方法：
 * 具备刷新的功能,刷完之后还可以继续写，例如QQ信息发送，每次回车时，都是flush出去
 * 但是流不关闭
 */

public class Demo15_BufferFlush {
	public static void main(String[] args) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("1.mp3"));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("1copy.mp3"));
		int b;
		while ((b = bis.read()) != -1) {
			bos.write(b);
		}
		//bos.flush();
//		bis.close(); 
//		bos.close();
	}
}

package edu.hit.byteio;

import java.io.*;

/**
 * IO流关键代码6行，每行都可能出现异常 IO流需要操作底层的，因此不能使用try catch，因为使用try catch实际上是把问题隐藏掉了
 * 没有向上去暴露，我们希望把出现的问题抛出去。 但是如果仅仅是在main方法中抛出IOException是不负责任的 ，而且流有可能关不掉
 * 即：在关流之前任何异常都直接退出，导致流不可关
 */
public class Demo18_ExceInFileStream {
	public static void main(String[] args) {
		FileInputStream fis = new FileInputStream("h.txt"); // 文件不存在
		FileOutputStream fos = new FileOutputStream("i.txt"); // 路径可能不存在
		int b;
		while ((b = fis.read()) != -1) { // 不可读
			fos.write(b); // 不可写
		}
		fis.close(); // 关不掉
		fos.close(); // 关不掉
	}
}

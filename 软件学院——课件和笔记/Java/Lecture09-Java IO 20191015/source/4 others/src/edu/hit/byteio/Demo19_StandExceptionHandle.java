package edu.hit.byteio;

import java.io.*;

/**
 * IO流关键代码6行，每行都可能出现异常 IO流需要操作底层的，因此不能使用try catch，因为使用try catch实际上是把问题隐藏掉了
 * 没有向上去暴露，我们希望把出现的问题抛出去。 但是如果仅仅是在main方法中抛出IOException是不负责任的 ，而且流有可能关不掉
 * 即：在关流之前任何异常都直接退出，导致流不可关 所以，把可能出现异常的代码用try判断，之后用finally关流
 * 不写catch，也就是不用catch隐藏问题
 */
public class Demo19_StandExceptionHandle {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("h.txt");
			fos = new FileOutputStream("i.txt");
			int b;
			while ((b = fis.read()) != -1) {
				fos.write(b);
			}
		} finally {
			try {
				if (fis != null) // fis有可能没有创建成功
					fis.close(); // 有可能关闭不上
			} finally { // try fianlly的嵌套目的是能关一个尽量关一个
				if (fos != null)
					fos.close();
			}
		}
	}
}

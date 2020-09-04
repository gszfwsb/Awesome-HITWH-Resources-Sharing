package io;
import java.io.*;

/**
 * close������flush���� * 
 * ������ǹر������ᷢ���������ļ���Դ�ļ���С��һ�µ����⡣ԭ�������һ�λ��岢û��д����
 * close������
 * �߱�ˢ�µĹ���,�ڹر���֮ǰ,�ͻ���ˢ��һ�λ�����,
 * �����������ֽ�ȫ��ˢ�µ��ļ���,�ٹر�,close����ˢ��֮�����д����Դ�ļ�
 * �ر���֮��Ͳ����ٶ�д�ˡ�
 * flush������
 * �߱�ˢ�µĹ���,ˢ��֮�󻹿��Լ���д������QQ��Ϣ���ͣ�ÿ�λس�ʱ������flush��ȥ
 * ���������ر�
 */

public class Demo15_BufferFlush {
	public static void main(String[] args) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("001.mp3"));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copy.mp3"));
		int b;
		while ((b = bis.read()) != -1) {
			bos.write(b);
		}
		//bos.flush();
		bis.close(); 
		bos.close();
		
	}
}

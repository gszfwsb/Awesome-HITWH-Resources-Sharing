package edu.hit.otherio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

public class Demo28_SequenceInputStream {

	/**
	 * @param args
	 * ��������������
	 * SequenceInputStream(InputStream s1, InputStream s2)
	 * ���϶��������
	 * SequenceInputStream(Enumeration<? extends InputStream> e)
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//demo1();
		//demo2();
		demo3();
	}

	public static void demo3() throws FileNotFoundException, IOException {
		FileInputStream fis1 = new FileInputStream("D:/subject/java/Lecture09-Java IO 20191015/source/4 Eclipse/o.txt");
		FileInputStream fis2 = new FileInputStream("D:/subject/java/Lecture09-Java IO 20191015/source/4 Eclipse/p.txt");
		FileInputStream fis3 = new FileInputStream("D:/subject/java/Lecture09-Java IO 20191015/source/4 Eclipse/.txt");
		
		Vector<FileInputStream> v = new Vector<>();					//�������϶���
		v.add(fis1);												//��������洢����
		v.add(fis2);
		v.add(fis3);
		
		Enumeration<FileInputStream> en = v.elements();
		SequenceInputStream sis = new SequenceInputStream(en);		//��ö���е����������ϳ�һ��
		FileOutputStream fos = new FileOutputStream("r.txt");
		
		int b;
		while((b = sis.read()) != -1) {
			fos.write(b);
		}
		
		sis.close();
		fos.close();
	}

	//���԰Ѷ�����ϲ���һ�������д���
	public static void demo2() throws FileNotFoundException, IOException {
		FileInputStream fis1 = new  FileInputStream("D:/subject/java/Lecture09-Java IO 20191015/source/4 Eclipse/a.txt");
		FileInputStream fis2 = new FileInputStream("D:/subject/java/Lecture09-Java IO 20191015/source/4 Eclipse/b.txt");
		SequenceInputStream sis = new SequenceInputStream(fis1, fis2);
		FileOutputStream fos = new FileOutputStream("D:/subject/java/Lecture09-Java IO 20191015/source/4 Eclipse/c.txt");
		
		int b;
		while((b = sis.read()) != -1) {
			fos.write(b);
		}
		
		sis.close(); //sis�ڹرյ�ʱ��,�Ὣ���췽���д����������Ҳ���ر�
		fos.close();
	}
    //�������SequenceInputStream������Ҫÿ����������д
	public static void demo1() throws FileNotFoundException, IOException {
		FileInputStream fis1 = new FileInputStream("D:/subject/java/Lecture09-Java IO 20191015/source/4 Eclipse/a.txt");		//�����ֽ�����������a.txt
		FileOutputStream fos = new FileOutputStream("D:/subject/java/Lecture09-Java IO 20191015/source/4 Eclipse/c.txt");		//�����ֽ����������c.txt		
		int b1;
		while((b1 = fis1.read()) != -1) {							//���ϵ���a.txt�϶�ȡ�ֽ�
			fos.write(b1);											//���������ֽ�д��c.txt��
		}
		fis1.close();											//�ر��ֽ�������		
		FileInputStream fis2 = new FileInputStream("D:/subject/java/Lecture09-Java IO 20191015/source/4 Eclipse/b.txt");
		int b2;
		while((b2 = fis2.read()) != -1) {
			fos.write(b2);
		}		
		fis2.close();
		fos.close();
	}
}

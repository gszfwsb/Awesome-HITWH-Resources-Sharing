import java.io.File;

public class File3_RenameAndDelete {

	/**
	 * * A:��������ɾ������
			* public boolean renameTo(File dest):���ļ�������Ϊָ�����ļ�·��
			* public boolean delete():ɾ���ļ������ļ���
		* B:������ע������
			* ���·������ͬ�����Ǹ�����
			* ���·������ͬ�����Ǹ��������С�
		* C:ɾ��ע�����
			* Java�е�ɾ�����߻���վ��
			* Ҫɾ��һ���ļ��У���ע����ļ����ڲ��ܰ����ļ������ļ���
	 */
	public static void main(String[] args) {
		demo1();
//		demo2();
	}

	public static void demo2() {
		File file1 = new File("yyy.txt");
		System.out.println(file1.delete());
		
		File file2 = new File("aaa");
		System.out.println(file2.delete());
		
		File file3 = new File("ccc");	//���ɾ��һ���ļ���,��ô�ļ��б����ǿյ�
		System.out.println(file3.delete());
	}

	public static void demo1() {
		File file1 = new File("source.txt");
		File file2 = new File("dest.txt");
		//File file2 = new File("d:\\dest.txt");
		System.out.println(file1.renameTo(file2));
	}

}

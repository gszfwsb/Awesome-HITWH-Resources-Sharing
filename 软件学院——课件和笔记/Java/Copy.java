import java.io.FileInputStream;
import java.io.FileOutputStream;
/*
public class Copy {
    public static void copy(String src, String dst) {
        try (FileInputStream fis = new FileInputStream(src); FileOutputStream fos = new FileOutputStream(dst)) {
            byte[] buf = new byte[8 * 1024];
            int k = -1;
            while ((k = fis.read(buf)) != -1) {
                fos.write(buf);
            }
        } catch (Exception e) {
            System.err.println("Exception happens!");
        }
    }

    public static void main(String[] args) {
        Copy.copy("C:\\Users\\DELL\\Desktop\\errata.pdf", "C:\\Users\\DELL\\Desktop\\Copy.pdf");
    }
}*/
//java 1.6
	    /*
	          * 注意事项：
	     * fis fos首先设置成null，防止局部变量无法关闭
	          * 第一个try-catch-finally体系解决：创建异常，读写异常，资源释放三个问题
	     * finally块中释放资源时，分开释放多个流资源，防止前面的异常导致后面也无法关闭
	          * 即能关一个关一个
	          * 关之前要判断是否非null
	          * 关闭的时候 也有可能引起异常，因此也要用try-catch-finally体系
	     * try-catch解决第一个关闭异常
	     * finally关闭第二个流，也要嵌套一个try-catch防止第二个的关闭异常
	     */
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("abc.txt");    //创建流异常
			fos = new FileOutputStream("coy.txt");
			
			int b;
			while ( (b= fis.read()) != -1 ) {    //读写异常
				fos.write(b);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(fis != null) {
					fis.close();    //关闭异常
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}


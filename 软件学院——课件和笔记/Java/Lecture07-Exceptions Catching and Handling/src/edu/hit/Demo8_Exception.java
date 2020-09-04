package edu.hit;

public class Demo8_Exception {
	public static void main(String[] args) {
		System.out.println(method());
	}

	public static int method() {
		int x = 10;
		try {
			x = 20;
			System.out.println(1 / 0);
			System.out.println("try");
			return x;
		} catch (Exception e) {
			x = 30;
			System.out.println("catch");
			return x;
		} finally {
			x = 40;
			System.out.println("finally");
			System.out.println(x);

			//千万不要在finally里面写返回语句,因为finally的作用是为了释放资源,是肯定会执行的
			//如果在这里面写返回语句,那么try和catch的结果都会被改变,所以这么写就是犯罪
		}
	}

}
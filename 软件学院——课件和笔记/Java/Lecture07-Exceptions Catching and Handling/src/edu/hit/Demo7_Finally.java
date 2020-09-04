package edu.hit;

public class Demo7_Finally {
	public static void main(String[] args) {
		try {
			System.out.println(10 / 0);
		} catch (Exception e) {
			System.out.println("除数为零");
			System.exit(0); // 特殊情况：退出jvm虚拟机			
		} finally {
			System.out.println("无论是否有异常都执行");
		}
	}
}

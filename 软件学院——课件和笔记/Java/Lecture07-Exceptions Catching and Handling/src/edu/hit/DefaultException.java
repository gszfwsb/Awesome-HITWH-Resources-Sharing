package edu.hit;

class MyException extends Exception { // 自定义异常类
	public MyException(String msg) {
		super(msg); // 调用超类中构造方法，传递错误信息
	}
}

public class DefaultException {
	public static void main(String args[]) {
		try {
			throw new MyException("自定义异常。");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

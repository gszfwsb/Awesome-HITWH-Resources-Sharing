package edu.hit;
class MyMath1 {
    public int div(int i, int j) throws Exception {
	return i / j;
    }
}
public class ExpDemo08 {
   public static void main(String args[]) throws Exception {
	System.out.println(new MyMath1().div(10, 0));
   }
}

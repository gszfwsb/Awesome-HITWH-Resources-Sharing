package edu.hit;
class MyMath {
   public int div(int i, int j) throws Exception { //евЁЖ
      return i / j;
   }
}
public class ExpDemo07 {
   public static void main(String args[]) {
      try {
          System.out.println(new MyMath().div(10, 0));
      } catch (Exception e) {
	   e.printStackTrace();
      }
  }
}

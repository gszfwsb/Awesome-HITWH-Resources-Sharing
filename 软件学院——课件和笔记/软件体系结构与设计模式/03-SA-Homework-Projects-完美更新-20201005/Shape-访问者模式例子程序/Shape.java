

public abstract class Shape {
   public abstract double getPerimeter();
   public abstract double getArea();
   public abstract void accept(Visitor v);
   public abstract void accept(Visitor2 v);
   public String describe(){
       return "This is an shape ";
   }
}

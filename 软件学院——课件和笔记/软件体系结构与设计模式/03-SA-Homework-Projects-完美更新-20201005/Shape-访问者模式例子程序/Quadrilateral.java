abstract class Quadrilateral extends Shape{

   //private double width;
   //private double height;

   //public Quadrilateral(double w,double h){
   //    width = w;
   //    height = h;
   //}
   public String describe(){
          return "This is a quadrilateral ";
   }
   public abstract void accept(Visitor v);
   public abstract void accept(Visitor2 v);

   public abstract double getPerimeter();
   //{
   //    return + height)*2;
   //}
   public abstract double getArea();
   //{
   //    return width*height;
   //}
}
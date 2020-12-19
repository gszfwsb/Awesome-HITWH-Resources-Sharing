
class Equilateral extends IsoscelesTriangle{  //等边三角形

   private double side;

   public Equilateral(double side){
       super (side, side);
       this.side = side;
   }
   public String describe(){
       return "This is a equilateral, with ";
   }
   public void accept(Visitor v){
       v.visitEquilateral(this);
   }
   public void accept(Visitor2 v){
       v.visit(this);
   }
   public double getPerimeter(){
       return (3*side);
   }
   public double gatArea(){
	   double h = Math.sqrt( side*side - (side/2)*(side/2));
	   double area = (side/2)*h;
       return area;
   }
   //特有接口
   public double getSide(){
       return side;
   }
}
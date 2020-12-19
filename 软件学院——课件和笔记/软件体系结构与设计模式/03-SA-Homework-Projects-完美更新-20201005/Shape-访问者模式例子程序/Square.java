
class Square extends Rectangle{

   private double side;

   public Square(double side){
       super (side, side);
       this.side = side;
   }
   public String describe(){
   	   return "This is a square, with ";
   }
   public void accept(Visitor v){
       v.visitSquare(this);
   }
   public void accept(Visitor2 v){
       v.visit(this);
   }
   public double getPerimeter(){
       return 4*side;
   }
   public double getArea(){
       return side*side;
   }
   //特有接口
   public double getSide(){
       return side;
   }
}
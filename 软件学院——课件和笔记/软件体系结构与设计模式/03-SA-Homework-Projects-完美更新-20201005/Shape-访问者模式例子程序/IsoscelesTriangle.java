
class IsoscelesTriangle extends Triangle{  //等腰三角形

   private double side;
   private double sideBottom;

   public IsoscelesTriangle(double side,double sideBottom){
	   super (side, side, sideBottom);
       this.side = side;
       this.sideBottom = sideBottom;

   }

   public String describe(){
         return "This is a isoscelesTriangle, with ";
   }

   public void accept(Visitor v){
       v.visitIsoscelesTriangle(this);
   }
   public void accept(Visitor2 v){
       v.visit(this);
   }


   public double getPerimeter(){
       return (2*side + sideBottom);
   }
   public double gatArea(){

	   double h = Math.sqrt( side*side - (sideBottom/2)*(sideBottom/2));
	   double area = (sideBottom/2)*h;
       return area;
   }
   //特有接口
   public double getSide(){
       return side;
   }
   public double getSideBottom(){
       return sideBottom;
   }
}
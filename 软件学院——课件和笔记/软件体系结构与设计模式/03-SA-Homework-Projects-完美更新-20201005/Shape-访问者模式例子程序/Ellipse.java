
class Ellipse extends Shape{  //椭圆

   private double centerX;
   private double centerY;
   private double xRadius;
   private double yRadius;

   public Ellipse(double centerX, double centerY, double xRadius, double yRadius){

       this.centerX = centerX;
       this.centerY = centerY;
       this.xRadius = xRadius;
       this.yRadius = yRadius;
   }
   public String describe(){
       return "This is an ellipse, with ";
   }

   public void accept(Visitor v){
   	   v.visitEllipse(this);
   }
   public void accept(Visitor2 v){
       v.visit(this);
   }
   public double getPerimeter(){
	   double p = Math.PI*Math.sqrt(2*xRadius*xRadius*yRadius*yRadius);
       return p;
   }
   public double getArea(){

       double area = Math.PI*(xRadius*yRadius);
       return area;
   }

    //特有接口
      public double getCenterX(){
          return centerX;
      }
      public double getCenterY(){
          return centerY;
      }
      public double getXRadius(){
          return xRadius;
      }
      public double getYRadius(){
	      return yRadius;
      }
}
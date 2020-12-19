
class Circle extends Ellipse{  //椭圆

   private double centerX;
   private double centerY;
   private double radius;

   public Circle(double centerX, double centerY, double radius){

	   super(centerX, centerY, radius, radius);
       this.centerX = centerX;
       this.centerY = centerY;
       this.radius = radius;
   }

   public String describe(){
	   return "This is a circle, with ";
   }
   public void accept(Visitor v){
	   v.visitCircle(this);
   }
   public void accept(Visitor2 v){
       v.visit(this);
   }
   public double getPerimeter(){
       double p = 2*Math.PI*radius;
       return p;
   }
   public double getArea(){

       double area = Math.PI*(radius*radius);
       return area;
   }
   //特有接口
   public double getCenterX(){
       return centerX;
   }
   public double getCenterY(){
       return centerY;
   }
   public double getRadius(){
       return radius;
   }
}
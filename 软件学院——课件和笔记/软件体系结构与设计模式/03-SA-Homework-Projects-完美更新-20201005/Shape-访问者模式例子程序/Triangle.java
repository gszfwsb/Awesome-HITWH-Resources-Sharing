
class Triangle extends Shape{

   private double sideA;
   private double sideB;
   private double sideC;

   public Triangle(double sideA,double sideB, double sideC){
       this.sideA = sideA;
       this.sideB = sideB;
       this.sideC = sideC;
   }
   public void accept(Visitor v){
       v.visitTriangle(this);
   }
   public void accept(Visitor2 v){
       v.visit(this);
   }

   public String describe(){
	   return "This is a triangle, with ";
   }
   public boolean isATriangle(){
	   boolean flag = false;
	   if((sideB + sideC > sideA) && (sideA + sideC > sideB) && (sideA + sideB > sideC)){
		   flag = true;
	   }
	  return flag;
   }
   public double getPerimeter(){
       return (sideA + sideB + sideC);
   }
   public double getArea(){

	   double p =0.5*(sideA + sideB + sideC);
	   double area = Math.sqrt( p*(p-sideA)*(p-sideB)*(p-sideC));
       return area;
   }

    //特有接口
   public double getSideA(){
       return sideA;
   }
   public double getSideB(){
       return sideB;
   }
   public double getSideC(){
       return sideC;
   }
}

class Trapezoid extends Quadrilateral{  //梯形

   private double x1;
   private double y1;
   private double x2;
   private double y2;
   private double x3;
   private double y3;
   private double x4;
   private double y4;

   private double d1;
   private double d2;
   private double d3;
   private double d4;


   //假设梯形的一对平行边平行于X轴
   public Trapezoid(double x1, double y1, double x2, double y2,
                    double x3, double y3, double x4, double y4){

       this.x1=x1;
	   this.y1=y1;
	   this.x2=x2;
	   this.y2=y2;
	   this.x3=x3;
	   this.y3=y3;
	   this.x4=x4;
	   this.y4=y4;
       boolean isQuadrilateral = false;
       boolean isTrapezoid = false;

       d1 = Math.sqrt( (x2-x1)*(x2-x1) +  (y2-y1)*(y2-y1) );
       d2 = Math.sqrt( (x3-x2)*(x3-x2) +  (y3-y2)*(y3-y2) );
       d3 = Math.sqrt( (x4-x3)*(x4-x3) +  (y4-y3)*(y4-y3) );
       d4 = Math.sqrt( (x1-x4)*(x1-x4) +  (y1-y4)*(y1-y4) );

       System.out.println("d1 = " + d1);
       System.out.println("d2 = " + d2);
       System.out.println("d3 = " + d3);
       System.out.println("d4 = " + d4);


       if( (d1 < (d2+d3+d4)) && (d2 < (d1+d3+d4)) && (d3 < (d1+d2+d4)) && (d4 < (d1+d2+d3))){
	   	   isQuadrilateral = true;
	   	   System.out.println("isQuadrilateral = " +isQuadrilateral);
	   }

       if( (y1==y2) && (y3==y4) && (y1 != y3) ){
		   isTrapezoid = true;
		   System.out.println("isTrapezoid = " +isTrapezoid);
	   }
	   else{
		   System.out.println("参数错误，假设一对平行边平行于X轴");
	   }

       if((isQuadrilateral == false) || (isTrapezoid == false)){

		  System.out.println("不是四边形，或者不是梯形");
	   }
   }

   public void accept(Visitor v){
        v.visitTrapezoid(this);
   }
   public void accept(Visitor2 v){
        v.visit(this);
   }
   public String describe(){
      	return "This is a Trapezoid, with ";
   }

   public double getPerimeter(){
       return d1+d2+d3+d4;
   }
   public double getArea(){
       double area = (d1 + d3)*(y1-y3)/2;
       return area;
   }

   public double getSide1(){
	   System.out.println("见鬼了 " + d1);
        return d1;
   }
   public double getSide2(){
        return d2;
   }
   public double getSide3(){
        return d3;
   }
   public double getSide4(){
        return d4;
   }
}
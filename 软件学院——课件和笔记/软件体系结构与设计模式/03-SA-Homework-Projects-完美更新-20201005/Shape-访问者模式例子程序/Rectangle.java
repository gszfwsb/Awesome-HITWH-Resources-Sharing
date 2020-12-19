class Rectangle extends Parallelogram{

   private double width;
   private double height;

   public Rectangle(double width,double height){
	   super(width, height, height );
       this.width = width;
       this.height = height;
   }
   public String describe(){
   	   return "This is a rectangle, with ";
   }
   public void accept(Visitor v){
       v.visitRectangle(this);
   }
   public void accept(Visitor2 v){
       v.visit(this);
   }

   public double getPerimeter(){
       return (width + height)*2;
   }
   public double getArea(){
       return width*height;
   }

    //特有接口
   public double getWidth(){
       return width;
   }
   public double getHeight(){
       return height;
   }
}
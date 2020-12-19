
public class TestShape {
     public static void main(String[] args) {

		 Visitor v = new Visitor();
		 Shape triangle = new Triangle(10, 20, 30);
		 Shape isosceles = new IsoscelesTriangle(60, 40);
		 Shape equilat = new Equilateral(80);
		 Shape right = new RightTriangle(120,80);

		 triangle.accept(v);
		 isosceles.accept(v);
		 equilat.accept(v);
		 right.accept(v);

         Shape para = new Parallelogram(100, 80, 50);
         Shape rect = new Rectangle(160,120);
         Shape sq = new Square(150);

         para.accept(v);
		 rect.accept(v);
		 sq.accept(v);

		 Shape ellipse = new Ellipse(60, 80, 100, 50);
         Shape circle = new Circle(60, 80, 100);

         ellipse.accept(v);
		 circle.accept(v);
       }
}
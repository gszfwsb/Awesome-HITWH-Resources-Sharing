
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;


public class ImageBlurring implements BufferedImgOperation{

   // ConvolveOp is Java API clas for image processing
   private ConvolveOp convolveObj;

   public ImageBlurring(){
      float weight = 1.0f / 9.0f;
	  float[] elements = new float[9];

	  for (int i = 0; i < 9; i++)
         elements[i] = weight;

      Kernel kernel = new Kernel(3, 3, elements);

      // Create Java API object for image procesing
      convolveObj = new ConvolveOp(kernel);
   }

   public BufferedImage filter(BufferedImage src, BufferedImage dst){

	  int width = src.getWidth();
	  int height = src.getHeight();
	  int type = src.getType();

	  if ( dst == null )
	     dst = new BufferedImage( width, height, type );

      // Call the method filter in ConvolveOp (Java API class)
      // to do image smoothing
      BufferedImage scaledImg = convolveObj.filter(src, dst);
      return scaledImg;
   }

}
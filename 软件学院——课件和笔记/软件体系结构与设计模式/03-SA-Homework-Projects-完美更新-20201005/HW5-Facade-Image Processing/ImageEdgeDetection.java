
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.BufferedImage;

public class ImageEdgeDetection{

   // ConvolveOp is Java API clas for image processing
   private ConvolveOp convolveObj;

   public ImageEdgeDetection(){
	  float[] elements = { 0.0f, -1.0f, 0.0f, -1.0f, 4.f, -1.0f, 0.0f, -1.0f,0.0f };

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
      // to do image edge detection
      BufferedImage scaledImg = convolveObj.filter(src, dst);
      return scaledImg;
   }
}
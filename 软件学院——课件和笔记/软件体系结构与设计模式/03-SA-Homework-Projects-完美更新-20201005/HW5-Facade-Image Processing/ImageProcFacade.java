import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.util.*;
import java.io.File;
import java.io.IOException;


public class ImageProcFacade{
   private JLabel[] imgLabel=new JLabel[6];
   private ArrayList filterNms;
   private JTextArea filterInfoTxt;
   private static final String IMAGES = "Images/";
   private static final String PROCIMAGES = "ProcessedImages/";

   /*===============================================*/
   /* Parameters: ftrStr: a list of filter names.   */
   /* imLbl: a list of labels used for adding image */
   /* on each one. infoTxt is for displaying filter */
   /* information                                   */
   /*===============================================*/
   public ImageProcFacade(ArrayList ftrStr, JLabel[] imLbl, JTextArea infoTxt) throws IOException{
	  imgLabel = imLbl;
	  filterNms = ftrStr;
	  filterInfoTxt = infoTxt;
   }


   // Process an image chosenImg, save it to a file in folder PROCIMAGES
   // and then display the original image and the processed image
   public void processImage(String chosenImg){

     String originalImg = IMAGES + chosenImg; // a file chosen from folder IMAGES

     int len = filterNms.size();
     if((len > 3) || (len == 0)){
	    filterInfoTxt.append("You need to select at least 1 and no more than 3 filters");
	 	return;
	 }

	 // Processed image names
	 String[] processedImgNames = new String[len];
   	 for(int m=0;m<len;m++){
   	    processedImgNames[m] = PROCIMAGES+"Procesed_"+ (m+1) + chosenImg;
	 }

   	 // Create image of BufferedImage type
   	 BufferedImage[] img = new BufferedImage[len+1];
   	 ImageIcon[] imgIcon;
   	 imgIcon = new ImageIcon[len+1];

   	 try{
   	    // content of img[0] is read from originalImg file
   	    img[0] = ImageIO.read(new File(originalImg));
   	 }
   	 catch (IOException e1){
   	    e1.printStackTrace();
   	 }

   	 // Create image icon in order to display the image
   	 // on a GUI
   	 imgIcon[0] = produceImgIcon(img[0]);
   	 displayImage(imgLabel[0],imgIcon[0]);

   	 String s = null;
   	 Iterator iterator = filterNms.iterator();

   	 for(int i=0; i<len; i++){
   	    if(iterator.hasNext()){
   	       s = (String) iterator.next();
   	       BufferedImgOperation ftrObj = createObj(s);

   	       // Call method filter(), which filter img[0], to do smoothing,
   	       // sharpening, etc.,and get resultant img[i+1]
   	       img[i+1] = ftrObj.filter(img[0], img[i+1]);

   	       try{
   	          // Write image file img[i+1] to a new file named processedImgNames[i]
   	          ImageIO.write(img[i+1], "JPEG", new File(processedImgNames[i]));
   	 	   }
   	 	   catch (IOException e){
   	 	      e.printStackTrace();
   	       }
   	       //Produce an image icon imgIcon[i+1] with image img[i+1],
   	       //for displaying the img[i+1] onto the icon imgIcon[i+1]
   	       imgIcon[i+1] = produceImgIcon(img[i+1]);
   	       displayImage(imgLabel[i+1],imgIcon[i+1]);

   	 	}
        String ftrDescription = getFilterDescription(s);
   	    showFilterInfo(filterInfoTxt, ftrDescription);
   	 }//end for
  }

 public BufferedImgOperation createObj(String node){
   	  BufferedImgOperation filterObj = null;

   	  if ( node.compareTo("BlurFilter")==0 ) {
   		  filterObj = new ImageBlurring();
      }
      else if ( node.compareTo("SharpenFilter")==0 ) {
 	  	  filterObj = new ImageSharpening();
   	  }
   	  // Students need to add code here


   	  return  filterObj;
    }


  /*==========================================*/
  /* Get the description of a selected filter */
  /*==========================================*/
  public String getFilterDescription(String filtor ){
	 String filterDesc = null;

      if ( filtor.compareTo("BlurFilter")==0 ) {
  	     filterDesc = "BlurFilter: do Blur filtering...\n";
      }
  	  else if ( filtor.compareTo("SharpenFilter")==0 ) {
	  	 filterDesc = "SharpenFilter: do Sharpening filtering...\n";
  	  }
  	  // Students need to add code here
  	  //else if ( filtor.compareTo("EdgeDetectionFilter")==0 ) {
	  //  	filterDesc = "EdgeDetectionFilter: do EdgeDetectionFilter Filtering...\n";
  	  //}

	  return filterDesc;
  }
  /*============================================*/
  /* Display the selected filter's description  */
  /* to the upper right coner of the screen     */
  /*============================================*/
    public void showFilterInfo(JTextArea filterInfoTxt, String filterInfo ){
       filterInfoTxt.append(filterInfo);
  }

  /*=======================================*/
  /* Create an image icon from a dir used  */
  /* to show an chosen image               */
  /*=======================================*/
  public ImageIcon produceImgIcon(Image img){
     ImageIcon imgIcon = new ImageIcon(img);
     return imgIcon;
  }

  /*================================*/
  /* Display an image to the Gui    */
  /*================================*/
  public void displayImage(JLabel imgLabel,ImageIcon imgIcon){
     imgLabel.setIcon(imgIcon);
     imgLabel.validate();
  }
}

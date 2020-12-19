import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
//import java.io.StringWriter;


/*======================================*/
/* This is the mediator class in the    */
/* mediator design pattern              */
/*======================================*/

public class BusinessMediator{

   private ArrayList<ParticipantGUI> companyList = new ArrayList<ParticipantGUI>();

   public void register(ParticipantGUI company){
       companyList.add(company);
   }

   public void addPossibleCus(ParticipantGUI p , String text){

	  Iterator e = companyList.iterator();
	  while (e.hasNext()){
	 	  ParticipantGUI c = (ParticipantGUI)e.next();
	 	  if(c != p){
	 		  c.addPossibleCustomer(text);
		  }
	 }
  }

  //update all other User interfaces
  public void updateAllGuis(ParticipantGUI p, String text){

  	 Iterator e = companyList.iterator();

  	 while (e.hasNext()){
          ParticipantGUI c = (ParticipantGUI)e.next();

          if(c != p){
		  	  c.displayInfo(text);
		  }
	  }
  }

  public void writePossibleCusToXmlFile(ParticipantGUI p , String[] cusData){

  	  Iterator e = companyList.iterator();
  	  while (e.hasNext()){
  	 	  ParticipantGUI c = (ParticipantGUI)e.next();
  	 	  if(c != p){
			  try{
  	 			  c.writeCandidateCusToXmlFile(cusData);
			  }
			  catch(Exception ee){
				  ee. printStackTrace();
		      }
  		  }
  	 }
  }

}
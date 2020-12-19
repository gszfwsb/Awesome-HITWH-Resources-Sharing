package hw9;
import Mediator.BusinessMediator;
import airlineGUI.AirlineGUI;
import hotelGUI.HotelGUI;
import participantGUI.ParticipantGUI;
import tourGUI.TourGUI;
import touriststorGUI.TouriststoreGUI;

public class Client{

   private static BusinessMediator mediator;

   public static void main(String args[])
   {
      try{
	     mediator = new BusinessMediator();

	  	 ParticipantGUI airLine = new AirlineGUI(mediator);
		 ParticipantGUI hotel = new HotelGUI(mediator);
	  	 ParticipantGUI tour = new TourGUI(mediator);
	  	ParticipantGUI shop = new 	TouriststoreGUI(mediator);
	  	 mediator.register(shop);
	  	 mediator.register(airLine);
	  	 mediator.register(hotel);
	  	 mediator.register(tour);
	  }
	  catch (Exception ee){
	  	 ee. printStackTrace();
	  }
   }
}
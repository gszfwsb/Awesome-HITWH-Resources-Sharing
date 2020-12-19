import java.awt.Color;



public class TemperatureConvertor{

   private float celsius;
   private float fahrenheit;
   private float kelvin;
   private float rankine;
   private float reaumur;


   public float getCelsiusTemperature(String opt, String t){

	  Float tem = new Float(t);
      float floatTem = tem.floatValue();

      if(opt == TemperatureGUI.CELSIUS){
	     celsius = floatTem;
	  }
	  else if(opt.compareTo(TemperatureGUI.FAHRENHEIT)==0){
         fahrenheit = floatTem;
         celsius = (fahrenheit-32f)/1.8f;
	  }
      else if(opt.compareTo(TemperatureGUI.KELVIN)==0){
		 kelvin = floatTem;
         celsius = kelvin-273.15f;
	  }
      else if(opt.compareTo(TemperatureGUI.RANKINE)==0){
		 rankine = floatTem;
         celsius = (rankine-32f-459.67f)/1.8f;   //有同学说有错误
	  }
      else if(opt.compareTo(TemperatureGUI.REAUMUR)==0){
		 reaumur = floatTem;
         celsius = reaumur*1.25f;
	  }
	  return celsius;
   }

   public float getFahrenheitTemperature(String opt,String t){

      Float tem = new Float(t);
      float floatTem = tem.floatValue();

      if(opt.compareTo(TemperatureGUI.CELSIUS)==0){
		 celsius = floatTem;
		 fahrenheit = (celsius*1.8f + 32f);
	  }
	  else if(opt.compareTo(TemperatureGUI.FAHRENHEIT)==0){
         fahrenheit = floatTem;
	  }
      else if(opt.compareTo(TemperatureGUI.KELVIN)==0){
		 kelvin = floatTem;
         fahrenheit = (kelvin*1.8f- 459.67f);
	  }
      else if(opt.compareTo(TemperatureGUI.RANKINE)==0){
		 rankine = floatTem;
         fahrenheit= (rankine-459.67f);
	  }
      else if(opt.compareTo(TemperatureGUI.REAUMUR)==0){
		 reaumur = floatTem;
         fahrenheit = (reaumur*2.25f + 32f);
	  }
	  return fahrenheit;
   }

   public float getKelvinTemperature(String opt,String t){
      Float tem = new Float(t);
      float floatTem = tem.floatValue();

      if(opt.compareTo(TemperatureGUI.CELSIUS)==0){
		 celsius = floatTem;
		 kelvin = (celsius + 273.15f);
	  }
	  else if(opt.compareTo(TemperatureGUI.FAHRENHEIT)==0){
         fahrenheit = floatTem;
         kelvin = (fahrenheit+ 459.67f)/1.8f ;
	  }
      else if(opt.compareTo(TemperatureGUI.KELVIN)==0){
		 kelvin = floatTem;
	  }
      else if(opt.compareTo(TemperatureGUI.RANKINE)==0){
		 rankine = floatTem;
         kelvin = rankine / 1.8f;
	  }
      else if(opt.compareTo(TemperatureGUI.REAUMUR)==0){
		 reaumur = floatTem;
         kelvin = reaumur*1.25f + 273.15f;
	  }
	  return kelvin;

   }

   public float getRankineTemperature(String opt,String t){
      Float tem = new Float(t);
      float floatTem = tem.floatValue();

      if(opt.compareTo(TemperatureGUI.CELSIUS)==0){
		 celsius = floatTem;
	     rankine = celsius*1.8f + 32f + 459.67f;
	  }
	  else if(opt.compareTo(TemperatureGUI.FAHRENHEIT)==0){
         //fahrenheit = floatTem;
         //write code here
	  }
      else if(opt.compareTo(TemperatureGUI.KELVIN)==0){
		 //write code here
	     //write code here
	  }
      else if(opt.compareTo(TemperatureGUI.RANKINE)==0){
		 rankine = floatTem;
	  }
      else if(opt.compareTo(TemperatureGUI.REAUMUR)==0){
		 //write code here
	     //write code here
	  }
	  return rankine;
   }

   public float getReaumurTemperature(String opt,String t){
      Float tem = new Float(t);
      float floatTem = tem.floatValue();

      if(opt.compareTo(TemperatureGUI.CELSIUS)==0){
		 celsius = floatTem;
	     reaumur = celsius*0.8f;
	  }
	  else if(opt.compareTo(TemperatureGUI.FAHRENHEIT)==0){
         //write code here
         //write code here
	  }
      else if(opt.compareTo(TemperatureGUI.KELVIN)==0){
		 //write code here
	     //write code here
	  }
      else if(opt.compareTo(TemperatureGUI.RANKINE)==0){
		 //write code here
		 //write code here
	  }
      else if(opt.compareTo(TemperatureGUI.REAUMUR)==0){
		 reaumur = floatTem;
	  }
	  return reaumur;
   }

   public Color getColor(float kelvinTem){

     Color color = Color.black;

     if( kelvinTem < 373 ){
	 	color = Color.black;
   	 }
   	 else if( (kelvinTem >= 373) && ( kelvinTem < 1000)){
	 	color = new Color(200, 50, 8, 255);
   	 }
     else if( (kelvinTem >= 1000) && ( kelvinTem < 1500)){
	    color = new Color(255, 80, 10, 255);
   	 }
     else if( (kelvinTem >= 1500) && ( kelvinTem < 2000)){
	    color = new Color(255, 130, 10, 255);
   	 }
     else if( (kelvinTem >= 2000) && ( kelvinTem < 2500)){
   	    color = new Color(255, 180, 10, 255);
   	 }
   	 else if( (kelvinTem >= 2500) && ( kelvinTem < 3000)){
	    color = new Color(255, 220, 10, 255);
   	 }
   	 else if( (kelvinTem >= 3000) && ( kelvinTem < 3500)){
	    color = new Color(255, 240, 10, 255);
   	 }
   	 else if( (kelvinTem >= 3500) && ( kelvinTem < 4000)){
	    color = new Color(230, 250, 90, 255);
   	 }
   	 else if( (kelvinTem >= 4000) && ( kelvinTem < 4500)){
	    color = new Color(220, 255, 100, 255);
   	 }
   	 else if( (kelvinTem >= 4500) && ( kelvinTem < 5000)){
	   color = new Color(200, 230, 180, 255);
   	 }
   	 else if( (kelvinTem >= 5000) && ( kelvinTem < 5500)){
	    color = new Color(200, 255, 220, 255);
   	 }
   	 else if( (kelvinTem >= 5500) && ( kelvinTem < 6000)){
	    color = new Color(200, 255, 220, 255);
   	 }
   	 else if( (kelvinTem >= 6000) && ( kelvinTem < 6500)){
	    color = new Color(200, 255, 220, 255);
   	 }
   	 else if( (kelvinTem >= 6500) && ( kelvinTem < 7000)){
	    color = new Color(200, 255, 220, 255);
   	 }
   	 else if( (kelvinTem >= 7000) && ( kelvinTem < 7500)){
	    color = new Color(150, 220, 225, 255);
   	 }
   	 else if( (kelvinTem >= 7500) && ( kelvinTem < 8000)){
	    color = new Color(150, 220, 230, 255);
   	 }
   	 else if( (kelvinTem >= 8000) && ( kelvinTem < 8500)){
	 	color = new Color(150, 220, 235, 255);
   	 }
   	 else if( (kelvinTem >= 8500) && ( kelvinTem < 9000)){
	 	color = new Color(150, 220, 240, 255);
   	 }
   	 else if( (kelvinTem >= 9000) && ( kelvinTem < 9500)){
	 	color = new Color(150, 220, 245, 255);
   	 }
   	 else if( (kelvinTem >= 9500) && ( kelvinTem < 10000)){
	 	color = new Color(150, 220, 250, 255);
   	 }
     else if( kelvinTem >= 10000 ){
	 	color = new Color(130, 180, 250, 255);
   	 }

   	 return color;
   }

}
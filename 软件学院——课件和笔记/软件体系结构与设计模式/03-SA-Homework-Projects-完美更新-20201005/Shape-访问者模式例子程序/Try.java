

public class Try{

	public static void main(String[] args) {

		String[] strs={"111.1","222.2","33.33"};

		Double[] ds=new Double[strs.length];

		for(int i=0;i<strs.length;i++){
			ds[i]=Double.valueOf(strs[i]);
			double ddd = ds[i];
			System.out.println(ddd);
	    }
	    double sum = ds[0]+ds[1]+ds[2];
	    System.out.println("Sum = " + sum);


	    //打印double型数组
	   // for(int k =0; k<3; k++){

	   //    System.out.println(d);


       // }
	}
}
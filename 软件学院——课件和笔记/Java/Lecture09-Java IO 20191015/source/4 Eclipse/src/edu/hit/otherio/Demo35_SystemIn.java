package edu.hit.otherio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Demo35_SystemIn {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		//InputStreamReader×ª»»Á÷
		String line = br.readLine();
		System.out.println(line);
		br.close();*/
		
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		System.out.println(line);
		sc.close();
	}

}

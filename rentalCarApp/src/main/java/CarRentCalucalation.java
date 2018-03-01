/**
 * 
 */
package main.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Ninganna.c
 *
 */
public class CarRentCalucalation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id="";
		String name="";
		String type="";
		String model="";
		int noOfSeats=0;
		int year=0;
		double kms=0;
		double hours=0.0;
		String totalTime="";
		String hhmm[];
		CarFactory factory=new CarFactory();
		ArrayList<Car> carList=factory.getAllCars();
		Scanner sc=new Scanner(System.in);  
	 
		System.out.println("Enter your ID");  
		id=sc.next();  
		System.out.println("Enter your name");  
		name=sc.next();  
		System.out.println("Select Car Type");
		
		Iterator<Car> carIterator=carList.iterator();
		while(carIterator.hasNext()){
			Car car=(Car)carIterator.next();
			System.out.println("Enter "+car.getCarId()+" if Car Type "+car.getCarName());
		}
		type=sc.next();  
		System.out.println("Enter Car Model");  
		model=sc.next();
		System.out.println("Enter Numer of Seats in a Car");  
		noOfSeats=sc.nextInt();
		System.out.println("Enter Car Model Year");  
		noOfSeats=sc.nextInt();
		System.out.println("Enter your Total Km Traveled");  
		kms=sc.nextDouble();
		System.out.println("Enter your Total Time Traveled in HH:MM Format");  
		totalTime=sc.next();  
		sc.close();
		try{
			if(totalTime != null){
				hhmm=totalTime.split(":");
				hours=Double.parseDouble(hhmm[0])+(Double.parseDouble(hhmm[1])/60);
			}
			RentalCarApp rentalCarApp=new RentalCarApp(factory);
			rentalCarApp.netRentalFare(id, name, type, model, noOfSeats, year, kms, hours);
		}
		catch(Exception exceptio){
			System.out.println("The Details Entered are incorrect, please enter valid details");  
		}

	}

}

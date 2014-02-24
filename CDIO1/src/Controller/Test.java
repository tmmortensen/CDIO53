package Controller;

import Boundary.Boundary;

public class Test {
	Boundary boundary = new Boundary();
	
	public Test(){
	}
	
	public void RunTest(){
		boolean weightChoice=true;
		while(weightChoice==true){
		int tara = boundary.getInt("please write the Tara weight of the weighting");
		int brutto = boundary.getInt("please write the brutto weight of the weighting" );
		int netto = brutto-tara; 
		boundary.showStringMessage("the netto weight of the object is: "+ netto);
		String weight = boundary.getString("would you like to weight another object? Y/N");
			if(weight.equalsIgnoreCase("y")){
				weightChoice=true;
			}
			else{
				weightChoice=false;
			}
		
		}
		
		
	}
	
	
}

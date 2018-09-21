 
public class Difficulty{
	int diffLvl;
	int[] angles = {0,30,45,60,90};
	public Difficulty(int startDiff){
		diffLvl = startDiff;
	}
	
	public double getRndAngle(){
		if (diffLvl <= 14)
		{// 0 30 45 60 90
			double angle = (double)(angles[(int)(Math.random() *5)]);
			return angle;
		}
		else //if (diffLvl > 7 && diffLvl <= 14)
		{// Angles 0, 30, 45, 60, 90
			int rand = ((int)(Math.random() * 3)) + 2;
			int chosenAngle = (int)(Math.random() * 5);
			double angle = (double)(angles[chosenAngle] * rand);
			if (angle == 360)
				angle = 0;
			return angle;	
		}
	}
	
	public double getRndLength(){ // lengths are from .5 to 10
		if (diffLvl <= 7){ //all whole lengths
			double length = (double)(((int)(Math.random() * 10) + 1));
			return length;
		} else if (diffLvl > 7 && diffLvl <= 14){ // all lengths with an ending of .5 or .0
			double length = (double)(((int)(Math.random() * 20) + 1) * .5);
			return length;
		} else if (diffLvl > 14) { // all lengths with one decimal point
			double length = (double)(((((int)(Math.random() * 100)) + 1) / 10.0 ));
			return length;
		}
		return 0.0;
	}
	
	public void advance(){
		diffLvl++;
	}
}
 
 
 

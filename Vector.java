 
public class Vector {
	private double angle, length;
	
	public Vector(double l, double a){
		angle = a;
		length = l;
	}
	
	public double getHorComp(){
		double output = (length * Math.cos(angle/180.0*Math.PI));
		if (Math.abs(output) < .01 && Math.abs(output) > 0){
			return 0;
		} else
			return output;
	}
	
	public double getVertComp(){
		double output = (length * Math.sin(angle/180.0*Math.PI));
		if (Math.abs(output) < .01 && Math.abs(output) > 0){
			return 0;
		} else
			return output;
	}
	
	public boolean equals(Vector b){
		return (Math.abs(this.length - b.length)) < 1 &&
				Math.abs((this.angle - b.angle)) < 1;
	}
	
	public String toString(){
		return "vector of length " + length + " at " + angle;
	}
}
 
 

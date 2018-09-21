import java.util.ArrayList;

public class Level {
	public Vector answer;
	public static ArrayList<Vector> inputs = new ArrayList<Vector>();
	public Difficulty diff;
	public int levelNumber;

	// used for instance drawing
	public int x = 200;
	public int y = 200;
	public double xFinal; // used for drawing start of vector
	public double yFinal; // used for drawing start of vector

	public Level(int levelNum) {
		levelNumber = levelNum;
		diff = new Difficulty(levelNum);
		answer = new Vector(diff.getRndLength() * 10, diff.getRndAngle());
		xFinal = answer.getHorComp();
		yFinal = answer.getVertComp();
	}

	public void addVector(Vector suggestion) {
		inputs.add(suggestion);
		x += suggestion.getHorComp();
		y += suggestion.getVertComp();
	}

	public boolean isComplete() {
		if (inputs.size() == getVectorsNeeded()) {
			if (answer.equals(combineVectors())) {
				return true;
			} else {
				inputs.removeAll(inputs);
				return false;
			}
		} else {
			return false;
		}
	}

	public void advanceLevel() {
		diff.advance();
		levelNumber++;
		inputs.removeAll(inputs);
		answer = new Vector(diff.getRndLength() * 10, diff.getRndAngle());
	}

	public void advanceLevel(int desire) {
		while (diff.diffLvl < desire) {
			diff.advance();
		}
		levelNumber = desire;
		inputs.removeAll(inputs);
		answer = new Vector(diff.getRndLength() * 10, diff.getRndAngle());
	}

	public ArrayList<Vector> getInputs() {
		return inputs;
	}

	public int getVectorsNeeded() {
		return levelNumber / 7 + 1;
	}

	// return a list of x and y values of all vector start and end points
	public ArrayList<Double> getDrawingPoints() {
		ArrayList<Double> output = new ArrayList<Double>();
		for (Vector v : inputs) {
			output.add(v.getHorComp() + 10);
			output.add(v.getVertComp() + 10);
		}
		return output;
	}

	// combines vectors by components and return final vector
	public Vector combineVectors() {
		double totHor = 0;
		double totVert = 0;
		for (Vector v : inputs) {
			totHor += v.getHorComp();
			totVert += v.getVertComp();
		}
		// Checks if any vectors are the same
		for (int i = 0; i < inputs.size(); i++) {
			for (int j = i; j < inputs.size(); j++) {
				if (inputs.get(i).equals(inputs.get(j)))
					;
			}
		}
		// totVert = totVert * -1.0;
		double length = Math.sqrt(Math.pow(totHor, 2) + Math.pow(totVert, 2));
		double angle = ((Math.asin(totVert / length)) * 180 / Math.PI);

		if (totVert > 0) {
			if (totHor < 0)
				angle = ((Math.acos(totHor / length)) * 180 / Math.PI);
		}
		if (totVert < 0) {
			if (totHor < 0)
				angle *= 2;
		}
		if (totVert == 0 && totHor < 0)
			angle += 180;
		while (angle < 0) {
			angle += 360.0;
		}
		return new Vector(length, angle);
	}
}

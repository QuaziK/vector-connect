import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener {
	JButton enter;
	JTextField lengthI, angleI, answer;
	ArrayList<Vector> vectors = new ArrayList<Vector>();
	JLabel question;
	static Level game;
	static Main main;

	public Main() {
		super("VectorConnect");
		setSize(315, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		JLabel prompt = new JLabel(
				"Input your guess vector here in the two boxes", JLabel.CENTER);
		lengthI = new JTextField("Length", 10);
		angleI = new JTextField("Angle (deg)", 10);
		enter = new JButton("Enter");
		question = new JLabel("<html>Level " + game.levelNumber + ": Create "
				+ game.getVectorsNeeded()
				+ " vector(s) to get from the origin to <br>("
				+ (game.answer.getHorComp() / 10) + ", "
				+ (game.answer.getVertComp() / 10) + ")<html>");
		FlowLayout flow = new FlowLayout();
		setLayout(flow);
		add(prompt);
		add(lengthI);
		add(angleI);
		add(enter);
		add(question);
		enter.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if ((lengthI.getText().equals("demo"))) {
			game.advanceLevel(Integer.parseInt(angleI.getText()));
			question.setText("<html>Level " + game.levelNumber + ": Create "
					+ game.getVectorsNeeded()
					+ " vector(s) to get from the origin to <br>("
					+ (game.answer.getHorComp() / 10) + ", "
					+ (game.answer.getVertComp() / 10) + ")<html>");
		} else if (!Double.isNaN(Double.parseDouble(lengthI.getText()))
				|| !Double.isNaN(Double.parseDouble(angleI.getText()))) {
			// set up the vector visualizer
			game.addVector(new Vector(
					Double.parseDouble(lengthI.getText()) * 10, Double
							.parseDouble(angleI.getText())));
			Runner r = new Runner(new ArrayList<Vector>(game.getInputs()));
			r.setVisible(true);
			r.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

			if ((game.inputs.size() == game.getVectorsNeeded())
					&& !(game.answer.equals(game.combineVectors()))) {
				// the amount of guesses was correct but the combined vector wasn't
				// game ends
				main.dispatchEvent(new WindowEvent(main,
						WindowEvent.WINDOW_CLOSING));
			}

			if (game.isComplete()) {
				// level is entirely correct
				game.advanceLevel();
				//update prompt
				question.setText("<html>Level " + game.levelNumber
						+ ": Create " + game.getVectorsNeeded()
						+ " vector(s) to get from the origin to <br>("
						+ (game.answer.getHorComp() / 10) + ", "
						+ (game.answer.getVertComp() / 10) + ")<html>");
			}
		} else {
			// demo code to advance to any level
			game.advanceLevel(Integer.parseInt(angleI.getText()));
			question.setText("<html>Level " + game.levelNumber + ": Create "
					+ game.getVectorsNeeded()
					+ " vector(s) to get from the origin to <br>("
					+ (game.answer.getHorComp() / 10) + ", "
					+ (game.answer.getVertComp() / 10) + ")<html>");

		}

	}

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null,"Welcome to the VectorConnect game\n"
			+ "in which the objective is to match\n"
			+ "the given vector in the form of a\n"
			+ "start and end point with an input vector.\n"
			+ "The range of vector lengths and angles required for input\n"
			+ "will increase in difficulty every 7th level.\n"
			+ "With more than one vector prompts,\n"
			+ "input subsequent vectors into the two\n"
			+ "text boxes until the amount of\n"
			+ "vectors for the level is met.\n"
			+ "One mistake and the game ends.\n"
			+ "Made by Yegor Kozhevnikov and Josh Deans");
		game = new Level(1);
		main = new Main();
	}

}

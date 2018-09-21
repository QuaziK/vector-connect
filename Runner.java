import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class Runner extends JFrame implements ActionListener {
	ArrayList<Vector> draw;
	JTextField textField;
 
	public Runner(ArrayList<Vector> input) {
		super("Vector Visualizer");
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		setSize(400, 400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width/2-this.getSize().width/2) + 400, dim.height/2-this.getSize().height/2);
		draw = input;
	}
 
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
 
		double startX = 200.0;
		double startY = 200.0;

		g2.setStroke(new BasicStroke(2));
		Line2D answer = new Line2D.Double(startX, startY, draw.get(0)
				.getHorComp() + startX, -1 * draw.get(0).getVertComp() + startY);
 
		g2.draw(answer);
		for (int d = 1; d < draw.size(); d++) {
			double curStartX = startX;
			double curStartY = startY;
			int dc = d;
			while (dc != 0) {
				curStartX += draw.get(dc - 1).getHorComp();
				curStartY += -1.0 * draw.get(dc - 1).getVertComp();
 
				dc--;
			}
			double curEndX = curStartX + draw.get(d).getHorComp();
			double curEndY = curStartY + draw.get(d).getVertComp() * -1;
			Line2D thi = new Line2D.Double(curStartX, curStartY, curEndX,
					curEndY);
			g2.draw(thi);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText();
		String distance = text.substring(0, text.indexOf(","));
		String radian = text.substring(text.indexOf(",") + 1);
		double length = Double.parseDouble(distance);
		double angle = Double.parseDouble(radian);
 
		ArrayList<Vector> asd = new ArrayList<Vector>();
		asd.add(new Vector(length, angle));
		Runner r = new Runner(asd);
 
		r.setVisible(true);
		r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

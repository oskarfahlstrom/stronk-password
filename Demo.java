import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Just a little demo of our password strength meter.
 * 
 * Created on Dec 11, 2012, 2:37:32 PM
 * 
 * @author Jacob Bergvall, Oskar Fahlström
 */
@SuppressWarnings("serial")
public class Demo extends JFrame {

	public Demo() {
		JScrollPane scroll = new JScrollPane(new PasswordStrengthMeter());

		add(scroll);
		setTitle("Password Strength Meter");
		setVisible(true);
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Demo();
			}
		});
	}
}

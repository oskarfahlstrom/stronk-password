import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Created on Dec 11, 2012, 2:37:32 PM
 * 
 * @author Jacob Bergvall, Oskar Fahlström
 */
@SuppressWarnings("serial")
public class Demo extends JFrame {

	/**
	 * Just a little demo of our password strength meter.
	 */
	public Demo() {
		/**
		 * A simple strength algorithm for this demo.
		 */
		final StrengthAlgorithm sa = new StrengthAlgorithm() {

			@Override
			public void strengthAlgorithm(String s) {
				// If you want to customize the algorithm for other languages,
				// simply change the strings.
				String lowercase = "abcdefghijklmnopqrstuvwxyzåäö";
				String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ";
				String numbers = "0123456789";
				String symbols = "!\"#¤%&/()=?@£$€{[]};,:._-'*";

				// TODO: Add a filter that informs you that certain words
				// shouldn't be used. For example "password", number sequences
				// like "1234" etc.

				boolean hasUpper = false;
				boolean hasLower = false;
				boolean hasNumber = false;
				boolean hasSymbol = false;

				if (s.length() >= 8) {
					PasswordStrengthMeter.score++;
					if (s.length() >= 12)
						PasswordStrengthMeter.score++;
				}
				for (int i = 0; i < lowercase.length(); i++) {
					if (memberOf(lowercase.charAt(i), s))
						hasLower = true;
				}
				for (int i = 0; i < uppercase.length(); i++) {
					if (memberOf(uppercase.charAt(i), s))
						hasUpper = true;
				}
				for (int i = 0; i < numbers.length(); i++) {
					if (memberOf(numbers.charAt(i), s))
						hasNumber = true;
				}
				for (int i = 0; i < symbols.length(); i++) {
					if (memberOf(symbols.charAt(i), s))
						hasSymbol = true;
				}
				if (hasUpper && hasLower)
					PasswordStrengthMeter.score++;
				if (hasNumber)
					PasswordStrengthMeter.score++;
				if (hasSymbol)
					PasswordStrengthMeter.score++;
			}

			@Override
			public boolean memberOf(char c, String s) {
				boolean member = false;
				for (int i = 0; s.length() > i; i++)
					if (c == s.charAt(i)) {
						member = true;
						break;
					}
				return member;
			}
		};

		PasswordStrengthMeter psm = new PasswordStrengthMeter(sa);

		/**
		 * Creates a listener for the input field. From here you can decide how
		 * the strength of the password should be displayed.
		 */
		psm.addInputFieldListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				PasswordStrengthMeter.score = 0; // reset score
				sa.strengthAlgorithm(PasswordStrengthMeter.inputField.getText());
				PasswordStrengthMeter.commentLabel
						.setText("Password strength:  "
								+ PasswordStrengthMeter.score + " / 5");
				PasswordStrengthMeter.resultLabel.setOpaque(true);
				PasswordStrengthMeter.resultLabel2.setOpaque(true);
				if (PasswordStrengthMeter.inputField.getText().length() == 0) {
					// Label is reset if all text is deleted from the input
					// field.
					PasswordStrengthMeter.resultLabel.setText("");
					PasswordStrengthMeter.resultLabel.setBackground(null);
					PasswordStrengthMeter.resultLabel2.setBackground(null);
				}
				if (PasswordStrengthMeter.score == 1) {
					PasswordStrengthMeter.resultLabel.setText("REALLY BAD");
					PasswordStrengthMeter.resultLabel.setBackground(Color.RED);
					PasswordStrengthMeter.resultLabel2.setBackground(Color.RED);
				}
				if (PasswordStrengthMeter.score == 2) {
					PasswordStrengthMeter.resultLabel.setText("WEAK");
					PasswordStrengthMeter.resultLabel
							.setBackground(Color.ORANGE);
					PasswordStrengthMeter.resultLabel2
							.setBackground(Color.ORANGE);
				}
				if (PasswordStrengthMeter.score == 3) {
					PasswordStrengthMeter.resultLabel.setText("OK");
					PasswordStrengthMeter.resultLabel
							.setBackground(Color.YELLOW);
					PasswordStrengthMeter.resultLabel2
							.setBackground(Color.YELLOW);
				}
				if (PasswordStrengthMeter.score == 4) {
					PasswordStrengthMeter.resultLabel.setText("GOOD");
					PasswordStrengthMeter.resultLabel.setBackground(Color.CYAN);
					PasswordStrengthMeter.resultLabel2
							.setBackground(Color.CYAN);
				}
				if (PasswordStrengthMeter.score == 5) {
					PasswordStrengthMeter.resultLabel.setText("EXCELLENT");
					PasswordStrengthMeter.resultLabel
							.setBackground(Color.GREEN);
					PasswordStrengthMeter.resultLabel2
							.setBackground(Color.GREEN);
				}

				// TODO: Allow the user to change how the strength of the
				// password is displayed. For example by showing it as a
				// percentage.
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		JScrollPane scroll = new JScrollPane(psm);

		// WINDOW SETTINGS //
		add(scroll);
		setTitle("Password Strength Meter");
		setVisible(true);
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Standard main method for thread safety.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Demo();
			}
		});
	}
}

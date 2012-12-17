import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Created on Dec 6, 2012, 8:28:10 AM
 * 
 * @author Jacob Bergvall, Oskar Fahlström
 */
@SuppressWarnings("serial")
public class PasswordStrengthMeter extends JComponent {

	static int score = 0;
	JLabel instructionLabel;
	JButton resetButton;
	final JLabel commentLabel, resultLabel, resultLabel2;
	final JTextField inputField;
	StrengthAlgorithm a;

	/**
	 * A simple program that calculates the strength of a password.
	 * 
	 * @param a
	 */
	public PasswordStrengthMeter(StrengthAlgorithm a) {
		instructionLabel = new JLabel("Enter your password");
		commentLabel = new JLabel("");
		inputField = new JTextField();
		resetButton = new JButton("Start over");
		resultLabel = new JLabel("");
		resultLabel2 = new JLabel("");

		this.a = a;

		// Set the default size of our fields.
		instructionLabel.setPreferredSize(new Dimension(200, 20));

		inputFieldListener(inputField);
		resetButtonListener(resetButton);

		// Create the layout.
		GridLayout layout = new GridLayout(3, 2);
		setLayout(layout);

		// Add the components to the frame.
		add(instructionLabel);
		add(inputField);
		add(commentLabel);
		add(resetButton);
		add(resultLabel);
		add(resultLabel2);
	}

	/**
	 * Set up the listener for the reset button.
	 * 
	 * @param resetButton
	 */
	public void resetButtonListener(final JButton resetButton) {
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputField.setText("");
				commentLabel.setText("");
				resultLabel.setText("");
				resultLabel.setBackground(null);
				resultLabel2.setBackground(null);
			}
		});
	}

	/**
	 * Set up the listener for the main input field.
	 * 
	 * @param inputField
	 */
	public void inputFieldListener(final JTextField inputField) {
		inputField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				score = 0; // reset score
				a.strengthAlgorithm(inputField.getText());
				commentLabel.setText("Password strength:  " + score + " / 5");
				resultLabel.setOpaque(true);
				resultLabel2.setOpaque(true);
				if (inputField.getText().length() == 0) {
					// Labels reset if all text is deleted from the input field.
					resultLabel.setText("");
					resultLabel.setBackground(null);
					resultLabel2.setBackground(null);
				}
				if (score == 1) {
					resultLabel.setText("REALLY BAD");
					resultLabel.setBackground(Color.RED);
					resultLabel2.setBackground(Color.RED);
				}
				if (score == 2) {
					resultLabel.setText("WEAK");
					resultLabel.setBackground(Color.ORANGE);
					resultLabel2.setBackground(Color.ORANGE);
				}
				if (score == 3) {
					resultLabel.setText("OK");
					resultLabel.setBackground(Color.YELLOW);
					resultLabel2.setBackground(Color.YELLOW);
				}
				if (score == 4) {
					resultLabel.setText("GOOD");
					resultLabel.setBackground(Color.CYAN);
					resultLabel2.setBackground(Color.CYAN);
				}
				if (score == 5) {
					resultLabel.setText("EXCELLENT");
					resultLabel.setBackground(Color.GREEN);
					resultLabel2.setBackground(Color.GREEN);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}
}

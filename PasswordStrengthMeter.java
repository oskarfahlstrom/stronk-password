import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	// CREATE COMPONENTS
	static int score = 0;
	JLabel instructionLabel;
	JButton resetButton;
	static JLabel commentLabel;
	static JLabel resultLabel;
	static JLabel resultLabel2;
	static JTextField inputField;
	StrengthAlgorithm sa;

	/**
	 * A simple program that calculates the strength of a password.
	 * 
	 * @param a
	 */
	public PasswordStrengthMeter(StrengthAlgorithm sa) {
		// INITIALIZE COMPONENTS
		instructionLabel = new JLabel("Enter your password");
		commentLabel = new JLabel("");
		inputField = new JTextField();
		resetButton = new JButton("Start over");
		resultLabel = new JLabel("");
		resultLabel2 = new JLabel("");

		// In order to implement the code from the StrengthAlgorithm in the
		// Demo-class.
		this.sa = sa;

		// Set the default size of our fields.
		instructionLabel.setPreferredSize(new Dimension(200, 20));

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
	 * Adds a listener to the input field.
	 * 
	 * @param e
	 */
	public void addInputFieldListener(KeyListener e) {
		inputField.addKeyListener(e);
	}
}

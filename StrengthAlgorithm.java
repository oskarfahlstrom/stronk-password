public class StrengthAlgorithm {

	public StrengthAlgorithm(String s) {

		// TODO Take a look at how String.contains() works.

		String lowercase = "abcdefghijklmnopqrstuvwxyz���";
		String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ���";
		String numbers = "0123456789";
		String symbols = "!\"#�%&/()=?@�$�{[]};,:._-'*";

		boolean hasUpper = false;
		boolean hasLower = false;
		boolean hasNumber = false;
		boolean hasSymbol = false;

		if (s.length() >= 8) {
			PasswordStrengthMeter.score++;
			// System.out.println("Fler �n 8: " + score);
			if (s.length() >= 12)
				PasswordStrengthMeter.score++;
			// System.out.println("Fler �n 12: " + score);
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
		// System.out.println("Sm� och stora: " + score);
		if (hasNumber)
			PasswordStrengthMeter.score++;
		// System.out.println("Siffror: " + score);
		if (hasSymbol)
			PasswordStrengthMeter.score++;
		// System.out.println("Specialtecken: " + score);
	}

	public static boolean memberOf(char c, String s) {
		boolean member = false;
		for (int i = 0; s.length() > i; i++) {
			if (c == s.charAt(i)) {
				member = true;
				break;
			}
		}
		return member;
	}
}

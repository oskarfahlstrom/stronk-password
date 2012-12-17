/**
 * Created on Dec 14, 2012, 9:43:17 AM
 * 
 * @author Jacob Bergvall, Oskar Fahlström
 */
public interface StrengthAlgorithm {

	/**
	 * The algorithm that claculates the strength of the input password.
	 * 
	 * @param s
	 */
	public void strengthAlgorithm(String s);

	/**
	 * Help method for the strength algorithm that identifies characters.
	 * Character groups can be customized for specific languages etc.
	 * 
	 * @param c
	 * @param s
	 * @return boolean
	 */
	public boolean memberOf(char c, String s);
}

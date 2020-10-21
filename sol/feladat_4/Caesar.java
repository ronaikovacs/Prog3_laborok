
public class Caesar {
	
	private String caesarCode(String input, char offset)
	{
		input = input.toUpperCase();
		
		String output = "";
		for (char c : input.toCharArray())
			output += (char) ((c - 'A' + offset - 'A') % 26 + 'A');
		return output;
	}

	String getCaesarCode(String input, char offset)
	{
		return caesarCode(input, offset);
	}
	
}

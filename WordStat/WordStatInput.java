import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.lang.IndexOutOfBoundsException;

public class WordStatInput {

	static int[] add;

    public static boolean isChar(char c) {
        if (c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION || Character.isLetter(c)) {
        	return true;
        } else {
        	return false;
        }
    }

    public static String[] moreSize(String[] text) {
    	return Arrays.copyOf(text, text.length * 2);
    }

    public static String[] pus(String str, String[] text, int j) {

    	if (j >= text.length) {
    		text = moreSize(text);
    	}

    	text[j] = str;
    	return text;
    }

    public static String[] bubleSort(String[] text) {

    	for(int i = 0; i < text.length; i++) {
    		if(text[i] == null)
    			continue;
			add[i]++;
    		for(int j = text.length - 1; j >= i + 1; j--) {
    			if (text[j] != null && text[i].equals(text[j]))
    			{
					add[i]++;
    				text[j] = null;
    			}
    		}
    	}
    	return text;
    }

    public static void main(String args[]) {   
        BufferedReader reader = null;

        String[] text = new String[1];
        int j = 0;

        StringBuilder str = new StringBuilder("");
        StringBuilder empt = new StringBuilder("");

        try { 
        	reader = new BufferedReader(
	            new InputStreamReader(
	                new FileInputStream(args[0]),
	                "utf8"
	            )
	        );

	        while(true) {
	        	String line = reader.readLine();
	        	
	        	if(line == null) {
	        		break;
	        	}
	        	int i1 = 0, i2 = 0;
				line = line.toLowerCase(Locale.ROOT);
	        	for(int i = 0; i < line.length(); i++){
	        		if(isChar(line.charAt(i))) {
	        			str.append(line.charAt(i));
	        		} else if (!str.toString().isEmpty()) {
	        			System.out.println(str);
		        		text = pus(str.toString(), text, j);
		        		j++;
		        		str = new StringBuilder("");
		        	}
		        }
		        if (!str.toString().isEmpty()) {
	        		text = pus(str.toString(), text, j);
	        		j++;
	        		str = new StringBuilder("");
	        	}
	        } 
	        reader.close();
        } catch (IOException e) {
	        	System.out.println("Can't open the file");
	    } 
        BufferedWriter writer = null;

		add = new int[text.length];

        text = bubleSort(text);
        
        try {
        	writer = new BufferedWriter(
	            new OutputStreamWriter(
	                new FileOutputStream(args[1]),
						StandardCharsets.UTF_8
	            )
	        );

        	int count = 1;

	        for(int i = 0; i < text.length; i++) {
				if (text[i] == null)
					continue;
				writer.write(text[i] + " " + add[i] + '\n');

				System.err.println(text[i] + " " + add[i]);
			}
	        writer.close();
        } catch (IOException e) {
        	System.out.println("Can't open the file" + e.getMessage());

        } 
    }
}
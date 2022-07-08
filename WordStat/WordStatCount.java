import java.io.*;
import java.util.*;
import java.lang.IndexOutOfBoundsException;
import java.lang.Object;

public class WordStatCount {

    public static boolean isChar(char simb) {
        return Character.getType(simb) == Character.DASH_PUNCTUATION || simb == '\''  || Character.isLetter(simb);
    }

    public static String getWord(int ind, String str) {

        int i = 0;
        for (i = ind; i < str.length(); i++) {
            if (!isChar(str.charAt(i))) {
                break;
            }
        }

        return str.substring(ind, i);
    }

    public static String[] pushStr(String str, String[] text, int j) {

    	if (j >= text.length) {
    		text = Arrays.copyOf(text, text.length * 2);
    	}

    	text[j] = str;
    	return text;
    }

    public static String[] swapStr(int i, int j, String[] text) {
        String str = text[j];
        text[j] = text[i];
        text[i] = str;

        return text;
    }

    public static int[] swapInt(int i, int j, int[] point) {
        int num = point[j];
        point[j] = point[i];
        point[i] = num;

        return point;
    }

    public static int countWord(String[] text, int i) {
        int count = 1;

        for (int j = i + 1; j < text.length; j++) {
            if (text[j] != null && text[i].equals(text[j])){
                text[j] = null;
                count++;
            }
        }

        return count;
    }
    
    public static void main(String args[]) {   

        String[] text = new String[1];
        int j = 0;

        try { 
        	BufferedReader reader = new BufferedReader(
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

                line = line.toLowerCase();

	        	for (int i = 0; i < line.length(); i++) {
                    String word = getWord(i, line);

                    if (!word.equals("")) {
                        text = pushStr(word, text, j);
                        j++;
                        i += word.length();
                    }
                }
	        }
	        reader.close();
        } catch (IOException e) {
	        	System.out.println("Can't read the file");
	    } 

        int[] point = new int[text.length * 2];

        int siz = j;


        for (int i = 0; i < text.length; i++) {
            if (text[i] == null) {
                point[i] = Integer.MAX_VALUE;
                continue;
            }
            int count = countWord(text, i);

            point[i] = count;


            for (j = i; j > 0; j--) {
                if (point[j] < point[j - 1]) {
                    point = swapInt(j - 1, j, point);
                    text = swapStr(j - 1, j, text);
                } else {
                    break;
                }
            }
        }

        try {
        	BufferedWriter writer = new BufferedWriter(
	            new OutputStreamWriter(
	                new FileOutputStream(args[1]),
	                "utf8"
	            )
	        );

        	int i = 0; 

            while (point[i] < Integer.MAX_VALUE) {
                writer.write(text[i] + " " + point[i] + "\n");
                i++;
            }

	        writer.close();
        } catch (IOException e) {
        	System.out.println("Can't write the file" + e.getMessage());

        } 
    }
}


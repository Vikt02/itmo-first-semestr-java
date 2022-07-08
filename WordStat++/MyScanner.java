import java.io.*;

public class MyScanner {

	private int iter = 0;
	private int strSize = 0;
	private Reader reader;
	private final int END = -2;
	private char[] buff = new char[505000];
	StringBuilder num;

	public MyScanner(InputStream input) throws IOException {
		reader = new InputStreamReader(input, "utf8");
	   	newBuff();
	   	iter = 0;
	   	num = new StringBuilder();
	}

// ------------------------------------------------------------- HERE BUFFER AND WORDS CODE --------------------
	public void close() throws IOException {
		reader.close();
	}
	private void newBuff() throws IOException {
		strSize = reader.read(buff);
		iter = -1;
	}

	private static boolean isSimbol(char simb) {
		return (isNum(simb) || isChar(simb));
	}

	private int nextWord() throws IOException {

		for (; iter <= strSize && strSize > 0; iter++) {
			if (iter == strSize) {
				newBuff();
				continue;
			}

			if (Character.toString(buff[iter]).equals(System.lineSeparator()) || buff[iter] == '\n') {
				return -1;
			}
			
			if (isSimbol(buff[iter])) {
				return iter;
			} 
		}
		return END;
	}

// ------------------------------------------------------------- HERE INTEGER CODE -----------------------------
	
	private static boolean isNum(char simb) {
		return (Character.isDigit(simb) || simb == '-' || ('a' <= simb && simb <= 'f'));
	}

	public boolean hasNextInt() throws IOException {
		int num = -1;
		for (; iter < strSize; iter++) {
			num = nextWord();
			if (num <= -1) {
				iter++;
				return false;
			} else {
				if (isNum(buff[iter])){
					return true;
				}
			}
		}
		return false;
	}

	public String nextInt() throws IOException {
		num.setLength(0);
		for (; iter < strSize; iter++) {
			if(isNum(buff[iter])) {
				num.append(buff[iter]);
			} else {
				break;
			}
			if (iter + 1 == strSize) {
				newBuff();
			}
		}
		return num.toString();
	}

// ------------------------------------------------------------- HERE STRING CODE ------------------------------

	private static boolean isChar(char simb) {
        return Character.getType(simb) == Character.DASH_PUNCTUATION || simb == '\''  || Character.isLetter(simb);
    }

	public boolean hasNextString() throws IOException {
		int num = -1;
		for (; iter < strSize; iter++) {
			num = nextWord();
			if (num <= -1) {
				iter++;
				return false;
			} else {
				if (isChar(buff[iter])){
					return true;
				}
			}
		}
		return false;
	}

	public String nextString() throws IOException {
		num.setLength(0);
		for (; iter < strSize; iter++) {
			if(isChar(buff[iter])) {
				num.append(buff[iter]);
			} else {
				break;
			}
			if (iter + 1 == strSize) {
				newBuff();
			}
		}
		return num.toString();
	}

// ------------------------------------------------------------- HERE LINE CODE --------------------------------

	public boolean hasNextLine() throws IOException {
		if (iter <= strSize) {
			int num = nextWord();
			if (num == END) {
				return false;
			}
			return true;
		}
		return false;
	}
}

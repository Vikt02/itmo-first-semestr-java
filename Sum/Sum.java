import java.util.Arrays;

public class Sum {

	public static int checkNum(int i, String arr) {
		int n = 0;
		int t = 1;
		if (arr.charAt(i) == '-') {
			t = -1;
			i++;
		}
		while (i < arr.length() && Character.isDigit(arr.charAt(i))) {
			n = n * 10 + (arr.charAt(i) - '0');
			i++;
		}
		return n * t;
	}

	public static void main(String[] args) {

		int ans = 0;

		for (String arg : args) {
			for (int j = 0; j < arg.length(); j++) {
				int num = checkNum(j, arg);
				if (num != 0) {
					j += Integer.toString(num).length();
					ans += num;
				}
			}
		}

		System.out.println(ans);
	}
}

import java.util.Arrays;

public class SumFloat {

	public static String checkNum(int i, String arr) {
		StringBuilder n = new StringBuilder("");
		int t = 0;
		if (arr.charAt(i) == '-') {
			n.append("-");
			i++;
		}
		while (i < arr.length() && (Character.isDigit(arr.charAt(i)) || arr.charAt(i) == '.'
				|| arr.charAt(i) == 'e' || arr.charAt(i) == 'E' || arr.charAt(i) == '-')) {
			n.append(arr.charAt(i));
			i++;
		}
		return n.toString();
	}

	public static void main(String[] args) {

		float ans = 0;

		for (String arg : args) {
			for (int j = 0; j < arg.length(); j++) {
				String num = checkNum(j, arg);
				if (!num.equals("")) {
					j += num.length();
					ans += Float.parseFloat(num);
				}
			}
		}

		System.out.println(ans);
	}
}

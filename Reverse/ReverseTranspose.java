import java.util.Scanner;

public class ReverseTranspose {

	public static void cop (int[] line2, int[] line) {

		for(int i = 0; i < line.length; i++)
			line2[i] = line[i];
	}

	public static int getLine (Scanner s, int siz, int[][] data) {
		int j = 0;
		int[] line = new int[0];
		while (s.hasNextInt()) {
			if (j == line.length) {
				line = Arrays.copyOf(line, line.length * 2);
			}
			line[j] = Integer.parseUnsignedInt(s.nextInt(), 16);
			j++;
		}
		data[siz] = new int[j];
		data[siz] = line;
		return j;
	}

	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);

		int siz = 0;
		int j = 0;
		int maxij = 0;
		int[][] data = new int[(int)1e6 + 1][];


		while (scan.hasNextLine()) {
			Scanner s = new Scanner(scan.nextLine());
			j = getLine (s, siz, data);
			if (maxij < j) {
				maxij = j;
			}
			siz++;
		}

		for (j = 0; j < maxij; j++) {
			for (int i = 0; i < siz; i++) {
				if(data[i].length > j)
					System.out.print(data[i][j] + " ");
			}
			if (j + 1 < maxij)
				System.out.print('\n');
		}

	} 

}
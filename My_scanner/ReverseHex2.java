import java.io.*;
import java.util.Arrays;

public class ReverseHex2 {

	public static int[][] getLine(MyScanner s, int siz, int[][] data) {
		int j = 0;
		int[] line = new int[1];
		
		try {
			while (s.hasNextInt()) {
				if (j == line.length) {
					line = Arrays.copyOf(line, line.length * 2);
				}
				line[j] = Integer.parseUnsignedInt(s.nextInt(), 16);
				j++;
			}
	    } catch (IOException e) {
			System.err.println("Can't read" + e.getMessage());
		}
		if (siz + 1 >= data.length) {
			int[][] data1 = new int [data.length * 2][];
			System.arraycopy(data, 0, data1, 0, data.length);
			data = data1;
		}
		
		data[siz] = Arrays.copyOf(line, j);
		
		return data;
	}

	public static void main(String[] args) throws IOException{
		MyScanner scan = new MyScanner(System.in);

		int siz = 0;
		int j = 0;
		int[][] data = new int[1][];


		while (scan.hasNextLine()) {
			data = getLine(scan, siz, data);
			siz++;
			
		}
		scan.close();
		for (int i = siz - 1; i >= 0; i--) {
			for (j = data[i].length - 1; j >= 0; j--) {
				System.out.print(data[i][j] + " ");
			}
			if (i > 0 || data[i].length == 0) System.out.println();
		}

	} 

}
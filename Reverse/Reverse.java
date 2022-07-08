import java.io.IOException;
import java.util.Arrays;

public class Reverse {


	public static void main (String[] args) {

		int i = 0;
		int j = 0;
		int[][] data = new int[1000][1];
		int[][] data1;

		try{
			MyScanner scan = new MyScanner(System.in);

			while (scan.hasNextLine()) {
				i = 0;
				if (j + 10 >= data.length) {
					data1 = new int [data.length * 2][1];
					System.arraycopy(data, 0, data1, 0, data.length);
					data = data1;
				}
				while (scan.hasNextInt()) {
					if(i >= data[j].length) {
						data[j] = Arrays.copyOf(data[j], data[j].length * 2);
					}
					data[j][i] = Integer.parseInt(scan.nextInt());
					i++;
				}
				data[j] = Arrays.copyOf(data[j], i);
				j++;
			}
		} catch (IOException e) {
            System.err.println("Can't open the file" + e.getMessage());
        }   

		int last = i;
		int siz = j;

		i--;

		for (j = siz - 1; j >= 0; j--) {
			for (int k = data[j].length - 1; k >= 0; k--) {
				System.out.print(data[j][k] + " ");
			}
			if(siz > 0)
				System.out.println();
		}
		

	} 

}
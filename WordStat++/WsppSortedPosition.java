import java.io.*;
import java.util.*;
import java.lang.IndexOutOfBoundsException;
import java.lang.Object;
import java.nio.charset.StandardCharsets;

public class WsppSortedPosition {
    
    public static void main(String args[]) throws IOException {   

        MyScanner scan = new MyScanner(new FileInputStream(args[0]));
         
        Map <String, MyPairIntList> col = new TreeMap<>();

        int countWord = 0;
        int line = 0;
        try {
            while (scan.hasNextLine()) {
                countWord = 0;
                line++;
                while(scan.hasNextString()){
                    String s = scan.nextString().toLowerCase();
                    //System.err.print(s + " ");
                    if (!col.containsKey(s)) {
                        MyPairIntList m = new MyPairIntList();
                        col.put(s, m);
                        m.push(line, ++countWord);
                    } else {
                        col.get(s).push(line, ++countWord);
                    }
                }
                
            }
        } catch (IOException e) {
            System.err.println("Can't open the file" + e.getMessage());
        }  finally {
            scan.close();
        }

        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    "utf8"
                )
            );
            for (String s : col.keySet()) {
                writer.write(s + " " + col.get(s).size() + " ");
                for (int i = 0; i < col.get(s).size(); i++) {
                    writer.write(col.get(s).getFirst(i) + ":");
                    writer.write(Integer.toString(col.get(s).getSecond(i)));
                    if(i + 1 < col.get(s).size())
                       writer.write(" ");
                }
                writer.newLine();
            }
            
            writer.close();
        } catch (IOException e) {
            System.out.println("Can't write the file" + e.getMessage());

        } 
        
    }
}


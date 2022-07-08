import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class Wspp {
    
    public static void main(String[] args) throws IOException {

        MyScanner scan = new MyScanner(new FileInputStream(args[0]));
         
        Map <String, ArrayList<Integer>> col = new LinkedHashMap<>();

        int countWord = 0;
        int line = 0;
        try {
            while (scan.hasNextLine()) {
                line++;
                while(scan.hasNextString()){
                    String s = scan.nextString().toLowerCase();
                    if (col.get(s) == null) {
                        col.put(s, new ArrayList<Integer>());
                    }
                    col.get(s).add(++countWord);
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
                        StandardCharsets.UTF_8
                )
            );
            int j = 0;
            for (String s : col.keySet()) {
                writer.write(s + " " + col.get(s).size() + " ");
                for (int i = 0; i < col.get(s).size(); i++) {
                    writer.write(Integer.toString((Integer) col.get(s).get(i)));
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


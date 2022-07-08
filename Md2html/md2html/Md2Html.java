package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Arrays;

public class Md2Html {

	static Map<String, Integer> col = new LinkedHashMap<>();
	static Map<Integer, String> uncol = new LinkedHashMap<>();
	static Map<Integer, Integer> index = new LinkedHashMap<>();
	static StringBuilder text;
	static int[] add = new int[11];
	static int siz = 0;

	private static String specialsymb(int i, StringBuilder str) {
		if (str.charAt(i) == '<') {
			return "&lt;";
		} else if (str.charAt(i) == '>') {
			return "&gt;";
		} else if (str.charAt(i) == '&') {
			return "&amp;";
		} else {
			return String.valueOf(str.charAt(i));
		}
	}

	private static int isMarkdown(int i, StringBuilder str) {

		if (str.charAt(i) == '\\') {
			str.replace(i, i + 1, "");
			return 10;
		}

		if (str.length() - i >= 2) {
			if (col.containsKey(String.valueOf(str.charAt(i)) + str.charAt(i + 1))) {
				return col.get(String.valueOf(str.charAt(i)) + str.charAt(i + 1));
			}
		}

		if (col.containsKey(String.valueOf(str.charAt(i)))) {
			return col.get(String.valueOf(str.charAt(i)));
		}
		return 11;
	}

	public static void parse(StringBuilder text) {

		for (int i = 0; i < text.length(); i++) {
			int mark = isMarkdown(i, text);
			if (mark > 8) {
				continue;
			}
			if (add[mark] == -9) {
				add[mark] = i;
				i += (mark <= 4 ? 1 : 0);
				continue;
			}
			index.put(add[mark], mark);
			index.put(i, -mark);
			i += (mark <= 4 ? 1 : 0);
			add[mark] = -9;

		}
	}

	public static int getParagraphSize(String str) {
		int num = 0;
		while (num < str.length() && str.charAt(num) == '#') {
			num++;
		}
		if (num >= str.length() || str.charAt(num) != ' ')
			num = 0;
		return num;
	}

	public static void main(String[] args) {
		col.put("**", 1);
		col.put("__", 2);
		col.put("--", 3);
		col.put("''", 4);
		col.put("_", 5);
		col.put("*", 6);
		col.put("`", 7);

		uncol.put(1, "strong");
		uncol.put(2, "strong");
		uncol.put(3, "s");
		uncol.put(4, "q");
		uncol.put(5, "em");
		uncol.put(6, "em");
		uncol.put(7, "code");

		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(args[0]),
							StandardCharsets.UTF_8
					)
			);
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(args[1]),
							StandardCharsets.UTF_8
					)
			);

			while(true) {
				String line = reader.readLine();
				if(line == null){
					break;
				}
				text = new StringBuilder();
				do {
					if(text.length() > 0) {
						text.append("\n");
					}
					text.append(line);
					line = reader.readLine();
				} while(line != null && line.length() > 0);

				int paragSize = getParagraphSize(text.toString());

				for (int i = 0; i < text.length(); i++) {
					if (!specialsymb(i, text).equals(String.valueOf(text.charAt(i)))) {
						text.replace(i, i + 1, specialsymb(i, text));
					}
				}
				for (int i = 0; i < 8; i++) {
					add[i] = -9;
				}
				if(text.length() > 0){
					parse(text);
				} else {
					continue;
				}

				int add = 3;

				if (paragSize > 0) {
					text.replace(0, paragSize + 1, "");
					add -= paragSize + 1;
					text = new StringBuilder("<h" + paragSize + ">" + text + "</h" + paragSize + ">");
					add += Integer.toString(paragSize).length();
				} else {
					text = new StringBuilder("<p>" + text + "</p>");
				}
				for (int i = 0; i < text.length(); i++) {
					if (index.containsKey(i - add) && index.get(i - add) > 0) {
						writer.write('<' + uncol.get(index.get(i - add)) + '>');
						if (index.get(i - add) <= 4) {
							i++;
						}
						continue;
					}
					if (index.containsKey(i - add) && index.get(i - add) < 0) {
						writer.write("</" + uncol.get(-index.get(i - add)) + '>');
						if (-index.get(i - add) <= 4) {
							i++;
						}
						continue;
					}
					writer.write(text.charAt(i));
				}

				index.clear();
				writer.newLine();

				if(line == null) {
					break;
				}
			}
			reader.close();
			writer.close();
		} catch (IOException e) {
			System.err.println("Can't read the file" + e.getMessage());
		}
	}
}

import java.io.*;
import java.util.*;
import java.util.regex.*;

//to use compile using javac then run using java
//currently setup to take command line argument so for example call it with: java Spelling wordToCheck
//otherwise it will just run the test cases: win,ha, and sat
//taken from https://raelcunha.com/spell-correct/
//faster option if needed: https://github.com/raelgc/java-spell-checker/blob/master/Spelling.java

class Spelling {

	private final HashMap<String, Integer> nWords = new HashMap<String, Integer>();

	public Spelling(String file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		Pattern p = Pattern.compile("\\w+");
		for(String temp = ""; temp != null; temp = in.readLine()){
			Matcher m = p.matcher(temp.toLowerCase());
			while(m.find()) nWords.put((temp = m.group()), nWords.containsKey(temp) ? nWords.get(temp) + 1 : 1);
		}
		in.close();
	}

	private final ArrayList<String> edits(String word) {
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0; i < word.length(); ++i) result.add(word.substring(0, i) + word.substring(i+1));
		for(int i=0; i < word.length()-1; ++i) result.add(word.substring(0, i) + word.substring(i+1, i+2) + word.substring(i, i+1) + word.substring(i+2));
		for(int i=0; i < word.length(); ++i) for(char c='a'; c <= 'z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i+1));
		for(int i=0; i <= word.length(); ++i) for(char c='a'; c <= 'z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i));
		return result;
	}

	public final String correct(String word) {
		if(nWords.containsKey(word)) return word;
		ArrayList<String> list = edits(word);
		HashMap<Integer, String> candidates = new HashMap<Integer, String>();
		for(String s : list) if(nWords.containsKey(s)) candidates.put(nWords.get(s),s);
		if(candidates.size() > 0) return candidates.get(Collections.max(candidates.keySet()));
		for(String s : list) for(String w : edits(s)) if(nWords.containsKey(w)) candidates.put(nWords.get(w),w);
		return candidates.size() > 0 ? candidates.get(Collections.max(candidates.keySet())) : word;
	}

	public static void main(String args[]) throws IOException {
		//for testing with the cmdline
		if(args.length > 0) System.out.println((new Spelling("food.txt")).correct(args[0]));

		//just create an object of Spelling type and then pass it the word we want in a for loop
		Spelling tester = new Spelling("food.txt");
		System.out.println("Testing win: " + tester.correct("win"));
		System.out.println("Testing ha: " +tester.correct("ha"));
		System.out.println("Testing sat: " +tester.correct("sat"));
	}

}

package projecteuler.problem42;

import java.io.*;
import java.util.Arrays;

public class TriangleWords {

	/**
	 * Author: James Norcross
	 * Date:2/10/15
	 * Purpose: Project Euler Problem 42
	 * Description:  Reads a dictionary of words.  For each word it calculates a number which is the sum of the value of each
	 * of its characters (with a being 1 and z being 26).  It then determines whether the value of the word is an element of the 
	 * triangle sequence described by tn = (1/2)*n*(n+1)
	 * Have basically 3 choices to check whether value is triangle number
	 * 		i) create and array of triangle numbers (probably 30 calculations) and do a binary search for each word (2000 words)
	 * 		ii) do a calculation for each word to see whether its value is solution of tn = (1/2)*n*(n + 1) (so 2000 calculations)
	 * 		iii) create a boolean array to represent triangle sequence (30 calculations, 2000 look ups)
	 * It seems that iii) will be most efficient
	 */
	public static void main(String[] args) {
		
		String[] words = readWords();
		
		if(!words[0].equals("")) {
			
			int triangleCount = 0;
			
			boolean[] isTriangle = getIsTriangle();
			
			for(String word : words) {
				if(isTriangle[getTotal(word)])
					triangleCount++;
			}
			
			System.out.println("The number of triangle words in the list is " + triangleCount);
			
		}
		else {
			System.out.println("There was a problem reading in the words");
		}


	}
	
	// reads words from csv file and returns them in String[], if there is problem reading the file returns 
	// a String array with one element which is the empty string
	private static String[]	readWords() {
		
		String[] words = {""};
			
		try {
			BufferedReader input = new BufferedReader(new FileReader("p042_words.txt"));
			String wordString = input.readLine();
			input.close();
			wordString = wordString.substring(1, wordString.length()-2);		// remove first and last "
			words = wordString.split("\",\"");
		}
		catch(IOException e) {
			System.out.println("Problem reading data from file");
		}
		
		return words;
	}
	
	// generates a boolean array representing triangle sequence where tn = (1/2)*n*(n+1)	
	private static boolean[] getIsTriangle() {
		
		boolean[] isTriangle = new boolean[400];
		Arrays.fill(isTriangle, false);
		int triangle = 1, n = 1;		// first element of triangle sequence is 1
		
		while(triangle < 400) {
			isTriangle[triangle] = true;
			n++;
			triangle = (n*(n+1))/2;
		}
		
		return isTriangle;
	}
	
	// returns an integer total for a String which is the sum of the values of its characters (given A=1 and Z=26)
	private static int getTotal(String s) {
		int total = 0;
		
		for(int i = 0; i < s.length(); i++) {
			total += s.charAt(i) - 'A' + 1;
		}
		
		return total;
	}

}

// ECE 422C Pig Latin Translator
// Ben Park
// sp54837
// Spring 2025

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Translator 
{
	

	/**
	 * Opens the file specified in String filename, reads each line in it
	 * Invokes translate () on each line in the file, and prints out the  
	 * translated piglatin string.
	 * @param filename - the name of the file that needs to be read
	 */
	public static void processLinesInFile (String filename) 
	{ 

		Translator translator = new Translator(); 
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				System.out.println("The input string is: " + s);
                                String pigLatin = translator.translate(s);
				System.out.println("The Pig Latin version is: " + pigLatin);
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} 
                catch (IOException e) 
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Converts the inputString into piglatin based on rules specified 
	 * in your assignment write-up. 
	 * 
	 * @param inputString - the String that needs to be translated to
	 * 			piglatin
	 * @return the String object containing the piglatin translation of the
	 *         inputString    
	 */
	public String translate (String inputString) 
	{ 
		// modify the following code. Add/delete anything you want after this point.
		if(inputString.length()<=0){
			return inputString;
		}

		String outputString = "";
		String word = "";
		boolean rule6 = false;
        for (int i=0; i<inputString.length(); i++) {
			if(!isValid(inputString.charAt(i))){
				rule6=true;
			}

			if(rule6){
				if((inputString.charAt(i))==' '){
					outputString += word + inputString.charAt(i);
					word="";
					rule6=false;
				}
				else {
					word += inputString.charAt(i);
				}
			}
			else {
				if ((" ,\":;.!?-".indexOf(inputString.charAt(i)) == -1)) {
					word += inputString.charAt(i);
				} else {
					outputString += translateWord(word) + inputString.charAt(i);
					word = "";
				}
			}
        }
		if(!word.isEmpty()){
			if(rule6){
				outputString += word;
			}
			else{
				outputString += translateWord(word);
			}
		}
		return outputString;
	}

	/**
	 * helper method intended to translate individual words
	 *
	 * @param inputWord - the word to be translated to piglatin
	 *
	 * @return the String object containing the piglatin translation of the inputWord
	 *
	 */
	public String translateWord (String inputWord){
        if(inputWord == ""){
            return "";
        }
        String outputWord="";
		int vowelIndex=0;
		for(int i=0; i<inputWord.length(); i++){
			if(isVowel(inputWord.charAt(i)) || inputWord.charAt(i) == '’'){
				if(i!=0 || (inputWord.charAt(i)!='y' && inputWord.charAt(i)!='Y'))
					break;
			}
			vowelIndex++;
		}
		if(vowelIndex==0){
			outputWord = (inputWord + "yay");
		}
		else if(vowelIndex<inputWord.length()){
			outputWord = inputWord.substring(vowelIndex) + inputWord.substring(0,vowelIndex) + "ay";
		}
		else{
			outputWord = inputWord;
		}
        return outputWord;
	}

	/**
	 * helper method intended to check if a character is a vowel
	 *
	 * @param c - the character to be checked for if its a vowel
	 *
	 * @return a boolean indicating whether the character is a vowel
	 *
	 */
	public boolean isVowel (char c){
        return ("aeiouyAEIOUY'".indexOf(c) != -1);
    }

	/**
	 * helper method intended to check if a character is a letter
	 *
	 * @param c - the character to be checked for if its a letter
	 *
	 * @return a boolean indicating whether the character is a letter
	 *
	 */
	public boolean isValid (char c){
		return ((c>=97 && c<=122) || (c>=65 && c<=90) || " ',\":;.!?-".indexOf(c)!=-1);
	}

	public static void main (String[] args)
	{ 
		if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
    processLinesInFile(args[0]);
	}
}

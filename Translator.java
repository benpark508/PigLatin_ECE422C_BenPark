// ECE 422C Translator 
// <Your Name Here>
// <Your EID Here>
// Slip days used: 0
// Fall 2024 

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
		String[] words = inputString.split(" ");
        for (String word : words) {
			if(word.indexOf("-")!=-1){
				outputString += translateWord(word.substring(0,word.indexOf("-"))) + "-";
				outputString += translateWord(word.substring(word.indexOf("-")+1));
			}
			else {
				outputString += (" " + translateWord(word));
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
        char last = inputWord.charAt(inputWord.length()-1);
        String outputWord="";
        boolean insert = false;
        if(!isLetter(last)){
            inputWord=inputWord.substring(0,inputWord.length()-1);
            insert = true;
        }
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
        if(insert){
            outputWord += last;
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
        return ("aeiouyAEIOUY".indexOf(c) != -1);
    }

	/**
	 * helper method intended to check if a character is a letter
	 *
	 * @param c - the character to be checked for if its a letter
	 *
	 * @return a boolean indicating whether the character is a letter
	 *
	 */
	public boolean isLetter (char c){
		return (c>=97 && c<=122) || (c>=65 && c<=90);
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

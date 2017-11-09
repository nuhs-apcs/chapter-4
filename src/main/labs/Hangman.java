package labs;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hangman {
	
	private static List<String> words = null;
	
	/**
	 * Loads words from the words.txt file
	 * @throws IOException
	 */
	public static void loadWords() throws IOException {
		Path wordsPath = FileSystems.getDefault().getPath("words.txt");
		List<String> lines = Files.readAllLines(wordsPath);
		words = new ArrayList<String>();
		for (String line : lines) {
			words.addAll(Arrays.asList(line.split(" ")));
		}	
	}
	
	/**
	 * Gets a random word from the loaded words
	 * @return a random word
	 * @see Hangman#loadWords()
	 */
	public static String getRandomWord() {
		if (words == null) {
			throw new NullPointerException("Word list was uninitialized. Did you forget to call loadWords()");
		}
		int index = (int) (Math.random() * words.size());
		return words.get(index);
	}
	
	/**
	 * Returns a string with the supplied number of underscores
	 * @param length the number of underscores
	 * @return the string of underscores
	 */
	public static String getBlankString(int length) {
		String s = "";
		for (int i = 0; i < length; i++) {
			s += "_";
		}
		return s;
	}
	
	/**
	 * Returns the valid lower case letters that haven't been 
	 * guessed already
	 * @param guessedLetters the already guessed letters
	 * @return a string of valid letters
	 */
	public static String getValidLetters(String guessedLetters) {
		String validLetters = "";
		for (char i = 97; i <= 122; i++) {
			if (!guessedLetters.contains("" + i)) {
				validLetters += i;
			}
		}
		return validLetters;
	}
	
	public static void main(String[] args) throws IOException {
		loadWords();
		
		playHangman();
	}
	
	public static void playHangman() {
		System.out.println("Welcome to the game, Hangman!");
		// modify the line below to get a random word
		String word = "shape";
		System.out.println("I'm thinking of a word that is " + word.length() + " letters long");
		
		// modify the line below to handle variable length words
		String guess = "__";
		
		int numGuesses = 8;
		String guessedLetters = "";
		
		Scanner scan = new Scanner(System.in);
		
		while (numGuesses > 0) {
			System.out.println("-------------");
			System.out.println("You have " + numGuesses + " guesses left");
			// modify the line below to store all of the valid letters
			String validLetters = "abcd";
			System.out.println("Available letters: " + validLetters);
			System.out.print("Please guess a letter: ");
			// modify the line below to handle upper case inputs
			String letter = scan.nextLine();
			if (letter.length() == 1 && validLetters.contains(letter)) {
				if (word.contains(letter)) {
					for (int i = 0; i < word.length(); i++) {
						if (word.substring(i, i + 1).equals(letter)) {
							// add a line below to replace the underscore at position i
							// with the actual letter (hint: use substrings)
						}
					}
					if (guess.equals(word)) {
						System.out.println("You guessed the word: " + word);
						numGuesses = 0;
					} else; {
						System.out.println("Good guess: " + guess);
					}
				} else {
					System.out.println("Oops! That letter is not in my word");
					if (numGuesses < 1) {
						System.out.println("Sorry, you ran out of guesses");
						System.out.println("The word was " + word);
					}
					numGuesses -= 2;
				}
				guessedLetters += letter;
			} else {
				System.out.println("Invalid guess! Try again.");
				numGuesses--;
			}
		}
		
		scan.close();
		
	}

}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class WordCounter {
	//WordCounter takes a passage of text and counts how many times each word appears
	//then prints each word and its frequency in order (most frequent to least frequent)

	/**
	 * instance variables: 
	 * 		input: initial input String
	 * 		words: input split up into words
	 */
	private String input;
	private HashMap countToWordList;

	/**
	 * constructor
	 * 	initialize input with given String
	 * 	initialize empty HashMap
	 * @param input: String to be counted
	 */
	public WordCounter(String input) {
		this.input = input;
		this.countToWordList = new HashMap<Integer, ArrayList<String>>();
	}

	/**
	 * main method with sample passage
	 * @param args
	 */
	public static void main(String[] args) {
		WordCounter test = new WordCounter("From the moment the first immigrants arrived on these shores, "
				+ "generations of parents have worked hard and sacrificed whatever is necessary so that their "
				+ "children could have the same chances they had; or the chances they never had. Because while "
				+ "we could never ensure that our children would be rich or successful; while we could never "
				+ "be positive that they would do better than their parents, America is about making it possible "
				+ "to give them the chance. To give every child the opportunity to try. Education is still the "
				+ "foundation of this opportunity. And the most basic building block that holds that foundation "
				+ "together is still reading. At the dawn of the 21st century, in a world where knowledge truly "
				+ "is power and literacy is the skill that unlocks the gates of opportunity and success, we all "
				+ "have a responsibility as parents and librarians, educators and citizens, to instill in our "
				+ "children a love of reading so that we can give them the chance to fulfill their dreams.");	
		test.splitString(test);
		test.print();
	}

	/**
	 * split input String into individual words
	 * @param input original String
	 * @return sorted HashMap of input's words
	 */
	public HashMap<Integer, ArrayList<String>> splitString(WordCounter input) {
		StringTokenizer split = new StringTokenizer(this.input, " ");
		String tempWord = "";
		ArrayList<String> tempList = new ArrayList<String>();
		while (split.hasMoreTokens()) {
			tempWord = split.nextToken();
			tempWord = this.removePunc(tempWord);
			this.sortString(tempWord);
		}
		return this.countToWordList;
	}

	/**
	 * helper method that determines which ArrayList to put tempWord into
	 * @param tempWord word to be sorted
	 */
	public void sortString(String tempWord) {
		ArrayList<String> tempList = new ArrayList<String>();
		//enter first word of input into ArrayList<String> and maps to int key 1
		if (this.countToWordList.size()==0) {
			tempList.add(tempWord);
			this.countToWordList.put(1, tempList);
			return;
		}
		else {
			for (int freq=this.countToWordList.size(); freq>0; freq--) {
				tempList = (ArrayList<String>) this.countToWordList.get(freq);
				//search for tempWord in each existing ArrayList<String> in countToWordList
				if (tempList!=null && tempList.contains(tempWord)) {
					//if found, remove tempWord from current list
					tempList.remove(tempWord);
					this.countToWordList.put(freq, tempList);
					//check if countToWordList has an ArrayList<String> mapped to frequency+1
					if (freq<this.countToWordList.size()) {
						//add tempWord to ArrayList<String> mapped to frequency+1
						tempList = (ArrayList<String>) this.countToWordList.get(freq+1);
						tempList.add(tempWord);
						this.countToWordList.put(freq+1, tempList);
					}
					else if (freq==this.countToWordList.size()) {
						//add tempWord to a new ArrayList<String> and map that to frequency+1
						tempList = new ArrayList<String>();
						tempList.add(tempWord);
						this.countToWordList.put(freq+1, tempList);
					}
					return;
				}
				else if (freq==1) {
					//if countToWordList does not contain tempWord, add it to the ArrayList<String> mapped to int 1 
					tempList = (ArrayList<String>) this.countToWordList.get(1);
					tempList.add(tempWord);
					this.countToWordList.put(1, tempList);
					return;
				}
			}
		}
	}

	/**
	 * removes punctuation from words and makes all lowercase
	 * @param tempWord word in input text
	 * @return word without punctuation/capitals
	 */
	public String removePunc(String tempWord) {
		int length = 0;
		tempWord = tempWord.toLowerCase();
		if (tempWord.contains(".") || tempWord.contains(",") || tempWord.contains(";")) {
			length = tempWord.length();
			tempWord = tempWord.substring(0, length-1);
		}
		return tempWord;
	}

	/**
	 * prints out sorted input text
	 */
	public void print() {
		ArrayList<String> tempList = new ArrayList<String>();
		while (!this.countToWordList.isEmpty()) {
			for (int freq = this.countToWordList.size(); freq>0; freq--) {
				tempList = (ArrayList<String>) this.countToWordList.get(freq);
				for (String word : tempList) {
					System.out.println(word + " " + freq);
				}
				this.countToWordList.remove(freq, tempList);
			}
		}

	}

}

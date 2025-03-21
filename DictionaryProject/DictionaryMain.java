package DictionaryProject;

import java.util.*;
public class DictionaryMain {
        //private static HashMap<String, String> letterToWord = new HashMap<>();
        //private static HashMap<String, String> wordToDefinition = new HashMap<>();


    public static void main(String[] args) {
        DictionaryFile book = new DictionaryFile();
        Scanner input = new Scanner(System.in);
        System.out.println("This is a dictionary for Computer Science terms or resources. Select a letter A-T");
        
        //put your file path here, something is currently wrong.
        book.dictionaryFile("C:\\Users\\derri\\OneDrive\\Desktop\\CS240\\DictionaryProject\\Defintions.txt");

        System.out.println("Enter a letter to see what words are under it: ");
        String userInput = input.next();

        if(userInput.length() > 0){
            char viewLetter = userInput.charAt(0);
            System.out.println("The words under it are: " + book.getWord(viewLetter)); 
        }else{
            System.out.println("A character must be entered. ");
        }
        //System.out.println("Enter a letter to see what words are under it.");
        //System.out.println("The words under it are: " + book.getWord(view));
        
        System.out.println("Enter a word that was under the letter you picked: ");
        String viewWord = input.nextLine();
        System.out.println("The defintion for the word is: " + book.getDefintion(viewWord));

       input.close();
    }


    public static void menu(){
        
    }
}

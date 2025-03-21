package DictionaryProject;

import java.io.*;
import java.util.HashMap;


public class DictionaryFile {
        private static HashMap<Character, String> letterToWord = new HashMap<>();
        private static HashMap<String, String> wordToDefinition = new HashMap<>();

      public void dictionaryFile(String filePath){
        
        File file = new File(filePath);//create a new file that can take in any file path inputted into it 

        //BufferedReader is used for efficient reading of lines in txt files.
        //FileReader is useed to create a character stream from a file.
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){//if the line read is not null
                String[] parts = line.split(":");
             
             //The parts will never be greater than 2 so give the program enough room to see parts indexed at 0 and 1
             if(parts.length >= 2){
              //if the part at the 0 index == 1 then it must be a letter, because words have more than one character so they wouldn't be equal to one when taking the full length.
                if(parts[0].length() == 1){
                    letterToWord.put(parts[0].charAt(0), parts[1]);
                //take the word and definition.
                }else{
                    wordToDefinition.put(parts[0], parts[1]);
                }   

            }
            }
            
        }catch(IOException e){
            System.out.printf("Error while loading file. %s%n", e.getMessage());
        }

    }

    //The purpose of this method is simply for user input in the main
    public String getWord(char letter){
        return letterToWord.get(letter);
    }

    //The purpose of this method is simply for user input in the main
    public String getDefintion(String word){
        return wordToDefinition.get(word);
    }


}



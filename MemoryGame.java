package CS240Assignments;

import java.util.*;

//This class plays a memory card game
//However, it is not the most efficent you could say because for each card you have to enter the row and column. 
public class MemoryGame{
    
    private static char[][] board;
  
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //define variables for use in main
        Stack<Integer> deck = new Stack<>();
        board = gameBoard();//create a variable that equals the method so that we can print the board with the characters
        int[][] hiddenValues = new int[4][5];

        decks(deck);
        placeCards(deck, hiddenValues);

        //This block of code is for testing purposes and is for displaying all of the cards meant for matching to get through the game quicker.
        /*for(int[] value : hiddenValues){
            System.out.println(Arrays.toString(value));
        }*/
    
        
        while(true){
         printBoard(board);// call board
         //Put the flipped method in the if loop so that once the game is over 
         if(flipped(hiddenValues, board, input)){
            System.out.println("All pairs matched you won the game");
         break;
         
       
        }
      
        input.close();
     }
    }

    //made into a method so that it can't get modified accidentally
    public static char[][] gameBoard(){
        char[][] board = {{' ',' ',' ',' ',' '},
                          {' ',' ',' ',' ',' '},
                          {' ',' ',' ',' ',' '},
                          {' ',' ',' ',' ',' '}};
        return board;
    }

    //Logic behind printing the face of the board.
    public static void printBoard(char[][] board){    
        for(int i = 0; i < board.length;i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == ' '){
                    System.out.print("|_|");
                }else{
                    System.out.print("|" + board[i][j] + "|");
                }
            
            }

            System.out.println();
        }
    }

    //Create a deck of 10 cards with another 10 exactly and assign them for a deck to play the matching game.
    //Using a stack for the deck works because the stack can contain all of the cards with no issues and then we can use the LIFO nature later to place the cards.
    public static Stack<Integer> decks(Stack<Integer> deck){
        final int numOfCards = 20;//Number of cards in the deck
        List<Integer> tempDeck = new LinkedList<>();//an arraylist to store the cards temporarily 

        //The point of this for loop is to take the amount of cards and divide it by two 
        //so that we have 10 cards and then we call it again to duplicate the same cards
        for(int i = 0; i < numOfCards / 2; i++){
            tempDeck.add(i);
            tempDeck.add(i);
        }
        
        Collections.shuffle(tempDeck);//shuffle tempDeck for randomizing the cards every game.

        //Iterate through tempDeck and assign put the cards that were in tempDeck into deck with the stacks push method
        for(Integer temp : tempDeck){
            deck.push(temp);
        }

        return deck;
    }

    //loop through hiddenValues rows and columns and then pop each card off the deck and assign it to a spot on hiddenValues grid.
    public static int[][] placeCards(Stack<Integer> deck, int[][] hiddenValues){
        for(int i = 0; i < hiddenValues.length; i++){
            for(int j = 0; j < hiddenValues[i].length; j++){
                if(deck.isEmpty()){
                    throw new IllegalArgumentException("Deck is empty");
                }else{
                    hiddenValues[i][j] = deck.pop();
                    
                }

                
            }
        }
        return hiddenValues;
    }

    //This method helps set bounds for determining if the card position entered to be a valid card position
    public static boolean isValidPosition(int row, int col, char[][] board){
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length && board[row][col] == ' ';
    }

   

    //This method handles the flipping of the cards logic
    public static boolean flipped(int[][] hiddenValues, char[][] board, Scanner input){
        int matchedPairs = 0;
        //Takes the amount of rows times it by the amount of columns and divides it by 2 to amount the total pairs 
        final int totalPairs = (board.length * board[0].length) / 2;


        while(matchedPairs < totalPairs){
        
          System.out.println("Flip the first card, enter row (1-4) and col (1-5): ");
          int rNum1 = input.nextInt() - 1;
          int cNum1 = input.nextInt() - 1;

          System.out.println("Flip the second card, enter row (1-4) and col (1-5)");
          int rNum2 = input.nextInt() - 1;
          int cNum2 = input.nextInt() - 1;

          if(rNum1 == rNum2 && cNum1 == cNum2){
            System.out.println("You can not choose the same card. Please pick again");
            continue;
          } 
          
          if(!isValidPosition(rNum1, cNum1, board) || !isValidPosition(rNum2, cNum2, board)){
            System.out.println("Not a valid position on the board");
            continue;
          }
          if(hiddenValues[rNum1][cNum1] != hiddenValues[rNum2][cNum2]){
            System.out.println("The cards do not match. Try again");
            continue;
          }

          if(hiddenValues[rNum1][cNum1] == hiddenValues[rNum2][cNum2]){
            System.out.println("The cards match");
            char value1 = Character.forDigit(hiddenValues[rNum1][cNum1], 10);//converts the integers to the character representation.
            char value2 = Character.forDigit(hiddenValues[rNum2][cNum2], 10);//converts the integers to the character representation.
   
            board[rNum1][cNum1] = value1;
            board[rNum2][cNum2] = value2;

            matchedPairs++;

            printBoard(board);
            
        }

        if(matchedPairs == totalPairs){
            return true;
       }
    }
    return false;
  }
}//end of class
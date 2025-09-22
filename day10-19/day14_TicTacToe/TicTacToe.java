package day14_TicTacToe;

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] Board = new char[5][5];        // 5x5 board (with separators)
        Scanner in = new Scanner(System.in);
        Random random = new Random();

        initBoard(Board);                       // Initialize empty board with symbols
        printIndexTable();                      // Print move index reference


        boolean gameOver = false;               // Track when game ends
        while (!gameOver){                      // Game loop until break
            
            printBoard(Board);                  // Show current board               
            promptUserMove(in, Board);          // User makes a move
            if (winner(Board, 'X')){            // Check if user wins
                printBoard(Board);
                System.out.println("\nCongratulations! You win!"); 
                break; 
            }
            
            if (isBoardFull(Board)) {           // Check tie before bot moves
                printBoard(Board);
                System.out.println("\nIt's a draw! No more moves left.");
                break;
            }

            promptBotMove(random,Board);        // Bot makes a move
            if (winner(Board, 'O')) {           // Check if bot wins
                printBoard(Board);
                System.out.println("\nThe computer wins this round. Better luck next time!"); 
                break; 
            }  
        }    
    }
    // Shapes the Board format with spaces, |, -, +
    public static void initBoard(char[][] b){
             for(int r = 0; r < b.length; r++){
                for(int c = 0; c < b[r].length; c++){
                    
                    boolean isPlayCell = (r % 2 == 0) && (c % 2 == 0);      // X/O goes here
                    boolean isVertSep  = (r % 2 == 0) && (c % 2 == 1);      // Vertical bar
                    boolean isHorizSep = (r % 2 == 1) && (c % 2 == 0);      // Horizontal dash
                    boolean isCross    = (r % 2 == 1) && (c % 2 == 1);      // Intersections

                    if (isPlayCell) {
                        b[r][c] = ' ';      // Empty playable cell (later X/O)
                    } else if (isVertSep) {
                        b[r][c] = '|';      // Vertical bar between cells
                    } else if (isHorizSep) {
                        b[r][c] = '-';      // Horizontal dash between rows
                    } else if (isCross) {
                        b[r][c] = '+';      // Intersection
                    }               
            }   
        }   
    } 
    // Display instructions and index mapping
    public static void printIndexTable() {
        System.out.println("Welcome to tic tac toe");
        System.out.println("User is X");
        System.out.println("Computer is O");
        System.out.println("Below is the index tables for your moves: \n");
        System.out.println(" 1 | 2 | 3 ");
        System.out.println("---+---+---");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println("---+---+---");
        System.out.println(" 7 | 8 | 9 \n"); 
    }
    // Print the current board state
    public static void printBoard(char[][] b) {  
        System.out.printf(" %c | %c | %c %n", b[0][0], b[0][2], b[0][4]);    // Row 0
        System.out.println("---+---+---");
        System.out.printf(" %c | %c | %c %n", b[2][0], b[2][2], b[2][4]);   // Row 2
        System.out.println("---+---+---");
        System.out.printf(" %c | %c | %c %n", b[4][0], b[4][2], b[4][4]);   // Row 4
    } 
    // User input for moves
    public static void promptUserMove(Scanner in, char[][] b) {
        boolean attemptMove = false;
        while (!attemptMove){    // Keep trying until valid move
            System.out.print("\nUser, enter your move (1-9): ");
            if (in.hasNextByte()) {                // Check if input is a number
                byte move = in.nextByte();
                if (move >= 1 && move <= 9) {        // Only allow 1–9
                    byte[] userRC = mapToBoard((byte) move); 
                    attemptMove = placeMove(b, userRC[0], userRC[1], 'X');  
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 9.");
                }
            } else {                                 // If not a number
                System.out.println("Invalid input. Numbers only!");
                in.next(); // clear invalid token
            }
            System.out.println(); // spacing
        }    
    }
    // Bot makes a random move
    public static void promptBotMove(Random random, char[][] b){
         String[] botMessages = {
        "Let me see...",
        "Calculating my next move...",
        "I’ve got a good one...",
        "Rolling the dice on this move..."
    };
    // Pick a random message
    int msgIndex = random.nextInt(botMessages.length);
    System.out.println(botMessages[msgIndex]);
    System.out.println();

        boolean attemptMove = false;
        while(!attemptMove){
            byte num = (byte)(random.nextInt(9) + 1);   // Random 1–9
            byte[] botRC = mapToBoard(num);             // Convert to row/col
            attemptMove = placeMove(b, botRC[0], botRC[1], 'O');    // Place 'O'

        }
    }
    // Map 1–9 index to (row, col) coordinates
    public static byte[] mapToBoard(byte choice){
         // choice is 1-9
        byte[][] mapping = {
            {0,0}, {0,2}, {0,4},    // Top row
            {2,0}, {2,2}, {2,4},    // Middle row
            {4,0}, {4,2}, {4,4}     // Bottom row
        };
        return mapping[choice - 1];     // subtract 1 because array index starts at 0
    }   
    // Place X or O on the board
    public static boolean placeMove(char[][] b, int r, int c, char symbol){
        if(b[r][c] == ' '){     // If cell is empty
            b[r][c] = symbol;   // Place the move
        } else if(b[r][c] != ' ' && symbol == 'X'){
            System.out.println("Invalid move. Cell is occupied.");  // User tried filled cell
            return false;
        } else {
            return false;   // Bot also retries if cell filled
        }
        return true;
         
        
    }
    // Check win condition for a symbol
    public static boolean winner(char[][] b, char symbol){
        // rows
        for (int r = 0; r <= 4; r += 2) {
            if (b[r][0] == symbol && b[r][2] == symbol && b[r][4] == symbol) {
                return true;
            }
        }
        // cols
        for (int c = 0; c <= 4; c += 2) {
            if (b[0][c] == symbol && b[2][c] == symbol && b[4][c] == symbol) {
                return true;
            }
        }
        // diagonals
        if (b[0][0] == symbol && b[2][2] == symbol && b[4][4] == symbol) {
            return true;
        }
        if (b[0][4] == symbol && b[2][2] == symbol && b[4][0] == symbol) {
            return true;
        }

        return false;
    }
    // Check if board is full (tie)
    public static boolean isBoardFull(char[][] b) { 
        for(int r = 0; r <= 4; r += 2){         // Only playable cells
            for(int c = 0; c <= 4; c += 2){ 
                if(b[r][c] == ' '){             // Found empty spot
                    return false; 
                }
            } 
        } 
        return true;
    } 
}
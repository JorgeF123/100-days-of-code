
// Import the Scanner class from java.util package to read user input
import java.util.Scanner; 
        
public class day11_numberStatistics {

    public static void main(String[] args) {

        // Declare variables
        int userInput;  // number of values the user wants to enter
        double[] userValues;  // array to store the numbers
        double userFloatInput,biggest,smallest,sum,average;

        // Scanner object for user input
        Scanner in = new Scanner(System.in);
        
        // Ask user how many numbers they want to input
        System.out.print("How many numbers would you like to enter: ");        
        userInput = in.nextInt();
        
        
        // Input validation: must be positive
        while (userInput <= 0){
            
             System.out.println("Error. Size must be positive. Try again");
             System.out.print("How many numbers would you like to enter: "); 
             userInput = in.nextInt();
                     
        }
        // Create array with user-specified size
        userValues = new double[userInput];
        
        // Prompt user to enter numbers one by one
        for (int i = 1; i <= userInput; i++){
            System.out.print("Enter number " + i + ": ");
            userFloatInput = in.nextDouble();
            userValues[i-1] = userFloatInput;   // store input in array
            
        }

        // Initialize biggest, smallest, and sum
        biggest = userValues[0];
        smallest = userValues[0];
        sum = 0.0;
        
        // Loop through the array
        for (double value : userValues){
            System.out.print(value + "  ");  // print numbers as entered
            
            sum += value;  // accumulate sum    
            
            // Update biggest number if current value is larger
            if (value > biggest){
                biggest = value;

            // Update smallest number if current value is smaller    
            } else if (value < smallest){
                smallest = value;
            
            }
        
        }

        in.close();  // Close scanner   
        average = sum / userValues.length;   // Compute average
        
        // Display results
        System.out.println("\nThe biggest number is " + biggest);
        System.out.println("The smallest number is " + smallest);
        System.out.printf("The sum of numbers is %.2f\n", sum);
        System.out.printf("The average of the number %.2f\n", average);
      
    }
}
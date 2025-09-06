def main():

    # Ask the user for input
    user_input = input('Enter a non-negative integer to calculate its factorial: ')

    # Validate input: must be digits only and non-negative
    while not user_input.isdigit() or int(user_input) < 0:
        
        if not user_input.isdigit():
            # Case if user typed letters/symbols
            print("Invalid input. Please enter digits only.")

        else:
             # Case if user typed a negative number
            print("Please enter a non-negative integer.")

        # Ask again until the input is valid
        user_input = input("Enter a non-negative integer to calculate its factorial: ")

    num = int(user_input)   # Convert the valid string input into an integer  
    result = factorial(num)  # Compute factorial using recursion

    print(f"Factorial of {num} is {result}")  # Display result

def factorial(n: int):
   
    """
    Recursive function to calculate factorial.
    Formula: n! = n × (n-1)!
    Base case: 0! = 1
    """

    if n == 0:  
        return 1
    
    recurse = factorial(n - 1)  # Recursive call: factorial of (n-1)
    result = n * recurse    # Multiply current n by factorial(n-1)

    return result

if __name__ == '__main__':
    main()  # Run the program starting at main()
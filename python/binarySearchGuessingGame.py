import python.rockPaperScissorsGame as function        # Importing input validation function from a previous project

CHOICES = ('h','l','c')


def main():

    # Starting bounds for binary search
    tries = 0
    low = 1
    high = 100
    

    print('')
    print('Binary Search Guessing Game')
    
    user_number = input('Enter a number between 1-100: ')       # So user can look back at the number there thinking about 
    
    # Makes sure the user entered a number
    while not function.is_valid_integer(user_number):   
        user_number = input('Enter a number between 1-100')

     # Main binary search loop
    while low <= high:
     
        mid = low + (high - low) // 2       # Calculate the middle of the current range
        tries += 1

        print(f"My guess is: {mid}")  
        print('user feedback: higher / lower / correct')
        
        
        user_feedback = input('Enter h/l/c: ').lower().strip()       # Get feedback from the user about the guess
        
        # Keep asking until the player gives valid feedback
        while user_feedback not in CHOICES:
            user_feedback = input('ERROR: Must enter h/l/c: ').lower().strip()

        # If guess is correct, announce win and end game
        if user_feedback == 'c':
            print(f"I guessed your number {mid} in {tries} tries!")
            break
        
        # If guess should be higher, adjust lower bound
        elif user_feedback == 'h':
            low = mid + 1

        # If guess should be lower, adjust upper bound
        elif user_feedback == 'l':
            high = mid - 1
    else:
        print("No possible numbers left, maybe inconsistent feedback?")          # This runs if the loop ends without a break





if __name__ == "__main__":
    main()
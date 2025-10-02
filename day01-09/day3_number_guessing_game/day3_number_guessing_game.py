import random

# Constants for difficulty modes
EASY = 1
MEDIUM = 2
HARD = 3

# Constants for number of tries per difficulty
TWENTY_TRIES = 20
TEN_TRIES = 10
FIVE_TRIES = 5

def main():

    # Generate a random number between 1 and 100
    random_num = random.randint(1,100)
    total = 1 # Keeps track of number of guesses

    # Intro + mode selection prompt
    print('Number Guessing Game')
    print('')
    print('----------------------------------------------------------------')
    print('Easy mode: 20 tries | Medium mode: 10 tries | Hard mode: 5 tries')
    print('----------------------------------------------------------------')
    print('')
    print('Enter 1 for Easy mode ')
    print('Enter 2 for Medium mode ')
    print('Enter 3 for Hard mode ')

    logic_start = True

    while logic_start:

        try:
            game_mode = int(input('Enter your mode: '))  # Ask user for game mode
            
            # Check if it's valid (1, 2, or 3)
            if game_mode >= 1 and game_mode <= 3:
                logic_start = False

            else:
                print('ERROR: Must be between 1 and 3.')

        except ValueError:
            print('ERROR: Must be a valid number.')


    # First user guess
    user_input = user_input_logic(random_num)

    # Run the guessing game loop
    game_logic(user_input,random_num,total,game_mode)


 
def user_input_logic(random_num):
    # Handles user guessing logic and input validation

    logic_starter = True

    while logic_starter:

        try:
            user_input = int(input('Guess a number between 1–100: ')) # Ask user to guess a number

            if user_input >= 1 and user_input <= 100:
                logic_starter = False  

            else:
                print('ERROR: Must be between 1 and 100.')

        except ValueError:
            print('ERROR: Must be a valid number.')


    # Give feedback on the guess
    if user_input < random_num:
        print()
        print('Too low')
        
    elif user_input > random_num:
        print()
        print('Too high')
      
    return user_input # Return the valid guess

    

def game_logic(user_input,random_num,total,game_mode):
    # Main loop that checks guesses and gives hints/loss messages

    while user_input != random_num:
        
        total += 1      # Increase number of attempts    
        
        # Ask for next guess
        user_input = user_input_logic(random_num)

        # Show a hint after 3 total attempts
        if total == 3:

            if random_num % 2 == 0:
                print()
                print("HINT: number is even.")

            else:
                print()
                print("HINT: number is odd.")
        
        # Check for loss based on difficulty level
        elif game_mode == EASY and total == TWENTY_TRIES:
            print('You lost')
            break

        elif game_mode == MEDIUM and total == TEN_TRIES:
            print('You lost')
            break
        
        elif game_mode == HARD and total == FIVE_TRIES:
            print('You lost')
            break
    
    # If the loop player won the game will end and congratulate the user
    else:
        print()
        print(f'Congratulates! You guessed the number {random_num} in {total} tries!')

# Run the game
main()
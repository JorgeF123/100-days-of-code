import random

# Menu option constants
PLAY_ROUND = 1
VIEW_SCOREBOARD = 2
QUIT = 3

# Global counters to store total results across multiple games
TOTAL_PLAYER_WINS = 0 
TOTAL_CPU_WINS = 0
TOTAL_TIES = 0

# Maps user input variations to the actual choice
NORMALIZE = {
    'r': 'rock', 
    'rock': 'rock',
    'p': 'paper',
    'paper': 'paper',
    's': 'scissors',
    'scissors': 'scissors'
}

# Shows which choice beats which rock beats scissors, etc
BEATS = {  
    'rock': 'scissors',
    'paper': 'rock',
    'scissors': 'paper'
}

# All valid choices for random CPU selection
CHOICES = ('rock','paper','scissors')

# Emojis for each choice
ICONS = {'rock': '🪨', 'paper': '📄', 'scissors': '✂️'}

def main():

    user_input = 0
    while user_input != QUIT:
        user_input = menu()

        if user_input == PLAY_ROUND:
            player_game_input()

        elif user_input == VIEW_SCOREBOARD:
            view_scoreboard()

        elif user_input == QUIT:
            print('Program over...')


def menu():

        

    while True:     
        
            print('~~~~~~~~~~~~~~~~~~~~~~~~~~~~')
            print('    Enter 1 to play round')
            print(' Enter 2 to view scoreboard')
            print('       Enter 3 to quit')
            print('~~~~~~~~~~~~~~~~~~~~~~~~~~~~')

            print()

            user_input = input('Enter 1 | 2 | 3 : ')    # get user_input as a string
            
            # keep asking until is_valid_integer(user_input) returns True
            while not is_valid_integer(user_input):

                user_input = input("ERROR ENTER OPTIONS ARE 1 | 2 | 3: ")
            
            user_input = int(user_input)    # Convert to int only after it passes validation

            if user_input >= 1 and user_input <= 3:     # Makes sure it’s between 1 and 3
    
                break  # If valid, break loop

            # If not in range, print the error and repeat the outer loop
            else:
                
                print()
                print('ERROR MUST ENTER A NUMBER BETWEEN 1 AND 3')

    
    return user_input # return the choice if valid

      
def player_game_input():

    global TOTAL_PLAYER_WINS, TOTAL_CPU_WINS, TOTAL_TIES
    
    # Scores for the current session
    player_wins = 0
    computer_wins = 0
    ties = 0

    print('')   
    print('╔═══════════════════════════╗')
    print('║  Rock, Paper, Scissors!   ║')
    print('╚═══════════════════════════╝')
    print('')
    print('Enter rock or r for rock')
    print('Enter paper or p for paper')
    print('Enter scissors or s for Scissors')
    print('')
    
    # Ask how many rounds to play
    number_of_games = input('how many rounds do you want to play enter in numbers: ')
    print('')
    
     # Validate that it’s a positive integer
    while not is_valid_integer(number_of_games) or int(number_of_games) <= 0:

        number_of_games = input('ERROR MUST ENTER A POSITIVE INTEGER AND MUST BE GREATER THEN 0: ')
        print('')

    number_of_games = int(number_of_games)          # Convert to int for looping

    # Play the chosen number of rounds
    for _ in range(number_of_games):
    
        player_choice = input('Enter choice: ').lower().strip()      # Get and normalize player choice
        print('')

        # Validate that the choice is one of r/p/s or rock/paper/scissors
        while player_choice not in NORMALIZE:

            player_choice = input('ERROR MUST ENTER rock,paper,scissors or r/p/s: ').lower().strip()
            print('')

        player = NORMALIZE[player_choice]        # Normalize the input (turn r into rock, etc.)
        computer = random.choice(CHOICES)        # CPU picks randomly from choices

         # Print choices as emojis
        print(f"You: {emojis(player)} | CPU: {emojis(computer)}")
       
        # Decide round result
        if player == computer:
            print('Tie.')
            ties += 1
            
        elif BEATS[player] == computer:
            print('You win this round.')
            player_wins += 1
                
        else:
            print('You lose this round.')
            computer_wins += 1
    
    # Add session results to total results
    TOTAL_PLAYER_WINS += player_wins
    TOTAL_CPU_WINS += computer_wins
    TOTAL_TIES += ties
    
    # Show final results for this session
    print(f"\nFinal: 🏆 You {player_wins} | 🤖 CPU {computer_wins} | 🤝 Ties {ties}")
    print('')


def view_scoreboard():
    print('')
    print('=== Scoreboard (All Sessions This Run) ===')
    print(f'🏆 You: {TOTAL_PLAYER_WINS} | 🤖 CPU: {TOTAL_CPU_WINS} | 🤝 Ties: {TOTAL_TIES}')
    print('')
    

def is_valid_integer(input_string):
   
    try:
        int(input_string)
        return True
    
    except ValueError:
        print("Invalid input. Please enter a valid integer.")
        return False
    

def emojis(choice: str) -> str:
    return ICONS[choice]


if __name__ == "__main__":
    main()

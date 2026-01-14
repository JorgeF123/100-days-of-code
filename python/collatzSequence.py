import python.rockPaperScissorsGame as function        # Reuse input validation function

tries = 0       # Track how many steps the sequence takes

# Prompt the user for input
user_input = (input('Enter a positive integer: ')).strip()

# Validate: must be a valid integer string AND greater than 0
while not function.is_valid_integer(user_input) or int(user_input) <= 0:
    user_input = input('ERROR MUST ENTER A POSITIVE INTEGER AND MUST BE GREATER THEN 0').strip()

# Convert to integer after validation
verified = int(user_input)      
seq = [verified]

# Collatz loop
while True:

    # If odd > 3n + 1
    if verified % 2:             
        verified = 3 * verified + 1

    # If even → n/2
    else:                           
        verified = verified // 2

    tries += 1      # Count this step
    seq.append(verified)        # Store new value in the sequence

    # Stop once the sequence reaches 1
    if verified == 1:   
        break

# Print results
print("Sequence:", " > ".join(map(str, seq)))
print("Steps:", tries)



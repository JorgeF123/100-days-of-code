# Ask the user to input the size of the multiplication table
N = int(input('Enter a number: '))

# Outer loop: iterate over each row of the table
for row in range(N):
    total = row + 1        # The current row's base number (starts from 1)
    print(f'{total:<5}',end='')     # Print the row label, left-aligned

    # Inner loop: calculate and print products for this row
    for col in range(N):
        pretest = col + 2      # Multipliers start from 2 instead of 1
        if pretest <= N:       # Only print if multiplier is within the table size
            print(f'{total * pretest:<5}',end='')        # Print the product, left-aligned
    print()        # Move to the next line after finishing one row

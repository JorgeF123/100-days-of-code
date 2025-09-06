# Ask the user how many numbers they want to input
count = int(input("How many numbers will you enter?: "))
print()

# Validate that the count is greater than 0
while count <= 0:

    print('ERROR MUST ENTER NUMBER GREATER THEN 0')
    count = int(input("How many numbers will you enter?: "))
    print()

# Initialize lists to store even and odd numbers
even_list = []
odd_list = []

# Initialize strings to display the even and odd numbers as formatted output
cleaned_even = ''
cleaned_odd = ''

# Loop to collect numbers from the user
for i in range(count):

    # Ask for a number with the current input number label
    user_numbers = int(input(f'Enter number {i + 1}: '))

    # Validate that the number is greater than 0
    while user_numbers <= 0:

        print('ERROR MUST ENTER NUMBER GREATER THEN 0')
        user_numbers = int(input(f'Enter number {i + 1}: '))
        print()
    
    # Check if the number is even
    if user_numbers % 2 == 0:
        even_list.append(user_numbers)          # Add to even list
        cleaned_even += str(user_numbers) + ' '     # Append to display string

    else:
        odd_list.append(user_numbers)           # Add to odd list
        cleaned_odd += str(user_numbers) + ' '      # Append to display string

# Count how many even and odd numbers were entered
even_count = len(even_list)
odd_count = len(odd_list)

print()
print(f'Total numbers entered: {count}')
print(f'Even numbers you entered: {cleaned_even} \nOdd numbers you entered: {cleaned_odd}')
print(f'Even number count: {even_count} \nOdd number count: {odd_count}') 
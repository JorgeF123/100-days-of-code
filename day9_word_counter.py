import string       # import string module contains string.punctuation

# Ask the user for a sentence and convert it to lowercase
sentence = input('Enter sentence: ').lower()

# Remove punctuation marks from the sentence
for p in string.punctuation:
    sentence = sentence.replace(p, "")

words = sentence.split()        # Split the cleaned sentence into a list of words

myDict = {}         # Create an empty dictionary to store word counts
# Loop through each word in the list
for key in words:
    myDict[key] = words.count(key)      # Count how many times the word appears in the list

# Print the final dictionary with words as keys and counts as values
print("Dictionary Items: ",  myDict)

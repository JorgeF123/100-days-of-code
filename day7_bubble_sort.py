def bubble(list_a):      # Define a function that takes a list and sorts it using Bubble Sort

    indexing_length = len(list_a) - 1       # Get the last index we need to compare
    sorted = False      # A flag to track whether the list is sorted or not

    # Keep looping until no swaps are made in a full pass
    while not sorted:      
       
        sorted = True       # Assume the list is sorted at the start of each pass
        
        # Go through each element up to the second-to-last index
        for i in range(0,indexing_length):
            if list_a[i] > list_a[i+1]:     # If the current element is greater than the next one
                sorted = False              # then it's not sorted yet

                list_a[i], list_a[i+1] = list_a[i+1], list_a[i]     # Swap the two elements



    return list_a   # When no swaps are made, the list is sorted and returned

test = [13,53,14,7,42,64,1,2,3,6]

print(f'unsorted list: {test}')
print('sorted list:',bubble(test))
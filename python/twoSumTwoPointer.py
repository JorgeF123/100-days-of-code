arr_left = [1, 2, 4, 6, 8, 11, 15]

target = 10

print(arr_left)
print(f'target:{target}')

i = 0
j = len(arr_left)-1     # start with leftmost and rightmost indexes

while i < j:

    # calculate sum of current left and right values
    s = arr_left[i] + arr_left[j] 

    # if the sum matches the target, print and stop
    if s == target:
        print(f'{arr_left[i]} + {arr_left[j]} = {target}')
        break

     # if sum is greater than target, move right pointer left
    if s > target: 
        j -= 1  

   # if sum is less than target, move left pointer right
    if s < target:
        i += 1 
else:
    print("No pair found")
    
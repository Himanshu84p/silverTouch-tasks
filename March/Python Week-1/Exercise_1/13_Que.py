# Print odd numbers between 1 to 10 in reverse order using for loop.


num_list = []
for i in range(1,11):
    if(i % 2 == 0):
        continue
    else:
        num_list.append(i)

reversed_list = num_list[::-1]
print("Odd numbers list is",reversed_list,"\n")
# Print odd numbers between 1 to 10 in reverse order using while.

count = int(0)
num_list = []
while(count <= 10):
    if(count % 2 == 0):
        count += 1
    else:
        num_list.append(count)
        count += 1

reversed_list = num_list[::-1]
print("Odd numbers list is",reversed_list,"\n")
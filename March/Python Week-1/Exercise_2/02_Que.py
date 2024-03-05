# Get a list of 1 to 20 then remove elements from list to get only even elements.

num_list = []
num = 1
while num <= 20:
    num_list.append(num)
    num += 1

num -= 2
while num >= 0:
    num_list.remove(num)
    num -= 2

print("Even number list is", num_list)
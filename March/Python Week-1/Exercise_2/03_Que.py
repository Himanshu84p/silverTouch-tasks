#Get a list of 1 to 8 and then 4 to 10. Get the common elements from both the list in a new list.

num_list1 = []
num_list2 = []
result_list = []

for i in range(1,9):
    num_list1.append(i)
for i in range(4,11):
    num_list2.append(i)

for num1 in num_list1:
    for num2 in num_list2:
        if(num1 == num2):
            result_list.append(num1)

print("Common elements are", result_list)



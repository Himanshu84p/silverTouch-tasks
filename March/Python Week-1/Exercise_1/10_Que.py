#biggest and smallest number using input and print

num1 = int(input("Enter the first number: \n"))
num2 = int(input("Enter the second number: \n"))
num3 = int(input("Enter the third number: \n"))

biggest_num = num1
smallest_num = num2

#biggest
if(num2 > biggest_num):
    biggest_num = num2
if(num3 > biggest_num):
    biggest_num = num3

#smallest
if(num2 < smallest_num):
    smallest_num = num2
if(num3 < smallest_num):
    smallest_num = num3

print("Biggest number is :\n",biggest_num)
print("Smallest number is :\n",smallest_num)

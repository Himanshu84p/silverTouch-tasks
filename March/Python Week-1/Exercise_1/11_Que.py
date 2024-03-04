# using 'input' and pass all the three parameters as a single input

input_str = input("Enter three numbers separated by spaces: ")

numbers = [float(num_str) for num_str in input_str.split(',')]

biggest_num = numbers[0]
smallest_num = numbers[0]

for num in numbers[1:]:
    if num > biggest_num:
        biggest_num = num

for num in numbers[1:]:
    if num < smallest_num:
        smallest_num = num

print("Biggest number is :\n",biggest_num)
print("Smallest number is :\n",smallest_num)
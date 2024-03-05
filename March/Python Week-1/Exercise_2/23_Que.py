# Get all the elements excluding first and last elements from list, tuple and string

num_list = [1,2,5,6,7,77,35,234]
num_tuple = (1,2,5,6,10,45,234,65)
string_1 = "Python is amazing language and easy to learn"
splitted_string = string_1.split(" ")

print("Last el and first el of list is excluded", num_list[1:-1])
print("Last el and first el of tuple is excluded", num_tuple[1:-1])
print("Last el and first el of string is excluded", splitted_string[1:-1])
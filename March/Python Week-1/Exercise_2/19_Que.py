#-------------------------converting the list to other-----------------------------
my_list = [1, 2, 3, 4, 5]

# Convert list to tuple
my_tuple = tuple(my_list)

# Convert list to set
my_set = set(my_list)

# Convert list to dictionary (assuming list of tuples)
my_dict = dict((i, i) for i in my_list)

print("List:", my_list)
print("Tuple:", my_tuple)
print("Set:", my_set)
print("Dictionary:", my_dict)

# -----------------------Converting tuple to the other--------------------------
my_tuple = (1, 2, 3, 4, 5)

# Convert tuple to list
my_list = list(my_tuple)

# Convert tuple to set
my_set = set(my_tuple)

# Convert tuple to dictionary (assuming list of tuples)
my_dict = dict((i, i) for i in my_tuple)

print("Tuple:", my_tuple)
print("List:", my_list)
print("Set:", my_set)
print("Dictionary:", my_dict)

# -----------------------Converting set to the other--------------------------
my_set = {1, 2, 3, 4, 5}

# Convert set to list
my_list = list(my_set)

# Convert set to tuple
my_tuple = tuple(my_set)

# Convert set to dictionary (assuming list of tuples)
my_dict = dict((i, i) for i in my_set)

print("Set:", my_set)
print("List:", my_list)
print("Tuple:", my_tuple)
print("Dictionary:", my_dict)


# -----------------------Converting dictionary to the other--------------------------

my_dict = {'a': 1, 'b': 2, 'c': 3, 'd': 4, 'e': 5}

# Convert dictionary to list of keys, values, or items
keys_list = list(my_dict.keys())
values_list = list(my_dict.values())
items_list = list(my_dict.items())

# Convert dictionary to tuple of keys, values, or items
keys_tuple = tuple(my_dict.keys())
values_tuple = tuple(my_dict.values())
items_tuple = tuple(my_dict.items())

# Convert dictionary to set of keys, values, or items
keys_set = set(my_dict.keys())
values_set = set(my_dict.values())
items_set = set(my_dict.items())

print("Dictionary:", my_dict)
print("List of keys:", keys_list)
print("List of values:", values_list)
print("List of items:", items_list)
print("Tuple of keys:", keys_tuple)
print("Tuple of values:", values_tuple)
print("Tuple of items:", items_tuple)
print("Set of keys:", keys_set)
print("Set of values:", values_set)
print("Set of items:", items_set)



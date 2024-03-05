# Generate a dictionary {1:1,2:1,3:1,4:1,...,10:1} in one line using dictionary's method

ratio_dict = {i:1 for i in range(1,11)}

print("Ratio dictionary is", ratio_dict)

#que 8 printing the all keys and values

for i in ratio_dict:
    print("Key is ", i, "Value is ", ratio_dict[i])
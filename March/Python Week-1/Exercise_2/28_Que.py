# Create a dictionary using range() as following. {'a':1, 'b':2, 'c':3, 'd':4, 'e':5....'y':25,
#  'z':26}. The code needs to be in one line.

ratio_dict = {chr(i):i-96 for i in range(97,97+26) }
print(ratio_dict)

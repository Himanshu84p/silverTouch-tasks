import random

arr = [random.choice([0, 1]) for _ in range(10)]

if(all(num == 0 for num in arr)):
    print("None")
elif(all(num == 1 for num in arr)):
    print("All")
else:
    print("Any")
import random
arr = [random.randint(1,100) for _ in range(10)]

print(arr)
print(list(filter(lambda a : a%2 == 0,arr)))
print(list(filter(lambda a : a%2 != 0,arr)))
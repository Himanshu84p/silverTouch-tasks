import random
arr1 = [random.randint(1,9) for num in range(10)]
arr2 = [random.randint(1,9) for num in range(10)]


print(arr1)
print(arr2)


print(list(map(lambda x,y: x*y ,arr1,arr2)))
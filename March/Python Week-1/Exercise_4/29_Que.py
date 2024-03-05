import random
def bubble_sort(arr):
    n = len(arr)
    for i in range(n):
        for j in range(0, n-i-1):
            if arr[j] > arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]



arr = [random.randint(1,100) for num in range(25)]

print("before sorted -> ",arr)
bubble_sort(arr)
print("after sorted -> ",arr)
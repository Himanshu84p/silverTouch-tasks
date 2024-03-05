import random

random_numbers = [random.randint(1, 100) for _ in range(10)]

random_numbers.sort(reverse=True)
print("Sorted list in descending order using sort():", random_numbers)
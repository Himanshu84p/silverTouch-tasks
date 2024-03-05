import random

def generateRandomNumbers():
    for i in range(1,11):
        ans = random.randint(1,100)
        yield ans


randome_generator = generateRandomNumbers()

for item in randome_generator:
    print(item,end=" ")
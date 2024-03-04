#  Print odd numbers  between 1 to 10 using continue in both for and while loop.

print("using for loop:\n")
for i in range(1,11):
    if i % 2 == 0:
        continue
    print(i)

print("Using while loop")
num = 1
while num <= 10:
    if(num % 2 == 0):
        num += 1
        continue
    print(num)
    num += 1


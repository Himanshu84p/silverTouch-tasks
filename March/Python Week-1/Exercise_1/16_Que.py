#  Write a function with recursion to give the factorial of a number.

def factorial(n):
    if n==0 or n==1:
        return 1
    else:
        return n * factorial(n-1)
    
num = int(input("Enter the number to calculate its factorial: \n"))
result = factorial(num)
print("Factorial of ", num, "is",result)
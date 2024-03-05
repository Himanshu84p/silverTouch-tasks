import random

def add_value(func):
    def wrapper(*args, **kwargs):
        random_value1 = random.randint(1, 10)
        random_value2 = random.randint(1, 10)
        result = func(random_value1, random_value2, *args, **kwargs)
        return result
    return wrapper

@add_value
def addition(x, y):
    return x + y

@add_value
def subtraction(x, y):
    return x - y

@add_value
def multiplication(x, y):
    return x * y

@add_value
def division(x, y):
    if y == 0:
        return "Error: Division by zero"
    return x / y

print("Addition Result is:", addition())
print("Subtraction Result is:", subtraction())
print("Multiplication Result is:", multiplication())
print("Division Result is:", division())

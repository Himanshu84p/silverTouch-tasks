def decorator_multi(a, b):
    def decorator(func):
        def wrapper(x, y):
            result = func(x * a, y * b)
            return result
        return wrapper
    return decorator

@decorator_multi(2, 3)
def operation(x, y):
    return x + y

result = operation(4, 5)
print("Result is:", result)

def decorator_sq(func):
    def wrapper(*args, **kwargs):
        result = func(*args, **kwargs)
        return result ** 2
    return wrapper

def decorator_inc_10(func):
    def wrapper(*args, **kwargs):
        result = func(*args, **kwargs)
        return result + 10
    return wrapper

@decorator_sq
@decorator_inc_10
def operation_num(x):
    return x

result = operation_num(5)
print("Result is:", result) 

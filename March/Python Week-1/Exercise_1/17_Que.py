def perform_operation(option, *operands):
    def addition(a, b, c):
        return a + b + c
    
    def subtraction(a, b, c):
        return a - b - c
    
    def multiplication(a, b, c):
        return a * b * c
    
    def division(a, b):
        if b != 0:
            return a / b
        else:
            return "Cannot divide by zero"
    
    def exponent(a, b):
        return a ** b
    
    def floor_division(a, b):
        if b != 0:
            return a // b
        else:
            return "Cannot divide by zero"
    
    if option == 1:
        result = addition(*operands)
    elif option == 2:
        result = subtraction(*operands)
    elif option == 3:
        result = multiplication(*operands)
    elif option == 4:
        result = division(*operands)
    elif option == 5:
        result = exponent(*operands)
    elif option == 6:
        result = floor_division(*operands)
    else:
        result = "Invalid argument"
    
    return result


option = int(input("Enter option (1=Addition, 2=Subtraction, 3=Multiplication, 4=Division, 5=Exponent, 6=Floor Division): "))
if option in [1, 2, 3]:
    operands = [float(input(f"Enter operand {i+1}: ")) for i in range(3)]
elif option in [4, 5, 6]:
    operands = [float(input(f"Enter operand {i+1}: ")) for i in range(2)]
else:
    operands = []
print("Result:", perform_operation(option, *operands))





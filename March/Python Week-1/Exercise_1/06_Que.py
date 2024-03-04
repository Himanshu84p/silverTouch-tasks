#Arithmetic operators
num1 = int(input("Enter first number: \n"))
num2 = int(input("Enter Second number: \n"))

operator = int(input("Enter operation 1.Addition 2.Subtraction 3.Multiplication 4.Division 5.Modulus 6.Exponentiation 7.Floor division\n"))

def perform_opr(opr):
    if(opr == 1):
        print("Addition of ",num1," and ",num2," is",num1 + num2)
    elif(opr == 2):
        print("Subtraction of ",num1," and ",num2," is",num1 - num2)
    elif(opr == 3):
        print("Multiplication of ",num1," and ",num2," is",num1 * num2)
    elif(opr== 4):
        print("Division of ",num1," and ",num2," is",num1 / num2)
    elif(opr== 5):
        print("Modulus of ",num1," and ",num2," is",num1 % num2)
    elif(opr == 6):
        print("Exponentiation of ",num1," power ",num2," is",num1 ** num2)
    elif(opr == 7):
        print("Floor division of ",num1," and ",num2," is",num1 // num2)

perform_opr(operator)


def addition(num1,num2):
    if(isinstance(num1,int) and isinstance(num2,int)):
        return num1+num2
    elif(isinstance(num1,float) and isinstance(num2,float)):
        return num1+num2
    else:
        raise Exception("vlaue should be int or float")
    

print(addition(1,2))
print(addition(1.4,2.5))
print(addition(1.4,2))
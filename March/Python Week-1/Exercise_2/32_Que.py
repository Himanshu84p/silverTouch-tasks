#  Create a function for string that will check whether a string is having the first 
# letter as Capital and not anyother letter is capital

def check_string_case(str):
    if(str[0].isupper()):
        return str[1:].islower()
    else:
        return False
        
greet = "Welcome to the python community"
print(check_string_case(greet))
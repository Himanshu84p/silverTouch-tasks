def process_arguments(arg1, arg2, *args):
    if len(args) == 0 :
        return arg1 * arg2
    elif len(args) == 1 :
        print("All 3 parameters:", arg1, arg2, args[0])
    elif len(args) == 2 :
        return arg1 + arg2 + args[0] + args[1]
    elif len(args) == 3 :
        result_mandatory = arg1 * arg2
        arg_keywords = args[0] * args[1] * args[2]
        return result_mandatory + arg_keywords
    else:
        return "Invalid number of arguments"


print(process_arguments(2, 3))  
print(process_arguments(2, 3, 4))  
print(process_arguments(2, 3, 4, 5)) 
print(process_arguments(2, 3, 4, 5, 6))  

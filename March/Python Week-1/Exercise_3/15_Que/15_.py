import pickle


def dumpVariables(filename, *variables):
    try:
        with open(filename,"wb") as file:
            pickle.dump(variables,file)
        print("variables have been dumped to file")
    except Exception as e:
        print("Exception:",e)




filename = "Exercise_3/15_Que/my_variables.data"
var1 = 10
var2 = "Hello, Himanshu!"
var3 = [1, 2, 3, 4, 5]
var4 = {"name": "Himanshu", "age": 21}

dumpVariables(filename, var1, var2, var3, var4)

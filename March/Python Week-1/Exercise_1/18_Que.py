def firstFun():
    print("Calling first function")

def secondFun():
    print("Calling first from second")
    firstFun()

secondFun()
class ListLengthError(Exception):
    pass

def checkListLength(arr):
    if(len(arr) < 5):
        raise ListLengthError("Minimum length of the list should be 5")


try:
    arr = [1,2,4]
    checkListLength(arr)
except ListLengthError as e:
    print("List length error:",e)
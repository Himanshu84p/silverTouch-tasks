file  = open("Exercise_3/14_Que/test_file.data","wb")

def readData(filename):
    try:
        with open(filename,"rb") as file:
            data = file.read()
            print("binary data", data)
    except FileNotFoundError:
        print("File not found.")
    except Exception as e:
        print("Error:", e)

def writeData(filename):
    data = bytes([0, 1, 2, 3, 4, 255])
    try:
        with open(filename, "wb") as file:
            file.write(data)
        print("Binary data has been written to", filename)
    except Exception as e:
        print("Error:", e)


filename = "test_file.data"
writeData(filename)
readData(filename)
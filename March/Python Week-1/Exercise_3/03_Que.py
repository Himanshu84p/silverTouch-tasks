try:
    result = 10 / 2
except ZeroDivisionError as e:
    print("ZeroDivisionError:", e)
else:
    print("there is no zero division error")

try:
    print(float(5))
except Exception as e:
    print("TypeError:", e)
else:
    print("there is no Type error")

try:
    my_list = [1, 2, 3]
    print(my_list[2])
except IndexError as e:
    print("IndexError:", e)
else:
    print("there is no Index error")

try:
    variable = "himanshu"
    print(variable)
except NameError as e:
    print("NameError:", e)
else:
    print("there is no Name error")

try:
    dict = {
        "name":"himanshu"
    }
    print(dict["name"])
except Exception as e:
    print("Exceptin:",e)
else:
    print("there is no Exception error")
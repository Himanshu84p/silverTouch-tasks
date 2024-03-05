try:
    result = 10 / 0
except ZeroDivisionError as e:
    print("ZeroDivisionError:", e)

try:
    print(float("himanshu"))
except Exception as e:
    print("TypeError:", e)

try:
    my_list = [1, 2, 3]
    print(my_list[3])
except IndexError as e:
    print("IndexError:", e)

try:
    print(variable)
except NameError as e:
    print("NameError:", e)

try:
    dict = {
        "name":"himanshu"
    }
    print(dict["HIMANSHU"])
except Exception as e:
    print("Exceptin:",e)
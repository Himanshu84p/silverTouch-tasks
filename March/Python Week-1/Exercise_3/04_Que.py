try:
    result = 10 / 0
except ZeroDivisionError as e:
    print("ZeroDivisionError:", e)
finally:
    print("this will always execute")

try:
    print(float("himanshu"))
except Exception as e:
    print("TypeError:", e)
finally:
    print("this will always execute")

try:
    my_list = [1, 2, 3]
    print(my_list[3])
except IndexError as e:
    print("IndexError:", e)
finally:
    print("this will always execute")

try:
    print(variable)
except NameError as e:
    print("NameError:", e)
finally:
    print("this will always execute")

try:
    dict = {
        "name":"himanshu"
    }
    print(dict["NAME"])
except Exception as e:
    print("Exceptin:",e)
finally:
    print("this will always execute")
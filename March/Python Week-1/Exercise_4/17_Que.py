class Person:
    def __init__(self,name,age) -> None:
        self.name = name
        self.age = age


himanshu = Person("Himanshu",21)

print(hasattr(himanshu, 'name'))

print(getattr(himanshu, 'age'))

setattr(himanshu, 'name', "Himanshu")
print(himanshu.name) 

delattr(himanshu, 'age')
print(hasattr(himanshu, 'age'))
    
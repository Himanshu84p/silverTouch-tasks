class Parent:
    def __init__(self):
        print("Constructor of the Parent class called.")
    
    def __del__(self):
        print("Destructor of the Parent class called.")
    
    def parent_method1(self):
        print("This is method 1 of the parent class.")
    
    def parent_method2(self):
        print("This is method 2 of the parent class.")

class Child(Parent):
    def __init__(self):
        super().__init__()
        print("Constructor of the Child class called.")
    
    def __del__(self):
        print("Destructor of the Child class called.")
    
    def child_method(self):
        print("This is a method of the child class.")

parent_obj = Parent()
child_obj = Child()
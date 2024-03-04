class Parent:
    def method(self):
        print("This is the method from the Parent class.")

class Child(Parent):
    def method(self):
        print("This is the method from the Child class.")

parent_obj = Parent()
child_obj = Child()

parent_obj.method()  
child_obj.method()   

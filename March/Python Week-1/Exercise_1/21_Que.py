class parent:
    def parent_method_1(self):
        print("this is parent's method 1")
    def parent_method_2(self):
        print("this is parent's method 2")

class child(parent):
    def child_method(self):
        print("This is the child method")

parent_obj = parent()
child_obj = child()

#using parent object accessing the parent and child method
parent_obj.parent_method_1()
parent_obj.parent_method_2()
# parent_obj.child_method() --> can not access the child's method

#using child's object to accessing the parent and child method
child_obj.parent_method_1()
child_obj.parent_method_2()
child_obj.child_method()

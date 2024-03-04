class MyClass:
    def __init__(self, a=None, b=None):
        if a is not None and b is not None:
            self.a = a
            self.b = b
        elif a is not None:
            self.a = a
            self.b = 0
        else:
            self.a = 0
            self.b = 0
    
    def add(self, x, y=None):
        if y is not None:
            return x + y
        else:
            return self.a + x


obj1 = MyClass()          
obj2 = MyClass(5)         
obj3 = MyClass(5, 10)     

print("Add method:")
print(obj1.add(5))         
print(obj2.add(5))         
print(obj3.add(5))        
print(obj3.add(5, 15))   
 
   

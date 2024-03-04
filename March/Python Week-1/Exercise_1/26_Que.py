class my_parent_class():
    x=10;
    y=10;
    def __init__(self,x=None,y=None):
        self.a = x
        self.b = y
        if(x==None):
            self.a = self.x
        if(y==None):
            self.b = self.y
            

    def add(self):
        self.res1 = self.a + self.b;
    def sub(self):
        self.res2 = self.a - self.b;
    def print_result(self):
        print("Res1: {0}".format(self.res1))
        print("Res2: {0}".format(self.res2))

    def __del__(self):
        print("Parent Destructor called")

#first object
parentObj1 = my_parent_class()
parentObj1.add()
parentObj1.sub()
parentObj1.print_result()

#second object
parentObj2 = my_parent_class(65)
parentObj2.add()
parentObj2.sub()
parentObj2.print_result()

#third object
parentObj3 = my_parent_class(124,874)
parentObj3.add()
parentObj3.sub()
parentObj3.print_result()


class my_child_class(my_parent_class):
    z=10;
    def __init__(self, x=None, y=None, z=None):
        super().__init__(x, y)
        self.c = z;
        if(z==None):
            self.c = self.z;
    def add(self):
        self.res1 = self.a+self.b+self.c;
    def sub(self):
        self.res2 = self.a-self.b-self.c;

    def __del__(self):
        print("Child Destructor called")
childClassObj = my_child_class();
childClassObj.add()
childClassObj.sub()
childClassObj.print_result()
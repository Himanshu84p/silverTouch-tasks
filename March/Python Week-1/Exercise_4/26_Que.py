class MyClass:
    globalVariable = "This is global variable"

    def __init__(self) -> None:
        self.instanceVariable = "This is an instance variable"

    def method(self):
        localVariable = "This is a local variable"
        
        globalAttributes = globals().keys()
        
        localAttributes = locals().keys()
        
        print("Global attributes accessible in this method:", globalAttributes)
        print("Local attributes accessible in this method:", localAttributes)


obj = MyClass()

obj.method()

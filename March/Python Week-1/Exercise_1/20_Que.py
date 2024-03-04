class MyClass:
    def __init__(self, var1, var2):
        self.var1 = var1
        self.var2 = var2
    
    def perform_operation(self, num):
        result = self.var1 * self.var2 * num
        return result
    
    def print_result(self):
        print("Result:", self.result)

obj = MyClass(5, 10)

obj.result = obj.perform_operation(3)

obj.print_result()

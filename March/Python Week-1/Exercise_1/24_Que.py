#-----------------------------multiple inheritance--------------------------------------------------

class Pet:
    def play(self):
        print("The pet is playing.")

class Animal:
    def eat(self):
        print("The animal is eating.")


class Dog(Pet, Animal):
    def bark(self):
        print("The dog is barking.")

dog = Dog()

dog.eat()   
dog.bark() 
dog.play()

#---------------------------multilevel inheritance-----------------------------------

class Grandparent:
    def grandparent_method(self):
        print("This is a method from Grandparent class.")

class Parent(Grandparent):
    def parent_method(self):
        print("This is a method from Parent class.")

class Child(Parent):
    def child_method(self):
        print("This is a method from Child class.")

class Grandchild(Child):
    def grandchild_method(self):
        print("This is a method from Grandchild class.")

grandchild_obj = Grandchild()


grandchild_obj.grandparent_method()  
grandchild_obj.parent_method()       
grandchild_obj.child_method()       
grandchild_obj.grandchild_method()   


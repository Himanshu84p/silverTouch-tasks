# How can we have two variables refering to a single list, set and dictionary

#for list
list_1 = [1,2,3,54,6,5,35]

list_2 = list_1
list_2.append(100)

print("\nlist 1 and list 2 is refferring the same list reference we updated list 2 then list 1 is also updating")
print("list 1 is",list_1)
print("list 2 is",list_2)

#for set
set_1 = {2,34,52,45}
set_2 = set_1

set_2.add(100)
print("\nset 1 and set 2 is refferring the same set reference we updated set 2 then set 1 is also updating")
print("set 1 is",set_1)
print("set 2 is",set_2)

#for dictionary

dict_1 = {'name': "Himanshu",'mobile':8989080388}
dict_2 = dict_1

dict_2.update({'age':21})
print("\ndictionary 1 and dictionary 2 is refferring the same dictionary reference we updated dictionary 2 then dictionary 1 is also updating")

print("Dictionary 1 is", dict_1)
print("Dictionary 2 is", dict_2)

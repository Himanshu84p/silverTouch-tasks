arr1 = ['a','b','c','d','e','f']
arr2 = [1,2,3,4,5,6]
dict = {}
list(map(lambda x,y: dict.update({x:y}) ,arr1,arr2))
print(dict)
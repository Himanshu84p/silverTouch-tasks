# There are two lists [1,2,3,4,5], [4,5,6,7] get a list from these two lists [1,2,3,6,7]

list_1 = [1,2,3,4,5]
list_2 = [4,5,6,7]

set_list_1 = set(list_1)
set_list_2 = set(list_2)

result_list = list(set_list_1.symmetric_difference(set_list_2))

print(result_list)
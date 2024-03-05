# There are two lists [1,2,3,4,5,6,7,8,9,10],[11,12,13,14,15,16,17,18,19,20]. Get a 
# third list from these two lists as [12,14,16,18,20,22,24,26,28,30]

list_1 = [1,2,3,4,5,6,7,8,9,10]
list_2 = [11,12,13,14,15,16,17,18,19,20]

result_list = []

for i in range(0,10):
    result_list.append(list_1[i] +list_2[i])
print(result_list)
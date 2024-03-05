set1 = {1, 2, 3, 4, 5}

set2 = {4, 5, 6, 7, 8}

# all set operation
union_set = set1.union(set2)
intersection_set = set1.intersection(set2)
difference_set1 = set1.difference(set2)
difference_set2 = set2.difference(set1)
symmetric_difference_set = set1.symmetric_difference(set2)
subset_check = set1.issubset(set2)
superset_check = set1.issuperset(set2)

el_in_set1 = 7 in set1
el_not_in_set1 = 7 not in set1

print("Union of two sets", union_set)
print("intersection of two set is", intersection_set)
print("difference set1 with set2", difference_set1)
print("difference set2 with set1", difference_set2)
print("symmetric difference in two sets", symmetric_difference_set)
print("Is set 1 is subset of set 2", subset_check)
print("Is set 1 is superset of set2 ", superset_check)
print("Is 4 in set 1 ", el_in_set1)
print("Is 7 is in set1", el_not_in_set1)
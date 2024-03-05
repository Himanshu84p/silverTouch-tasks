#x=(1,2,3,4,5), y=(4,5,6,7). Combine these two tuples in a single tuple ignoring the common elements.

x = (1,2,3,4,5)
y = (4,5,6,7)

set_x = set(x)
set_y = set(y)

diff_x = set_x.difference(set_y)
diff_y = set_y.difference(set_x)

res_tuple = tuple(diff_x.union(diff_y))

print("tuple is", res_tuple)
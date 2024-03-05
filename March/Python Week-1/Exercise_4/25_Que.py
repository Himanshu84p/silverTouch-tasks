import random
import string

random_characters = ''.join(random.choice(string.ascii_letters + string.digits) for _ in range(10))

print(random_characters)

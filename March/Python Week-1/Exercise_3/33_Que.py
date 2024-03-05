import re

def is_valid_url(url):
    pattern = r"^(https?|ftp):\/\/[^\s/$.?#].[^\s]*$"
    return re.match(pattern, url) is not None

urls = [
    "http://www.himanshu.com",
    "https://silver.com/path",
    "ftp://ftp.kevin.com",
    "invalidurl",
]

for url in urls:
    print(f"{url}: {is_valid_url(url)}")

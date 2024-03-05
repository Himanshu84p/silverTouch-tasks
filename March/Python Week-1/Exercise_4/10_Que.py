def hexadecimal_to_binary(hexadecimal):
    binary = bin(int(hexadecimal, 16))[2:]
    return binary

def hexadecimal_to_decimal(hexadecimal):
    decimal = int(hexadecimal, 16)
    return decimal

def hexadecimal_to_octal(hexadecimal):
    decimal = int(hexadecimal, 16)
    octal = oct(decimal)[2:]
    return octal

hexadecimal_number = '10'
binary = hexadecimal_to_binary(hexadecimal_number)
decimal = hexadecimal_to_decimal(hexadecimal_number)
octal = hexadecimal_to_octal(hexadecimal_number)

print(f"Hexadecimal: {hexadecimal_number}")
print(f"Binary: {binary}")
print(f"Decimal: {decimal}")
print(f"Octal: {octal}")

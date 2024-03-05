def primeGenerator():
    primes = []
    for i in range(2,101):
        is_prime = True
        for prime in primes:
            if i%prime == 0:
                is_prime = False
                break
        if is_prime:
            primes.append(i)
            yield i


prime_generator = primeGenerator()
for prime in prime_generator:
    print(prime, end=" ")
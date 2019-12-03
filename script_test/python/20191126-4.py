def isPrime(n):
    if n <= 1:
        return False
    if n == 2:
        return True
    for i in range(2, int(n/2)+1):
        if n%i == 0:
            return False
    return True

def isSpecialPrime(n):
    if not isPrime(n):
        return False
    num = str(n)
    for s in num:
        if not isPrime(int(s)):
            return False
    return True


if __name__ == "__main__":
    result = []
    for num in range(2, 1000):
        if isSpecialPrime(num):
            result.append(num)

    # for num in range(2, 100):
    #     if isPrime(num):
    #         result.append(num)
    print(','.join(list(map(str, result))))
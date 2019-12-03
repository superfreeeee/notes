# string = '13,121,99,3443,7878,19,878'
def isReverse(string):
    reverse_string = string[::-1]
    return string == reverse_string

def classify(strings):
    reverseStrings = []
    not_reverseStrings = []
    for string in strings:
        if isReverse(string):
            reverseStrings.append(string)
        else:
            not_reverseStrings.append(string)
    return reverseStrings, not_reverseStrings

if __name__ == "__main__":
    strings = input().split(',')
    reverseStrings, not_reverseStrings = classify(strings)
    print(list(map(int, reverseStrings)))
    print(list(map(int, not_reverseStrings)))

# 13,121,99,3443,7878,19,878
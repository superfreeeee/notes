def process(nums):
    nums = list(map(int, nums.split(',')))
    nums = list(set(nums))
    nums.sort()
    # print(nums)
    return nums

def printResult(nums):
    results = [(nums[0], nums[1])]
    for i in range(1, len(nums)-1):
        if (nums[i+1] - nums[i]) < results[0][1] - results[0][0]:
            results = [(nums[i], nums[i+1])]
        elif (nums[i+1] - nums[i]) == results[0][1] - results[0][0]:
            results.append((nums[i], nums[i+1]))
    for result in results:
        print(result)

if __name__ == "__main__":
    nums = process(input())
    printResult(nums)
    pass



# 45,12,45,11,32,5,6
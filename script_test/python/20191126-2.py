def split_and_findLongest(statements):
    names = []
    jobs = []
    for statement in statements:
        temp = statement.split(' ')
        name = temp[0]
        job = temp[3]

        names.append(name)
        jobs.append(job)
    
    statements = list(zip(names, jobs))
    statements = sorted(statements, key=lambda x:x[0])
    print(statements)

    target = statements[0]
    for item in statements:
        if len(item[1]) > len(target[1]):
            target = item
    print(target)

if __name__ == "__main__":
    statements = input().split(', ')
    statements[-1] = statements[-1][:-1]
    split_and_findLongest(statements)

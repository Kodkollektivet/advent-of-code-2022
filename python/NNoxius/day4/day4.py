import os

path = os.getcwd() + "\\python\\NNoxius\\day4\\input.txt"


def part1():
    counter = 0

    with open(path, "r") as file:
        for line in file:
            line = line.strip().split(",")
            elve_one = [int(x) for x in line[0].split("-")]
            elve_two = [int(x) for x in line[1].split("-")]

            if elve_one[0] >= elve_two[0] and elve_one[1] <= elve_two[1] or elve_two[0] >= elve_one[0] and elve_two[1] <= elve_one[1]:
                counter += 1

    print(counter)


def part2():
    counter = 0

    with open(path, "r") as file:
        for line in file:
            line = line.strip().split(",")
            elve_one = [int(x) for x in line[0].split("-")]
            elve_two = [int(x) for x in line[1].split("-")]

            if elve_one[1] >= elve_two[0] and elve_one[1] <= elve_two[1] or elve_two[1] >= elve_one[0] and elve_two[1] <= elve_one[1]:
                counter += 1
                print(elve_one, elve_two)

    print(counter)


part1()
part2()

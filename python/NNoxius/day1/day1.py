import os

path = os.getcwd() + "\\python\\NNoxius\\day1\\input.txt"


def part1():
    max_calories = 0
    calories = 0

    with open(path, "r") as file:
        for line in file:
            line = line.strip()
            if line != "":
                calories += int(line)
            else:
                if calories > max_calories:
                    max_calories = calories
                calories = 0

    print(max_calories)


def part2():
    calories = 0
    calories_list = []

    with open(path, "r") as file:
        for line in file:
            line = line.strip()
            if line != "":
                calories += int(line)
            else:
                calories_list.append(calories)
                calories = 0

    calories_list.sort(reverse=True)

    print(sum(calories_list[0:3]))


part1()
part2()

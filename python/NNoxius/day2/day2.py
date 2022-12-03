import os

path = os.getcwd() + "\\python\\NNoxius\\day2\\input.txt"


def part1():
    score = 0

    with open(path, "r") as file:
        for line in file:
            line = line.strip().split()
            if line[0] == "A":
                if line[1] == "X":
                    score += 1 + 3
                elif line[1] == "Y":
                    score += 2 + 6
                elif line[1] == "Z":
                    score += 3 + 0
            elif line[0] == "B":
                if line[1] == "X":
                    score += 1 + 0
                elif line[1] == "Y":
                    score += 2 + 3
                elif line[1] == "Z":
                    score += 3 + 6
            elif line[0] == "C":
                if line[1] == "X":
                    score += 1 + 6
                elif line[1] == "Y":
                    score += 2 + 0
                elif line[1] == "Z":
                    score += 3 + 3
        
    print(score)


def part2():
    score = 0

    with open(path, "r") as file:
        for line in file:
            line = line.strip().split()
            if line[0] == "A":
                if line[1] == "X":
                    score += 3 + 0
                elif line[1] == "Y":
                    score += 1 + 3
                elif line[1] == "Z":
                    score += 2 + 6
            elif line[0] == "B":
                if line[1] == "X":
                    score += 1 + 0
                elif line[1] == "Y":
                    score += 2 + 3
                elif line[1] == "Z":
                    score += 3 + 6
            elif line[0] == "C":
                if line[1] == "X":
                    score += 2 + 0
                elif line[1] == "Y":
                    score += 3 + 3
                elif line[1] == "Z":
                    score += 1 + 6
        
    print(score)



part1()
part2()
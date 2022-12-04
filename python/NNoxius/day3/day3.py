import os

path = os.getcwd() + "\\python\\NNoxius\\day3\\input.txt"
letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"


def part1():
    priority = 0

    with open(path, "r") as file:
        for line in file:
            line = line.strip()
            compartment_one = line[0:int(len(line)/2)]
            compartment_two = line[int(len(line)/2):len(line)]
            
            for item in compartment_one:
                if item in compartment_two:
                    priority += letters.index(item) + 1
                    break
    
    print(priority)


def part2():
    group_counter = 1
    priority = 0

    with open(path, "r") as file:
        for line in file:
            line = line.strip()
            if group_counter == 1:
                elve_one = line
            elif group_counter == 2:
                elve_two = line
            elif group_counter == 3:
                elve_three = line
                group_counter = 0

                for item in elve_one:
                    if item in elve_two and item in elve_three:
                        priority += letters.index(item) + 1
                        break

            group_counter += 1
    
    print(priority)


part1()
part2()
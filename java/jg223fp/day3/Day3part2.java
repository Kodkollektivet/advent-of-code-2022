package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day3part2 {
    
  // create priority map
    private Map<String, Integer> itemPrioritys = new HashMap<String, Integer>() {
      {   int i = 1;
          for (char ch = 'a'; ch <= 'z'; ch++) { 
              put(String.valueOf(ch), i); 
              i += 1;
          }
      }
      {   int i = 27;
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            put(String.valueOf(ch), i); 
            i += 1;
        }
    }
      };

    public int getItemPriority(String key) {
      return itemPrioritys.get(key);
    }

    public static void main(String[] args) throws FileNotFoundException{

      File file = new File("C:\\src\\github\\advent-of-code-2022\\java\\jg223fp\\day3\\input.txt");
      Scanner scan = new Scanner(file);
      Day3part1 todaysObject = new Day3part1(); 
      int prioritySum = 0;

      while (scan.hasNextLine()){
        try {

          String rucksack1 = scan.nextLine();
          String rucksack2 = scan.nextLine();
          String rucksack3 = scan.nextLine();

          for (int i = 0; i < rucksack1.length(); i++) {

            String item = String.valueOf(rucksack1.charAt(i));

            if (rucksack2.indexOf(item) > -1 && rucksack3.indexOf(item) > -1  ) {
              prioritySum += todaysObject.getItemPriority(item);
              break;     
            }
          }

        } catch (Exception e) { 
        }

      }
    System.out.println(prioritySum);
    scan.close();
    }
  }
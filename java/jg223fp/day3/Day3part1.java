package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day3part1 {
    
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

          String rucksack = scan.nextLine();
          String firstCompartment = rucksack.substring(0, rucksack.length() / 2);
          String secondCompartment = rucksack.substring((rucksack.length() / 2), rucksack.length() );

          for (int i = 0; i < firstCompartment.length(); i++) {

            String item = String.valueOf(firstCompartment.charAt(i));

            if (secondCompartment.indexOf(item) > -1) {
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
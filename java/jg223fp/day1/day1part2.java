package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

public class day1part2 {

    public static void main(String[] args) throws FileNotFoundException{
      File file = new File("C:\\src\\Knacka_kod\\Advent of code\\day1\\cals.txt");
      Scanner scan = new Scanner(file);

      ArrayList<Integer> calList = new ArrayList<Integer>();
      int elfCals = 0;

      while (scan.hasNextLine()){
        try {
          int cals = Integer.parseInt(scan.nextLine());
          elfCals += cals;
        } catch (Exception e) {
          calList.add(elfCals);
          elfCals = 0;
        }
      }
    
    calList.sort(Comparator.reverseOrder());
    
    int totalCals = 0;
    for (int i = 0; i < 3; i++) {
      totalCals += calList.get(i);
    }  
    
    System.out.println(totalCals);

    scan.close();
    }
  }
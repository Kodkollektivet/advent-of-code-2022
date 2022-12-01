package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day1part1 {

    public static void main(String[] args) throws FileNotFoundException{
      File file = new File("C:\\src\\Knacka_kod\\Advent of code\\day1\\cals.txt");
      Scanner scan = new Scanner(file);

      int mostCals = 0;
      int elfCals = 0;

      while (scan.hasNextLine()){
        try {
          int cals = Integer.parseInt(scan.nextLine());
          elfCals += cals;
        } catch (Exception e) {
          if (elfCals > mostCals){
            mostCals = elfCals;
          }
          elfCals = 0;
        }
      
      }
    System.out.println(mostCals);  
    scan.close();
    }
  }
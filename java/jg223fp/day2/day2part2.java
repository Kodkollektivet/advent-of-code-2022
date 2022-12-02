package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day2part2 {

    public static void main(String[] args) throws FileNotFoundException{
      File file = new File("C:\\src\\github\\advent-of-code-2022\\java\\jg223fp\\day2\\input.txt");
      Scanner scan = new Scanner(file);

      int total = 0;

      while (scan.hasNextLine()){
        try {
          String[] round = scan.nextLine().split(" ");
          switch (round[0]) {
            case "A":
              switch (round[1]) {
                case "X":   
                  total += 3;
                  break;
                case "Y":
                  total += 4;   
                  break;
                case "Z":
                  total += 8;
                  break;
                default:
                  break;
              } 
              break;
            case "B":
              switch (round[1]) {
                case "X":
                  total += 1;
                  break;
                case "Y":
                  total += 5;
                  break;
                case "Z":
                  total += 9;
                  break;
                default:
                  break;
              }
              break;
            case "C": 
              switch (round[1]) {
                case "X":
                  total += 2;
                  break;
                case "Y":
                  total += 6; 
                  break;
                case "Z":
                  total += 7;
                  break;
                default:
                  break;
              }
              break;
          
            default:
              break;
          }
        } catch (Exception e) { 
        }
      }
    System.out.println(total);
    scan.close();
    }
  }
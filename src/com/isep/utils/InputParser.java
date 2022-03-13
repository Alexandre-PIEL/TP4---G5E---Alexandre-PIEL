
package com.isep.utils;

import java.util.Scanner;

/**
 * Class InputParser
 */
public class InputParser {

  //
  // Fields
  //
  private final Scanner scanner;


  //
  // Constructors
  //
  public InputParser () {
    this.scanner = new Scanner(System.in);
  }
  
  //
  // Methods
  //
  public String askStringUser(String question) {
    System.out.print(question);
    return this.scanner.next();
  }

  public int askIntUser(String question) {
    System.out.print(question);
    int input = 0;
    while (true) {
      try {
        input = Integer.parseInt(this.scanner.next());
        break;
      } catch (Exception e) {
        System.out.println("\nERROR !");
        System.out.print(question);
      }
    }
    return input;
  }

}

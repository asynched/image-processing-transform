package com.unip.pdi;

import com.unip.pdi.cli.CommandLineApp;

public class App {
  /**
   * Executes the application
   * 
   * @param args Arguments for the application.
   */
  public static void main(String[] args) {
    CommandLineApp
        .fromDefaults()
        .run(args);
  }
}

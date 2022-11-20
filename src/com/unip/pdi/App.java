package com.unip.pdi;

import com.unip.pdi.cli.CommandLine;

public class App {
  /**
   * Executes the application
   * 
   * @param args Arguments for the application (discarded)
   */
  public static void main(String[] args) {
    var commandLine = CommandLine.fromDefaults();
    commandLine.run();
    commandLine.destroy();
  }
}

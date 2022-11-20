package com.unip.pdi.cli;

import java.util.Arrays;
import java.util.Set;

import com.unip.pdi.factories.CLICommandFactory;

public class CommandLineApp {
  private static final Set<String> types = Set.of("filter", "transform", "all");

  /**
   * Runs the command line application.
   * 
   * @param args Arguments for the application.
   */
  public void run(String[] args) {
    if (args.length < 1) {
      System.out.println("Not enough arguments.");
      System.out.println("Usage:\n\tcli <type> <args>");
      return;
    }

    var type = args[0];

    if (!types.contains(type)) {
      System.out.println("Invalid type. Valid types are: all, filter and transform");
      return;
    }

    CLICommandFactory
        .getCommand(type)
        .expect("The given command type is not valid")
        .execute(Arrays.stream(args).skip(1).toArray(String[]::new));
  }

  /**
   * Creates a new command line application with default settings.
   * 
   * @return A new command line application.
   */
  public static CommandLineApp fromDefaults() {
    return new CommandLineApp();
  }
}

package com.unip.pdi.cli;

import java.util.Map;

import com.unip.pdi.functional.Result;

import java.util.Arrays;
import java.util.HashMap;

public class Parser {
  private final String[] arguments;

  public Parser(String... arguments) {
    this.arguments = arguments;
  }

  public Result<Map<String, String>, IllegalArgumentException> parse(String[] args) {
    try {
      var map = new HashMap<String, String>();

      for (var i = 0; i < args.length; i += 2) {
        map.put(args[i].replace("--", ""), args[i + 1]);
      }

      for (var key : arguments) {
        if (!map.containsKey(key)) {
          return Result.err(new IllegalArgumentException("Missing required argument: " + key));
        }
      }

      return Result.ok(map);
    } catch (Exception err) {
      var message = String.join(" ", Arrays.stream(arguments).map((arg) -> "--" + arg + " arg").toArray(String[]::new));
      System.out.println("Error: Couldn't parse arguments.\nUsage:\n\tcli " + message + "\n");
      System.exit(1);
      return null;
    }
  }
}

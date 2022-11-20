package com.unip.pdi.factories;

import com.unip.pdi.cli.commands.ApplyAllTransforms;
import com.unip.pdi.cli.commands.ApplyFilter;
import com.unip.pdi.cli.commands.ApplyTransform;
import com.unip.pdi.cli.commands.ICommand;
import com.unip.pdi.functional.Option;

public class CLICommandFactory {
  public static Option<ICommand> getCommand(String command) {
    if (command.equals("transform")) {
      return Option.some(new ApplyTransform());
    }

    if (command.equals("filter")) {
      return Option.some(new ApplyFilter());
    }

    if (command.equals("all")) {
      return Option.some(new ApplyAllTransforms());
    }

    return Option.none();
  }
}

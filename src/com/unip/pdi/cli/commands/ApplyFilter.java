package com.unip.pdi.cli.commands;

import java.util.Set;

import com.unip.pdi.cli.Parser;
import com.unip.pdi.factories.ImageTransformFactory;
import com.unip.pdi.fs.FileSystem;

public class ApplyFilter implements ICommand {
  public static final Set<String> filters = Set.of("red", "green", "blue", "gray", "invert");
  public static final Parser parser = new Parser("input", "output", "filter");

  @Override()
  public void execute(String[] args) {
    var result = parser.parse(args);

    if (result.isErr()) {
      System.out.println(result.unwrapErr().getMessage());
      return;
    }

    var arguments = result.unwrap();
    var filter = arguments.get("filter");

    if (!filters.contains(filter)) {
      System.out.println("Error: Invalid filter type, valid types are: red, green, blue, gray and invert.\n");
      return;
    }

    var input = arguments.get("input");
    var output = arguments.get("output");

    System.out.printf("Applying '%s' filter to '%s' and saving to '%s'\n", filter, input, output);

    var startTime = System.currentTimeMillis();

    var sourceImage = FileSystem.readImage(input).expect("Could not read source image.");
    var filteredImage = ImageTransformFactory
        .getColorFilter(filter)
        .expect("The provided filter is not valid.")
        .transform(sourceImage);

    FileSystem
        .writeImage(output, filteredImage)
        .expect("Could not write filtered image.");

    var endTime = System.currentTimeMillis();

    System.out.println("Done! (" + (endTime - startTime) + "ms)");

  }
}

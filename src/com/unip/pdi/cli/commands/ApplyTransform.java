package com.unip.pdi.cli.commands;

import java.util.Set;

import com.unip.pdi.cli.Parser;
import com.unip.pdi.factories.ImageTransformFactory;
import com.unip.pdi.fs.FileSystem;

public class ApplyTransform implements ICommand {
  private static final Set<String> transforms = Set.of("horizontal", "vertical");
  private static final Parser parser = new Parser("input", "output", "transform");

  @Override()
  public void execute(String[] args) {
    var result = parser.parse(args);

    if (result.isErr()) {
      System.out.println(result.unwrapErr().getMessage());
      return;
    }

    var arguments = result.unwrap();
    var type = arguments.get("transform");

    if (!transforms.contains(type)) {
      System.out.println("Error: Invalid transform type, valid types are: horizontal and vertical.\n");
      return;
    }

    var input = arguments.get("input");
    var output = arguments.get("output");

    System.out.printf("Applying '%s' transform to '%s' and saving to '%s'\n", type, input, output);

    var startTime = System.currentTimeMillis();

    var sourceImage = FileSystem.readImage(input).expect("Could not read source image.");
    var transformedImage = ImageTransformFactory
        .getTransform(type)
        .expect("The provided transform is not valid.")
        .transform(sourceImage);

    FileSystem
        .writeImage(output, transformedImage)
        .expect("Could not write transformed image.");

    var endTime = System.currentTimeMillis();

    System.out.println("Done! (" + (endTime - startTime) + "ms)");
  }
}

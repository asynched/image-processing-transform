package com.unip.pdi.cli.commands;

import com.unip.pdi.cli.Parser;
import com.unip.pdi.factories.ImageTransformFactory;
import com.unip.pdi.fs.FileSystem;

public class ApplyAllTransforms implements ICommand {
  private static final Parser parser = new Parser("input", "output");

  @Override()
  public void execute(String[] args) {
    var result = parser.parse(args);

    if (result.isErr()) {
      System.out.println(result.unwrapErr().getMessage());
      return;
    }

    var arguments = result.unwrap();

    var input = arguments.get("input");
    var output = arguments.get("output");
    var transforms = ImageTransformFactory.getAllTransforms();

    for (var transform : transforms) {
      var transformName = transform.getClass().getSimpleName().toLowerCase();
      var outputSplit = output.split("\\.");
      var outputName = outputSplit[0];
      var outputExtension = outputSplit[1];

      var outputFilename = String.format("%s_%s.%s", outputName, transformName, outputExtension);

      System.out.printf("Applying '%s' transform to '%s' and saving to '%s'\n", transformName, input, output);

      var startTime = System.currentTimeMillis();

      var sourceImage = FileSystem.readImage(input).expect("Could not read source image.");
      var transformedImage = transform.transform(sourceImage);

      FileSystem
          .writeImage(outputFilename, transformedImage)
          .expect("Could not write transformed image.");

      var endTime = System.currentTimeMillis();

      System.out.println("Done! (" + (endTime - startTime) + "ms)");
    }
  }
}

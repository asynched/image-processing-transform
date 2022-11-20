package com.unip.pdi.cli;

import java.util.Scanner;
import java.util.Set;
import java.nio.file.Paths;

import com.unip.pdi.fs.FileSystem;
import com.unip.pdi.factories.ImageTransformFactory;

public class CommandLine {
  private final Scanner scanner;
  private final String rootPath;
  private static final Set<String> types = Set.of("filter", "transform", "all");

  /**
   * Creates a new command line interface application
   * 
   * @param scanner Scanner to read the user input from.
   */
  CommandLine(Scanner scanner) {
    this.scanner = scanner;
    this.rootPath = Paths.get("").toAbsolutePath().toString();
  }

  /**
   * Reads the input from the scanner.
   * 
   * @param text Text to be displayed to the user.
   * @return The input from the user.
   */
  String input(String text) {
    System.out.print("?> " + text);
    return scanner.nextLine().trim();
  }

  /**
   * Runs the command line application.
   */
  public void run() {
    var type = input("Digite o tipo de transformação a ser aplicada [filter/transform/all]: ");

    if (!types.contains(type)) {
      System.out.printf("Tipo de transformação inválido ('%s'). Encerrando aplicação.\n", type);
      System.exit(1);
    }

    var fileName = input("Digite o nome do arquivo a ser processado: ");

    if (type.equals("all")) {
      var imageFilePath = Paths.get(rootPath, fileName);
      processAllTransforms(imageFilePath.toString());
      return;
    }

    var outputFileName = input("Digite o nome do arquivo de saída: ");
    var imageFilePath = Paths.get(rootPath, fileName).toString();
    var outputFilePath = Paths.get(rootPath, "output", outputFileName).toString();

    if (type.equals("filter")) {
      var filter = input("Digite o filtro a ser aplicado [red/green/blue]: ");

      System.out.printf("Processando arquivo %s com o filtro '%s' e salvando em %s\n", fileName, filter,
          outputFileName);

      processFilter(imageFilePath, outputFilePath, filter);
    }

    if (type.equals("transform")) {
      System.out.printf("Digite a transformação a ser aplicada [horizontal/vertical]: ");
      var transform = scanner.nextLine().trim();

      System.out.printf("Processando arquivo %s com a transformação '%s' e salvando em %s\n", fileName, transform,
          outputFileName);

      processTransform(imageFilePath, outputFilePath, transform);
    }

  }

  /**
   * Destroys the command line application, cleaning it's resources.
   */
  public void destroy() {
    scanner.close();
  }

  /**
   * Process an image with all the transforms available.
   * 
   * @param fileName The file name of the image to be processed.
   */
  void processAllTransforms(String fileName) {
    var image = FileSystem
        .readImage(fileName)
        .expect("Não foi possível ler a imagem");

    for (var filter : ImageTransformFactory.getAllTransforms()) {
      var filteredImage = filter.transform(image);
      var outputFilePath = Paths.get(rootPath, "output", String.format("%s.png", filter.getClass().getSimpleName()));

      FileSystem.writeImage(outputFilePath.toString(), filteredImage)
          .expect("Não foi possível salvar o arquivo " + outputFilePath);
    }
  }

  /**
   * Processes an image with a transform filter (horizontal or vertical).
   * 
   * @param fileName       The file name of the image to be processed.
   * @param outputFileName The file name of the output image.
   * @param transform      The transform to be applied.
   */
  void processTransform(String fileName, String outputFileName, String transform) {
    var filter = ImageTransformFactory
        .getTransform(transform)
        .expect("A transformação informada não é válida");

    var image = FileSystem
        .readImage(fileName)
        .expect("Não foi possível ler o arquivo de origem");

    var filteredImage = filter.transform(image);

    FileSystem
        .writeImage(outputFileName, filteredImage)
        .expect("Não foi possível salvar o arquivo de saída");
  }

  /**
   * Processes an image with a filter (red, green or blue).
   * 
   * @param fileName       The file name of the image to be processed.
   * @param outputFileName The file name of the output image.
   * @param filterName     The filter to be applied.
   */
  void processFilter(String fileName, String outputFileName, String filterName) {
    var filter = ImageTransformFactory
        .getColorFilter(filterName)
        .expect("O filtro informado não é válido");

    var image = FileSystem
        .readImage(fileName)
        .expect("Não foi possível ler o arquivo de origem");

    var filteredImage = filter.transform(image);

    FileSystem
        .writeImage(outputFileName, filteredImage)
        .expect("Não foi possível salvar o arquivo de saída");
  }

  /**
   * Builds a new command line application from the defaults.
   * 
   * @return A new command line application.
   */
  public static CommandLine fromDefaults() {
    return new CommandLine(new Scanner(System.in));
  }
}

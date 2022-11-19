package com.unip.pdi.cli;

import java.util.Scanner;

import com.unip.pdi.fs.FileSystem;
import com.unip.pdi.factories.ImageTransformFactory;

public class CommandLine {
  public static void run() {
    var scanner = new Scanner(System.in);

    System.out.printf("Digite o nome do arquivo a ser processado: ");
    var fileName = scanner.nextLine();

    System.out.printf("Digite o nome do arquivo de saída: ");
    var outputFileName = scanner.nextLine();

    System.out.printf("Digite o filtro a ser aplicado [red/green/blue]: ");
    var filter = scanner.nextLine();

    scanner.close();

    System.out.printf("Processando arquivo %s com o filtro '%s' e salvando em %s\n", fileName, filter, outputFileName);
    processImage(fileName, outputFileName, filter);
  }

  static void processImage(String fileName, String outputFileName, String filterName) {
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
}

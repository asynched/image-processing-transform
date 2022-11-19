package com.unip.pdi.images.colors;

import java.awt.image.BufferedImage;

import com.unip.pdi.images.ImageTransform;

public class GreenFilter implements ImageTransform {
  @Override()
  public BufferedImage transform(BufferedImage image) {
    var width = image.getWidth();
    var height = image.getHeight();
    var newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        var pixel = image.getRGB(i, j);

        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;

        int newPixel = (red << 16) | (green << 8) | blue;

        newImage.setRGB(i, j, newPixel);
      }
    }

    return newImage;
  }
}

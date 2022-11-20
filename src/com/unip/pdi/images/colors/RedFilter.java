package com.unip.pdi.images.colors;

import java.awt.image.BufferedImage;

import com.unip.pdi.images.ITransform;

/**
 * Transform that applies a red filter to the image.
 */
public class RedFilter implements ITransform {
  @Override()
  public BufferedImage transform(BufferedImage image) {
    var width = image.getWidth();
    var height = image.getHeight();
    var newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        var pixel = image.getRGB(i, j);

        int red = (pixel >> 16) & 0xff;
        int green = 0;
        int blue = 0;

        int newPixel = (red << 16) | (green << 8) | blue;

        newImage.setRGB(i, j, newPixel);
      }
    }

    return newImage;
  }
}

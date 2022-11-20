package com.unip.pdi.images.colors;

import java.awt.image.BufferedImage;

import com.unip.pdi.images.ITransform;

/**
 * Transform that applies an invert filter to the image.
 */
public class InvertFilter implements ITransform {
  @Override()
  public BufferedImage transform(BufferedImage image) {
    var width = image.getWidth();
    var height = image.getHeight();

    var result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (var x = 0; x < width; x++) {
      for (var y = 0; y < height; y++) {
        var pixel = image.getRGB(x, y);

        var red = (pixel >> 16) & 0xff;
        var green = (pixel >> 8) & 0xff;
        var blue = (pixel) & 0xff;

        var newPixel = ((255 - red) << 16) | ((255 - green) << 8) | (255 - blue);

        result.setRGB(x, y, newPixel);
      }
    }

    return result;
  }
}

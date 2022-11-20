package com.unip.pdi.images.colors;

import java.awt.image.BufferedImage;

import com.unip.pdi.images.ITransform;

/**
 * Transform that applies a gray filter to the image.
 */
public class GrayFilter implements ITransform {
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

        var gray = (red + green + blue) / 3;

        var newPixel = (gray << 16) | (gray << 8) | gray;

        result.setRGB(x, y, newPixel);
      }
    }

    return result;
  }
}

package com.unip.pdi.images.transform;

import java.awt.image.BufferedImage;

import com.unip.pdi.images.ImageTransform;

public class VerticalMirror implements ImageTransform {
  @Override()
  public BufferedImage transform(BufferedImage image) {
    var width = image.getWidth();
    var height = image.getHeight();

    var result = new BufferedImage(width, height, image.getType());

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        var pixel = image.getRGB(x, y);
        result.setRGB(width - x - 1, y, pixel);
      }
    }

    return result;
  }
}

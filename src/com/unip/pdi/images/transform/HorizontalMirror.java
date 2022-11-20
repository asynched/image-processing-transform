package com.unip.pdi.images.transform;

import java.awt.image.BufferedImage;

import com.unip.pdi.images.ITransform;

/**
 * Transform that flips the image horizontally.
 */
public class HorizontalMirror implements ITransform {
  @Override()
  public BufferedImage transform(BufferedImage image) {
    var width = image.getWidth();
    var height = image.getHeight();

    var result = new BufferedImage(width, height, image.getType());

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        var pixel = image.getRGB(x, y);
        result.setRGB(x, height - y - 1, pixel);
      }
    }

    return result;
  }
}

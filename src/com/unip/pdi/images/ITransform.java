package com.unip.pdi.images;

import java.awt.image.BufferedImage;

/**
 * Interface for image transformations.
 */
public interface ITransform {
  /**
   * Applies the transformation to the image.
   * 
   * @param image The image to be transformed.
   * @return The transformed image.
   */
  public BufferedImage transform(BufferedImage image);
}

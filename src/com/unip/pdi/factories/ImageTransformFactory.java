package com.unip.pdi.factories;

import com.unip.pdi.functional.Option;
import com.unip.pdi.images.ImageTransform;
import com.unip.pdi.images.colors.BlueFilter;
import com.unip.pdi.images.colors.GreenFilter;
import com.unip.pdi.images.colors.RedFilter;

public class ImageTransformFactory {
  public static Option<ImageTransform> getColorFilter(String type) {
    ImageTransform filter = null;

    switch (type) {
      case "red":
        filter = new RedFilter();
        break;
      case "green":
        filter = new GreenFilter();
        break;
      case "blue":
        filter = new BlueFilter();
        break;
    }

    if (filter == null) {
      return Option.none();
    }

    return Option.some(filter);
  }
}

package com.unip.pdi.factories;

import com.unip.pdi.functional.Option;
import com.unip.pdi.images.ITransform;
import com.unip.pdi.images.colors.BlueFilter;
import com.unip.pdi.images.colors.GreenFilter;
import com.unip.pdi.images.colors.RedFilter;
import com.unip.pdi.images.transform.HorizontalMirror;
import com.unip.pdi.images.transform.VerticalMirror;

public class ImageTransformFactory {
  /**
   * Helper method to get all the transforms available in the application.
   * 
   * @return An instance of all the transforms available in the application.
   */
  public static ITransform[] getAllTransforms() {
    return new ITransform[] {
        new RedFilter(),
        new GreenFilter(),
        new BlueFilter(),
        new HorizontalMirror(),
        new VerticalMirror(),
    };
  }

  /**
   * Helper method to get a specific transform by its name.
   * 
   * @param type The name of the transform.
   * @return An instance of the transform if it exists, otherwise it returns none.
   */
  public static Option<ITransform> getTransform(String type) {
    if (type.equals("horizontal")) {
      return Option.some(new HorizontalMirror());
    }

    if (type.equals("vertical")) {
      return Option.some(new VerticalMirror());
    }

    return Option.none();
  }

  /**
   * Helper method to get a specific color filter by its name.
   * 
   * @param type The name of the color filter.
   * @return An instance of the color filter if it exists, otherwise it returns
   *         none.
   */
  public static Option<ITransform> getColorFilter(String type) {
    if (type.equals("red")) {
      return Option.some(new RedFilter());
    }

    if (type.equals("green")) {
      return Option.some(new GreenFilter());
    }

    if (type.equals("blue")) {
      return Option.some(new BlueFilter());
    }

    return Option.none();
  }
}

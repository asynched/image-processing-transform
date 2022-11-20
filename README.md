# image-processing-transform

This is a simple image processing tool for processing images using Java.

## Requirements

- Java >= 11
- GNU/Make

## Build

You can build the project using the makefile in the root directory.

```sh
make build
```

> This will generate a file named `cli.jar` in the root of the project.

## Usage

- Filter

```sh
# Filters: red, green, blue, gray, invert.
java -jar cli.jar filter --input $INPUT_FILE --output $OUTPUT_FILE --filter $FILTER
```

- Transforms

```sh
# Transforms: horizontal, vertical.
java -jar cli.jar transform --input $INPUT_FILE --output $OUTPUT_FILE --transform $TRANSFORM
```

- All

```sh
java -jar cli.jar all --input $INPUT_FILE --output $OUTPUT_FILE
```

> Note: This requires the `jar` file to have been generated.

## Transformations

### Filters - Image colorization

- Red;
- Green;
- Blue;
- Gray;
- Invert.

### Transforms - Image rotation

- Horizontal - Horizontal flip;
- Vertical - Vertical flip.

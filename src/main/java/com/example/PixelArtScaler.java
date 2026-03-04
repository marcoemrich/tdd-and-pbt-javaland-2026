package com.example;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PixelArtScaler {

    List<String> scale(List<String> image, int scaleFactor) {
        return image.stream()
                .map(row -> scaleHorizontally(row, scaleFactor))
                .flatMap(row -> scaleVertically(row, scaleFactor))
                .toList();
    }

    private String scaleHorizontally(String row, int scaleFactor) {
        return row.chars()
                .mapToObj(c -> String.valueOf((char) c).repeat(scaleFactor))
                .collect(Collectors.joining());
    }

    private Stream<String> scaleVertically(String row, int scaleFactor) {
        return Collections.nCopies(scaleFactor, row).stream();
    }

}

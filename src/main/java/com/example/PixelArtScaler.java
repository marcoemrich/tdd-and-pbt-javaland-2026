package com.example;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class PixelArtScaler {

    List<String> scale(List<String> image, int scaleFactor) {
        return image.stream()
                .map(row -> scaleHorizontally(row, scaleFactor))
                .flatMap(row -> scaleVertically(row, scaleFactor))
                .toList();
    }

    private String scaleHorizontally(String row, int scaleFactor) {
        StringBuilder result = new StringBuilder();
        for (char c : row.toCharArray()) {
            result.append(String.valueOf(c).repeat(scaleFactor));
        }
        return result.toString();
    }

    private Stream<String> scaleVertically(String row, int scaleFactor) {
        return Collections.nCopies(scaleFactor, row).stream();
    }

}

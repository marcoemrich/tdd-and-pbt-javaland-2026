package com.example;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class PixelArtScalerProperties {

    private static final char[] PIXEL_CHARS = {'#', '.', '@', '*'};

    private final PixelArtScaler scaler = new PixelArtScaler();

    // --- Properties ---

    @Property
    void scaledImageHasCorrectNumberOfRows(
            @ForAll("images") List<String> image,
            @ForAll @IntRange(min = 1, max = 5) int factor
    ) {
        var result = scaler.scale(image, factor);
        assertThat(result).hasSize(image.size() * factor);
    }

    @Property
    void scaledImageHasCorrectRowLength(
            @ForAll("images") List<String> image,
            @ForAll @IntRange(min = 1, max = 5) int factor
    ) {
        var result = scaler.scale(image, factor);
        int expectedLength = image.isEmpty() ? 0 : image.getFirst().length() * factor;
        assertThat(result).allSatisfy(row ->
                assertThat(row).hasSize(expectedLength)
        );
    }

    @Property
    void scaleFactor1IsIdentity(@ForAll("images") List<String> image) {
        assertThat(scaler.scale(image, 1)).isEqualTo(image);
    }

    @Property
    void scalingPreservesCharacterSet(
            @ForAll("images") List<String> image,
            @ForAll @IntRange(min = 1, max = 5) int factor
    ) {
        Set<Character> originalChars = charsIn(image);
        Set<Character> scaledChars = charsIn(scaler.scale(image, factor));
        assertThat(scaledChars).isEqualTo(originalChars);
    }

    @Property
    void samplingScaledImageRecoversOriginal(
            @ForAll("images") List<String> image,
            @ForAll @IntRange(min = 1, max = 5) int factor
    ) {
        var scaled = scaler.scale(image, factor);
        var sampled = sample(scaled, factor);
        assertThat(sampled).isEqualTo(image);
    }

    @Property
    void scaleIsMonoidComposition(
            @ForAll("images") List<String> image,
            @ForAll @IntRange(min = 1, max = 3) int a,
            @ForAll @IntRange(min = 1, max = 3) int b
    ) {
        var scaledTwice = scaler.scale(scaler.scale(image, a), b);
        var scaledOnce = scaler.scale(image, a * b);
        assertThat(scaledTwice).isEqualTo(scaledOnce);
    }

    // --- Generators ---

    @Provide
    Arbitrary<List<String>> images() {
        return Arbitraries.integers().between(0, 5).flatMap(rows ->
                Arbitraries.integers().between(1, 5).flatMap(cols -> {
                    Arbitrary<String> row = Arbitraries.strings()
                            .withChars(PIXEL_CHARS)
                            .ofLength(cols);
                    return row.list().ofSize(rows);
                })
        );
    }

    // --- Helpers ---

    private Set<Character> charsIn(List<String> image) {
        return image.stream()
                .flatMapToInt(String::chars)
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
    }

    private List<String> sample(List<String> scaled, int factor) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < scaled.size(); i += factor) {
            String row = scaled.get(i);
            StringBuilder sampled = new StringBuilder();
            for (int j = 0; j < row.length(); j += factor) {
                sampled.append(row.charAt(j));
            }
            result.add(sampled.toString());
        }
        return result;
    }
}

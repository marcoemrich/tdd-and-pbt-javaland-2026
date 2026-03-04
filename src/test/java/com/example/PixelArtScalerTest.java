package com.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PixelArtScalerTest {

    private final PixelArtScaler scaler = new PixelArtScaler();

    @Test
    void shouldReturnEmptyForEmptyInput() {
        assertThat(scaler.scale(List.of(), 1)).isEmpty();
    }

    @Test
    void shouldReturnSinglePixelUnchangedForScaleFactor1() {
        assertThat(scaler.scale(List.of("@"), 1)).containsExactly("@");
    }

    @Test
    void shouldScaleSinglePixelByFactor2() {
        assertThat(scaler.scale(List.of("@"), 2)).containsExactly("@@", "@@");
    }

    @Test
    void shouldScaleSingleRowWithMultiplePixelsByFactor2() {
        assertThat(scaler.scale(List.of("#."), 2)).containsExactly("##..", "##..");
    }

    @Test
    void shouldScaleComplexCheckerboardPatternByFactor2() {
        assertThat(scaler.scale(List.of(
            "#.#",
            ".#.",
            "#.#"), 2))
                .containsExactly(
                    "##..##",
                    "##..##",
                    "..##..",
                    "..##..",
                    "##..##",
                    "##..##");
    }

}

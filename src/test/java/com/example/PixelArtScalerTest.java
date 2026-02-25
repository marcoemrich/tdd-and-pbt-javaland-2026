package com.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PixelArtScalerTest {

    @Test
    void shouldReturnEmptyForEmptyInput() {
        var scaler = new PixelArtScaler();
        assertThat(scaler.scale(List.of(), 1)).isEmpty();
    }

    @Test
    void shouldReturnSinglePixelUnchangedForScaleFactor1() {
        var scaler = new PixelArtScaler();
        assertThat(scaler.scale(List.of("@"), 1)).containsExactly("@");
    }

    @Test
    void shouldScaleSinglePixelHorizontallyByFactor2() {
        var scaler = new PixelArtScaler();
        assertThat(scaler.scale(List.of("@"), 2)).containsExactly("@@", "@@");
    }

    @Test
    void shouldScaleSinglePixelBothDirectionsByFactor2() {
        var scaler = new PixelArtScaler();
        assertThat(scaler.scale(List.of("@"), 2)).containsExactly("@@", "@@");
    }

    @Test
    void shouldScaleSingleRowWithMultiplePixelsByFactor2() {
        var scaler = new PixelArtScaler();
        assertThat(scaler.scale(List.of("#."), 2)).containsExactly("##..", "##..");
    }

    @Test
    void shouldScaleComplexCheckerboardPatternByFactor2() {
        var scaler = new PixelArtScaler();
        assertThat(scaler.scale(List.of("#.#", ".#.", "#.#"), 2))
                .containsExactly("##..##", "##..##", "..##..", "..##..", "##..##", "##..##");
    }

}

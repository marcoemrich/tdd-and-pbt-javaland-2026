package com.example;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.assertj.core.api.Assertions.assertThat;

class SmokeProperties {

    @Property
    void stringConcatenationLength(@ForAll String a, @ForAll String b) {
        assertThat(a.length() + b.length()).isEqualTo((a + b).length());
    }
}

package ru.netology;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExtraSpacesTrimTest {

    @Test
    public void testExtraTrim() {
        assertEquals("a, a!", ExtraSpacesTrim.extraTrim("    a    ,   a   !    "));
    }
}
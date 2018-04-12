package ru.aorlov.psshape;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author AndrewOrlov (stagereagle6@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TriangleTest {

    /**
     * Test draw.
     */
    @Test
    public void whenDrawTriangle() {
        Triangle triangle = new Triangle();
        assertThat(triangle.draw(),
                is(
                        new StringBuilder()
                                .append("  + ").append(System.lineSeparator())
                                .append(" +  +").append(System.lineSeparator())
                                .append("+    +").append(System.lineSeparator())
                                .append("++++++")
                                .toString()
                )
        );

        System.out.println(triangle.draw());
    }
}

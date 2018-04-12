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
public class SquareTest {

    /**
     * Test draw.
     */
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(square.draw(),
                is(
                        new StringBuilder()
                                .append("+++++++").append(System.lineSeparator())
                                .append("+     +").append(System.lineSeparator())
                                .append("+     +").append(System.lineSeparator())
                                .append("+++++++")
                                .toString()
                )
        );
    }
}

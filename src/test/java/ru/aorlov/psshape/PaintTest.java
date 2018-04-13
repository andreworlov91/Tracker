package ru.aorlov.psshape;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author AndrewOrlov (stagereagle6@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    private final PrintStream stdout = System.out;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }
    @Test
    public void whenDrawSquare() {
        // выполняем действия пишушиее в консоль.
        new Paint().draw(new Square());
        // проверяем результат вычисления
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("+++++++").append(System.lineSeparator())
                                .append("+     +").append(System.lineSeparator())
                                .append("+     +").append(System.lineSeparator())
                                .append("+++++++").append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        new StringJoiner(
                                System.lineSeparator(), "",
                                System.lineSeparator())
                                .add("  ^  ")
                                .add(" ^ ^ ")
                                .add("^^^^^")
                                .toString()
                )
        );
    }
}

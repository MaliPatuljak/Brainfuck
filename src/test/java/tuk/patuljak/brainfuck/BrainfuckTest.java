package tuk.patuljak.brainfuck;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/*
    System Rules requires JUnit4.
 */
@RunWith(Parameterized.class)
public class BrainfuckTest {
    private final Expectation expectation;

    public BrainfuckTest(final Expectation expectation) {
        this.expectation = expectation;
    }

    @Rule
    public final SystemOutRule stdoutRule =
        new SystemOutRule()
            .mute()
            .enableLog();

    @Test
    public void testCode() {
        startProgram(expectation.inputCode);
        assertEquals(expectation.expectedOutput, getStdoutLog());
    }

    @Parameterized.Parameters
    public static Collection<Object[]> provideTestExpectations() {
        final var expectations = new ArrayList<Object[]>(Expectation.values().length);

        for(var expectation : Expectation.values()) {
            expectations.add(
                new Object[]{ expectation }
            );
        }

        return expectations;
    }

    private void startProgram(final String code) {
        final var input = new String[] {
            code
        };

        Brainfuck.main(input);
    }

    private String getStdoutLog() {
        return stdoutRule
            .getLogWithNormalizedLineSeparator();
    }
}

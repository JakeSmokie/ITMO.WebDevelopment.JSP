package test;

import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import main.java.ru.jakesmokie.areacheckers.AreaCheckerParameters;
import main.java.ru.jakesmokie.areacheckers.AreaCheckerVar1802;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AreaCheckerVar1802Test {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {true, new AreaCheckerParameters(0, 0, 1)},
                {false, new AreaCheckerParameters(-0.00001, -0.00001, 1)},
                {false, new AreaCheckerParameters(-0.00001, -0.5, 1)},
                {false, new AreaCheckerParameters(-0.00001, -1, 1)},
                {false, new AreaCheckerParameters(-0.5, -0.00001, 1)},
                {false, new AreaCheckerParameters(-1, -0.00001, 1)},

                {true, new AreaCheckerParameters(0.5, 0.5, 1)},
                {true, new AreaCheckerParameters(0.49999, 0.49999, 1)},
                {true, new AreaCheckerParameters(0.7, 0.3, 1)},
                {true, new AreaCheckerParameters(0.69999, 0.29999, 1)},
                {true, new AreaCheckerParameters(0.3, 0.7, 1)},
                {true, new AreaCheckerParameters(0.29999, 0.69999, 1)},

                {false, new AreaCheckerParameters(0.50001, 0.50001, 1)},
                {false, new AreaCheckerParameters(0.70001, 0.30001, 1)},
                {false, new AreaCheckerParameters(0.30001, 0.70001, 1)},
                {false, new AreaCheckerParameters(1, 1, 1)},

                {true, new AreaCheckerParameters(0.5, 0, 1)},
                {true, new AreaCheckerParameters(0, -0.5, 1)},
                {true, new AreaCheckerParameters(0.4, -0.1, 1)},
                {true, new AreaCheckerParameters(0.1, -0.4, 1)},
                {true, new AreaCheckerParameters(0.33, -0.33, 1)},

                {false, new AreaCheckerParameters(0.5, -0.00001, 1)},
                {false, new AreaCheckerParameters(0.00001, -0.5, 1)},
                {false, new AreaCheckerParameters(0.500001, -0.00001, 1)},
                {false, new AreaCheckerParameters(0.00001, -0.500001, 1)},
                {false, new AreaCheckerParameters(0.5, -0.5, 1)},

                {true, new AreaCheckerParameters(-1, 1, 1)},
                {true, new AreaCheckerParameters(0, 1, 1)},
                {true, new AreaCheckerParameters(-1, 0, 1)},
                {true, new AreaCheckerParameters(-1, 0.5, 1)},
                {true, new AreaCheckerParameters(-0.5, 1, 1)},

                {false, new AreaCheckerParameters(-1, 1.00001, 1)},
                {false, new AreaCheckerParameters(0, 1.00001, 1)},
                {false, new AreaCheckerParameters(-1.00001, 1, 1)},
                {false, new AreaCheckerParameters(-1.00001, 0, 1)},
                {false, new AreaCheckerParameters(-1.00001, 0.5, 1)},
                {false, new AreaCheckerParameters(-0.50001, 1.00001, 1)},
        });
    }

    private final boolean expected;
    private final AreaCheckerParameters parameters;
    private AreaCheckerVar1802 areaChecker;

    @Before
    public void setUp() {
        areaChecker = new AreaCheckerVar1802();
    }

    public AreaCheckerVar1802Test(boolean expected, AreaCheckerParameters parameters) {
        this.expected = expected;
        this.parameters = parameters;
    }

    @Test
    public void isPointInArea() {
        final val x = parameters.getX();
        final val y = parameters.getY();
        final val r = parameters.getR();

        for (int i = 1; i < 10; i++) {
            Assert.assertEquals(expected,
                    areaChecker.IsPointInArea(new AreaCheckerParameters(x * i, y * i, r * i)));
        }

    }
}

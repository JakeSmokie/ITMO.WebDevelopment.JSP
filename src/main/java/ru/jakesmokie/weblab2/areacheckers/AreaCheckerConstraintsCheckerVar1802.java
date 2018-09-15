package ru.jakesmokie.weblab2.areacheckers;

import java.util.Arrays;
import java.util.List;

public class AreaCheckerConstraintsCheckerVar1802 extends AbstractAreaCheckerConstraintsChecker {
    private static final List<Double> validRValues = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);

    public boolean checkConstraints(AreaCheckerParameters p) {
        return p.getX() > -10 && p.getX() < 10 &&
                validRValues.contains(p.getR()) &&
                p.getY() > -10 && p.getY() < 10;
    }

    @Override
    public String toString() {
        return "X: (-10, 10)" +
                "Y: (-10, 10)" +
                "R: {1.0, 2.0, 3.0, 4.0, 5.0}";
    }
}

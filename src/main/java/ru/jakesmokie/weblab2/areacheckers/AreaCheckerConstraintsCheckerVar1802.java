package ru.jakesmokie.weblab2.areacheckers;

import java.util.Arrays;
import java.util.List;

public class AreaCheckerConstraintsCheckerVar1802 extends AbstractAreaCheckerConstraintsChecker {
    private static List<Double> validXValues = Arrays.asList(-2.0, -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.0);
    private static List<Double> validRValues = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);

    public boolean checkConstraints(AreaCheckerParameters p) {
        return validXValues.contains(p.getX()) &&
                validRValues.contains(p.getR()) &&
                p.getY() > -5 && p.getY() < 3;
    }
}

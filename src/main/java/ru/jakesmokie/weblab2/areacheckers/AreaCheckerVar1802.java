package ru.jakesmokie.weblab2.areacheckers;

import lombok.val;

public class AreaCheckerVar1802 extends AbstractAreaChecker {
    @Override
    public boolean IsPointInArea(AreaCheckerParameters parameters) {
        final val x = parameters.getX();
        final val y = parameters.getY();
        final val r = parameters.getR();

        if (x >= 0 && y >= 0 && checkFirstQuarter(x, y, r)) {
            return true;
        }

        if (x <= 0 && y >= 0 && checkSecondQuarter(x, y, r)) {
            return true;
        }

        return x >= 0 && y <= 0 && checkFourthQuarter(x, y, r);

    }

    private boolean checkFirstQuarter(double x, double y, double r) {
        return y <= -x + r;
    }

    private boolean checkSecondQuarter(double x, double y, double r) {
        return y <= r && x >= -r;
    }

    private boolean checkFourthQuarter(double x, double y, double r) {
        return x * x + y * y <= r * r / 4;
    }
}

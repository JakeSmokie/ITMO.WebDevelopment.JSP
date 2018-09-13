package ru.jakesmokie.weblab2.areacheckers;

public class AreaCheckerVar666 extends AbstractAreaChecker {
    public boolean IsPointInArea(AreaCheckerParameters parameters) {
        return (int) (parameters.getY() - parameters.getX() * parameters.getR() + 1337) % 10 == 0;
    }
}

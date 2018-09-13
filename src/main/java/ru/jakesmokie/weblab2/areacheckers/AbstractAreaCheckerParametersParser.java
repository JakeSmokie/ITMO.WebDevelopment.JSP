package ru.jakesmokie.weblab2.areacheckers;

import java.util.Map;

public abstract class AbstractAreaCheckerParametersParser {
    public abstract AreaCheckerParameters parseParameters(Map<String, String[]> params);
}

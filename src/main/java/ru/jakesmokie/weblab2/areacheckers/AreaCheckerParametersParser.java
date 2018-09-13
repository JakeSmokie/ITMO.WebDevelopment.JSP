package ru.jakesmokie.weblab2.areacheckers;

import lombok.val;

import java.util.Map;

public class AreaCheckerParametersParser extends AbstractAreaCheckerParametersParser {
    @Override
    public AreaCheckerParameters parseParameters(Map<String, String[]> params) {
        final val x = params.get("x")[0];
        final val y = params.get("y")[0];
        final val r = params.get("r")[0];

        try {
            return new AreaCheckerParameters(
                    Double.parseDouble(x),
                    Double.parseDouble(y),
                    Double.parseDouble(r)
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

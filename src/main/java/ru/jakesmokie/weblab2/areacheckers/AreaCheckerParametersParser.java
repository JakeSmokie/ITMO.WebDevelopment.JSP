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
            double x1 = Double.parseDouble(x);
            double y1 = Double.parseDouble(y);

            return new AreaCheckerParameters(
                    x1 == -0.0 ? 0.0 : x1,
                    y1 == -0.0 ? 0.0 : y1,
                    Double.parseDouble(r)
            );
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }
}

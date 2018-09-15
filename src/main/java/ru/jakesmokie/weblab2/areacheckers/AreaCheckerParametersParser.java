package ru.jakesmokie.weblab2.areacheckers;

import lombok.val;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

            x1 = truncateDouble(x1);
            y1 = truncateDouble(y1);

            return new AreaCheckerParameters(
                    x1 == -0.0 ? 0.0 : x1,
                    y1 == -0.0 ? 0.0 : y1,
                    Double.parseDouble(r)
            );
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    private double truncateDouble(double value) {
        return BigDecimal.valueOf(value)
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
    }
}

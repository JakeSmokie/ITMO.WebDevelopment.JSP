package ru.jakesmokie.weblab2.servlets;

import lombok.Builder;
import lombok.Data;
import ru.jakesmokie.weblab2.areacheckers.AreaCheckerParameters;

@Data
@Builder
public class AreaCheckServletResult {
    private AreaCheckerParameters parameters;
    private boolean isPointInArea;
    private String date;
}

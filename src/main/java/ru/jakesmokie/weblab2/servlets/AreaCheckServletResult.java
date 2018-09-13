package ru.jakesmokie.weblab2.servlets;

import lombok.Data;
import ru.jakesmokie.weblab2.areacheckers.AreaCheckerParameters;

@Data
public class AreaCheckServletResult {
    private AreaCheckerParameters parameters;
    private boolean result;
    private boolean success;
}

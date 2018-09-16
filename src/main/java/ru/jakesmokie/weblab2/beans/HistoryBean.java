package ru.jakesmokie.weblab2.beans;

import lombok.Data;
import ru.jakesmokie.weblab2.servlets.AreaCheckServletResult;

import java.util.concurrent.ConcurrentLinkedQueue;

@Data
public class HistoryBean {
    private final ConcurrentLinkedQueue<AreaCheckServletResult> history =
            new ConcurrentLinkedQueue<>();
}

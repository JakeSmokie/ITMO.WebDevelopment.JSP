package ru.jakesmokie.weblab2.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import lombok.val;
import ru.jakesmokie.weblab2.areacheckers.AbstractAreaChecker;
import ru.jakesmokie.weblab2.areacheckers.AbstractAreaCheckerConstraintsChecker;
import ru.jakesmokie.weblab2.areacheckers.AbstractAreaCheckerParametersParser;
import ru.jakesmokie.weblab2.areacheckers.AreaCheckerParameters;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AreaCheckServlet extends HttpServlet {

    private DateTimeFormatter dateTimeFormatter;
    private AbstractAreaChecker areaChecker;
    private AbstractAreaCheckerParametersParser parametersParser;
    private AbstractAreaCheckerConstraintsChecker constraintsChecker;
    private int maxHistorySize;

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        final val areaCheckerName = config.getInitParameter("area-checker");
        final val parametersParserName = config.getInitParameter("parameters-parser");
        final val constrainstsCheckerName = config.getInitParameter("constraints-checker");
        final val format = config.getInitParameter("datetime-format");
        final val historySize = config.getInitParameter("max-history-size");

        try {
            areaChecker = ((AbstractAreaChecker) createClassInstanceByName(areaCheckerName));
            parametersParser = ((AbstractAreaCheckerParametersParser) createClassInstanceByName(parametersParserName));
            constraintsChecker = ((AbstractAreaCheckerConstraintsChecker) createClassInstanceByName(constrainstsCheckerName));
        } catch (Exception e) {
            throw new Exception("Check web.xml and specify " +
                    "area-checker, parameters-parser, constraints-checker classes", e);
        }

        try {
            dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        } catch (Exception e) {
            throw new Exception("Please provide date time format in web.xml", e);
        }

        try {
            maxHistorySize = Integer.parseInt(historySize);
        } catch (Exception e) {
            throw new Exception("Please provide max history size in web.xml", e);
        }
    }

    @SneakyThrows
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        final val parameters = parametersParser.parseParameters(req.getParameterMap());
        final val result = getResult(parameters);

        sendResponse(resp, result);

        if (result == null) {
            return;
        }

        addResultToHistory(req, result);
    }

    private void addResultToHistory(HttpServletRequest req, AreaCheckServletResult result) {
        final val session = req.getSession();

        String historyAttribute = "history";
        synchronized (req.getSession()) {
            if (session.getAttribute(historyAttribute) == null) {
                session.setAttribute(historyAttribute, new ConcurrentLinkedQueue<AreaCheckServletResult>());
            }
        }

        final val history = (ConcurrentLinkedQueue<AreaCheckServletResult>)
                session.getAttribute(historyAttribute);

        if (history.size() > maxHistorySize) {
            history.poll();
        }

        history.add(result);
    }

    private AreaCheckServletResult getResult(AreaCheckerParameters parameters) {
        if (parameters == null || !constraintsChecker.checkConstraints(parameters)) {
            return null;
        }

        return AreaCheckServletResult.builder()
                .parameters(parameters)
                .isPointInArea(areaChecker.IsPointInArea(parameters))
                .date(LocalDateTime.now().format(dateTimeFormatter))
                .build();
    }

    @SneakyThrows
    private void sendResponse(HttpServletResponse resp, AreaCheckServletResult result) {
        if (result == null) {
            resp.sendError(400, "Parameters are not valid for constraints " +
                    constraintsChecker.toString());
            return;
        }

        resp.setContentType("application/json");
        resp.getWriter().println(gson.toJson(result));
    }

    @SneakyThrows
    private Object createClassInstanceByName(String className) {
        final val clazz = Class.forName(className);
        final val constructor = clazz.getConstructor();

        return constructor.newInstance();
    }
}

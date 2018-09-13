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

public class AreaCheckServlet extends HttpServlet {
    private AbstractAreaChecker areaChecker;
    private AbstractAreaCheckerParametersParser parametersParser;
    private AbstractAreaCheckerConstraintsChecker constraintsChecker;

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        final val areaCheckerName = config.getInitParameter("area-checker");
        final val parametersParserName = config.getInitParameter("parameters-parser");
        final val constrainstsCheckerName = config.getInitParameter("constraints-checker");

        try {
            areaChecker = ((AbstractAreaChecker) createClassInstanceByName(areaCheckerName));
            parametersParser = ((AbstractAreaCheckerParametersParser) createClassInstanceByName(parametersParserName));
            constraintsChecker = ((AbstractAreaCheckerConstraintsChecker) createClassInstanceByName(constrainstsCheckerName));
        } catch (Exception e) {
            throw new Exception("Check web.xml and specify " +
                    "area-checker, parameters-parser, constraints-checker classes", e);
        }
    }

    @SneakyThrows
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        final val session = req.getSession();
        final val writer = resp.getWriter();
        resp.setContentType("application/json");

        final val parameters =
                parametersParser.parseParameters(req.getParameterMap());

        final val result = getResult(parameters);

        if (result == null) {
            resp.sendError(400, "Parameters are not valid for constraints " + constraintsChecker.toString());
            return;
        }

        writer.println(gson.toJson(result));
    }

    private AreaCheckServletResult getResult(AreaCheckerParameters parameters) {
        final val result = new AreaCheckServletResult();
        result.setParameters(parameters);

        if (parameters == null || !constraintsChecker.checkConstraints(parameters)) {
            return null;
        }

        result.setResult(areaChecker.IsPointInArea(parameters));
        return result;
    }

    @SneakyThrows
    private Object createClassInstanceByName(String className) {
        final val clazz = Class.forName(className);
        final val constructor = clazz.getConstructor();

        return constructor.newInstance();
    }
}

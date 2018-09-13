package ru.jakesmokie.weblab2.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import lombok.val;
import ru.jakesmokie.weblab2.areacheckers.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AreaCheckServlet extends HttpServlet {
    private AbstractAreaChecker areaChecker = new AreaCheckerVar1802();
    private AbstractAreaCheckerParametersParser parametersParser = new AreaCheckerParametersParser();
    private AbstractAreaCheckerConstraintsChecker constraintsChecker = new AreaCheckerConstraintsCheckerVar1802();

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @SneakyThrows
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        final val session = req.getSession();
        final val writer = resp.getWriter();
        resp.setContentType("application/json");

        final val parameters =
                parametersParser.parseParameters(req.getParameterMap());

        final val result = getResult(parameters);

        writer.println(gson.toJson(result));
    }

    private AreaCheckServletResult getResult(AreaCheckerParameters parameters) {
        final val result = new AreaCheckServletResult();
        result.setParameters(parameters);

        if (parameters == null || !constraintsChecker.checkConstraints(parameters)) {
            result.setSuccess(false);
        } else {
            result.setResult(areaChecker.IsPointInArea(parameters));
            result.setSuccess(true);
        }

        return result;
    }
}

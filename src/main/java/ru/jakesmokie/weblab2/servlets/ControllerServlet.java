package ru.jakesmokie.weblab2.servlets;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import lombok.val;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log
public class ControllerServlet extends HttpServlet {

    @Override
    @SneakyThrows
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        final val parameters = req.getParameterMap();
        final val context = req.getServletContext();

        if (parameters.containsKey("x") &&
                parameters.containsKey("y") &&
                parameters.containsKey("r")) {

            context.getRequestDispatcher("/check")
                    .forward(req, resp);
            return;
        }

        context.getRequestDispatcher("/content.jsp")
                .forward(req, resp);
    }

}

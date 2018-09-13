package ru.jakesmokie.weblab2.filters;

import lombok.SneakyThrows;
import lombok.val;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AreaCheckerFilter implements Filter {
    @SneakyThrows
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        final val httpReq = (HttpServletRequest) req;

        if (!checkParameters(httpReq.getParameterMap())) {
            ((HttpServletResponse) resp).sendError(400, "Not all parameters are set");

            return;
        }

        chain.doFilter(req, resp);
    }

    private boolean checkParameters(Map<String, String[]> parameters) {
        return parameters.containsKey("x") &&
                parameters.containsKey("y") &&
                parameters.containsKey("r");
    }
}

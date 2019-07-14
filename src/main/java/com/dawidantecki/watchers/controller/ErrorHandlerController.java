package com.dawidantecki.watchers.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandlerController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public String errorPage(HttpServletRequest request, Model model) {
        int code = errorCode(request);
        String message = errorMessage(request);

        if (code == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute("errorMsg", message);
            return "404";
        } else if (code == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            model.addAttribute("errorMsg", message);
            return "500";
        }

        model.addAttribute("errorMsg", message);
        model.addAttribute("errorCode", code);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * Retrieve current error code
     *
     * @param request
     * @return error code
     */
    private int errorCode(HttpServletRequest request) {
        return (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    }

    /**
     * Retrieve current error message
     *
     * @param request
     * @return error message
     */
    private String errorMessage(HttpServletRequest request) {
        return (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
    }

}

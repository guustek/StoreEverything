package com.example.storeeverything.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.NoSuchElementException;

@ControllerAdvice
class ErrorControllerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView handleNoSuchElementException(Exception e) {
        Error error = new Error(HttpStatus.NOT_FOUND, e.getMessage());
        return buildModelAndView(error);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleExceptions(Exception e) {
        String stackTrace = convertStackTraceToString(e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = e.getMessage();
        Error error = new Error(status, message, stackTrace);
        return buildModelAndView(error);
    }

    private String convertStackTraceToString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    private ModelAndView buildModelAndView(Error error) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}

package dev.panthu.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice(annotations = Controller.class)  // Only for MVC controllers
public class MvcExceptionHandler {

    @ExceptionHandler
    public String handleMVCException(CustomerNotFoundException exc, Model model) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("message", exc.getMessage());
        model.addAttribute("timestamp", LocalDateTime.now());
        return "error/customer-error";
    }

    @ExceptionHandler
    public String handleMVCException(Exception exc, Model model) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("message", exc.getMessage());
        model.addAttribute("timestamp", LocalDateTime.now());
        return "error/customer-error";
    }
} 
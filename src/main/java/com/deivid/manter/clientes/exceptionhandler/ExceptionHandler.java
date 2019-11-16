package com.deivid.manter.clientes.exceptionhandler;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public ExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<Erro> erros = this.createErrorList(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        String userMessage = messageSource.getMessage("recurso.nao.encontrado", null, LocaleContextHolder.getLocale());
        String devMessage = ex.getCause().toString();
        List<Erro> erros = Collections.singletonList(new Erro(userMessage, devMessage));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> createErrorList(BindingResult bindingResult){
        List<Erro> erros = new ArrayList<>();

        bindingResult.getFieldErrors().forEach( f -> {
            String userMessage = messageSource.getMessage(f , LocaleContextHolder.getLocale());
            String devMessage = f.toString();

            erros.add(new Erro(userMessage, devMessage));
        });

        return erros;
    }

    public static class Erro{

        @Getter
        private String userMessage;
        @Getter
        private String devMessage;

        public Erro(String userMessage, String devMessage) {
            this.userMessage = userMessage;
            this.devMessage = devMessage;
        }
    }
}

package com.example.esr.api.exceptionhandler;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
                                                                       WebRequest request){

        var status = HttpStatus.NOT_FOUND;
        var problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<Object> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (Objects.isNull(body)) {

            body = Problem.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();

        }else if(body instanceof String){

            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();

        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {

        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitulo())
                .dateTime(LocalDateTime.now())
                .detail(detail);

    }
}

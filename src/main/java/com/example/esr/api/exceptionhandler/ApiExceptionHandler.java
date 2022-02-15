package com.example.esr.api.exceptionhandler;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request){

      var status = HttpStatus.INTERNAL_SERVER_ERROR;

      var problemType = ProblemType.ERRO_DE_SISTEMA;

      var detail = UserMessage.MSG_ERRO_GENERICA.getMensagem();

      var userMessage = UserMessage.MSG_ERRO_GENERICA.getMensagem();

        // Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
        // fazendo logging) para mostrar a stacktrace no console
        // Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
        // para você durante, especialmente na fase de desenvolvimento
        ex.printStackTrace();

        var problem = createProblemBuilder(status, problemType, detail, userMessage).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
                                                                       WebRequest request){

        var status = HttpStatus.NOT_FOUND;

        var problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;

        var detail = ex.getMessage();

        var userMessage = UserMessage.MSG_RECURSO_NAO_ENCONTRADO.getMensagem();

        var problem = createProblemBuilder(status, problemType, detail, userMessage).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request) {

        var status = HttpStatus.BAD_REQUEST;

        var problemType = ProblemType.ERRO_NEGOCIO;

        var detail = ex.getMessage();

        var userMessage = UserMessage.MSG_VIOLACAO_REGRA_NEGOCIO.getMensagem();

        var problem = createProblemBuilder(status, problemType, detail, userMessage).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<Object> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){

        var status = HttpStatus.CONFLICT;

        var problemType = ProblemType.ENTIDADE_EM_USO;

        var detail = ex.getMessage();

        var userMessage = UserMessage.MSG_OPERACAO_NAO_PERMITIDA.getMensagem();

        var problem = createProblemBuilder(status, problemType, detail, userMessage).build();


        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


        var problemType = ProblemType.DADOS_INVALIDOS;

        var detail = UserMessage.MSG_DADOS_INVALIDOS.getMensagem();

        var userMessage = UserMessage.MSG_DADOS_INVALIDOS.getMensagem();

        var bidingResult = ex.getBindingResult();

        List<Field> problemFields = bidingResult.getFieldErrors()
                                    .stream()
                                    .map(fieldError -> Field.builder()
                                    .message(fieldError.getField().toUpperCase() +" "+ fieldError.getDefaultMessage())
                                    .build())
                                    .collect(Collectors.toList());

        var problem = createProblemBuilder(status, problemType, detail, userMessage)
                            .fields(problemFields)
                            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var rootCause = ExceptionUtils.getRootCause(ex);

        if(rootCause instanceof InvalidFormatException){

            return handleInvalidFormatException((InvalidFormatException)rootCause, headers, status, request);

        }

        if(rootCause instanceof PropertyBindingException){

            return handlePropertyBindingException((PropertyBindingException)rootCause, headers, status, request);

        }

        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        var detail = "O corpo da requisição está inválido.Verifique erro de sintaxe.";

        var userMessage = UserMessage.MSG_DADOS_INVALIDOS.getMensagem();

        var problem = createProblemBuilder(status, problemType, detail, userMessage).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        var detail = String.format("A propiedade '%s' não existe no recurso %s.", ex.getPropertyName(), ex.getReferringClass().getSimpleName());

        var userMessage = UserMessage.MSG_DADOS_INVALIDOS.getMensagem();

        var problem = createProblemBuilder(status, problemType, detail, userMessage).build();


        return handleExceptionInternal(ex, problem, headers, status, request);

    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        // Criei o método joinPath para reaproveitar em todos os métodos que precisam
        // concatenar os nomes das propriedades (separando por ".")
        var path = joinPath(ex.getPath());

        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        String detail = String.format("A propiedade '%s' recebeu o valor '%s' que é de um tipo inválido." +
                                        "Corrija e informe um valor compatível com o tipo %s.", path, ex.getValue(), ex.getTargetType().getSimpleName());

        var userMessage = UserMessage.MSG_DADOS_INVALIDOS.getMensagem();

        var problem = createProblemBuilder(status, problemType, detail, userMessage).build();


        return handleExceptionInternal(ex, problem, headers, status, request);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (Objects.isNull(body)) {

            body = Problem.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(UserMessage.MSG_ERRO_GENERICA.getMensagem())
                    .timestamp(LocalDateTime.now())
                    .build();

        }else if(body instanceof String){

            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();

        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail,
                                                        String userMessage) {

        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitulo())
                .timestamp(LocalDateTime.now())
                .userMessage(userMessage)
                .detail(detail);

    }

    private String joinPath(List<JsonMappingException.Reference> reference){

        return reference.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

    }

}

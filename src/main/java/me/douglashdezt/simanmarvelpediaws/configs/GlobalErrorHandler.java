package me.douglashdezt.simanmarvelpediaws.configs;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import me.douglashdezt.simanmarvelpediaws.dtos.GeneralResponse;
import me.douglashdezt.simanmarvelpediaws.utils.ErrorMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse> generalErrorHandler(Exception e) {
        e.printStackTrace();
        log.error(e.getClass().descriptorString());
        log.error(e.getMessage());
        return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GeneralResponse> idErrorHandler(Exception e) {
        log.error(e.getMessage());
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GeneralResponse> emptyBody(Exception e) {
        log.error(e.getMessage());
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<GeneralResponse> validationHandler(HandlerMethodValidationException e) {
        log.error(e.getMessage());
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> validationDTOError(MethodArgumentNotValidException e) {
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, ErrorMapper.map(
                e.getFieldErrors()
        ));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GeneralResponse> noResourceFound(NoResourceFoundException e) {
        return GeneralResponse.getResponse(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<GeneralResponse> validationHandler(Exception e) {
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<GeneralResponse> feignError(FeignException e) {
        log.error(e.getMessage());
        return GeneralResponse.getResponse(HttpStatus.OK);
    }
}
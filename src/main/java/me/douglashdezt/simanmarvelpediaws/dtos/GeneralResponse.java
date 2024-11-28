package me.douglashdezt.simanmarvelpediaws.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class GeneralResponse {
    private String message;
    private Object data;

    // Método que retorna una respuesta con HttpStatus.OK y mensaje por defecto
    public static ResponseEntity<GeneralResponse> getResponse() {
        return new ResponseEntity<>(
                new GeneralResponse(HttpStatus.OK.getReasonPhrase(), null),
                HttpStatus.OK
        );
    }

    // Método que recibe HttpStatus, mensaje y datos
    public static ResponseEntity<GeneralResponse> getResponse(HttpStatus status, String message, Object data) {
        return new ResponseEntity<>(
                new GeneralResponse(message, data),
                status
        );
    }

    // Método que recibe HttpStatus y mensaje
    public static ResponseEntity<GeneralResponse> getResponse(HttpStatus status, String message) {
        return new ResponseEntity<>(
                new GeneralResponse(message, null),
                status
        );
    }

    // Método que recibe HttpStatus y datos
    public static ResponseEntity<GeneralResponse> getResponse(HttpStatus status, Object data) {
        return new ResponseEntity<>(
                new GeneralResponse(status.getReasonPhrase(), data),
                status
        );
    }

    // Método que recibe mensaje y datos (status 200 por defecto)
    public static ResponseEntity<GeneralResponse> getResponse(String message, Object data) {
        return new ResponseEntity<>(
                new GeneralResponse(message, data),
                HttpStatus.OK
        );
    }

    // Método que recibe solo HttpStatus
    public static ResponseEntity<GeneralResponse> getResponse(HttpStatus status) {
        return new ResponseEntity<>(
                new GeneralResponse(status.getReasonPhrase(), null),
                status
        );
    }

    // Método que recibe solo mensaje (status 200 por defecto)
    public static ResponseEntity<GeneralResponse> getResponse(String message) {
        return new ResponseEntity<>(
                new GeneralResponse(message, null),
                HttpStatus.OK
        );
    }

    // Método que recibe solo datos (status 200 por defecto)
    public static ResponseEntity<GeneralResponse> getResponse(Object data) {
        return new ResponseEntity<>(
                new GeneralResponse(HttpStatus.OK.getReasonPhrase(), data),
                HttpStatus.OK
        );
    }
}

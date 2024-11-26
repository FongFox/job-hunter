package vn.hoidanit.jobhunter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.hoidanit.jobhunter.domain.RestResponse;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = IdInvalidException.class)
    public ResponseEntity<RestResponse<Object>> handleIdInvalidException(IdInvalidException idException) {
        RestResponse<Object> restResponse = new RestResponse<Object>();

        restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restResponse.setError(idException.getMessage());
        restResponse.setMessage("IdInvalidException");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }
}

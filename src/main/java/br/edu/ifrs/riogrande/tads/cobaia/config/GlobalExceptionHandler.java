package br.edu.ifrs.riogrande.tads.cobaia.config;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j // Simple Log for Java
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return ResponseEntity.badRequest().body(
      Map.of("erros", e.getFieldErrors())
    );
  }
  
  @ExceptionHandler(IllegalStateException.class)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void handleIllegalStateException(IllegalStateException e) {
    log.info("Ocorreu exceção " + e);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<?> handleIllegalArgumentException(
      IllegalArgumentException e) {
    
    log.warn("Ocorreu exceção", e);
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  void catchAll(Exception e) {
    // log.error("Exceção", e);
    log.error("Exceção " + e.getMessage());
  }
}

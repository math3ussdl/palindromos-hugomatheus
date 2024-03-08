package me.math3ussdl.application.handler;

import me.math3ussdl.domain.exception.MatrixMalformedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class GlobalExceptionHandlerTest {

    @Test
    @DisplayName("It should handle the MatrixMalformedException, return BAD_REQUEST status code")
    public void testHandleMatrixMalformedException() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        MatrixMalformedException exception = new MatrixMalformedException("Matrix is malformed");

        // Act
        ResponseEntity<Object> response = handler.handleMatrixMalformedException(exception);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("Matrix is malformed");
    }

    @Test
    @DisplayName("It should handle the RuntimeException, return INTERNAL_SERVER_ERROR status code")
    public void testHandleOtherExceptions() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        RuntimeException exception = new RuntimeException("Unexpected error");

        // Act
        ResponseEntity<Object> response = handler.handleOtherExceptions(exception);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("An unexpected error occurred!");
    }
}

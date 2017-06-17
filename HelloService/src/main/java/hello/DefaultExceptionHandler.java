package hello;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.transaction.RollbackException;

@RestControllerAdvice
public class DefaultExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handlePersistenceConflict() {}

    @ExceptionHandler(value = {TransactionSystemException.class})
    public ResponseEntity handleTxException(TransactionSystemException ex) {
        Throwable t = ex.getCause();

        if (t instanceof RollbackException) {
            t = t.getCause();
        }

        if(t instanceof javax.validation.ConstraintViolationException ||
           t instanceof org.hibernate.exception.ConstraintViolationException) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(t.getMessage());
        } else {
            return new ResponseEntity(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        }
    }
}

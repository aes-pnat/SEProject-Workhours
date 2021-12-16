package progi.dugonogiprogi.radnovrijeme.backend.rest.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MissingEmployeeException.class)
    protected ResponseEntity<?> handleMissingEmployee(Exception e, WebRequest request) {
        Map<String, String> props = new HashMap<>();
        props.put("message", e.getMessage());
        props.put("status", "417");
        props.put("error", "Expectation Failed");
        log.error(e.getMessage());
        return new ResponseEntity<>(props, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(RequestDeniedException.class)
    protected ResponseEntity<?> handleDeniedRequest(Exception e, WebRequest request) {
        Map<String, String> props = new HashMap<>();
        props.put("message", e.getMessage());
        props.put("status", "400");
        props.put("error", "Bad Request");
        log.error(e.getMessage());
        return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityMissingException.class)
    protected ResponseEntity<?> handleEntityMissing(Exception e, WebRequest request) {
        Map<String, String> props = new HashMap<>();
        props.put("message", e.getMessage());
        props.put("status", "417");
        props.put("error", "Expectation Failed");
        log.error(e.getMessage());
        return new ResponseEntity<>(props, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<?> handleIllegalArgument(Exception e, WebRequest request) {
        Map<String, String> props = new HashMap<>();
        props.put("message", e.getMessage());
        props.put("status", "400");
        props.put("error", "Bad Request");
        log.error(e.getMessage());
        return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordCheckException.class)
    protected ResponseEntity<?> handleInvalidPasswordCheck(Exception e, WebRequest request) {
        Map<String, String> props = new HashMap<>();
        props.put("message", e.getMessage());
        props.put("status", "400");
        props.put("error", "Bad Request");
        log.error(e.getMessage());
        return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e, WebRequest request) {
        Map<String, String> props = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for(var error : e.getAllErrors()) {
            if(first) {
                log.error(error.getDefaultMessage());
                sb.append(error.getDefaultMessage());
                first = false;
            }
            else {
                log.error(error.getDefaultMessage());
                sb.append(" ");
                sb.append(error.getDefaultMessage());
            }
        }
        props.put("message", sb.toString());
        props.put("status", "400");
        props.put("error", "Bad Request");
        return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
    }
}

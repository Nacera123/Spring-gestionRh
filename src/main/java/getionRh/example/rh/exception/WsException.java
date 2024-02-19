package getionRh.example.rh.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

/**
 * Renvoie les exception
 */
public class WsException extends ResponseStatusException {
    public WsException(HttpStatusCode status, String message) {
        super(status, message);
    }
}

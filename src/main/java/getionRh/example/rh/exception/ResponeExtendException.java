package getionRh.example.rh.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ResponeExtendException extends ResponseStatusException {


    public ResponeExtendException(HttpStatusCode status) {
        super(status);
    }

    public ResponeExtendException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public ResponeExtendException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public ResponeExtendException(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    protected ResponeExtendException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }

    //
//    public ResponeExtendException(HttpStatusCode status) {
//        super(status);
//    }
    @Override
    public String getMessage() {
        return  (super.getReason() != null ? " \"" + super.getReason() + "\"" : "");
    }





}

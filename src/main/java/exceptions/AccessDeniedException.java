package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccessDeniedException extends Exception{

    private static final long serialVersionUID = 1L;

    public AccessDeniedException(String message){
        super(message);
    }
}
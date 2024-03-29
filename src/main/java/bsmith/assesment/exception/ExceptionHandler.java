package bsmith.assesment.exception;

import bsmith.assesment.dto.GameResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ExceptionHandler {

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(GameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GameResponse badRequestHandler(GameException ex) {
        String message = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), message);
        return new GameResponse(apiError);
    }

}


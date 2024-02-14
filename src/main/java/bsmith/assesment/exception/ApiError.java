package bsmith.assesment.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiError {
    private Integer statusCode;
    private String message;
    private List<ValidationError> errors;

    public ApiError(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
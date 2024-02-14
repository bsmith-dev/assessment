package bsmith.assesment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
class ValidationError {
    private String fieldName;
    private Object value;
    private String message;

}
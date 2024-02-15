package bsmith.assesment.dto;

import bsmith.assesment.exception.ApiError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameResponse {

    private Long gameId;
    private String card;
    private String message;
    private ApiError error;

    public GameResponse(ApiError apiError) {
        this.error = apiError;
    }
}

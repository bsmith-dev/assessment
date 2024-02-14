package bsmith.assesment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameResponseDto {
    private String gameId;
    private String card;
    private String deck;
    private String message;

}

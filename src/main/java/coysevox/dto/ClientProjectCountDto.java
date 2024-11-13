package coysevox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientProjectCountDto {
    private String clientName;
    private Long projectCount;
}

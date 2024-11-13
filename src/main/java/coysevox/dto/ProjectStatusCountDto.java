package coysevox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectStatusCountDto {
    private String status;
    private Long count;
}
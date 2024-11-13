package coysevox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryProductCountDto {
    private String categoryName;
    private Long productCount;
}
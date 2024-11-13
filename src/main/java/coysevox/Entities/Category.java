package coysevox.Entities;

import jakarta.persistence.*;
import lombok.Data;
import coysevox.dto.CategoryDto;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    @Lob
    private String description;

    public CategoryDto getDto(){
        CategoryDto categoryDto =new CategoryDto();
        categoryDto.setId(id);
        categoryDto.setName(name);
        categoryDto.setDescription(description);
        return categoryDto;
    }
}

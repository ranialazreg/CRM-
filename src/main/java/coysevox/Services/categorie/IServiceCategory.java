package coysevox.Services.categorie;



import coysevox.Entities.Category;
import coysevox.dto.CategoryDto;

import java.util.List;

public interface IServiceCategory {

    public Category CreateCategory(CategoryDto cat);
    public List<Category> getAllCategory();



    }

package coysevox.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import coysevox.Entities.Category;
import coysevox.Services.categorie.ServiceCategory;
import coysevox.dto.CategoryDto;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/categorie")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final ServiceCategory serviceCategory;

    @PostMapping("/Add")
    ResponseEntity<Category> CreateCategory(@ModelAttribute CategoryDto cat){
        Category category = serviceCategory.CreateCategory(cat);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @GetMapping("")
    ResponseEntity<List<Category>> GetAll(){
        List<Category> list = serviceCategory.getAllCategory();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getcatById(@PathVariable Long id){
        CategoryDto itemsDto = serviceCategory.getcatById(id);
        if (itemsDto != null){
            return ResponseEntity.ok(itemsDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/Update/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateItem(@PathVariable Long id ,@ModelAttribute CategoryDto itemsDto) throws IOException {
        CategoryDto itemDto1 = serviceCategory.updateDto(id,itemsDto);
        if (itemDto1 != null){
            return ResponseEntity.ok(itemDto1);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteitem(@PathVariable Long id){
        boolean delete = serviceCategory.deleteitem(id);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }
}

package coysevox.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import coysevox.Services.produit.ServiceProduct;
import coysevox.dto.ProductDto;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/produit")
@RequiredArgsConstructor
public class AdminProductController {

    private final ServiceProduct serviceProduct;

   @PostMapping("/Add")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto productDto1 = serviceProduct.addProduct(productDto);
return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);

    }


    @GetMapping("")
    //@PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<ProductDto>> GetAll(){
        List<ProductDto> list = serviceProduct.getAllProd();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);

    }
    @GetMapping("/search/{name}")
    ResponseEntity<List<ProductDto>> GetAllByName(@PathVariable String name){
        List<ProductDto> list = serviceProduct.GetAllByName(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }


    @DeleteMapping("/delete/{id}")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteprod(@PathVariable Long id){
        boolean delete = serviceProduct.deleteprod(id);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getprodById(@PathVariable Long id){
        ProductDto itemsDto = serviceProduct.getItemById(id);
        if (itemsDto != null){
            return ResponseEntity.ok(itemsDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/Update/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> updateItem(@PathVariable Long id ,@ModelAttribute ProductDto itemsDto) throws IOException {
        ProductDto itemDto1 = serviceProduct.updateDto(id,itemsDto);
        if (itemDto1 != null){
            return ResponseEntity.ok(itemDto1);
        }else {
            return ResponseEntity.notFound().build();
        }
    }



    }

package coysevox.Services.produit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import coysevox.Entities.Category;
import coysevox.Entities.Product;
import coysevox.Repository.CategoryRepository;
import coysevox.Repository.ProductRepository;
import coysevox.dto.CategoryProductCountDto;
import coysevox.dto.ProductDto;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceProduct implements IServiceProduct{
    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product prod = new Product();
        prod.setName(productDto.getName());
        prod.setDescription(productDto.getDescription());
        prod.setPrix(productDto.getPrix());
        prod.setId(productDto.getId());
        Category cat = categoryRepository.findById(productDto.getCategoryId()).get();
        prod.setCategory(cat);
        prod.setQuantity(productDto.getQuantity());

        if (productDto.getImg() == null) {
            // If the image is null, you can choose to skip setting the image
            // or set a default value (if applicable). For now, we will skip.
            prod.setImg(null);
            return productRepository.save(prod).getDto(); // Save without the image
        }
        prod.setImg(productDto.getImg().getBytes());
        return productRepository.save(prod).getDto();
    }
    public ProductDto getItemById(Long id){
        Optional<Product> optionalItems = productRepository.findById(id);
        if (optionalItems.isPresent()){
            return optionalItems.get().getDto();
        }else {
            return null;
        }

    }


    public ProductDto updateDto(Long id , ProductDto itemsDto) throws IOException {
        Optional<Product> optionalItems = productRepository.findById(id);
        if (optionalItems.isPresent() ){
            Product items = optionalItems.get();
            items.setName(itemsDto.getName());
            items.setDescription(itemsDto.getDescription());
            items.setPrix(itemsDto.getPrix());
            Category cat = categoryRepository.findById(itemsDto.getCategoryId()).get();
            items.setCategory(cat);
            items.setQuantity(itemsDto.getQuantity());
            if (itemsDto.getImg() != null){
                items.setImg(itemsDto.getImg().getBytes());
            }
            return productRepository.save(items).getDto();
        }else {
            return null;
        }
    }

    public List<ProductDto> getAllProd(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> GetAllByName(String name){
        List<Product> list = productRepository.findAllByNameContaining(name);
        return list.stream().map(Product::getDto).collect(Collectors.toList());

    }

    public boolean deleteprod(Long id){
        Optional<Product> optionalProduct= productRepository.findById(id);
        if(optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
    public List<CategoryProductCountDto> getProductCountsByCategory() {
        List<Product> products = productRepository.findAll();
        Map<String, Long> categoryCounts = new HashMap<>();

        for (Product product : products) {
            String categoryName = product.getCategory().getName(); // Adjust based on your model
            categoryCounts.put(categoryName, categoryCounts.getOrDefault(categoryName, 0L) + 1);
        }

        List<CategoryProductCountDto> result = new ArrayList<>();
        for (Map.Entry<String, Long> entry : categoryCounts.entrySet()) {
            result.add(new CategoryProductCountDto(entry.getKey(), entry.getValue()));
        }
        return result;
    }


}

package coysevox.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import coysevox.Services.projet.ServiceProjet;
import coysevox.dto.ProjetDto;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/projet")
public class ProjetController {
    @Autowired
    ServiceProjet serviceProjet;

    @GetMapping("")
    ResponseEntity<List<ProjetDto>> GetAll(){
        List<ProjetDto> list = serviceProjet.getAllProd();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);

    }

    @PostMapping("/Add")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjetDto> addDevi(@ModelAttribute ProjetDto projetDto) throws IOException {
        ProjetDto clientDto1 = serviceProjet.addproj(projetDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDto1);

    }

    @DeleteMapping("/delete/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletedevi(@PathVariable Long id){
        boolean delete = serviceProjet.deleteprod(id);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetDto> getcliById(@PathVariable Long id){
        ProjetDto itemsDto = serviceProjet.getItemById(id);
        if (itemsDto != null){
            return ResponseEntity.ok(itemsDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/Update/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjetDto> updateItem(@PathVariable Long id ,@ModelAttribute ProjetDto itemsDto) throws IOException {
        ProjetDto itemDto1 = serviceProjet.updateDto(id,itemsDto);
        if (itemDto1 != null){
            return ResponseEntity.ok(itemDto1);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}

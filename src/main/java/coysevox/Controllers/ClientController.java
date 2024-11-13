package coysevox.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import coysevox.Services.client.ServiceClient;
import coysevox.dto.clientDto;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ServiceClient serviceClient;


    @GetMapping("")
    ResponseEntity<List<clientDto>> GetAll(){
        List<clientDto> list = serviceClient.getAllProd();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);

    }

    @PostMapping("/Add")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<clientDto> addDevi(@ModelAttribute clientDto clientDto) throws IOException {
        clientDto clientDto1 = serviceClient.addclient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDto1);

    }

    @DeleteMapping("/delete/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletedevi(@PathVariable Long id){
        boolean delete = serviceClient.deleteprod(id);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<clientDto> getcliById(@PathVariable Long id){
        clientDto itemsDto = serviceClient.getItemById(id);
        if (itemsDto != null){
            return ResponseEntity.ok(itemsDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/Update/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<clientDto> updateItem(@PathVariable Long id ,@ModelAttribute clientDto itemsDto) throws IOException {
        clientDto itemDto1 = serviceClient.updateDto(id,itemsDto);
        if (itemDto1 != null){
            return ResponseEntity.ok(itemDto1);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}

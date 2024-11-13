package coysevox.Services.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import coysevox.Entities.client;
import coysevox.Repository.ClientRepository;
import coysevox.dto.clientDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceClient {
    private final ClientRepository clientRepository;

    public clientDto addclient(clientDto clientDto) throws IOException {
        client prod = new client();
        prod.setName(clientDto.getName());
        prod.setEmail(clientDto.getEmail());
        prod.setId(clientDto.getId());
        prod.setImg(clientDto.getImg().getBytes());
        prod.setPhoneNumber(clientDto.getPhoneNumber());
        return clientRepository.save(prod).getDto();
    }
    public clientDto getItemById(Long id){
        Optional<client> optionalItems = clientRepository.findById(id);
        if (optionalItems.isPresent()){
            return optionalItems.get().getDto();
        }else {
            return null;
        }

    }


    public clientDto updateDto(Long id , clientDto itemsDto) throws IOException {
        Optional<client> optionalItems = clientRepository.findById(id);
        if (optionalItems.isPresent() ){
            client items = optionalItems.get();
            items.setName(itemsDto.getName());
            items.setEmail(itemsDto.getEmail());
            items.setPhoneNumber(items.getPhoneNumber());
            if (itemsDto.getImg() != null){
                items.setImg(itemsDto.getImg().getBytes());
            }
            return clientRepository.save(items).getDto();
        }else {
            return null;
        }
    }

    public List<clientDto> getAllProd(){
        List<client> products = clientRepository.findAll();
        return products.stream().map(client::getDto).collect(Collectors.toList());
    }


    public boolean deleteprod(Long id){
        Optional<client> optionalProduct= clientRepository.findById(id);
        if(optionalProduct.isPresent()){
            clientRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }





}

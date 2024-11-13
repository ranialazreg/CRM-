package coysevox.Entities;

import jakarta.persistence.*;
import lombok.Data;
import coysevox.dto.clientDto;

@Entity
@Data
public class client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;

    private String email ;

    private Long phoneNumber ;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img ;

    public clientDto getDto() {
        clientDto client = new clientDto();
        client.setId(id);
        client.setName(name);
        client.setEmail(email);
        client.setByteImg(img);
        client.setPhoneNumber(phoneNumber);

        return client;
    }



}

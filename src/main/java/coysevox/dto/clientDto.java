package coysevox.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data

public class clientDto {

    private Long id ;

    private String name ;
    private String email ;
    private byte[] byteImg ;
    private MultipartFile img;
    private Long phoneNumber ;

}

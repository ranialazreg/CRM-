package coysevox.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import coysevox.dto.ProjetDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String description;
    private String budget;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "client_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private client c;

    public ProjetDto getDto(){
        ProjetDto projetDto =new ProjetDto();
        projetDto.setId(id);
        projetDto.setStatus(status);
        projetDto.setBudget(budget);
        projetDto.setDescription(description);
        projetDto.setStartDate(startDate);
        projetDto.setEndDate(endDate);
        projetDto.setClientId(c.getId());
        projetDto.setClientName(c.getName());
        return projetDto;
    }

    // Additional attributes


}

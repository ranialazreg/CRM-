package coysevox.Services.projet;

import coysevox.dto.ClientProjectCountDto;
import coysevox.dto.ProjectStatusCountDto;
import coysevox.dto.ProjetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import coysevox.Entities.Projet;
import coysevox.Entities.client;
import coysevox.Repository.ClientRepository;
import coysevox.Repository.ProjetRepository;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceProjet {

    private final ProjetRepository projetRepository;
    private final ClientRepository clientRepository;


    public ProjetDto addproj(ProjetDto projetDto) throws IOException {
        Projet projet = new Projet();
        projet.setBudget(projetDto.getBudget());
        projet.setDescription(projetDto.getDescription());
        projet.setId(projetDto.getId());
        projet.setStartDate(projetDto.getStartDate());
        projet.setEndDate(projetDto.getEndDate());
        client c = clientRepository.findById(projetDto.getClientId()).get();
        projet.setC(c);
        projet.setStatus(projetDto.getStatus());
        return projetRepository.save(projet).getDto();
    }
    public ProjetDto getItemById(Long id){
        Optional<Projet> optionalItems = projetRepository.findById(id);
        if (optionalItems.isPresent()){
            return optionalItems.get().getDto();
        }else {
            return null;
        }

    }


    public ProjetDto updateDto(Long id , ProjetDto projetDto) throws IOException {
        Optional<Projet> optionalItems = projetRepository.findById(id);
        if (optionalItems.isPresent() ){
            Projet projet = optionalItems.get();
            projet.setBudget(projetDto.getBudget());
            projet.setDescription(projetDto.getDescription());
            projet.setId(projetDto.getId());
            projet.setStartDate(projetDto.getStartDate());
            projet.setEndDate(projetDto.getEndDate());
            client c = clientRepository.findById(projetDto.getClientId()).get();
            projet.setC(c);
            projet.setStatus(projetDto.getStatus());
            return projetRepository.save(projet).getDto();
        }else {
            return null;
        }
    }

    public List<ProjetDto> getAllProd(){
        List<Projet> projets = projetRepository.findAll();
        return projets.stream().map(Projet::getDto).collect(Collectors.toList());
    }


    public boolean deleteprod(Long id){
        Optional<Projet> optionalProduct= projetRepository.findById(id);
        if(optionalProduct.isPresent()){
            projetRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public List<ProjectStatusCountDto> getProjectCountsByStatus() {
        List<Projet> projets = projetRepository.findAll();
        Map<String, Long> statusCounts = new HashMap<>();

        for (Projet projet : projets) {
            String status = projet.getStatus();
            statusCounts.put(status, statusCounts.getOrDefault(status, 0L) + 1);
        }

        List<ProjectStatusCountDto> result = new ArrayList<>();
        for (Map.Entry<String, Long> entry : statusCounts.entrySet()) {
            result.add(new ProjectStatusCountDto(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    public List<ClientProjectCountDto> getProjectCountsByClient() {
        List<Projet> projets = projetRepository.findAll();
        Map<String, Long> clientCounts = new HashMap<>();

        for (Projet proj : projets) {
            String clientName = proj.getC().getName(); // Adjust based on your model
            clientCounts.put(clientName, clientCounts.getOrDefault(clientName, 0L) + 1);
        }

        List<ClientProjectCountDto> result = new ArrayList<>();
        for (Map.Entry<String, Long> entry : clientCounts.entrySet()) {
            result.add(new ClientProjectCountDto(entry.getKey(), entry.getValue()));
        }
        return result;
    }


}

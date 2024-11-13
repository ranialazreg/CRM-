package coysevox.Controllers;


import coysevox.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import coysevox.Entities.KpiResponse;
import coysevox.Services.produit.ServiceProduct;
import coysevox.Services.projet.ServiceProjet;
import coysevox.dto.CategoryProductCountDto;
import coysevox.dto.ClientProjectCountDto;
import coysevox.dto.ProjectStatusCountDto;

import java.util.List;

@RestController
@RequestMapping("/api/Kpi")
public class KpiController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    DeviRepository deviRepository;
    @Autowired
    ServiceProduct serviceProduct;
    @Autowired
    ServiceProjet serviceProjet;



    @GetMapping
    public KpiResponse getKpiData() {
        KpiResponse response = new KpiResponse();
        response.setTotalCategories(categoryRepository.count()); // Replace with actual count
        response.setTotalClients(clientRepository.count());    // Replace with actual count
        response.setTotalProducts(productRepository.count());     // Replace with actual count
        response.setTotalProjects(productRepository.count());      // Replace with actual count
        response.setTotalDevis(deviRepository.count());         // Replace with actual count

        return response;
    }
    @GetMapping("/category-product-counts")
    public List<CategoryProductCountDto> getProductCountsByCategory() {
        return serviceProduct.getProductCountsByCategory();
    }
    @GetMapping("/client-project-counts")
    public List<ClientProjectCountDto> getClientCountsByProject() {
        return serviceProjet.getProjectCountsByClient();
    }

    @GetMapping("/project-status-counts")
    public List<ProjectStatusCountDto> getProjectCountsByStatus() {
        return serviceProjet.getProjectCountsByStatus();
    }


// Create a DTO for your response

}

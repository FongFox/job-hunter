package vn.hoidanit.jobhunter.controller;

import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.dto.RestfulPaginationDTO;
import vn.hoidanit.jobhunter.service.CompanyService;
import vn.hoidanit.jobhunter.util.annotation.ApiMessage;
import vn.hoidanit.jobhunter.util.exception.DataNotFoundException;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("")
    public ResponseEntity<Company> createNewCompany(@RequestBody @Valid Company resquestCompany) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.companyService.handleCreateCompany(resquestCompany));
    }

    @GetMapping("")
    @ApiMessage("Fetch All Companies")
    public ResponseEntity<RestfulPaginationDTO> fetchAllCompanies(@Filter Specification<Company> specification, Pageable pageable) {
        return ResponseEntity.ok(this.companyService.handleFetchAllCompaniesWithPagination(specification, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Company> fetchCompanyById(@PathVariable int id) throws DataNotFoundException {
        Company dbCompany = this.companyService.handFetchCompanyById(id);
        if (dbCompany == null) {
            throw new DataNotFoundException("Company not found with this id!");
        }

        return ResponseEntity.ok(dbCompany);
    }

    @PutMapping("")
    public ResponseEntity<Company> updateCompany(@RequestBody @Valid Company resquestCompany) throws DataNotFoundException {
        if (this.companyService.handFetchCompanyById(resquestCompany.getId()) == null) {
            throw new DataNotFoundException("Company not found with this id!");
        }

        return ResponseEntity.ok(this.companyService.handleUpdateCompany(resquestCompany));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable int id) throws DataNotFoundException {
        boolean isCompanyDeleted = this.companyService.handleDeleteCompanyById(id);
        if (!isCompanyDeleted) {
            throw new DataNotFoundException("Company not found with this id!");
        }

        return ResponseEntity.ok(null);
    }
}

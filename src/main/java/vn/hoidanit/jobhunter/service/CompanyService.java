package vn.hoidanit.jobhunter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.dto.Meta;
import vn.hoidanit.jobhunter.dto.RestfulPaginationDTO;
import vn.hoidanit.jobhunter.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company handleCreateCompany(Company company) {
        Company tempCompany = new Company(company.getName(), company.getDescription(), company.getAddress(), company.getLogo());
        return this.companyRepository.save(tempCompany);
    }

    public List<Company> handleFetchAllCompanies(Pageable pageable) {
        Page<Company> pageCompany = this.companyRepository.findAll(pageable);

        return pageCompany.getContent();
    }

    public RestfulPaginationDTO handleFetchAllCompaniesWithPagination(Specification<Company> companySpecification,  Pageable pageable) {
        Page<Company> pageCompany = this.companyRepository.findAll(companySpecification ,pageable);
        RestfulPaginationDTO restfulPaginationDTO = new RestfulPaginationDTO();
        Meta meta = new Meta();

        meta.setPage(pageCompany.getNumber() + 1);
        meta.setPageSize(pageCompany.getSize());

        meta.setPages(pageCompany.getTotalPages());
        meta.setTotal(pageCompany.getTotalElements());

        restfulPaginationDTO.setMeta(meta);
        restfulPaginationDTO.setResult(pageCompany.getContent());

        return restfulPaginationDTO;
    }

    public Company handFetchCompanyById(int id) {
        return this.companyRepository.findById(id).orElse(null);
    }

    public Company handleUpdateCompany(Company company) {
        Company dbCompany = this.handFetchCompanyById(company.getId());
        if(dbCompany == null) {
            return null;
        }

        dbCompany.setName(company.getName());
        dbCompany.setDescription(company.getDescription());
        dbCompany.setAddress(company.getAddress());
        dbCompany.setLogo(company.getLogo());

        return this.companyRepository.save(dbCompany);
    }

    public boolean handleDeleteCompanyById(int id) {
        if(this.companyRepository.findById(id).isEmpty()) {
            return false;
        }

        this.companyRepository.deleteById(id);

        return true;
    }
}

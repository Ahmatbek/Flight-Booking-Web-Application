package kg.attractor.projects.controlwork9.mapper;

import kg.attractor.projects.controlwork9.dto.CompanyDto;
import kg.attractor.projects.controlwork9.model.Company;
import org.springframework.stereotype.Service;

@Service
public class CompanyMapper {
    public Company toEntity(CompanyDto dto) {
        Company company = new Company();
        company.setName(dto.getName());
        company.setActive(dto.isActive());
        company.setId(dto.getId());
        company.setLogo(dto.getLogo());
        company.setEmail(dto.getEmail());
        company.setPassword(dto.getPassword());
        return company;
    }
    public CompanyDto toDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setActive(company.getActive());
        companyDto.setLogo(company.getLogo());
        companyDto.setEmail(company.getEmail());
        companyDto.setPassword(company.getPassword());
        return companyDto;
    }
}

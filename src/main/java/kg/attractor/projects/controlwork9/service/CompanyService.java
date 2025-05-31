package kg.attractor.projects.controlwork9.service;

import kg.attractor.projects.controlwork9.dto.CompanyDto;

import java.util.List;
import java.util.NoSuchElementException;

public interface CompanyService {

    void createCompany(CompanyDto company);
     List<CompanyDto> getAllCompanies();
     void freeze(long id);
     void unfreeze(long id);
     CompanyDto getCompanyById(long id);
}

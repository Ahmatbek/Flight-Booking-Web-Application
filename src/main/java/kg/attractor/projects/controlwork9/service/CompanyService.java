package kg.attractor.projects.controlwork9.service;

import kg.attractor.projects.controlwork9.dto.UserDto;

import java.util.List;
import java.util.NoSuchElementException;

public interface CompanyService {

    void createCompany(UserDto company);
     List<UserDto> getAllCompanies();
     void freeze(long id);
     void unfreeze(long id);
     UserDto getCompanyById(long id);
}

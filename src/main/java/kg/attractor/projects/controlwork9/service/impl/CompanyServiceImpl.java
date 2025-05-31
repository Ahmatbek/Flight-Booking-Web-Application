package kg.attractor.projects.controlwork9.service.impl;


import kg.attractor.projects.controlwork9.dto.CompanyDto;
import kg.attractor.projects.controlwork9.dto.FlightDto;
import kg.attractor.projects.controlwork9.mapper.CompanyMapper;
import kg.attractor.projects.controlwork9.model.Company;
import kg.attractor.projects.controlwork9.repository.CompanyRepository;
import kg.attractor.projects.controlwork9.repository.FlightRepository;
import kg.attractor.projects.controlwork9.service.CompanyService;
import kg.attractor.projects.controlwork9.service.FlightService;
import kg.attractor.projects.controlwork9.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;
    private final TicketService ticketService;
    private final FlightService flightService;

//    private final


    @Override
    public void createCompany(CompanyDto company) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = company.getPassword();
        company.setPassword(passwordEncoder.encode(password));
        companyRepository.save(companyMapper.toEntity(company));
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toDto)
                .toList();
    }

    @Override
    public void freeze(long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));

        List<FlightDto> flightDtos = flightService.findByCompanyId(company.getId());

        Boolean canFreeze = ticketService.isAllowableToFreeze(company.getId(), flightDtos);

        if (Boolean.TRUE.equals(canFreeze)) {
            company.setActive(false);
            companyRepository.save(company);
            log.info("Frozen company: {}", company);

        } else {
            log.warn("Cannot freeze company {} due to active constraints", company.getId());
        }

    }


    @Override
    public void unfreeze(long id) {
        Company company = companyRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Company not found"));
        company.setActive(true);
        companyRepository.save(company);
    }

    @Override
    public CompanyDto getCompanyById(long id) {
        Company company =  companyRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Company not found"));
        return companyMapper.toDto(company);
    }


}

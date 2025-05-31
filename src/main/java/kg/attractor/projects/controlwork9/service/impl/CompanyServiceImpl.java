package kg.attractor.projects.controlwork9.service.impl;


import kg.attractor.projects.controlwork9.dto.FlightDto;
import kg.attractor.projects.controlwork9.dto.UserDto;
import kg.attractor.projects.controlwork9.mapper.UserMapper;
import kg.attractor.projects.controlwork9.model.User;
import kg.attractor.projects.controlwork9.repository.FlightRepository;
import kg.attractor.projects.controlwork9.repository.UserRepository;
import kg.attractor.projects.controlwork9.service.CompanyService;
import kg.attractor.projects.controlwork9.service.FlightService;
import kg.attractor.projects.controlwork9.service.TicketService;
import kg.attractor.projects.controlwork9.service.UserService;
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
//    private final CompanyMapper companyMapper;
//    private final CompanyRepository companyRepository;
    private final TicketService ticketService;
    private final FlightService flightService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

//    private final


    @Override
    public void createCompany(UserDto company) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = company.getPassword();
        company.setPassword(passwordEncoder.encode(password));
        userRepository.save(userMapper.toEntity(company));
    }

    @Override
    public List<UserDto> getAllCompanies() {
        return userRepository.findAllByAuthorityId(3)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public void freeze(long id) {
        User company = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));

        List<FlightDto> flightDtos = flightService.findByCompanyId(company.getId());

        Boolean canFreeze = ticketService.isAllowableToFreeze(company.getId(), flightDtos);

        if (Boolean.TRUE.equals(canFreeze)) {
            company.setEnabled(false);
            userRepository.save(company);
            log.info("Frozen company: {}", company);

        } else {
            log.warn("Cannot freeze company {} due to active constraints", company.getId());
        }

    }


    @Override
    public void unfreeze(long id) {
        User company = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Company not found"));
        company.setEnabled(true);
        userRepository.save(company);
    }

    @Override
    public UserDto getCompanyById(long id) {
        User company =  userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Company not found"));
        return userMapper.toDto(company);
    }


}

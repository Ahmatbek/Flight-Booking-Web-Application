package kg.attractor.projects.controlwork9.controller;

import jakarta.validation.Valid;
import kg.attractor.projects.controlwork9.dto.CompanyDto;
import kg.attractor.projects.controlwork9.dto.UserDto;
import kg.attractor.projects.controlwork9.mapper.CompanyMapper;
import kg.attractor.projects.controlwork9.service.CompanyService;
import kg.attractor.projects.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {
    private final CompanyService companyService;
    private final UserService userService;
    private final CompanyMapper companyMapper;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/create")
    public String index(Model model) {
        model.addAttribute("companyDto", new CompanyDto());
        return "admin/create";
    }

    @PostMapping("/create")
    public String register(@Valid CompanyDto userDto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/create";
        }else {
            UserDto user =  new UserDto();
            user.setAuthority("COMPANY");
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            companyService.createCompany(userDto);
            userService.addUser(user);
            return "redirect:/users/profile";
        }
    }
    @GetMapping("companies")
    public String companies(Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        return "admin/companies";
    }

    @PostMapping("freeze/{id}")
    public String freeze(Model model
    , @PathVariable long id){
        companyService.freeze(id);
        return "redirect:/admin/companies";
    }
    @PostMapping("unfreeze/{id}")
    public String unfreeze(Model model
    , @PathVariable long id){
        companyService.unfreeze(id);
        return "redirect:/admin/companies";
    }
}

package kg.attractor.projects.controlwork9.service.impl;


import kg.attractor.projects.controlwork9.dto.UserDto;
import kg.attractor.projects.controlwork9.service.AuthorizedUserService;
import kg.attractor.projects.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizedUserServiceImpl implements AuthorizedUserService {
    private final UserService userService;

    @Override
    public UserDto getAuthorizedUserDetails() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        log.info("getAuthorizedUserDetails: {}", authentication);
        return userService.findUserByEmail(authentication.getName());
    }
}

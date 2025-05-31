package kg.attractor.projects.controlwork9.service;


import kg.attractor.projects.controlwork9.dto.UserDto;

public interface UserService {
    UserDto findUserByEmail(String email);

    Boolean checkUserInDB(String email);

    Long addUser(UserDto user);

    UserDto findUserById(Long id);

}

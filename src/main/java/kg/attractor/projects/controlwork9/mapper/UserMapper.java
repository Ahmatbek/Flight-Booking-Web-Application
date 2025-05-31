package kg.attractor.projects.controlwork9.mapper;

import kg.attractor.projects.controlwork9.dto.UserDto;
import kg.attractor.projects.controlwork9.model.Authority;
import kg.attractor.projects.controlwork9.model.User;
import kg.attractor.projects.controlwork9.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final AuthorityRepository authorityRepository;

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .authority(user.getAuthority().getName())
                .enabled(user.getEnabled())
                .build();
    }

    public User toEntity(UserDto userDto) {
        Authority authority = authorityRepository.findByName(userDto.getAuthority()).orElseThrow(NoSuchElementException::new);

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAuthority(authority);
        user.setId(userDto.getId());
        user.setEnabled(userDto.getEnabled());
        user.setName(userDto.getName());
        return user;
    }

}


package kg.attractor.projects.controlwork9.service.impl;

import kg.attractor.projects.controlwork9.dto.UserDto;
import kg.attractor.projects.controlwork9.mapper.UserMapper;
import kg.attractor.projects.controlwork9.repository.UserRepository;
import kg.attractor.projects.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto findUserByEmail(String email) {
        log.info("Find user by email: {}", email);
        return userRepository.findByEmail(email).map(userMapper::toDto).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Boolean checkUserInDB(String email) {
        log.info("Check user in db: {}", email);
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Long addUser(UserDto user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Add user: {}", user);
        return userRepository.save(userMapper.toEntity(user)).getId();
    }

    @Override
    public UserDto findUserById(Long id) {
        log.info("Find user by id: {}", id);
        return userRepository.findById(id).map(userMapper::toDto).orElseThrow(NoSuchElementException::new);
    }
}

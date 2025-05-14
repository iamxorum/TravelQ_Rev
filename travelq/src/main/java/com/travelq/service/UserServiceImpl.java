package com.travelq.service;

import com.travelq.dto.UserDto;
import com.travelq.exception.UserNotFoundException;
import com.travelq.model.UserEntity;
import com.travelq.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto addUser(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        UserEntity saved = userRepository.save(userEntity);
        return modelMapper.map(saved, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userList = userRepository.findAll();
        return userList.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        UserEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        modelMapper.map(userDto, existingUser); // suprascrie câmpurile

        UserEntity updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        userRepository.deleteById(userId);
    }
}

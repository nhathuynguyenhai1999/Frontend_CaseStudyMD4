package com.example.casestudylibrary.service.impl;

import com.example.casestudylibrary.config.UserPrinciple;
import com.example.casestudylibrary.domain.Category;
import com.example.casestudylibrary.domain.User;
import com.example.casestudylibrary.domain.dto.req.UserReqDto;
import com.example.casestudylibrary.domain.dto.res.CategoryResDto;
import com.example.casestudylibrary.domain.dto.res.UserResDto;
import com.example.casestudylibrary.repository.IUserRepository;
import com.example.casestudylibrary.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserResDto> findAll() {
        return userRepository.findAll().
                stream().map(c -> new UserResDto(c.getId(),c.getFullName(), c.getPhone(), c.getUsername(), c.getPassword(), c.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserPrinciple.build(userRepository.findByUsername(username));
    }
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public UserResDto save(UserReqDto userReqDto) {
        User user = new User();
        user.setFullName(userReqDto.getFullName());
        user.setPhone(userReqDto.getPhone());
        user.setUsername(userReqDto.getUsername());
        user.setPassword(userReqDto.getPassword());
        user.setEmail(userReqDto.getEmail());
        userRepository.save(user);

        return user.toUserResDto();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserResDto findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user.toUserResDto();
    }

    @Override
    public UserResDto update(Long id, UserReqDto userReqDto) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFullName(userReqDto.getFullName());
            user.setPhone(userReqDto.getPhone());
            user.setUsername(userReqDto.getUsername());
            user.setPassword(userReqDto.getPassword());
            user.setEmail(userReqDto.getEmail());
            userRepository.save(user);
            return user.toUserResDto();
        } else {
            return user.toUserResDto();
        }
    }

}

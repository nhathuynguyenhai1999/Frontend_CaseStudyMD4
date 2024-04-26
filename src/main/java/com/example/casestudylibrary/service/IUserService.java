package com.example.casestudylibrary.service;

import com.example.casestudylibrary.domain.dto.req.CategoryReqDto;
import com.example.casestudylibrary.domain.dto.req.UserReqDto;
import com.example.casestudylibrary.domain.dto.res.CategoryResDto;
import com.example.casestudylibrary.domain.dto.res.UserResDto;

import java.util.List;

public interface IUserService {
    List<UserResDto> findAll();
    UserResDto save(UserReqDto userReqDto);
    void delete(Long id);
    UserResDto findById(Long id);
    UserResDto update(Long id, UserReqDto userReqDto);

//    UserResDto findCurrentUser();
}

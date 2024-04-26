package com.example.casestudylibrary.service;

public interface IExistsService {

    boolean existsById(Long id);
    boolean isService(String serviceName);
}

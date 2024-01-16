package com.github.goomon.boot.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleService {

    private final SimpleRepository repository;

    public void save(String id) {
        repository.save(id);
    }
}

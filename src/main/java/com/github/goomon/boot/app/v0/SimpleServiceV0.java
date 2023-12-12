package com.github.goomon.boot.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleServiceV0 {

    private final SimpleRepositoryV0 repository;

    public void save(String id) {
        repository.save(id);
    }
}

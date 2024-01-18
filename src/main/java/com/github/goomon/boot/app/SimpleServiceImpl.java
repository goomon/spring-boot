package com.github.goomon.boot.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleServiceImpl implements SimpleService {

    private final SimpleRepository repository;

    @Override
    public void save(String id) {
        repository.save(id);
    }
}

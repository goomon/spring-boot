package com.github.goomon.boot.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SimpleRepository implements SimpleRepositoryInterface {

    @Override
    public void save(String id) {
        if (id.equals("ex")) {
            throw new IllegalArgumentException();
        }
        sleep(1000);
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

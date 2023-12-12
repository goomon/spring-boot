package com.github.goomon.boot.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SimpleRepositoryV0 {

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

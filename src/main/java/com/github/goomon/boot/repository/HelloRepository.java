package com.github.goomon.boot.repository;

import com.github.goomon.boot.model.Hello;

public interface HelloRepository {
    Hello findHello(String name);

    void increaseCount(String name);

    default Long countOf(String name) {
        Hello hello = findHello(name);
        return hello == null ? 0 : hello.getCount();
    }
}

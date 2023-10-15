package com.github.goomon.boot.model;

public class Hello {
    private String name;

    private Long count;

    public Hello(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public Long getCount() {
        return count;
    }
}

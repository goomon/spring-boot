package com.github.goomon.boot.aspect.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.github.goomon.boot..*(..))")
    public void all() {}

    @Pointcut("execution(* *..*Service.*(..))")
    public void serviceOnly() {}

    @Pointcut("all() && serviceOnly()")
    public void allAndServiceOnly() {}
}

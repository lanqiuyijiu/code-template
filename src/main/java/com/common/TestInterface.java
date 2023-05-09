package com.common;

public interface TestInterface {
    default String me() {

        return null;
    }


    final int i = 0;

   public   abstract String test();
}

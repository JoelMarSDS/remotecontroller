package com.cuscuzcomjava.remotecontroller.entity.enumeration;

public enum TypeUserEnumeration {

    ADMIN (1L),
    USER_COMUM (2L);

    Long id;

    public Long getId() {
        return id;
    }

    TypeUserEnumeration(Long id) {
        this.id = id;
    }
}

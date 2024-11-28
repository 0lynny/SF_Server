package org.sfaas.SFaaS.domain.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role {

    ALL("관리자"),
    A_FACTORY("A 공장"),
    B_FACTORY("B 공장");

    private final String value;

}
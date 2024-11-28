package org.sfaas.SFaaS.domain.logData.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LogType {
    WARNING, ERROR, INFO;
}
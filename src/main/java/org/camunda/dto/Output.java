package org.camunda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Output {

    private final int result;

    private final String message;
}

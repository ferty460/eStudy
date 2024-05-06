package com.example.estudy.domain.answer;

import lombok.Data;

import java.util.Map;

@Data
public class GapsAnswerRequest {

    private Map<String, String> userAnswer;

    private Long gapsId;

}

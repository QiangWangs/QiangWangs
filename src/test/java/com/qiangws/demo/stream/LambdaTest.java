package com.qiangws.demo.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LambdaTest {

    private Lambda lambdaUnderTest;

    @BeforeEach
    void setUp() {
        lambdaUnderTest = new Lambda();
    }


    @Test
    void testScopeExperiment() {
        assertThat(lambdaUnderTest.scopeExperiment()).isEqualTo("result");
    }
}

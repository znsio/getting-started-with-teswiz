package com.znsio.sample.e2e.businessLayer;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import com.znsio.teswiz.tools.SensitiveDataMasker;
import com.znsio.teswiz.tools.StringUtils;

class TeswizContractTest {

    @Test
    void masksSensitiveDataAndNormalisesScenarioNames() {
        SensitiveDataMasker.setShowSensitiveData(false);

        assertThat(SensitiveDataMasker.mask("https://user:password@example.com"))
                .contains("***:***@");
        assertThat(StringUtils.normaliseScenarioName("Teswiz public methods!"))
                .isEqualTo("Teswiz_public_methods_");
    }

    @Test
    void doNotMaskSensitiveDataAndNormalisesScenarioNames() {
        SensitiveDataMasker.setShowSensitiveData(true);

        assertThat(SensitiveDataMasker.mask("https://user:password@example.com"))
                .contains("user:password@");
        assertThat(StringUtils.normaliseScenarioName("Teswiz public methods!"))
                .isEqualTo("Teswiz_public_methods_");
    }
}

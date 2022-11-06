package com.wt.commons.validation;

import java.util.Map;

/**
 * @author Will.WT
 * @date 2022/11/04 17:03
 */
public class ValidationResult {

    private boolean hasError;
    private Map<String, String> errorMap;

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "hasError=" + hasError +
                ", errorMap=" + errorMap +
                '}';
    }
}

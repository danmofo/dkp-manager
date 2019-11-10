package com.dmoffat.dkpmanager.model.forms;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ValidationErrors {
    private boolean hasErrors;
    private List<ValidationError> errors;

    public ValidationErrors(BindingResult result, MessageSource messageSource) {
        this.hasErrors = result.hasErrors();
        this.errors = new ArrayList<>();

        for(FieldError error : result.getFieldErrors()) {
            System.out.println("Error codes: " + Arrays.toString(error.getCodes()));
            ValidationError validationError =
                    new ValidationError(error.getField(), messageSource.getMessage(error, Locale.UK));
            this.errors.add(validationError);
        }
    }

    public boolean isHasErrors() { return hasErrors; }
    public List<ValidationError> getErrors() { return errors; }

    @Override
    public String toString() {
        return "ValidationErrors{" +
                "hasErrors=" + hasErrors +
                ", errors=" + errors +
                '}';
    }
}

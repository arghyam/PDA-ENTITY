package com.pda.entity.utils;

import com.pda.entity.exceptions.UnprocessableEntitiesException;
import com.pda.entity.exceptions.ValidationError;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void valiadtePojo(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ValidationError> errorList = new ArrayList<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                ValidationError validationError = new ValidationError(error.getField(), error.getDefaultMessage());
                errorList.add(validationError);
            }
            throw new UnprocessableEntitiesException(errorList);

        }
    }
}

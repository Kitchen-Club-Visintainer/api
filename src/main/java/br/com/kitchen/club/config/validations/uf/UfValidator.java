package br.com.kitchen.club.config.validations.uf;

import br.com.kitchen.club.entity.enums.Uf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import static java.util.Arrays.stream;

public class UfValidator implements ConstraintValidator<UfValid, String> {

    @Override
    public void initialize(UfValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        var find = stream(Uf.values())
                .filter(u -> u.nome().equals(s))
                .findFirst();
        return find.isPresent();
    }
}

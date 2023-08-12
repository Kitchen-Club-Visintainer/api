package br.com.kitchen.club.config.validations.uf;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UfValidator.class)
public @interface UfValid {

    String message() default "Uf informada inválida -> informar o nome do estado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

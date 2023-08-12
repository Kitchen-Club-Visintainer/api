package br.com.kitchen.club.config.validations.telefone;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = TelefoneValidator.class)
public @interface TelefoneValid {

    String message() default "Número do telefone inválido. Forneça apenas número";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

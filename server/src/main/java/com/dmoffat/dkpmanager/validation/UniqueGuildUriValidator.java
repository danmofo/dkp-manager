package com.dmoffat.dkpmanager.validation;

import com.dmoffat.dkpmanager.model.Guild;
import com.dmoffat.dkpmanager.model.forms.EditGuildForm;
import com.dmoffat.dkpmanager.service.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueGuildUriValidator implements ConstraintValidator<UniqueUri, EditGuildForm> {

    @Autowired private GuildService guildService;

    @Override
    public void initialize(UniqueUri constraintAnnotation) {}

    @Override
    public boolean isValid(EditGuildForm value, ConstraintValidatorContext context) {

        if(value.getCurrentUri().equals(value.getUri())) {
            return true;
        }

        Guild guild = guildService.findByUri(value.getUri());
        return guild == null;
    }
}

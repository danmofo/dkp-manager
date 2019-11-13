package com.dmoffat.dkpmanager.model.email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SendGridListTemplateResponse {
    private List<SendGridTemplate> templates;

    public List<SendGridTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(List<SendGridTemplate> templates) {
        this.templates = templates;
    }

    @Override
    public String toString() {
        return "SendGridListTemplateResponse{" +
                "templates=" + templates +
                '}';
    }
}

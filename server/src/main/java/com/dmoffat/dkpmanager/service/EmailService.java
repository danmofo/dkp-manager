package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.model.email.SendGridListTemplateResponse;
import com.dmoffat.dkpmanager.model.email.SendGridTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class EmailService {
    private static final Logger logger = LogManager.getLogger(EmailService.class);

    @Autowired private ObjectMapper objectMapper;
    @Autowired private SendGrid sendGridClient;

    public boolean send(String templateName, String recipient) {
        return send(templateName, recipient, null);
    }

    /**
     * @return True if the email was send successfully. False if:
     * - Template ID couldn't be derived from the template name
     * - Request body JSON couldn't be written
     * - SendGrid API returns non-200 status
     */
    public boolean send(String templateName, String recipient, Map<String, String> templateData) {
        String templateId = getTemplateIdForName(templateName);
        if(templateId == null) {
            logger.warn("No template could be found with the name: " + templateName);
            return false;
        }

        Request request = getSendMailRequest();

        String requestJson = getSendMailRequestJson(templateId, recipient, templateData);
        if(requestJson == null) {
            logger.debug("Failed to write request JSON.");
            return false;
        }
        request.setBody(requestJson);

        try {
            Response response = sendGridClient.api(request);
            logger.debug("Send mail response code: " + response.getStatusCode());
            return response.getStatusCode() == HttpStatus.SC_ACCEPTED;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private Request getSendMailRequest() {
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.addHeader("Content-Type", "application/json");
        return request;
    }

    private String getSendMailRequestJson(String templateId, String recipient, Map<String, String> templateData) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("template_id", templateId);

        Personalization personalization = new Personalization();
        personalization.addTo(new Email(recipient));
        if(templateData != null && !templateData.isEmpty()) {
            for(Map.Entry<String, String> sub : templateData.entrySet()) {
                personalization.addDynamicTemplateData(sub.getKey(), sub.getValue());
            }
        }
        requestBody.put("personalizations", Collections.singletonList(personalization));

        Map<String, String> from = new HashMap<>();
        from.put("email", "danmofo@gmail.com");
        requestBody.put("from", from);



        try {
            return objectMapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getTemplateIdForName(String templateName) {
        List<SendGridTemplate> templates = listTemplates();
        for(SendGridTemplate template : templates) {
            if(template.getName().equals(templateName)) {
                return template.getId();
            }
        }
        return null;
    }

    private List<SendGridTemplate> listTemplates() {
        try {
            Request request = new Request();
            request.setMethod(Method.GET);
            request.setEndpoint("templates");
            request.addQueryParam("generations", "dynamic");
            Response response = sendGridClient.api(request);

            if(response.getStatusCode() != HttpStatus.SC_OK) {
                return Collections.emptyList();
            }

            SendGridListTemplateResponse templateResponse  =
                    objectMapper.readValue(response.getBody(), SendGridListTemplateResponse.class);
            return templateResponse.getTemplates();
        } catch (IOException | JsonParseException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

}

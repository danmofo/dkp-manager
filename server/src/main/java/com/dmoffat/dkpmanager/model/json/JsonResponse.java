package com.dmoffat.dkpmanager.model.json;

import com.dmoffat.dkpmanager.model.forms.ValidationErrors;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResponse {
    private boolean success;
    private Map<String, Object> payload;

    private JsonResponse() {}

    public JsonResponse(boolean success) {
        this.success = success;
    }

    public JsonResponse(ValidationErrors validationErrors) {
        this.success = !validationErrors.isHasErrors();
        this.addPayload("validation", validationErrors);
    }

    public JsonResponse addPayload(String key, Object value) {
        if(this.payload == null) {
            this.payload = new HashMap<>();
        }
        this.payload.put(key, value);
        return this;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public Map<String, Object> getPayload() { return payload; }
    public void setPayload(Map<String, Object> payload) { this.payload = payload; }
}

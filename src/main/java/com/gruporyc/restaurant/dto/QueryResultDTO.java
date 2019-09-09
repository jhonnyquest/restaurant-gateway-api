package com.gruporyc.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class QueryResultDTO {

    @NotNull
    private String queryText;

    @NotNull
    private IntentDTO intent;

    @NotNull
    private float intentDetectionConfidence;

    @NotNull
    private String languageCode;

    @JsonProperty("queryText")
    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    @JsonProperty("intent")
    public IntentDTO getIntent() {
        return intent;
    }

    public void setIntent(IntentDTO intent) {
        this.intent = intent;
    }

    @JsonProperty("intentDetectionConfidence")
    public float getIntentDetectionConfidence() {
        return intentDetectionConfidence;
    }

    public void setIntentDetectionConfidence(float intentDetectionConfidence) {
        this.intentDetectionConfidence = intentDetectionConfidence;
    }

    @JsonProperty("languageCode")
    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "queryText='" + queryText + '\'' +
                ", intent=" + intent +
                ", intentDetectionConfidence=" + intentDetectionConfidence +
                ", languageCode='" + languageCode + '\'' +
                '}';
    }
}

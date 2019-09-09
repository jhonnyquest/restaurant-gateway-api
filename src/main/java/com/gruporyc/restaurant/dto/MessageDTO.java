package com.gruporyc.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class MessageDTO {

    @NotNull
    private String responseId;

    @NotNull
    private  QueryResultDTO queryResult;

    @NotNull
    private String session;

    @JsonProperty("responseId")
    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    @JsonProperty("queryResult")
    public QueryResultDTO getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(QueryResultDTO queryResult) {
        this.queryResult = queryResult;
    }

    @JsonProperty("session")
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Message{" +
                "responseId='" + responseId + '\'' +
                ", queryResult=" + queryResult +
                ", session='" + session + '\'' +
                '}';
    }
}

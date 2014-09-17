package com.nulabinc.backlog4j;

import com.nulabinc.backlog4j.http.BacklogHttpResponse;
import com.nulabinc.backlog4j.internal.json.Jackson;

/**
 * Exception thrown when a api response contains error.
 *
 * @author nulab-inc
 */
public class BacklogAPIException extends BacklogException {

    protected BacklogHttpResponse response;
    private int statusCode = -1;
    private BacklogAPIError backlogAPIError;

    public BacklogAPIException(String message, Throwable cause) {
        super(message, cause);
        decode(message);
    }

    public BacklogAPIException(String message, Exception cause, int statusCode) {
        this(message, cause);
        decode(message);
        this.statusCode = statusCode;
    }

    public BacklogAPIException(String message, BacklogHttpResponse response) {
        this(message);
        decode(response.asString());
        this.response = response;
        this.statusCode = response.getStatusCode();
    }

    public BacklogAPIException(String s) {
        super(s);
    }

    public BacklogAPIException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        StringBuilder value = new StringBuilder();
        value.append(super.getMessage());
        if(statusCode > 0){
            value.append("\n");
            value.append("status code - ").append(statusCode);
        }
        if (backlogAPIError != null) {
            for (BacklogAPIErrorMessage errorMessage: backlogAPIError.getErrors()) {

                value.append("\n");
                value.append("message - ").append(errorMessage.getMessage()).append("\n");
                value.append("code - ").append(errorMessage.getCode()).append("\n");
                String info = errorMessage.getErrorInfo();
                if (info != null && info.length() > 0) {
                    value.append("errorInfo - ").append(info).append("\n");
                }
                String moreInfo = errorMessage.getMoreInfo();
                if (moreInfo != null && moreInfo.length() > 0) {
                    value.append("moreInfo - ").append(moreInfo).append("\n");
                }
            }
        }
        return value.toString();
    }

    public BacklogAPIError getBacklogAPIError() {
        return backlogAPIError;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    protected void decode(String str) {
        if (str != null && str.startsWith("{")) {
            backlogAPIError = Jackson.fromJsonString(str, BacklogAPIError.class);
        }
    }
}
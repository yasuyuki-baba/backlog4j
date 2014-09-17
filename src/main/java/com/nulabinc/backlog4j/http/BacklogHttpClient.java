package com.nulabinc.backlog4j.http;

import com.nulabinc.backlog4j.BacklogException;
import com.nulabinc.backlog4j.api.option.GetParams;
import com.nulabinc.backlog4j.api.option.QueryParams;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * @author nulab-inc
 */
public interface BacklogHttpClient {
    void setApiKey(String apiKey);
    void setBearerToken(String bearerToken);
    void setReadTimeout(int readTimeout);
    void setConnectionTimeout(int connectionTimeout);
    BacklogHttpResponse get(String endpoint, GetParams getParams, QueryParams queryParams) throws BacklogException;
    BacklogHttpResponse post(String endpoint, List<NameValuePair> postParams) throws BacklogException;
    BacklogHttpResponse patch(String endpoint, List<NameValuePair> patchParams) throws BacklogException;
    BacklogHttpResponse put(String endpoint, List<NameValuePair> patchParams) throws BacklogException;
    BacklogHttpResponse delete(String endpoint, NameValuePair param) throws BacklogException;
    BacklogHttpResponse postMultiPart(String endpoint, Map<String, Object> postParams) throws BacklogException;
}
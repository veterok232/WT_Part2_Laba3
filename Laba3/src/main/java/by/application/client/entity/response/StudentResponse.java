package by.application.client.entity.response;

import java.io.Serializable;

/**
 * Response model
 */
public class StudentResponse implements Serializable
{
    /**
     * Response type
     */
    private ResponseType responseType;

    /**
     * Response information
     */
    private Object body;

    /**
     * Get response type
     *
     * @return ResponseType
     */
    public ResponseType getResponseType() {
        return responseType;
    }

    /**
     * Set response type
     *
     * @param responseType Response type
     */
    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    /**
     * Get response information
     *
     * @return Object
     */
    public Object getBody() {
        return body;
    }

    /**
     * Set response information
     *
     * @param body Response information
     */
    public void setBody(Object body) {
        this.body = body;
    }
}

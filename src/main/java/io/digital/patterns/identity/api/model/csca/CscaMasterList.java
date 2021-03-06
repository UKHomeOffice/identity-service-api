package io.digital.patterns.identity.api.model.csca;

import lombok.Data;

import java.util.Date;

@Data
public class CscaMasterList {
    private String etag;
    private byte[] content;
    private Date lastModified;
}

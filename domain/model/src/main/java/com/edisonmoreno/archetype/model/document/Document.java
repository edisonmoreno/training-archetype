package com.edisonmoreno.archetype.model.document;
import lombok.*;

@Getter
@Setter
@Data
@Builder(toBuilder = true)
public class Document {
    private Long id;
    private String name;
    private String uri;
}
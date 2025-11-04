package com.example.demo.interfaces.clients.model.common.model.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serial;
import java.io.Serializable;

/*Pour comprendre le fonctionnement, voir page AbstractInput
L’annotation @JsonInclude(JsonInclude.Include.NON_EMPTY) indique à Jackson de ne pas inclure les champs vides ou nuls lors de la sérialisation.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AbstractOutput implements Serializable {
    @Serial
    private static final long serialVersionUID = 7572848181599073884L;
}

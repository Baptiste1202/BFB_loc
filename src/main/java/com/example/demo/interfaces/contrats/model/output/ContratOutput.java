package com.example.demo.interfaces.contrats.model.output;


import com.example.demo.interfaces.clients.model.common.model.input.AbstractInput;
import com.example.demo.interfaces.clients.model.common.model.output.AbstractOutput;
import lombok.*;

import java.io.Serial;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString

public class ContratOutput extends AbstractOutput {
    @Serial
    private static final long serialVersionUID = -8419498096184696157L;
    private final String id;
    private String Startdate;
    private String Enddate;
    private String state;
}

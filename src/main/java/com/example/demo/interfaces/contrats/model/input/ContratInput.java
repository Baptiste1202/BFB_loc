package com.example.demo.interfaces.contrats.model.input;



import com.example.demo.interfaces.clients.model.common.model.input.AbstractInput;
import lombok.*;

import java.io.Serial;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString

public class ContratInput extends AbstractInput {
    @Serial
    private static final long serialVersionUID = 1513410389151026038L;
    private final String id;
}

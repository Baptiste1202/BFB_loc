package com.example.demo.interfaces.vehicules.model.input;


import com.example.demo.interfaces.clients.model.common.model.input.AbstractInput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class VehiculeInput extends AbstractInput {
    @Serial
    private static final long serialVersionUID = 7698048718184543190L;
    private String registration;

}

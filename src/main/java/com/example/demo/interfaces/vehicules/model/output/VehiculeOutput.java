package com.example.demo.interfaces.vehicules.model.output;


import com.example.demo.interfaces.clients.model.common.model.input.AbstractInput;
import com.example.demo.interfaces.clients.model.common.model.output.AbstractOutput;
import lombok.*;

import java.io.Serial;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class VehiculeOutput extends AbstractOutput {
    @Serial
    private static final long serialVersionUID = 1329601726051905865L;
    private String brand;
    private String model;
    private String engine;
    private String color;
    private final String registration;
    private String date;
    private String state;
}

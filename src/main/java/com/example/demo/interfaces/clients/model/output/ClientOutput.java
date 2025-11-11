package com.example.demo.interfaces.clients.model.output;

import com.example.demo.interfaces.clients.model.common.model.output.AbstractOutput;
import lombok.*;

import java.io.Serial;
import java.util.UUID;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ClientOutput extends AbstractOutput {
    @Serial
    private static final long serialVersionUID = -277603070005624486L;
    private final String identifier;
    private String lastname;
    private String firstname;
    private String birthday;
    private String licenseNumber;
    private String address;
}

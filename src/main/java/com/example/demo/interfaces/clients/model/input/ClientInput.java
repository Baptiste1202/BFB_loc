package com.example.demo.interfaces.clients.model.input;

import com.example.demo.interfaces.clients.model.common.model.input.AbstractInput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ClientInput extends AbstractInput {
    @Serial
    private static final long serialVersionUID = -6256916847598118362L;
    private String lastname;
    private String firstname;
    private String genre;
}

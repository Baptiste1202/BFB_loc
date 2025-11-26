package com.example.demo.interfaces.vehicles;

import com.example.demo.business.vehicles.VehicleService;
import com.example.demo.business.vehicles.model.Vehicle;
import com.example.demo.interfaces.common.exception.NotFoundException;
import com.example.demo.interfaces.vehicles.model.input.VehicleInput;
import com.example.demo.interfaces.vehicles.model.input.VehicleUpdateInput;
import com.example.demo.interfaces.vehicles.model.output.VehicleOutput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("BFB/v1/clients/{clientId}/vehicles")
public class VehiclesController {

    private final VehicleService vehicleService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<VehicleOutput> getAll(@PathVariable("clientId") String clientId) {
        Set<Vehicle> vehicles =
                vehicleService.getAllFilteredByClient(UUID.fromString(clientId))
                        .stream()
                        .collect(Collectors.toSet());
        return vehicles.stream()
                .map(VehicleOutput::from)
                .collect(Collectors.toSet());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleOutput create(@PathVariable("clientId") String clientId,
                                @RequestBody VehicleInput input) {
        Vehicle newVehicle = VehicleInput.convert(input);
        Vehicle created = vehicleService.create(UUID.fromString(clientId), newVehicle);
        return VehicleOutput.from(created);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleOutput getOne(@PathVariable("clientId") String clientId,
                                @PathVariable("vehicleId") String vehicleId) {
        Vehicle vehicle = vehicleService.getOneFilteredByClient(UUID.fromString(clientId), UUID.fromString(vehicleId))
                .orElseThrow(() -> new NotFoundException(
                        String.format("Le véhicule %s pour le client %s n'a pas été trouvé.", vehicleId, clientId)
                ));
        return VehicleOutput.from(vehicle);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{vehicleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("clientId") String clientId,
                       @PathVariable("vehicleId") String vehicleId,
                       @RequestBody VehicleUpdateInput input) {
        Vehicle updatedVehicle = vehicleService.getOneFilteredByClient(UUID.fromString(clientId), UUID.fromString(vehicleId))
                .map(existing -> VehicleUpdateInput.from(input, existing))
                .orElseThrow(() -> new NotFoundException(
                        String.format("Le véhicule %s pour le client %s n'a pas été trouvé.", vehicleId, clientId)
                ));
        vehicleService.update(UUID.fromString(clientId), updatedVehicle);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{vehicleId}")
    public void delete(@PathVariable("clientId") String clientId,
                       @PathVariable("vehicleId") String vehicleId) {
        vehicleService.delete(UUID.fromString(clientId), UUID.fromString(vehicleId));
    }
}

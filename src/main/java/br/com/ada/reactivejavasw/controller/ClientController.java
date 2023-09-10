package br.com.ada.reactivejavasw.controller;

import br.com.ada.reactivejavasw.dto.ClientDTO;
import br.com.ada.reactivejavasw.dto.ResponseDTO;
import br.com.ada.reactivejavasw.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Create a client",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody())
    public Mono<ResponseDTO> create(@RequestBody ClientDTO productDTO) {
        return this.clientService.create(productDTO);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Find all clients")
    public Flux<ResponseDTO<ClientDTO>> getAll() {
        return this.clientService.getAll();
    }

    @GetMapping("{email}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Find by email of client")
    public Mono<ResponseDTO<ClientDTO>> findByEmail(@PathVariable("email") String email) {
        return this.clientService.findByEmail(email);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Update a client")
    public Mono<ResponseDTO> update(@RequestBody ClientDTO clientDTO){
        return this.clientService.update(clientDTO);
    }

    @DeleteMapping("{email}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<ResponseDTO> delete(@PathVariable("email") String email) {
        return this.clientService.delete(email);
    }
}

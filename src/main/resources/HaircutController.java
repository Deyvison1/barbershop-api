package com.api.barbershop.controllers;

import com.api.barbershop.dtos.HaircutDTO;
import com.api.barbershop.dtos.PathApi;
import com.api.barbershop.services.IHaircutService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathApi.HAIRCUT)
@Tag(name = "Haircuts", description = "Gerencia os cortes de cabelo disponíveis")
public class HaircutController {

    private final IHaircutService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiResponse(responseCode = "201", description = "Busca o corte pelo identificador.")
    public ResponseEntity<HaircutDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findByIdDTO(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiResponse(responseCode = "201", description = "Corte criado com sucesso")
    public ResponseEntity<HaircutDTO> add(@RequestBody final HaircutDTO dto) {
        HaircutDTO response = service.add(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiResponse(responseCode = "201", description = "Corte editado com sucesso")
    public ResponseEntity<HaircutDTO> update(@PathVariable UUID id, @RequestBody final HaircutDTO dto) {
        HaircutDTO response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiResponse(responseCode = "204", description = "Corte excluido com sucesso")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}

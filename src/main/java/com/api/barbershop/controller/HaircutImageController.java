package com.api.barbershop.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.barbershop.dto.constants.HaircutImageMessages;
import com.api.barbershop.dto.haircut.image.HaircutImageDTO;
import com.api.barbershop.service.IHaircutImageService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(HaircutImageMessages.BASE_PATH)
@RequiredArgsConstructor
@Tag(name = HaircutImageMessages.TITLE, description = HaircutImageMessages.DESCRIPTION)
public class HaircutImageController {

	private final IHaircutImageService service;

	@PostMapping("/{haircutId}/upload")
	@PreAuthorize(HaircutImageMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "204", description = HaircutImageMessages.UPLOAD)
	public ResponseEntity<HaircutImageDTO> uploadImage(@PathVariable UUID haircutId,
			@RequestParam("file") MultipartFile file, @RequestParam(defaultValue = "false") boolean active)
			throws IOException {

		HaircutImageDTO dto = service.uploadImageToDatabase(haircutId, file, active);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@GetMapping("/download/{id}")
	@PreAuthorize(HaircutImageMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "204", description = HaircutImageMessages.DOWNLOAD)
	public ResponseEntity<byte[]> downloadImage(@PathVariable UUID id) {
		HaircutImageDTO dto = service.getImageById(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dto.getFilename() + "\"")
				.contentType(MediaType.parseMediaType(dto.getContentType())).body(dto.getData());
	}

	@DeleteMapping("/{id}")
	@PreAuthorize(HaircutImageMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "204", description = HaircutImageMessages.DELETED)
	public ResponseEntity<Void> remove(@PathVariable UUID id) {
		service.remove(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Ativa a imagem de um corte.
	 * 
	 * @param id UUID da imagem que deve ser ativada
	 * @return 200 OK se sucesso
	 */
	@PutMapping("/{id}/activate")
	@PreAuthorize(HaircutImageMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "204", description = HaircutImageMessages.ACTIVE_IMAGE)
	public ResponseEntity<Void> activateImage(@PathVariable UUID id) {
		service.activeImage(id);
		return ResponseEntity.ok().build();
	}

}

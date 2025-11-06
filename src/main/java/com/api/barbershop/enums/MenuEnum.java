package com.api.barbershop.enums;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.barbershop.dto.MenuDTO;

import lombok.Getter;

@Getter
public enum MenuEnum {

	// --- MENUS PRINCIPAIS ---
	CONTROL_HAIRCUT("Gerenciamento de cortes", null, "pi pi-file", Set.of("ADMIN")),
	CONTROL_BARBER("Gerenciamento de barbeiros.", null, "pi pi-user-plus", Set.of("ADMIN")),
	// --- SUBMENUS DE CURRICULUM ---
	NEW_HAIRCUT("Novo Corte", "/haircut/form", "pi pi-plus", Set.of("ADMIN"), CONTROL_HAIRCUT),
	FIND_ALL_HAIRCUT("Buscar Cortes", "/haircut/list", "pi pi-list", Set.of("ADMIN"), CONTROL_HAIRCUT),
	NEW_BARBER("Novo Barbeiro", "/barber/form", "pi pi-plus", Set.of("ADMIN"), CONTROL_BARBER),
	FIND_ALL_BARBER("Buscar Barbeiros", "/barber/list", "pi pi-list", Set.of("ADMIN"), CONTROL_BARBER);

	private final String label;
	private final String routerLink;
	private final String icon;
	private final Set<String> roles;
	private final MenuEnum items;

	MenuEnum(String label, String url, String icon, Set<String> roles) {
		this(label, url, icon, roles, null);
	}

	MenuEnum(String label, String routerLink, String icon, Set<String> roles, MenuEnum item) {
		this.label = label;
		this.routerLink = routerLink;
		this.icon = icon;
		this.roles = roles;
		this.items = item;
	}

	// ================================================================

	public static Set<MenuDTO> findAll() {
		return Arrays.stream(values()).filter(menu -> menu.getItems() == null).map(MenuEnum::toDTO)
				.sorted(Comparator.comparing(MenuDTO::getLabel)).collect(Collectors.toCollection(LinkedHashSet::new));
	}

	private static MenuDTO toDTO(MenuEnum menu) {
		Set<MenuDTO> submenus = Arrays.stream(values()).filter(sub -> menu.equals(sub.getItems())).map(MenuEnum::toDTO)
				.collect(Collectors.toCollection(LinkedHashSet::new));

		return new MenuDTO(menu.getLabel(), menu.getRouterLink(), menu.getIcon(), menu.getRoles(), submenus);
	}
}

package com.api.barbershop.dto.auth;

public class KeycloakApiPaths {

	private KeycloakApiPaths() {
	}

	public static final String BASE_REALM = "/admin/realms/BARBERSHOP";

	public static final class Users {
		public static final String BASE = BASE_REALM + "/users";
		public static final String BY_ID = BASE + "/{id}";
		public static final String RESET_PASSWORD = BY_ID + "/reset-password";
		public static final String ROLE_MAPPINGS_REALM = BY_ID + "/role-mappings/realm";
	}

	public static final class Groups {
		public static final String BASE = BASE_REALM + "/groups";
		public static final String MEMBERS = BASE + "/{groupId}/members";
		public static final String SEARCH = BASE + "?search={groupName}";
	}

	public static final class Roles {
		public static final String BASE = BASE_REALM + "/roles";
		public static final String BY_NAME = BASE + "/{roleName}";
		public static final String USERS_BY_ROLE = BY_NAME + "/users";
	}
}

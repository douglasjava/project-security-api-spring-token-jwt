package com.montreal.mock.montrealauth.model.enums;

public enum RoleName {

	ROLE_CLIENT("client"), ROLE_ADMIN("admin");

	private String descricao;

	RoleName(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}

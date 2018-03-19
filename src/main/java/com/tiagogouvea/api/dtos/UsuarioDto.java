package com.tiagogouvea.api.dtos;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UsuarioDto {

	@NotEmpty(message = "O email é obrigatório")
	@Email
	private String email;
	
	@NotEmpty(message = "A senha é obrigatória")
	@Size(min = 6, max = 8, message = "A senha deve ter entre 6 e 8 caracteres")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message="A senha só pode ser composta por letras e numeros")
	private String senha;
	
	@NotEmpty(message = "A confirmação de senha é obrigatória")
	private String confirmacaoDeSenha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmacaoDeSenha() {
		return confirmacaoDeSenha;
	}

	public void setConfirmacaoDeSenha(String confirmacaoDeSenha) {
		this.confirmacaoDeSenha = confirmacaoDeSenha;
	}
	
}

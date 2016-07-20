/**
 * 
 */
package com.concrete.model.constants;

/**
 * Constantes de Mensagens
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public class MensagemConstants {

	private MensagemConstants() {
		super();
	}

	public static final String DADOS_INVALIDOS = "dados inválidos.";
	public static final String NOME_NAO_INFORMADO = "Nome não informado.";
	public static final String EMAIL_NAO_INFORMADO = "E-mail não informado.";
	public static final String PASSWORD_NAO_INFORMADO = "Password não informado.";
	public static final String DDD_NAO_INFORMADO = "DDD do telefone não informado.";
	public static final String TELEFONE_NAO_INFORMADO = "Número do telefone não informado.";
	public static final String EMAIL_JA_EXISTENTE = "E-mail já existente";

	public static final String SUBJECT_EMAIL_USER_REGISTRATION = "Cadastro de Usuário na plataforma concrete-api";
	public static final String TEXT_EMAIL_USER_REGISTRATION = "Usuário: {0} - E-mail: {1} cadastrado na plataforma concrete-api";

	public static final String SUBJECT_TOKEN_USER = "Token de Usuário";


}

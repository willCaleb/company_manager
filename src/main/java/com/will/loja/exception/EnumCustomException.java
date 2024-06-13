package com.will.loja.exception;

public enum EnumCustomException {
    CPF_INVALIDO("O CPF informado {0} é inválido."),
    CATEGORIA_CATEGORIA_PRINCIPAL_DIFERENTE("A Categoria superior pertence à outra categoria principal."),
    IMGUR_IMPOSSIVEL_GERAR_TOKEN("Não foi possível gerar um Token em Imgur: {0}"),
    IMGUR_IMPOSSIVEL_RECUPERAR_CONTA("Não foi possível recuoerar os dados da conta: {0}"),
    IMGUR_IMPOSSIVEL_FAZER_UPLOAD("Não foi possível fazer o upload da imagem: {0}"),
    IMGUR_IMPOSSIVEL_CONVERTER_ARQUIVO("Não foi possível converter o arquivo: {0}"),

    PEDIDO_NAO_E_POSSIVEL_FINALIZAR("O pedido não pode ser finalizados pois contém itens não finalizados!"),
    PEDIDO_FINALIZADO("Pedido já finalizado"),
    OBJETOS_CLASSES_DIFERENTES("Objetos de classes diferentes!"),
    PEDIDO_CANCELADO_OU_FINALIZADO_SEM_PERMISSAO_ALTERAR("O pedido foi cancelado ou finalizado e não permite alteraçã0."),
    TITULO_CANCELADO_NAO_PERMITE_ALTERACAO("Esse título não permite alteração pois seu status é CANCELADO"),
    TITULO_LIQUIDADO_NAO_PERMITE_ALTERACAO("Esse título não permite alteração pois seu status é LIQUIDADO"),
    TITULO_PARCIALMENTE_LIQUIDADO_SEM_OBSERVACAO("O título {0} está parcialmente liquidado e é obrigatória uma observação"),
    PROFISSIONAL_CPF_JA_CADASTRADO("O CPF {0} já está cadastrado no sistema."),
    PROFISSIONAL_JA_CADASTRADO_PESSOA("Já existe um profissional cadastrado para a pessoa {0}"),
    PESSOA_CPF_JA_CADASTRADO("CPF {0} já cadastrado."),
    AVALIACAO_PROFISSIONAL_JA_AVALIADO("Você já avaliou esse profissional, deseja atualizar a avaliação?"),
    IMGUR_NAO_FOI_POSSIVEL_EXCUIR("Não foi possível excluir a imagem");

    private final String message;

    EnumCustomException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}

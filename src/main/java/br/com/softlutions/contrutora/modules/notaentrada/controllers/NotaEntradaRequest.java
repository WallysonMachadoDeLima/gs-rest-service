package br.com.softlutions.contrutora.modules.notaentrada.controllers;

import java.util.List;

import br.com.softlutions.contrutora.modules.notaentrada.entities.NotaEntrada;
import br.com.softlutions.contrutora.modules.notaentrada.entities.NotaEntradaItem;

public class NotaEntradaRequest {
    private NotaEntrada nota;
    private List<NotaEntradaItem> itens;

    public NotaEntrada getNota() { return nota; }
    public void setNota(NotaEntrada nota) { this.nota = nota; }

    public List<NotaEntradaItem> getItens() { return itens; }
    public void setItens(List<NotaEntradaItem> itens) { this.itens = itens; }
}

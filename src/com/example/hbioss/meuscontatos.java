package com.example.hbioss;

public class meuscontatos {
	private String name;
	private int idContato;
    private String ultimamensagem;
    private String vistoporultimo;
    private String foto;
    private boolean client;
 
    public meuscontatos() {
    }
 
    public meuscontatos(int idContato, String name, String ultimamensagem, String vistoporultimo, boolean client, String foto) {
        this.idContato = idContato;
    	this.name = name;
        this.ultimamensagem = ultimamensagem;
        this.vistoporultimo = vistoporultimo;
        this.client = client;//pode ser usado para verificar se é amigo ou não na rede
        this.foto = foto;
    }
    
    public int getIdContato() {
        return idContato;
    }
 
    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }    
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    
    public String getVistoPorUltimo() {
        return vistoporultimo;
    }
 
    public void setVistoPorUltimo(String vistoporultimo) {
        this.vistoporultimo = vistoporultimo;
    }     
 
    public String getUltimaMensagem() {
        return ultimamensagem;
    }
 
    public void setUltimaMensagem(String ultimamensagem) {
        this.ultimamensagem = ultimamensagem;
    } 
 
    public boolean isClient() {
        return client;
    }
 
    public void setClient(boolean client) {
        this.client = client;
    }
 
    public String getFoto() {
        return foto;
    }
 
    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public enum Type {
        FEMALE, MALE;
    }
}

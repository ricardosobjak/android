package br.edu.utfpr.android.listview.listview2;

import android.graphics.Bitmap;

public class ItemListView {
	private String texto;
	private Bitmap foto;

	public ItemListView() {
	}

	public ItemListView(String texto, Bitmap foto) {
		this.texto = texto;
		this.foto = foto;
	}

	public void setFoto(Bitmap foto) {
		this.foto = foto;
	}
	
	public Bitmap getFoto() {
		return foto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
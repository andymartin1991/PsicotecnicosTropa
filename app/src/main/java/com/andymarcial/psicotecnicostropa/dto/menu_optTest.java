package com.andymarcial.psicotecnicostropa.dto;

import android.graphics.drawable.Drawable;

public class menu_optTest {

    protected Drawable foto;
    protected String nombre;
    protected String cargo;
    protected long id;

    public menu_optTest(Drawable foto, String nombre, String cargo) {
        super();
        this.foto = foto;
        this.nombre = nombre;
        this.cargo = cargo;
    }

    public menu_optTest(Drawable foto, String nombre, String cargo, long id) {
        super();
        this.foto = foto;
        this.nombre = nombre;
        this.cargo = cargo;
        this.id = id;
    }

    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

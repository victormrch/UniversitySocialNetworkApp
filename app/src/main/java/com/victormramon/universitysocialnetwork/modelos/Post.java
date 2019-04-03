/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victormramon.universitysocialnetwork.modelos;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Jesus
 */
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idPost;

    private String contenido;

    private Date fecha;

    private Usuario idPublicador;

    public Post() {
    }

    public Post(Integer idPost) {
        this.idPost = idPost;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getIdPublicador() {
        return idPublicador;
    }

    public void setIdPublicador(Usuario idPublicador) {
        this.idPublicador = idPublicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPost != null ? idPost.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.idPost == null && other.idPost != null) || (this.idPost != null && !this.idPost.equals(other.idPost))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "redSocial.modelos.Post[ idPost=" + idPost + " ]";
    }

}

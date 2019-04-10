/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victormramon.universitysocialnetwork.modelos;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jesus
 */

public class Grupos implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idGrupo;

    private String nombre;

    private List<Usuario> usuarioList;

    private List<ComentarioGrupo> comentarioGrupoList;


    private Integer groupSize;

    public Grupos() {
    }

    public Grupos(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Grupos(Integer idGrupo, String nombre) {
        this.idGrupo = idGrupo;
        this.nombre = nombre;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }


    public List<ComentarioGrupo> getComentarioGrupoList() {
        return comentarioGrupoList;
    }

    public void setComentarioGrupoList(List<ComentarioGrupo> comentarioGrupoList) {
        this.comentarioGrupoList = comentarioGrupoList;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize() {
        this.groupSize = this.getUsuarioList().size();

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupos)) {
            return false;
        }
        Grupos other = (Grupos) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "redSocial.modelos.Grupos[ idGrupo=" + idGrupo + " ]";
    }

}

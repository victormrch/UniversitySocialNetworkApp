/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victormramon.universitysocialnetwork.modelos;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author Jesus
 */

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nombre;

    private String apellidos;

    private String password;

    private String telefono;

    private String email;

    private List<Grupos> gruposList;

    private List<Usuario> amigosList;

    private List<Post> postList;

    private List<ComentarioGrupo> comentarioGrupoList;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String nombre, String apellidos, String password, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Grupos> getGruposList() {
        return gruposList;
    }

    public void setGruposList(List<Grupos> gruposList) {
        this.gruposList = gruposList;
    }


    public List<Usuario> getAmigosList() {
        return amigosList;
    }

    public void setUsuarioList(List<Usuario> amigosList) {
        this.amigosList = amigosList;
    }



    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }


    public List<ComentarioGrupo> getComentarioGrupoList() {
        return comentarioGrupoList;
    }

    public void setComentarioGrupoList(List<ComentarioGrupo> comentarioGrupoList) {
        this.comentarioGrupoList = comentarioGrupoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "redSocial.modelos.Usuario[ id=" + id + " ]";
    }
    
    public static String toJson(Usuario user) {
        Gson gson = new GsonBuilder().create();

        for(Usuario u: user.getAmigosList()) {
            u.setGruposList(null);
            u.setUsuarioList(null);
            u.setComentarioGrupoList(null);
            u.setPostList(null);
        }
        for (Post p: user.getPostList()) {
            p.setIdPublicador(null);
        }
        for (ComentarioGrupo c: user.getComentarioGrupoList()) {
            c.setIdAutor(null);
            c.setIdGrupo(null);
        }
        for (Grupos g: user.getGruposList()) {
            g.setGroupSize();
            g.setUsuarioList(null);
//            for (Usuario u: g.getUsuarioList())  {
//                if (!u.equals(user)) {
//                    u.setComentarioGrupoList(null);
//                    u.setGruposList(null);
//                    u.setPostList(null);
//                    u.setUsuarioList(null);
//                }
//            }
            for (ComentarioGrupo c: g.getComentarioGrupoList()) {
                c.setIdAutor(null);
                c.setIdGrupo(null);
            }
        }
        String userJson = gson.toJson(user);
        return userJson;
    }
    
}

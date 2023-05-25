package com.example.sqllite;

public class Comentario {

    public Comentario() {
        super();
    }

    public Comentario(String comentario) {
        super();
        this.comentario = comentario;
    }

    private long id;
    private String comentario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}

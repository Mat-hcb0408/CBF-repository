package com.cbf1.cbf1.confrontos;

public class Confrontos {
    private final Integer id_clubeConfronto;
    private final Integer id_partidaConfronto;
    private final Integer id_mandanteConfronto;

    public Confrontos(Integer id_clubeConfronto, Integer id_partidaConfronto, Integer id_mandanteConfronto) {
        this.id_clubeConfronto = id_clubeConfronto;
        this.id_partidaConfronto = id_partidaConfronto;
        this.id_mandanteConfronto = id_mandanteConfronto;
    }

    public Integer getId_clubeConfronto() {
        return id_clubeConfronto;
    }

    public Integer getId_partidaConfronto() {
        return id_partidaConfronto;
    }

    public Integer getId_mandanteConfronto() {
        return id_mandanteConfronto;
    }
}

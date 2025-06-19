package Backend;

import java.util.List;

public class Item {
    public enum TipoItem {
        SELECCION_MULTIPLE,
        VERDADERO_FALSO
    }

    public enum NivelBloom {
        RECORDAR,
        ENTENDER,
        APLICAR,
        ANALIZAR,
        EVALUAR,
        CREAR
    }

    private String pregunta;
    private List<String> opciones;
    private int respuestaCorrecta;
    private TipoItem tipo;
    private NivelBloom nivel;
    private int tiempoEstimado;

    public Item(String pregunta, List<String> opciones, int respuestaCorrecta,
                TipoItem tipo, NivelBloom nivel, int tiempoEstimado) {
        this.pregunta = pregunta;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.tipo = tipo;
        this.nivel = nivel;
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getPregunta() {
        return pregunta;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public NivelBloom getNivel() {
        return nivel;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }
}
package Backend;

import java.util.*;

public class Prueba {
    private List<Item> items;
    private Map<Integer, Integer> respuestas;
    private boolean finalizada;

    public Prueba(List<Item> items) {
        this.items = items;
        this.respuestas = new HashMap<>();
        this.finalizada = false;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getCantidadItems() {
        return items.size();
    }

    public int getTiempoTotalEstimado() {
        return items.stream().mapToInt(Item::getTiempoEstimado).sum();
    }

    public void guardarRespuesta(int indiceItem, int indiceRespuesta) {
        if (!finalizada) {
            respuestas.put(indiceItem, indiceRespuesta);
        }
    }

    public Integer getRespuestaUsuario(int indiceItem) {
        return respuestas.get(indiceItem);
    }

    public boolean esRespuestaCorrecta(int indiceItem) {
        Integer resp = respuestas.get(indiceItem);
        if (resp == null) return false;
        return resp == items.get(indiceItem).getRespuestaCorrecta();
    }

    public void finalizar() {
        this.finalizada = true;
    }

    public boolean estaFinalizada() {
        return finalizada;
    }

    public Map<Item.NivelBloom, Double> porcentajeCorrectasPorNivel() {
        Map<Item.NivelBloom, Integer> totales = new EnumMap<>(Item.NivelBloom.class);
        Map<Item.NivelBloom, Integer> correctas = new EnumMap<>(Item.NivelBloom.class);

        for (Item.NivelBloom nivel : Item.NivelBloom.values()) {
            totales.put(nivel, 0);
            correctas.put(nivel, 0);
        }

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            Item.NivelBloom nivel = item.getNivel();
            totales.put(nivel, totales.get(nivel) + 1);
            if (esRespuestaCorrecta(i)) {
                correctas.put(nivel, correctas.get(nivel) + 1);
            }
        }

        Map<Item.NivelBloom, Double> porcentajes = new EnumMap<>(Item.NivelBloom.class);
        for (Item.NivelBloom nivel : Item.NivelBloom.values()) {
            int total = totales.get(nivel);
            if (total == 0) {
                porcentajes.put(nivel, 0.0);
            } else {
                porcentajes.put(nivel, 100.0 * correctas.get(nivel) / total);
            }
        }
        return porcentajes;
    }

    public Map<Item.TipoItem, Double> porcentajeCorrectasPorTipo() {
        Map<Item.TipoItem, Integer> totales = new EnumMap<>(Item.TipoItem.class);
        Map<Item.TipoItem, Integer> correctas = new EnumMap<>(Item.TipoItem.class);

        for (Item.TipoItem tipo : Item.TipoItem.values()) {
            totales.put(tipo, 0);
            correctas.put(tipo, 0);
        }

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            Item.TipoItem tipo = item.getTipo();
            totales.put(tipo, totales.get(tipo) + 1);
            if (esRespuestaCorrecta(i)) {
                correctas.put(tipo, correctas.get(tipo) + 1);
            }
        }

        Map<Item.TipoItem, Double> porcentajes = new EnumMap<>(Item.TipoItem.class);
        for (Item.TipoItem tipo : Item.TipoItem.values()) {
            int total = totales.get(tipo);
            if (total == 0) {
                porcentajes.put(tipo, 0.0);
            } else {
                porcentajes.put(tipo, 100.0 * correctas.get(tipo) / total);
            }
        }
        return porcentajes;
    }
}

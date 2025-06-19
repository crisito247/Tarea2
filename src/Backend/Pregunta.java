package Backend;

import java.util.List;

public class Pregunta {
    private Item item;
    private List<String> respuestasUsuario;
    private boolean respondidaCorrectamente;

    public Pregunta(Item item) {
        this.item = item;
        this.respondidaCorrectamente = false;
    }

    public Item getItem() {
        return item;
    }

    public void responder(List<String> respuestasUsuario) {
        this.respuestasUsuario = respuestasUsuario;
        this.respondidaCorrectamente = evaluarRespuesta();
    }

    private boolean evaluarRespuesta() {
        if (item.getTipo() == Item.TipoItem.SELECCION_MULTIPLE) {
            try {
                int respuesta = Integer.parseInt(respuestasUsuario.get(0));
                return respuesta == item.getRespuestaCorrecta();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return false;
            }
        } else if (item.getTipo() == Item.TipoItem.VERDADERO_FALSO) {
            String respuesta = respuestasUsuario.get(0).toLowerCase();
            String correcta = item.getOpciones().get(item.getRespuestaCorrecta()).toLowerCase();
            return respuesta.equals(correcta);
        }
        return false;
    }

    public boolean esRespondidaCorrectamente() {
        return respondidaCorrectamente;
    }

    public List<String> getRespuestasUsuario() {
        return respuestasUsuario;
    }
}

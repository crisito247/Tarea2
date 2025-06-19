import Backend.Item;
import Backend.Pregunta;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Item item = new Item(
                "¿Cuál es la capital de Francia?",
                Arrays.asList("Madrid", "París", "Londres", "Berlín"),
                1,
                Item.TipoItem.SELECCION_MULTIPLE,
                Item.NivelBloom.RECORDAR,
                30
        );

        Pregunta pregunta = new Pregunta(item);
        pregunta.responder(Arrays.asList("1"));

        System.out.println("¿Respuesta correcta? " + pregunta.esRespondidaCorrectamente());
    }
}


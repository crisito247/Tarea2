package Backend;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CargadorDeItems {
    public static List<Item> cargarDesdeArchivo(File archivo) throws Exception {
        List<Item> items = new ArrayList<>();
        List<String> lineas = Files.readAllLines(archivo.toPath());

        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes.length < 6)
                throw new Exception("Formato incorrecto en línea: " + linea);

            String pregunta = partes[0];
            List<String> opciones = Arrays.asList(partes[1].split("\\|"));
            int respuesta = Integer.parseInt(partes[2]);
            Item.TipoItem tipo = Item.TipoItem.valueOf(partes[3].toUpperCase());
            Item.NivelBloom nivel = Item.NivelBloom.valueOf(partes[4].toUpperCase());
            int tiempo = Integer.parseInt(partes[5]);

            items.add(new Item(pregunta, opciones, respuesta, tipo, nivel, tiempo));
        }

        return items;
    }
}

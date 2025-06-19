package Frontend;

import Backend.Item;
import Backend.Prueba;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VentanaResumen extends JFrame {
    private Prueba prueba;
    private VentanaInicial ventanaInicial;
    private VentanaPrueba ventanaPrueba;

    private JTextArea txtResumen;
    private JButton btnRevisarRespuestas, btnVolverAlInicio;

    public VentanaResumen(Prueba prueba, VentanaInicial ventanaInicial, VentanaPrueba ventanaPrueba) {
        this.prueba = prueba;
        this.ventanaInicial = ventanaInicial;
        this.ventanaPrueba = ventanaPrueba;

        setTitle("Resumen de resultados - Tarea2025");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponentes();
        mostrarResumen();
    }

    private void initComponentes() {
        setLayout(new BorderLayout());

        txtResumen = new JTextArea();
        txtResumen.setEditable(false);
        txtResumen.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(txtResumen);

        btnRevisarRespuestas = new JButton("Revisar respuestas");
        btnVolverAlInicio = new JButton("Volver al inicio");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnRevisarRespuestas);
        panelBotones.add(btnVolverAlInicio);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnRevisarRespuestas.addActionListener(e -> revisarRespuestas());
        btnVolverAlInicio.addActionListener(e -> volverAlInicio());
    }

    private void mostrarResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("Resumen de la prueba:\n\n");

        int correctas = 0;
        for (int i = 0; i < prueba.getCantidadItems(); i++) {
            if (prueba.esRespuestaCorrecta(i)) {
                correctas++;
            }
        }

        sb.append(String.format("Preguntas correctas: %d de %d (%.2f%%)\n\n",
                correctas, prueba.getCantidadItems(), 100.0 * correctas / prueba.getCantidadItems()));

        sb.append("Porcentaje de aciertos por nivel Bloom:\n");
        Map<Item.NivelBloom, Double> porNivel = prueba.porcentajeCorrectasPorNivel();
        for (Item.NivelBloom nivel : Item.NivelBloom.values()) {
            sb.append(String.format(" - %-10s : %.2f%%\n", nivel, porNivel.get(nivel)));
        }

        sb.append("\nPorcentaje de aciertos por tipo de ítem:\n");
        Map<Item.TipoItem, Double> porTipo = prueba.porcentajeCorrectasPorTipo();
        for (Item.TipoItem tipo : Item.TipoItem.values()) {
            sb.append(String.format(" - %-20s : %.2f%%\n", tipo, porTipo.get(tipo)));
        }

        txtResumen.setText(sb.toString());
    }

    private void revisarRespuestas() {
        VentanaPrueba ventanaRevision = new VentanaPrueba(prueba, ventanaInicial);
        ventanaRevision.setVisible(true);
        ventanaRevision.activarModoRevision();
        this.dispose();
    }

    private void volverAlInicio() {
        ventanaInicial.setVisible(true);
        this.dispose();
    }
}
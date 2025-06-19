package Frontend;

import Backend.Item;
import Backend.Prueba;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaPrueba extends JFrame {
    private Prueba prueba;
    private int indiceActual = 0;
    private VentanaInicial ventanaInicial;

    private JLabel lblPregunta;
    private JRadioButton[] opcionesRadio;
    private ButtonGroup grupoOpciones;
    private JButton btnAnterior, btnSiguiente;

    private boolean modoRevision = false;

    public VentanaPrueba(Prueba prueba, VentanaInicial ventanaInicial) {
        this.prueba = prueba;
        this.ventanaInicial = ventanaInicial;

        setTitle("Aplicación de Prueba - Ítem");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponentes();
        mostrarItem(indiceActual);
    }

    private void initComponentes() {
        setLayout(new BorderLayout());

        lblPregunta = new JLabel("Pregunta");
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        lblPregunta.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.Y_AXIS));
        grupoOpciones = new ButtonGroup();

        opcionesRadio = new JRadioButton[4];
        for (int i = 0; i < opcionesRadio.length; i++) {
            opcionesRadio[i] = new JRadioButton();
            grupoOpciones.add(opcionesRadio[i]);
            panelOpciones.add(opcionesRadio[i]);
        }

        JPanel panelBotones = new JPanel();
        btnAnterior = new JButton("Volver atrás");
        btnSiguiente = new JButton("Siguiente");

        btnAnterior.addActionListener(e -> irAnterior());
        btnSiguiente.addActionListener(e -> irSiguiente());

        panelBotones.add(btnAnterior);
        panelBotones.add(btnSiguiente);

        add(lblPregunta, BorderLayout.NORTH);
        add(panelOpciones, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void mostrarItem(int indice) {
        Item item = prueba.getItems().get(indice);
        lblPregunta.setText((indice + 1) + ". " + item.getPregunta());

        List<String> opciones = item.getOpciones();

        for (int i = 0; i < opcionesRadio.length; i++) {
            if (i < opciones.size()) {
                opcionesRadio[i].setText(opciones.get(i));
                opcionesRadio[i].setVisible(true);
                opcionesRadio[i].setEnabled(!modoRevision);
            } else {
                opcionesRadio[i].setVisible(false);
            }
            opcionesRadio[i].setForeground(Color.BLACK);
            opcionesRadio[i].setIcon(null);
        }

        Integer respUsuario = prueba.getRespuestaUsuario(indice);
        if (respUsuario != null && respUsuario < opciones.size()) {
            opcionesRadio[respUsuario].setSelected(true);
        } else {
            grupoOpciones.clearSelection();
        }

        if (modoRevision) {
            int correcta = item.getRespuestaCorrecta();
            for (int i = 0; i < opciones.size(); i++) {
                if (i == correcta) {
                    opcionesRadio[i].setText(opciones.get(i) + " ✓");
                } else if (prueba.getRespuestaUsuario(indice) != null && prueba.getRespuestaUsuario(indice) == i) {
                    opcionesRadio[i].setText(opciones.get(i) + " ✗");
                }
            }
        }

        btnAnterior.setEnabled(indice > 0);
        if (modoRevision) {
            btnSiguiente.setText(indice == prueba.getCantidadItems() - 1 ? "Volver al resumen" : "Siguiente");
        } else {
            btnSiguiente.setText(indice == prueba.getCantidadItems() - 1 ? "Enviar respuestas" : "Siguiente");
        }
    }

    private void irAnterior() {
        if (indiceActual > 0) {
            indiceActual--;
            mostrarItem(indiceActual);
        }
    }

    private void irSiguiente() {
        if (modoRevision) {
            if (indiceActual == prueba.getCantidadItems() - 1) {
                VentanaResumen ventanaResumen = new VentanaResumen(prueba, ventanaInicial, this);
                ventanaResumen.setVisible(true);
                this.dispose();
                return;
            }
            indiceActual++;
            mostrarItem(indiceActual);
            return;
        }

        int seleccionada = -1;
        for (int i = 0; i < opcionesRadio.length; i++) {
            if (opcionesRadio[i].isSelected()) {
                seleccionada = i;
                break;
            }
        }
        if (seleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una respuesta para continuar.");
            return;
        }
        prueba.guardarRespuesta(indiceActual, seleccionada);

        if (indiceActual == prueba.getCantidadItems() - 1) {
            prueba.finalizar();
            VentanaResumen ventanaResumen = new VentanaResumen(prueba, ventanaInicial, this);
            ventanaResumen.setVisible(true);
            this.dispose();
        } else {
            indiceActual++;
            mostrarItem(indiceActual);
        }
    }

    public void activarModoRevision() {
        modoRevision = true;
        mostrarItem(indiceActual);
    }
}
package Frontend;

import Backend.CargadorDeItems;
import Backend.Item;
import Backend.Prueba;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class VentanaInicial extends JFrame {
    private JLabel lblInfo;
    private JButton btnCargarArchivo, btnIniciarPrueba;
    private File archivoSeleccionado;
    private Prueba prueba;

    public VentanaInicial() {
        setTitle("Cargar prueba - Tarea2025");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        lblInfo = new JLabel("Seleccione un archivo para cargar los ítems.");
        btnCargarArchivo = new JButton("Cargar archivo");
        btnIniciarPrueba = new JButton("Iniciar prueba");
        btnIniciarPrueba.setEnabled(false);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCargarArchivo);
        panelBotones.add(btnIniciarPrueba);

        add(lblInfo, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnCargarArchivo.addActionListener(e -> cargarArchivo());
        btnIniciarPrueba.addActionListener(e -> iniciarPrueba());
    }

    private void cargarArchivo() {
        JFileChooser chooser = new JFileChooser(".");
        int opcion = chooser.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            archivoSeleccionado = chooser.getSelectedFile();
            try {
                List<Item> items = CargadorDeItems.cargarDesdeArchivo(archivoSeleccionado);
                prueba = new Prueba(items);
                lblInfo.setText("Ítems cargados: " + prueba.getCantidadItems() +
                        " - Tiempo total estimado: " + prueba.getTiempoTotalEstimado() + " min.");
                btnIniciarPrueba.setEnabled(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar el archivo:\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void iniciarPrueba() {
        if (prueba != null) {
            VentanaPrueba ventanaPrueba = new VentanaPrueba(prueba, this);
            ventanaPrueba.setVisible(true);
            this.setVisible(false);
        }
    }
}
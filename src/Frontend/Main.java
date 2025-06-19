package Frontend;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaInicial ventanaInicial = new VentanaInicial();
            ventanaInicial.setVisible(true);
        });
    }
}

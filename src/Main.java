import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Control controller = new Control();
            controller.showMainMenu();
        });
    }
}

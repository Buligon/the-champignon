package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class TelaInicial {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("The Champignon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(true);

        // Load the image for the background
        ImageIcon imageIcon = new ImageIcon("src/main/java/utils/background.jpg");

        // Create a JLabel for the background
        JLabel background = new JLabel(imageIcon) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imageIcon.getImage() != null) {
                    g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Set the layout to null to allow manual placement of components
        background.setLayout(null);

        frame.setContentPane(background);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create icon ImageIcons for the menu items
        // Create and resize icon ImageIcons for the menu items
        ImageIcon pessoasIcon = resizeIcon("src/main/java/utils/icon_person.png", 30, 30);
        ImageIcon movimentacoesIcon = resizeIcon("src/main/java/utils/icon_movimentacao.png", 30, 30);
        ImageIcon estoqueIcon = resizeIcon("src/main/java/utils/icon_cogumelo.png", 30, 30);

         // Menu "Pessoas" with icon
        JMenu pessoasMenu = new JMenu("");
        pessoasMenu.setIcon(pessoasIcon);

        // Sub-menu items under "Pessoas"
        JMenuItem funcionariosMenuItem = new JMenuItem("Funcionários");
        JMenuItem fornecedoresMenuItem = new JMenuItem("Fornecedores");
        JMenuItem clientesMenuItem = new JMenuItem("Clientes");

        // Add sub-menu items to "Pessoas"
        pessoasMenu.add(funcionariosMenuItem);
        pessoasMenu.add(fornecedoresMenuItem);
        pessoasMenu.add(clientesMenuItem);

        // Menu "Movimentações" with icon
        JMenu movimentacoesMenu = new JMenu("");
        movimentacoesMenu.setIcon(movimentacoesIcon);

        // Sub-menu items under "Movimentações"
        JMenuItem compraMenuItem = new JMenuItem("Compra");
        JMenuItem vendaMenuItem = new JMenuItem("Venda");

        // Add sub-menu items to "Movimentações"
        movimentacoesMenu.add(compraMenuItem);
        movimentacoesMenu.add(vendaMenuItem);

        // Menu "Estoque" with icon
        JMenu estoqueMenu = new JMenu("");
        estoqueMenu.setIcon(estoqueIcon);

        // Sub-menu items under "Estoque"
        JMenuItem cogumelosMenuItem = new JMenuItem("Cogumelos");
        JMenuItem classesMenuItem = new JMenuItem("Classes");
        JMenuItem unidadesMenuItem = new JMenuItem("Unidades");

        // Add sub-menu items to "Estoque"
        estoqueMenu.add(cogumelosMenuItem);
        estoqueMenu.add(classesMenuItem);
        estoqueMenu.add(unidadesMenuItem);

        // Add menus to the menu bar
        menuBar.add(pessoasMenu);
        menuBar.add(movimentacoesMenu);
        menuBar.add(estoqueMenu);

        // Add the menu bar to the frame
        frame.setJMenuBar(menuBar);

        // Add action listeners for menu items if needed
        // ...
        
        

        frame.setVisible(true);
    }
    
    // Helper method to resize an ImageIcon
    private static ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaInicial {
    private static ImageIcon redimensionaIcone(String path, int tamanho) {
        ImageIcon icon = new ImageIcon(path);
        
        Image image = icon.getImage().getScaledInstance(tamanho, tamanho, Image.SCALE_SMOOTH);
        
        return new ImageIcon(image);
    }

    private static void init() {
        JFrame frameTela = new JFrame("The Champignon");
        frameTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTela.setSize(800, 600);
        frameTela.setResizable(true);
        frameTela.setLocationRelativeTo(null);

        // Seleciona a imagem para o background da tela inicial e sobrescreve o fundo original
        ImageIcon image = new ImageIcon("src/main/java/utils/background.jpg");

        JLabel background = new JLabel(image) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image.getImage() != null) {
                    g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        background.setLayout(null);
        frameTela.setContentPane(background);

        // Cria a barra para o menu
        JMenuBar menuBar = new JMenuBar();

        // Cria e redimensiona os ícones para os itens do menu
        int tamanhoIcones = 30;
        ImageIcon pessoasIcon = redimensionaIcone("src/main/java/utils/icon_person.png", tamanhoIcones);
        ImageIcon movimentacoesIcon = redimensionaIcone("src/main/java/utils/icon_movimentacao.png", tamanhoIcones);
        ImageIcon estoqueIcon = redimensionaIcone("src/main/java/utils/icon_cogumelo.png", tamanhoIcones);

        // Criação dos menus e seus itens
        // Menu pessoas
        JMenu pessoasMenu = new JMenu("");
        pessoasMenu.setIcon(pessoasIcon);

        JMenuItem funcionariosMenuItem = new JMenuItem("Funcionários");
        JMenuItem fornecedoresMenuItem = new JMenuItem("Fornecedores");
        JMenuItem clientesMenuItem = new JMenuItem("Clientes");

        pessoasMenu.add(funcionariosMenuItem);
        pessoasMenu.add(fornecedoresMenuItem);
        pessoasMenu.add(clientesMenuItem);

        // Menu movimentações
        JMenu movimentacoesMenu = new JMenu("");
        movimentacoesMenu.setIcon(movimentacoesIcon);

        JMenuItem compraMenuItem = new JMenuItem("Compra");
        JMenuItem vendaMenuItem = new JMenuItem("Venda");

        movimentacoesMenu.add(compraMenuItem);
        movimentacoesMenu.add(vendaMenuItem);

        // Menu estoque
        JMenu estoqueMenu = new JMenu("");
        estoqueMenu.setIcon(estoqueIcon);

        JMenuItem cogumelosMenuItem = new JMenuItem("Cogumelos");
        JMenuItem especiesMenuItem = new JMenuItem("Espécies");
        JMenuItem unidadesMenuItem = new JMenuItem("Unidades");

        estoqueMenu.add(cogumelosMenuItem);
        estoqueMenu.add(especiesMenuItem);
        estoqueMenu.add(unidadesMenuItem);

        // Adição dos menus na barra
        menuBar.add(pessoasMenu);
        menuBar.add(movimentacoesMenu);
        menuBar.add(estoqueMenu);

        frameTela.setJMenuBar(menuBar);

        // Add action listener for "Espécies" menu item
        especiesMenuItem.addActionListener((ActionEvent e) -> {
            abreTela(0, frameTela);
        });
        
        frameTela.setVisible(true);
    }
    
    private static void abreTela(int telaNum, JFrame frameTela) {
        SwingUtilities.invokeLater(() -> {
            switch (telaNum) {
                case 0:
                    TelaEspecies tela = new TelaEspecies(frameTela);
                    tela.setVisible(true);
                    break;
                default:
                    throw new AssertionError();
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> init());
    }
    
}

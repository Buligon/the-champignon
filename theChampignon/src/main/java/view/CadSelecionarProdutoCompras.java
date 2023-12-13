package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.rn.ProdutosRN;
import model.vo.Produtos;

public class CadSelecionarProdutoCompras extends JDialog {
    private JLabel label_quantidade;
    private JTextField txtfield_quantidade;
    private JButton btn_adicionar;
    private JButton btn_cancelar;
    private JTable tableProdutos;
    private DefaultTableModel produtosTableModel;
    private CadCompras parent;

    private Produtos produtoSelecionado;
    private float quantidadeSelecionado;

    public CadSelecionarProdutoCompras(CadCompras parent) {
        this.parent = parent;
        setTitle("Selecionar Produto");
        setResizable(false);
        setLocationRelativeTo(parent);

        label_quantidade = new JLabel("Quantidade:");
        txtfield_quantidade = new JTextField();
        btn_adicionar = new JButton("Adicionar");
        btn_cancelar = new JButton("Cancelar");
        produtosTableModel = new DefaultTableModel();


        produtosTableModel.addColumn("ID");
        produtosTableModel.addColumn("Descrição");
        produtosTableModel.addColumn("Quantidade");
        produtosTableModel.addColumn("Custo");
        produtosTableModel.addColumn("Valor");
        produtosTableModel.addColumn("Espécie");
        produtosTableModel.addColumn("Unidade");

        listarProdutos();

        tableProdutos = new JTable(produtosTableModel);
        tableProdutos.setDefaultEditor(Object.class, null);
        tableProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tableProdutos);

        btn_adicionar.addActionListener((ActionEvent e) -> {
            handleAddButtonAction();
        });

        btn_cancelar.addActionListener((ActionEvent e) -> {
            produtoSelecionado = null;
            quantidadeSelecionado = 0;
            dispose();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                produtoSelecionado = null;
                quantidadeSelecionado = 0;
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(label_quantidade)
                                .addComponent(txtfield_quantidade)
                                .addComponent(scrollPane)
                                 .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cancelar)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_adicionar)
                        )
                        )
                        .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_quantidade)
                .addComponent(txtfield_quantidade)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPane)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(btn_cancelar)
                        .addComponent(btn_adicionar)
                )
                .addContainerGap(20, Short.MAX_VALUE)
        );

        pack();
    }
    
    private void handleAddButtonAction() {
        int selectedRowIndex = tableProdutos.getSelectedRow();
        if (selectedRowIndex != -1) {
            ProdutosRN produtosRN = new ProdutosRN();

            long idProduto = (long) tableProdutos.getValueAt(selectedRowIndex, 0);
            produtoSelecionado = produtosRN.obterProdutoPorId(idProduto);

            try {
                quantidadeSelecionado = Float.parseFloat(txtfield_quantidade.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }  
            
            if (produtoSelecionado == null || quantidadeSelecionado <= 0) {
                JOptionPane.showMessageDialog(this, "Selecione um produto e informe uma quantidade válida!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                parent.adicionarProdutoTabela(produtoSelecionado, quantidadeSelecionado);
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto na tabela!", "Nenhum produto selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void listarProdutos() {
        ProdutosRN produtosRN = new ProdutosRN();

        List<Produtos> produtos = produtosRN.listarProdutos();
        for (Produtos produto : produtos) {
            Object[] rowData = {
                    produto.getId(),
                    produto.getDescricao(),
                    produto.getQuantidade(),
                    produto.getCusto(),
                    produto.getValor(),
                    produto.getEspecie().getDescricao(),
                    produto.getUnidade().getDescricao()
            };
            produtosTableModel.addRow(rowData);
        }
    }

}
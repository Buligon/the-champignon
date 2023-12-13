package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import model.rn.FuncionariosRN;
import model.rn.FornecedoresRN;
import model.rn.ComprasRN;
import model.vo.Funcionarios;
import model.vo.Fornecedores;
import model.vo.Produtos;

public class CadCompras extends JFrame {

    private JLabel label_dataCompra;
    private JFormattedTextField txtfield_dataCompra;
    private JLabel label_funcionario;
    private JComboBox<String> comboFuncionario;
    private JLabel label_cliente;
    private JComboBox<String> comboFornecedor;
    private JButton btn_adicionar;
    private JButton btn_cancelar;
    private TelaCompras parent;
    private DefaultTableModel produtosTableModel;
    private JTable tableProducts;
    private JScrollPane scrollPaneProducts;
    private JButton btn_addProduct;
    private JButton btn_removeProduct;
    private List<Produtos> produtosSelecionados;
    private List<Float> quantidade;

    public CadCompras(TelaCompras parent) {
        this.parent = parent;
        label_dataCompra = new JLabel();
        MaskFormatter dateFormatter;
        try {
            dateFormatter = new MaskFormatter("##/##/####");
            txtfield_dataCompra = new JFormattedTextField(dateFormatter);
        } catch (ParseException e) {
        }
        label_funcionario = new JLabel();
        comboFuncionario = new javax.swing.JComboBox<>();
        label_cliente = new JLabel();
        comboFornecedor = new javax.swing.JComboBox<>();
        btn_adicionar = new JButton();
        btn_cancelar = new JButton();
        produtosTableModel = new DefaultTableModel();
        tableProducts = new JTable(produtosTableModel);
        scrollPaneProducts = new JScrollPane(tableProducts);
        btn_addProduct = new JButton("Adicionar Produto");
        btn_removeProduct = new JButton("Remover Produto");
        produtosSelecionados = new ArrayList<>();
        quantidade = new ArrayList<>();

        setTitle("Cadastro de Compras");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        label_dataCompra.setText("Data da Compra:");
        txtfield_dataCompra.setColumns(10);

        label_funcionario.setText("Funcionário:");
        prencheComboBoxFuncionarios();

        label_cliente.setText("Fornecedor:");
        prencheComboBoxFornecedores();

        btn_adicionar.setText("Adicionar");
        btn_cancelar.setText("Cancelar");

        tableProducts.setModel(produtosTableModel);
        produtosTableModel.addColumn("Produto");
        produtosTableModel.addColumn("Quantidade");
        produtosTableModel.addColumn("Valor Unitário");
        produtosTableModel.addColumn("Valor Total");

        btn_addProduct.addActionListener((ActionEvent e) -> {
            CadSelecionarProdutoCompras dialog = new CadSelecionarProdutoCompras(CadCompras.this);
            dialog.setVisible(true);
        });

        btn_removeProduct.addActionListener((ActionEvent e) -> {
            int[] selectedRows = tableProducts.getSelectedRows();
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                produtosTableModel.removeRow(selectedRows[i]);
                produtosSelecionados.remove(selectedRows[i]);
                quantidade.remove(selectedRows[i]);
            }
        });

        btn_cancelar.addActionListener((ActionEvent e) -> {
            dispose();
        });

        btn_adicionar.addActionListener((ActionEvent e) -> {
            String dataCompra = txtfield_dataCompra.getText().trim();
            String selectedFuncionario = (String) comboFuncionario.getSelectedItem();
            String selectedFornecedor = (String) comboFornecedor.getSelectedItem();

            if (!dataCompra.isEmpty() && selectedFuncionario != null && selectedFornecedor != null) {
                ComprasRN comprasRN = new ComprasRN();

                if (comprasRN.adicionarCompras(dataCompra, selectedFuncionario, selectedFornecedor, produtosSelecionados, quantidade) == 1) {
                    parent.listarCompras();
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(
                        CadCompras.this,
                        "Por favor, preencha todos os campos!",
                        "Atenção",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label_dataCompra)
                        .addComponent(txtfield_dataCompra)
                        .addComponent(label_funcionario)
                        .addComponent(comboFuncionario)
                        .addComponent(label_cliente)
                        .addComponent(comboFornecedor)
                        .addComponent(scrollPaneProducts)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_addProduct)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_removeProduct)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cancelar)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_adicionar)
                        )
                )
                .addContainerGap(35, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(label_dataCompra)
                .addComponent(txtfield_dataCompra)
                .addComponent(label_funcionario)
                .addComponent(comboFuncionario)
                .addComponent(label_cliente)
                .addComponent(comboFornecedor)
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(scrollPaneProducts)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(btn_addProduct)
                        .addComponent(btn_removeProduct)
                )
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(btn_cancelar)
                        .addComponent(btn_adicionar)
                )
                .addContainerGap(20, Short.MAX_VALUE)
        );

        pack();
    }

    public void adicionarProdutoTabela(Produtos produto, float quantidadeProduto) {
        if (produto != null && quantidadeProduto > 0) {
            double precoUnitario = produto.getValor();
            double valorTotal = precoUnitario * quantidadeProduto;

            Object[] rowData = {
                produto.getDescricao(),
                quantidadeProduto,
                precoUnitario,
                valorTotal
            };
            quantidade.add(quantidadeProduto);
            produtosTableModel.addRow(rowData);
            produtosSelecionados.add(produto);
        }
    }

    private void prencheComboBoxFuncionarios() {
        FuncionariosRN funcionariosRN = new FuncionariosRN();
        List<Funcionarios> funcionariosList = funcionariosRN.listarFuncionario();

        List<String> descricaoList = funcionariosList.stream().map(Funcionarios::getNome).toList();

        comboFuncionario.setModel(new DefaultComboBoxModel<>(descricaoList.toArray(String[]::new)));
    }

    private void prencheComboBoxFornecedores() {
        FornecedoresRN fornecedoresRN = new FornecedoresRN();
        List<Fornecedores> fornecedoresList = fornecedoresRN.listarFornecedores();

        List<String> descricaoList = fornecedoresList.stream().map(Fornecedores::getRazaoSocial).toList();

        comboFornecedor.setModel(new DefaultComboBoxModel<>(descricaoList.toArray(String[]::new)));
    }

}

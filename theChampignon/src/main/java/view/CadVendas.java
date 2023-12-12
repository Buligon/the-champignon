package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import model.rn.FuncionariosRN;
import model.rn.ClientesRN;
import model.rn.VendasRN;
import model.vo.Funcionarios;
import model.vo.Clientes;
import model.vo.Produtos;

public class CadVendas extends JFrame {

    private JLabel label_dataVenda;
    private JFormattedTextField txtfield_dataVenda;
    private JLabel label_funcionario;
    private JComboBox<String> comboFuncionario;
    private JLabel label_cliente;
    private JComboBox<String> comboCliente;
    private JButton btn_adicionar;
    private JButton btn_cancelar;
    private TelaVendas parent;
    private DefaultTableModel produtosTableModel;
    private JTable tableProducts;
    private JScrollPane scrollPaneProducts;
    private JButton btn_addProduct;
    private JButton btn_removeProduct;
    private List<Produtos> produtosSelecionados;
    private List<Float> quantidade;

    public CadVendas(TelaVendas parent) {
        this.parent = parent;
        label_dataVenda = new JLabel();
        MaskFormatter dateFormatter;
        try {
            dateFormatter = new MaskFormatter("##/##/####");
            txtfield_dataVenda = new JFormattedTextField(dateFormatter);
        } catch (ParseException e) {}
        label_funcionario = new JLabel();
        comboFuncionario = new javax.swing.JComboBox<>();
        label_cliente = new JLabel();
        comboCliente = new javax.swing.JComboBox<>();
        btn_adicionar = new JButton();
        btn_cancelar = new JButton();
        produtosTableModel = new DefaultTableModel();
        tableProducts = new JTable(produtosTableModel);
        scrollPaneProducts = new JScrollPane(tableProducts);
        btn_addProduct = new JButton("Adicionar Produto");
        btn_removeProduct = new JButton("Remover Produto");
        produtosSelecionados = new ArrayList<>();
        quantidade = new ArrayList<>();

        setTitle("Cadastro de Vendas");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        label_dataVenda.setText("Data da Venda:");
        txtfield_dataVenda.setColumns(10);
        
        label_funcionario.setText("Funcionário:");
        prencheComboBoxFuncionarios();

        label_cliente.setText("Cliente:");
        prencheComboBoxClientes();

        btn_adicionar.setText("Adicionar");
        btn_cancelar.setText("Cancelar");

        tableProducts.setModel(produtosTableModel);
        produtosTableModel.addColumn("Produto");
        produtosTableModel.addColumn("Quantidade");
        produtosTableModel.addColumn("Valor Unitário");
        produtosTableModel.addColumn("Valor Total");

        btn_addProduct.addActionListener((ActionEvent e) -> {
            CadSelecionarProduto dialog = new CadSelecionarProduto(CadVendas.this);
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
            String dataVenda = txtfield_dataVenda.getText().trim();
            String selectedFuncionario = (String) comboFuncionario.getSelectedItem();
            String selectedCliente = (String) comboCliente.getSelectedItem();
            
            if (!dataVenda.isEmpty() && selectedFuncionario != null && selectedCliente != null) {
                VendasRN vendasRN = new VendasRN();
                
                if (vendasRN.adicionarVenda(dataVenda, selectedFuncionario, selectedCliente, produtosSelecionados, quantidade) == 1) {
                    parent.listarVendas();
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(
                        CadVendas.this,
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
                        .addComponent(label_dataVenda)
                        .addComponent(txtfield_dataVenda)
                        .addGap(10)
                        .addComponent(label_funcionario)
                        .addComponent(comboFuncionario)
                        .addGap(10)
                        .addComponent(label_cliente)
                        .addComponent(comboCliente)
                        .addComponent(scrollPaneProducts)
                        .addComponent(btn_addProduct)
                        .addComponent(btn_removeProduct)
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
                .addComponent(label_dataVenda)
                .addComponent(txtfield_dataVenda)
                .addGap(10)
                .addComponent(label_funcionario)
                .addComponent(comboFuncionario)
                .addGap(10)
                .addComponent(label_cliente)
                .addComponent(comboCliente)
                .addComponent(scrollPaneProducts)
                .addComponent(btn_addProduct)
                .addComponent(btn_removeProduct)
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

    private void prencheComboBoxClientes() {
        ClientesRN clientesRN = new ClientesRN();
        List<Clientes> clientesList = clientesRN.listarClientes();

        List<String> descricaoList = clientesList.stream().map(Clientes::getNome).toList();

        comboCliente.setModel(new DefaultComboBoxModel<>(descricaoList.toArray(String[]::new)));
    }

}

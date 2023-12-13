package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.rn.ComprasRN;
import model.rn.VendasRN;
import model.vo.Compras;
import model.vo.Vendas;

public class TelaCompras extends javax.swing.JFrame {

    private JButton btn_adicionar;
    private JButton btn_excluir;
    private javax.swing.JComboBox<String> combo_filtros;
    private javax.swing.JPanel panel_tela;
    private JScrollPane scrollpanel_tabela;
    private javax.swing.JLabel label_filtros;
    private javax.swing.JLabel label_pesquisa;
    private javax.swing.JTable tab_compras;
    private javax.swing.JTextField txtfield_pesquisa;
    private DefaultTableModel tableModel;
    private int registroSelecionado = -1;

    public TelaCompras(JFrame callingFrame) {
        panel_tela = new javax.swing.JPanel();
        btn_adicionar = new JButton();
        scrollpanel_tabela = new JScrollPane();
        tab_compras = new JTable();
        btn_excluir = new JButton();
        txtfield_pesquisa = new javax.swing.JTextField();
        combo_filtros = new javax.swing.JComboBox<>();
        label_pesquisa = new javax.swing.JLabel();
        label_filtros = new javax.swing.JLabel();
        tableModel = new DefaultTableModel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Compras");
        setResizable(true);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Data Compra");
        tableModel.addColumn("Funcionário");
        tableModel.addColumn("Fornecedor");
        tableModel.addColumn("Valor Total");

        tab_compras.setModel(tableModel);
        tab_compras.setDefaultEditor(Object.class, null);

        tab_compras.getSelectionModel().addListSelectionListener(evt -> registroSelecionado(evt));

        scrollpanel_tabela.setViewportView(tab_compras);

        btn_adicionar = new JButton("Adicionar");
        btn_adicionar.addActionListener(evt -> btn_adicionarPressionado(evt));

        btn_excluir = new JButton("Excluir");
        btn_excluir.addActionListener(evt -> btn_excluirPressionado(evt));

        label_filtros.setText("Filtros:");
        PreencheComboFiltro();

        label_pesquisa.setText("Pesquisa:");
        txtfield_pesquisa.setToolTipText("Sua pesquisa");
        txtfield_pesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                atualizaTelaFiltros();
            }
        });

        listarCompras();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel_tela);
        panel_tela.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(label_pesquisa)
                                                                .addGap(220)
                                                                .addComponent(label_filtros))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(txtfield_pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(combo_filtros, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 220, Short.MAX_VALUE))
                                        .addComponent(scrollpanel_tabela)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btn_excluir)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btn_adicionar)))
                                .addGap(35))
        );

        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_pesquisa)
                                        .addComponent(label_filtros))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtfield_pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(combo_filtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10)
                                .addComponent(scrollpanel_tabela)
                                .addGap(10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_adicionar)
                                        .addComponent(btn_excluir))
                                .addGap(10))
        );


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel_tela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel_tela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void PreencheComboFiltro() {
        ComprasRN comprasRN = new ComprasRN();

        Field[] camposCompra = comprasRN.listarCamposCompras();

        for (Field campo : camposCompra) {
            if (!campo.getName().equals("cancelado") && !campo.getName().equals("produtos")) {
                combo_filtros.addItem(campo.getName());
            }
        }
    }

    private void atualizaTelaFiltros() {
        ComprasRN comprasRN = new ComprasRN();

        String campo = (String) combo_filtros.getSelectedItem();
        String filtro = txtfield_pesquisa.getText().trim();

        List<Compras> compras = comprasRN.filtrarCompras(campo, filtro);

        tableModel.setRowCount(0);

        for (Compras compra : compras) {
            Object[] dadosLinha = {
                compra.getId(),
                compra.getDataCompras(),
                compra.getFornecedor().getRazaoSocial(),
                compra.getFuncionario().getNome(),
                comprasRN.calcularValorTotal(compra)
            };
            tableModel.addRow(dadosLinha);
        }
    }

    void listarCompras() {
        ComprasRN comprasRN = new ComprasRN();

        tableModel.setRowCount(0);

        List<Compras> compras = comprasRN.listarCompras();
        for (Compras venda : compras) {
            Object[] dadosLinha = {
                venda.getId(),
                venda.getDataCompras(),
                venda.getFornecedor().getRazaoSocial(),
                venda.getFuncionario().getNome(),
                comprasRN.calcularValorTotal(venda)
            };
            tableModel.addRow(dadosLinha);
        }
    }

    private void btn_adicionarPressionado(ActionEvent evt) {
        CadCompras cadCompras = new CadCompras(this);
        cadCompras.setVisible(true);
    }

    private void btn_excluirPressionado(ActionEvent evt) {
        if (registroSelecionado >= 0) {
            long idCompra = (long) tableModel.getValueAt(registroSelecionado, 0);

            ComprasRN comprasRN = new ComprasRN();
            comprasRN.excluirCompras(idCompra);

            listarCompras();
        }
    }

    private void registroSelecionado(javax.swing.event.ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
            registroSelecionado = tab_compras.getSelectedRow();
        }
    }
}

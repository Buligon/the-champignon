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
import model.rn.VendasRN;
import model.vo.Vendas;

public class TelaVendas extends javax.swing.JFrame {
    private JButton btn_adicionar;
    private JButton btn_excluir;
    private javax.swing.JComboBox<String> combo_filtros;
    private javax.swing.JPanel panel_tela;
    private JScrollPane scrollpanel_tabela;
    private javax.swing.JLabel label_filtros;
    private javax.swing.JLabel label_pesquisa;
    private javax.swing.JTable tab_vendas;
    private javax.swing.JTextField txtfield_pesquisa;
    private DefaultTableModel tableModel;
    private int registroSelecionado = -1;

    public TelaVendas(JFrame callingFrame) {
        panel_tela = new javax.swing.JPanel();
        btn_adicionar = new JButton();
        scrollpanel_tabela = new JScrollPane();
        tab_vendas = new JTable();
        btn_excluir = new JButton();
        txtfield_pesquisa = new javax.swing.JTextField();
        combo_filtros = new javax.swing.JComboBox<>();
        label_pesquisa = new javax.swing.JLabel();
        label_filtros = new javax.swing.JLabel();
        tableModel = new DefaultTableModel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vendas");
        setResizable(true);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Data Venda");
        tableModel.addColumn("Funcionário");
        tableModel.addColumn("Cliente");
        tableModel.addColumn("Valor Total");
        
        tab_vendas.setModel(tableModel);
        tab_vendas.setDefaultEditor(Object.class, null);
        
        tab_vendas.getSelectionModel().addListSelectionListener(evt -> registroSelecionado(evt));
                
        scrollpanel_tabela.setViewportView(tab_vendas);
        
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
        
        listarVendas();
                
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel_tela);
        panel_tela.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(label_pesquisa)
                                                .addComponent(txtfield_pesquisa)
                                        )
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(label_filtros)
                                                .addComponent(combo_filtros)
                                        )
                                )
                                .addComponent(scrollpanel_tabela)

                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_excluir)
                                        .addComponent(btn_adicionar)
                                )
                        )
                        .addGap(35)
                )
        );

        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(label_pesquisa)
                                .addComponent(label_filtros)
                        )
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtfield_pesquisa)
                                .addComponent(combo_filtros)
                        )
                        .addGap(10)
                        .addComponent(scrollpanel_tabela)
                        .addGap(10)
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(btn_adicionar)
                                .addComponent(btn_excluir)
                        )
                        .addGap(10)
                )
        );
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(panel_tela));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(panel_tela));
        
        pack();
    }

    private void PreencheComboFiltro() {
        VendasRN vendasRN = new VendasRN();

        Field[] camposVenda = vendasRN.listarCamposVendas();

        for (Field campo : camposVenda) {
            if (!campo.getName().equals("cancelado") && !campo.getName().equals("produtos")) {
                combo_filtros.addItem(campo.getName());
            }
        }
    }

    private void atualizaTelaFiltros() {
        VendasRN vendasRN = new VendasRN();

        String campo = (String) combo_filtros.getSelectedItem();
        String filtro = txtfield_pesquisa.getText().trim();

        List<Vendas> vendas = vendasRN.filtrarVenda(campo, filtro);

        tableModel.setRowCount(0);

        for (Vendas venda : vendas) {
            Object[] dadosLinha = {
                venda.getId(),
                venda.getDataVenda(),
                venda.getFuncionario().getNome(),
                venda.getCliente().getNome(),
                vendasRN.calcularValorTotal(venda)
            };
            tableModel.addRow(dadosLinha);
        }
    }

    void listarVendas() {
        VendasRN vendasRN = new VendasRN();
        
        tableModel.setRowCount(0);

        List<Vendas> vendas = vendasRN.listarVendas();
        for (Vendas venda : vendas) {
            Object[] dadosLinha = {
                venda.getId(),
                venda.getDataVenda(),
                venda.getFuncionario().getNome(),
                venda.getCliente().getNome(),
                vendasRN.calcularValorTotal(venda)
            };
            tableModel.addRow(dadosLinha);
        }
    }

    private void btn_adicionarPressionado(ActionEvent evt) {
        CadVendas telaVendas = new CadVendas(this);
        telaVendas.setVisible(true);
    }

    private void btn_excluirPressionado(ActionEvent evt) {
        if (registroSelecionado >= 0) {
            long idVenda = (long) tableModel.getValueAt(registroSelecionado, 0);

            VendasRN vendasRN = new VendasRN();
            vendasRN.excluirVenda(idVenda);

            listarVendas();
        }
    }

    private void registroSelecionado(javax.swing.event.ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
            registroSelecionado = tab_vendas.getSelectedRow();
        }
    }
}

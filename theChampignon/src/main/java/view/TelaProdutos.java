package view;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import model.rn.ProdutosRN;
import model.vo.Produtos;

public class TelaProdutos extends javax.swing.JFrame{
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JComboBox<String> combo_filtros;
    private javax.swing.JPanel panel_tela;
    private javax.swing.JScrollPane scrollpanel_tabela;
    private javax.swing.JLabel label_filtros;
    private javax.swing.JLabel label_pesquisa;
    private javax.swing.JTable tab_produtos;
    private javax.swing.JTextField txtfield_pesquisa;
    private javax.swing.table.DefaultTableModel tableModel;
    private int registroSelecionado = -1;
    
    public TelaProdutos(JFrame callingFrame) {
        panel_tela = new javax.swing.JPanel();
        btn_adicionar = new javax.swing.JButton();
        scrollpanel_tabela = new javax.swing.JScrollPane();
        tab_produtos = new javax.swing.JTable();
        btn_excluir = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        txtfield_pesquisa = new javax.swing.JTextField();
        combo_filtros = new javax.swing.JComboBox<>();
        label_pesquisa = new javax.swing.JLabel();
        label_filtros = new javax.swing.JLabel();
        tableModel = new DefaultTableModel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produtos");
        setResizable(true);
        setLocationRelativeTo(null);
        
        tab_produtos.setModel(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Descrição");
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Custo");
        tableModel.addColumn("Valor");
        tableModel.addColumn("Espécie");
        tableModel.addColumn("Unidade");
       
        tab_produtos.setDefaultEditor(Object.class, null);
        
        tab_produtos.getSelectionModel().addListSelectionListener(evt -> registroSelecionado(evt));
        listarProdutos();
 
        scrollpanel_tabela.setViewportView(tab_produtos);
        
        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(evt -> btn_excluirPressionado(evt));

        btn_editar.setText("Editar");
        btn_editar.addActionListener(evt -> btn_editarPressionado(evt));
        
        btn_adicionar.setText("Adicionar");
        btn_adicionar.addActionListener(evt -> btn_adicionarPressionado(evt)); 
        
        label_filtros.setText("Filtros:");
        
        label_pesquisa.setText("Pesquisa:");
        txtfield_pesquisa.setToolTipText("Sua pesquisa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel_tela);
        panel_tela.setLayout(jPanel1Layout);
        
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    // -START-------------------- Filtros --------------------START-     
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
                    // --END--------------------- Filtros ---------------------END--
                         
                    .addComponent(scrollpanel_tabela) // Painel pra tabela
                        
                    // -START---------- Botões adicionar, editar e excluir ----------START- 
                    .addGroup(jPanel1Layout.createSequentialGroup() 
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // necessário para alinhá-los ao final
                        .addComponent(btn_excluir)
                        .addComponent(btn_editar)
                        .addComponent(btn_adicionar)
                    )
                    // -END------------ Botões adicionar, editar e excluir ------------END- 
                )
                .addGap(35)
            )
        );

        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup()
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10)
                // -START-------------------- Filtros --------------------START-     
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE) // necessário para ajustar altura em tela cheia 
                    .addComponent(label_pesquisa)
                    .addComponent(label_filtros)
                )
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE) // necessário para ajustar altura em tela cheia
                    .addComponent(txtfield_pesquisa)
                    .addComponent(combo_filtros)
                )
                // --END--------------------- Filtros ---------------------END--
                    
                .addGap(10)
                .addComponent(scrollpanel_tabela)
                .addGap(10)
                    
                // -START---------- Botões adicionar, editar e excluir ----------START- 
                .addGroup(jPanel1Layout.createParallelGroup()
                    .addComponent(btn_adicionar)
                    .addComponent(btn_excluir)
                    .addComponent(btn_editar)
                )
                // -END------------ Botões adicionar, editar e excluir ------------END- 
                .addGap(10)
            )
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(panel_tela));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(panel_tela));

        pack();
    }
    
    void listarProdutos() {
        ProdutosRN produtosRN = new ProdutosRN();

        DefaultTableModel tableModel = (DefaultTableModel) tab_produtos.getModel();
        tableModel.setRowCount(0);

        List<Produtos> produtos = produtosRN.listarProdutos();
        for (Produtos produto : produtos) {
            Object[] dadosLinha = {
                produto.getId(),
                produto.getDescricao(),
                produto.getQuantidade(),
                produto.getCusto(),
                produto.getValor(),
                produto.getEspecie().getDescricao(),
                produto.getUnidade().getDescricao()
            };
            tableModel.addRow(dadosLinha);
        }
    }
    
    
    private void btn_adicionarPressionado(java.awt.event.ActionEvent evt) { 
        CadProdutos telaProdutos = new CadProdutos(this);
        telaProdutos.setVisible(true);
    }
                                                                           

    private void btn_editarPressionado(java.awt.event.ActionEvent evt) {
        if (registroSelecionado >= 0) {
            long id = (long) tableModel.getValueAt(registroSelecionado, 0);

            EdtProdutos telaEditar = new EdtProdutos(this, id);
            telaEditar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro para editar!", "Nenhum registro selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }                                  

    private void btn_excluirPressionado(java.awt.event.ActionEvent evt) {
        if (registroSelecionado >= 0) {
            long idProduto = (long) tableModel.getValueAt(registroSelecionado, 0);

            ProdutosRN produtosRN = new ProdutosRN();
            produtosRN.excluirProduto(idProduto);

            listarProdutos();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro para excluir!", "Nenhum registro selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }    
    
    private void registroSelecionado(ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
                registroSelecionado = tab_produtos.getSelectedRow();
        }
    }
    
}

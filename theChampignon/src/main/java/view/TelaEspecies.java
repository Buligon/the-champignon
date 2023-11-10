package view;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import model.rn.EspeciesRN;
import model.vo.Especies;

public class TelaEspecies extends javax.swing.JFrame{
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JComboBox<String> combo_filtros;
    private javax.swing.JPanel panel_tela;
    private javax.swing.JScrollPane scrollpanel_tabela;
    private javax.swing.JLabel label_filtros;
    private javax.swing.JLabel label_pesquisa;
    private javax.swing.JTable tab_especies;
    private javax.swing.JTextField txtfield_pesquisa;
    private javax.swing.table.DefaultTableModel tableModel;
    private int registroSelecionado = -1;
    
    public TelaEspecies(JFrame callingFrame) {
        panel_tela = new javax.swing.JPanel();
        btn_adicionar = new javax.swing.JButton();
        scrollpanel_tabela = new javax.swing.JScrollPane();
        tab_especies = new javax.swing.JTable();
        btn_excluir = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        txtfield_pesquisa = new javax.swing.JTextField();
        combo_filtros = new javax.swing.JComboBox<>();
        label_pesquisa = new javax.swing.JLabel();
        label_filtros = new javax.swing.JLabel();
        tableModel = new DefaultTableModel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Espécies");
        setResizable(true);
        setLocationRelativeTo(null);
        
        tab_especies.setModel(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Descrição");
       
        tab_especies.getSelectionModel().addListSelectionListener(evt -> registroSelecionado(evt));
        listarEspecies();
 
        scrollpanel_tabela.setViewportView(tab_especies);
        
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
    
    void listarEspecies() {
        EspeciesRN especiesRN = new EspeciesRN();

        // Limpa a tabela antes de colocar novos dados
        tableModel.setRowCount(0);

        List<Especies> especies = especiesRN.listarEspecies();
        for (Especies especie : especies) {
            Object[] dadosLinha = {especie.getId(), especie.getDescricao()};
            tableModel.addRow(dadosLinha);
        }
    }           
    
    private void btn_adicionarPressionado(java.awt.event.ActionEvent evt) { 
        CadEspecies telaEspecies = new CadEspecies(this);
        telaEspecies.setVisible(true);
    }
                                                                              

    private void btn_editarPressionado(java.awt.event.ActionEvent evt) {
        if (registroSelecionado >= 0) {
            long id = (long) tableModel.getValueAt(registroSelecionado, 0);
            String descricao = (String) tableModel.getValueAt(registroSelecionado, 1);

            EdtEspecies telaEditar = new EdtEspecies(this, id, descricao);
            telaEditar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro para editar!", "Nenhum registro selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }                                         

    private void btn_excluirPressionado(java.awt.event.ActionEvent evt) {
        if (registroSelecionado >= 0) {
            long idEspecie = (long) tableModel.getValueAt(registroSelecionado, 0);

            EspeciesRN especiesRN = new EspeciesRN();
            especiesRN.excluirEspecie(idEspecie);

            listarEspecies();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro para excluir!", "Nenhum registro selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }      
    private void registroSelecionado(ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
                registroSelecionado = tab_especies.getSelectedRow();
        }
    }
    
}

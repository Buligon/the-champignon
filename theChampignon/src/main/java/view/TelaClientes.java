package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import model.rn.ClientesRN;
import model.rn.EnderecoRN;
import model.vo.Clientes;

public class TelaClientes extends javax.swing.JFrame{
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JComboBox<String> combo_filtros;
    private javax.swing.JPanel panel_tela;
    private javax.swing.JScrollPane scrollpanel_tabela;
    private javax.swing.JLabel label_filtros;
    private javax.swing.JLabel label_pesquisa;
    private javax.swing.JTable tab_clientes;
    private javax.swing.JTextField txtfield_pesquisa;
    private javax.swing.table.DefaultTableModel tableModel;
    
    private int registroSelecionado = -1;
    
    public TelaClientes(JFrame callingFrame) {
        panel_tela = new javax.swing.JPanel();
        btn_adicionar = new javax.swing.JButton();
        scrollpanel_tabela = new javax.swing.JScrollPane();
        tab_clientes= new javax.swing.JTable();
        btn_excluir = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        txtfield_pesquisa = new javax.swing.JTextField();
        combo_filtros = new javax.swing.JComboBox<>();
        combo_filtros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizaTelaFiltros();
            }
        });
        
        label_pesquisa = new javax.swing.JLabel();
        label_filtros = new javax.swing.JLabel();
        tableModel = new DefaultTableModel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setResizable(true);
        setLocationRelativeTo(null);
        
        tab_clientes.setModel(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Email");
        tableModel.addColumn("Telefone");
        tableModel.addColumn("Cidade");
        tableModel.addColumn("Rua");
        tableModel.addColumn("Numero");
       
        tab_clientes.getSelectionModel().addListSelectionListener(evt -> registroSelecionado(evt));
        listarClientes();
 
        scrollpanel_tabela.setViewportView(tab_clientes);
        
        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(evt -> btn_excluirPressionado(evt));

        btn_editar.setText("Editar");
        btn_editar.addActionListener(evt -> btn_editarPressionado(evt));
        
        btn_adicionar.setText("Adicionar");
        btn_adicionar.addActionListener(evt -> btn_adicionarPressionado(evt)); 
        
        label_filtros.setText("Filtros:");
        ComboBoxFiltroClientes();
        
        label_pesquisa.setText("Pesquisa:");
        txtfield_pesquisa.addKeyListener(new KeyAdapter() {
        @Override
            public void keyReleased(KeyEvent e) {
                atualizaTelaFiltros();
            }   
        });

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
    
        
    private void ComboBoxFiltroClientes(){
        ClientesRN clientesRN = new ClientesRN();
        EnderecoRN enderecosRN = new EnderecoRN();
        
        Field[] camposEndereco = enderecosRN.listarCamposEndereco();
        Field[] camposClientes = clientesRN.listarCamposClientes();
        
        for (Field campo : camposClientes) {
            if(campo.getName() != "cancelado" && campo.getName() != "endereco"){
                combo_filtros.addItem(campo.getName());
            }
        }
        
        for (Field campo : camposEndereco) {
            if(campo.getName() != "numero" && campo.getName() != "idEndereco"){
                combo_filtros.addItem(campo.getName());
            }
        }
    }    
    
    void listarClientes() {
        ClientesRN clientesRN = new ClientesRN();
        
        tableModel.setRowCount(0);

        List<Clientes> clientes = clientesRN.listarClientes();
        for (Clientes cliente : clientes) {
            Object[] dadosLinha = {
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco().getCidade(),
                cliente.getEndereco().getRua(),
                cliente.getEndereco().getNumero()
                
            };
            tableModel.addRow(dadosLinha);
        }
    }      

    private void atualizaTelaFiltros(){
       ClientesRN clientesRN = new ClientesRN();
       
       String campo = (String) combo_filtros.getSelectedItem();
       String filtro = txtfield_pesquisa.getText().trim();
       
       List<Clientes> clientes = clientesRN.filtrarCliente(campo, filtro);
       
       tableModel.setRowCount(0);
       
       for (Clientes cliente : clientes) {
            Object[] dadosLinha = {
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco().getCidade(),
                cliente.getEndereco().getRua(),
                cliente.getEndereco().getNumero()
                
            };
            tableModel.addRow(dadosLinha);
        }
    }
    
    private void btn_adicionarPressionado(java.awt.event.ActionEvent evt) { 
        CadClientes telaClientes = new CadClientes(this);
        telaClientes.setVisible(true);
    }
                                                                              
    private void btn_editarPressionado(java.awt.event.ActionEvent evt) {
        if (registroSelecionado >= 0) {
            long id = (long) tableModel.getValueAt(registroSelecionado, 0);
            
            EdtClientes telaEditar = new EdtClientes(this, id);
            telaEditar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro para editar!", "Nenhum registro selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }                                         

    private void btn_excluirPressionado(java.awt.event.ActionEvent evt) {
        if (registroSelecionado >= 0) {
            long idPessoa = (long) tableModel.getValueAt(registroSelecionado, 0);

            ClientesRN clientesRN = new ClientesRN();
            clientesRN.excluirCliente(idPessoa);

            listarClientes();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro para excluir!", "Nenhum registro selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }   
    
    private void registroSelecionado(ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
                registroSelecionado = tab_clientes.getSelectedRow();
        }
    }
}

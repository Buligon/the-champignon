package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import model.rn.EnderecoRN;
import model.rn.FuncionariosRN;
import model.vo.Funcionarios;

public class TelaFuncionarios extends javax.swing.JFrame{
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JComboBox<String> combo_filtros;
    private javax.swing.JPanel panel_tela;
    private javax.swing.JScrollPane scrollpanel_tabela;
    private javax.swing.JLabel label_filtros;
    private javax.swing.JLabel label_pesquisa;
    private javax.swing.JTable tab_funcionarios;
    private javax.swing.JTextField txtfield_pesquisa;
    private javax.swing.table.DefaultTableModel tableModel;
    
    private int registroSelecionado = -1;
    
    public TelaFuncionarios(JFrame callingFrame) {
        panel_tela = new javax.swing.JPanel();
        btn_adicionar = new javax.swing.JButton();
        scrollpanel_tabela = new javax.swing.JScrollPane();
        tab_funcionarios= new javax.swing.JTable();
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
        setTitle("Funcionários");
        setResizable(true);
        setLocationRelativeTo(null);
        
        tab_funcionarios.setModel(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Email");
        tableModel.addColumn("Telefone");
        tableModel.addColumn("Salário");
        tableModel.addColumn("Admissão");
        tableModel.addColumn("Demissão");
        tableModel.addColumn("Cidade");
        tableModel.addColumn("Rua");
        tableModel.addColumn("Numero");
       
        tab_funcionarios.getSelectionModel().addListSelectionListener(evt -> registroSelecionado(evt));
        listarFuncionarios();
 
        scrollpanel_tabela.setViewportView(tab_funcionarios);
        
        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(evt -> btn_excluirPressionado(evt));

        btn_editar.setText("Editar");
        btn_editar.addActionListener(evt -> btn_editarPressionado(evt));
        
        btn_adicionar.setText("Adicionar");
        btn_adicionar.addActionListener(evt -> btn_adicionarPressionado(evt)); 
        
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
    
    private void PreencheComboFiltro(){
        FuncionariosRN funcionariosRN = new FuncionariosRN();
        EnderecoRN enderecosRN = new EnderecoRN();
        
        Field[] camposEndereco = enderecosRN.listarCamposEndereco();
        Field[] camposFuncionarios = funcionariosRN.listarCamposFuncionarios();
        
        for (Field campo : camposFuncionarios) {
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
    
    void listarFuncionarios() {
        FuncionariosRN funcionariosRN = new FuncionariosRN();
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        tableModel.setRowCount(0);

        List<Funcionarios> funcionarios = funcionariosRN.listarFuncionario();
        for (Funcionarios funcionario : funcionarios) {
            Object[] dadosLinha = {
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getSalario(),
                formato.format(funcionario.getAdmissao()),
                funcionario.getDemissao() == null ? funcionario.getDemissao() : formato.format(funcionario.getDemissao()),
                funcionario.getEndereco().getCidade(),
                funcionario.getEndereco().getRua(),
                funcionario.getEndereco().getNumero()
                
            };
            tableModel.addRow(dadosLinha);
        }
    }
    
     private void atualizaTelaFiltros(){
       FuncionariosRN funcionariosRN = new FuncionariosRN();
       
       String campo = (String) combo_filtros.getSelectedItem();
       String filtro = txtfield_pesquisa.getText().trim();
       
       List<Funcionarios> funcionarios = funcionariosRN.filtrarFuncionario(campo, filtro);
       
       tableModel.setRowCount(0);
       
       Calendar cal = Calendar.getInstance();
       SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
       
       
       
       for (Funcionarios funcionario : funcionarios) {
            Object[] dadosLinha = {
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getSalario(),
                formato.format(funcionario.getAdmissao()),
                funcionario.getDemissao() == null ? funcionario.getDemissao() : formato.format(funcionario.getDemissao()),              
                funcionario.getEndereco().getCidade(),
                funcionario.getEndereco().getRua(),
                funcionario.getEndereco().getNumero()
                
            };
            tableModel.addRow(dadosLinha);
        }
    }

    private void btn_adicionarPressionado(java.awt.event.ActionEvent evt) { 
        CadFuncionarios telaFuncionarios = new CadFuncionarios(this);
        telaFuncionarios.setVisible(true);
    }
                                                                              

    private void btn_editarPressionado(java.awt.event.ActionEvent evt) {
        if (registroSelecionado >= 0) {
            long id = (long) tableModel.getValueAt(registroSelecionado, 0);
            
            EdtFuncionarios telaEditar = new EdtFuncionarios(this, id);
            telaEditar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro para editar!", "Nenhum registro selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }                                         

    private void btn_excluirPressionado(java.awt.event.ActionEvent evt) {
        if (registroSelecionado >= 0) {
            long idPessoa = (long) tableModel.getValueAt(registroSelecionado, 0);

            FuncionariosRN funcionariosRN = new FuncionariosRN();
            funcionariosRN.excluirFuncionario(idPessoa);
            
            listarFuncionarios();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro para excluir!", "Nenhum registro selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }      
    private void registroSelecionado(ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
                registroSelecionado = tab_funcionarios.getSelectedRow();
        }
    }
    
}

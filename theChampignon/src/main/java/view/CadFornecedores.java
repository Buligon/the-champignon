package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import model.rn.FornecedoresRN;
import model.vo.Enderecos;

public class CadFornecedores extends JFrame {
    private javax.swing.JLabel label_nome;
    private javax.swing.JTextField txtfield_nome;
    private javax.swing.JLabel label_cnpj;
    private javax.swing.JTextField txtfield_cnpj;
    private javax.swing.JLabel label_email;
    private javax.swing.JTextField txtfield_email;
    private javax.swing.JLabel label_telefone;
    private javax.swing.JTextField txtfield_telefone; 
    private javax.swing.JLabel label_endereco;
    private javax.swing.JLabel label_txtEndereco;
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_endereco;

    private TelaFornecedores parent;
    private Enderecos endereco;

    public CadFornecedores(TelaFornecedores parent) {
        this.parent = parent;
        
        label_nome = new javax.swing.JLabel();
        txtfield_nome = new javax.swing.JTextField();
        label_cnpj = new javax.swing.JLabel();
        txtfield_cnpj = new javax.swing.JTextField();
        label_email = new javax.swing.JLabel();
        txtfield_email = new javax.swing.JTextField();
        label_telefone = new javax.swing.JLabel();
        txtfield_telefone = new javax.swing.JTextField();  
        label_endereco = new javax.swing.JLabel();
        label_txtEndereco = new javax.swing.JLabel(); 
        btn_adicionar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_endereco = new javax.swing.JButton();

        setTitle("Cadastro de Fornecedor");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        label_nome.setText("Nome:");
        txtfield_nome.setColumns(20);

        label_cnpj.setText("CNPJ:");
        txtfield_cnpj = new JFormattedTextField(createCNPJFormatter());
        txtfield_cnpj.setColumns(10);
        
        label_email.setText("Email:");
        txtfield_email.setColumns(10);
        
        label_telefone.setText("Telefone:");
        txtfield_telefone = new JFormattedTextField(createTelefoneFormatter());
        txtfield_telefone.setColumns(10);
        
        label_endereco.setText("Endereco: ");
        label_txtEndereco.setText("Cidade, Rua, Número");
       
        btn_adicionar.setText("Adicionar");
        btn_cancelar.setText("Cancelar");
        btn_endereco.setText("Adicionar Endereço");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label_nome)
                        .addComponent(txtfield_nome)
                        .addGap(10)
                        .addComponent(label_cnpj)
                        .addComponent(txtfield_cnpj)                           
                        
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(label_email)
                                        .addComponent(txtfield_email)
                                        .addComponent(label_telefone)
                                        .addComponent(txtfield_telefone)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(label_endereco)
                                        .addComponent(label_txtEndereco)
                                        .addComponent(btn_endereco)
                                )
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
                    .addComponent(label_nome)
                    .addComponent(txtfield_nome)
                    .addGap(10)
                    .addComponent(label_cnpj)
                    .addComponent(txtfield_cnpj)
                    
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(label_email)
                                    .addComponent(txtfield_email)
                                    .addComponent(label_telefone)
                                    .addComponent(txtfield_telefone)
                            )
                            .addGroup(layout.createSequentialGroup()
                                      .addComponent(label_endereco)
                                      .addComponent(label_txtEndereco)
                                      .addComponent(btn_endereco)
                            )
                    )
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(btn_cancelar)
                            .addComponent(btn_adicionar)
                    )
                    .addContainerGap(20, Short.MAX_VALUE)
            );

            btn_endereco.addActionListener((ActionEvent e) -> {
                CadEndereco tela = new CadEndereco(null, null, this);
                tela.setVisible(true);

                tela.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        endereco = tela.getEndereco();
                    }   
                });

            });

            btn_cancelar.addActionListener((ActionEvent e) -> {
                dispose();
            });

            btn_adicionar.addActionListener((ActionEvent e) -> {

                String nome = txtfield_nome.getText().trim();
                String cnpjText = txtfield_cnpj.getText().trim();
                String emailText = txtfield_email.getText().trim();
                String telText = txtfield_telefone.getText().trim();
              
                if (!nome.isEmpty()) {

                    String cnpj = cnpjText;
                    String email = emailText;
                    String telefone = telText;

                    FornecedoresRN FornecedoresRN = new FornecedoresRN();
                    FornecedoresRN.adicionarFornecedor(nome, cnpj, email, telefone, endereco);
                    parent.listarFornecedores();

                    dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, insira um Nome!", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
            });


            pack();
        }

    private MaskFormatter createCNPJFormatter() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("##.###.###/####-##");
            formatter.setPlaceholderCharacter(' '); 
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    private MaskFormatter createTelefoneFormatter() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("(##) #####-####");
            formatter.setPlaceholderCharacter(' ');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }
}

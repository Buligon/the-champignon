package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import model.rn.ClientesRN;
import model.vo.Enderecos;
import model.vo.PessoaFisica;

public class CadClientes extends JFrame {
    private javax.swing.JLabel label_nome;
    private javax.swing.JTextField txtfield_nome;
    private javax.swing.JLabel label_cpf;
    private javax.swing.JTextField txtfield_cpf;
    private javax.swing.JLabel label_email;
    private javax.swing.JTextField txtfield_email;
    private javax.swing.JLabel label_telefone;
    private javax.swing.JTextField txtfield_telefone; 
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_cancelar;
    private TelaClientes parent;

    public CadClientes(TelaClientes parent) {
        this.parent = parent;
        
        label_nome = new javax.swing.JLabel();
        txtfield_nome = new javax.swing.JTextField();
        label_cpf = new javax.swing.JLabel();
        txtfield_cpf = new javax.swing.JTextField();
        label_email = new javax.swing.JLabel();
        txtfield_email = new javax.swing.JTextField();
        label_telefone = new javax.swing.JLabel();
        txtfield_telefone = new javax.swing.JTextField();        
       
        btn_adicionar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setTitle("Cadastro de Cliente");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        label_nome.setText("Nome:");
        txtfield_nome.setColumns(20);

        label_cpf.setText("CPF:");
        txtfield_cpf.setColumns(5);

        label_email.setText("Email:");
        txtfield_email.setColumns(5);
        
        label_telefone.setText("Telefone:");
        txtfield_telefone.setColumns(5);

        btn_adicionar.setText("Adicionar");
        btn_cancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label_nome)
                        .addComponent(txtfield_nome)
                        
                        .addComponent(label_cpf)
                        .addComponent(txtfield_cpf)
                        
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(label_email)
                                        .addComponent(txtfield_email)
                                        .addComponent(label_telefone)
                                        .addComponent(txtfield_telefone)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
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
                
                .addComponent(label_cpf)
                .addComponent(txtfield_cpf)
              
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label_email)
                                .addComponent(txtfield_email)
                                .addComponent(label_telefone)
                                .addComponent(txtfield_telefone)
                        )
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(btn_cancelar)
                        .addComponent(btn_adicionar)
                )
                .addContainerGap(20, Short.MAX_VALUE)
        );

        btn_cancelar.addActionListener((ActionEvent e) -> {
            dispose();
        });

        btn_adicionar.addActionListener((ActionEvent e) -> {
            String nome = txtfield_nome.getText().trim();
            String cpfText = txtfield_cpf.getText().trim();
            String emailText = txtfield_email.getText().trim();
            String telText = txtfield_telefone.getText().trim();
           
            
            if (!nome.isEmpty()) {
                try {
                    
                    Enderecos endereco = null;
                    String cpf = cpfText;
                    String email = emailText;
                    String telefone = telText;

                    ClientesRN ClientesRN = new ClientesRN();
                    ClientesRN.adicionarCliente(nome, cpf, email, telefone, endereco);

                    parent.listarClientes();

                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos para Custo e Valor", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, insira uma descrição!", "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        });


        pack();
    }   
}
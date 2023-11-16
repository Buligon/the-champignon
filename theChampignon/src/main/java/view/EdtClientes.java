package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import model.rn.EspeciesRN;
import model.rn.ClientesRN;
import model.rn.UnidadesRN;
import model.vo.Clientes;
import model.vo.Enderecos;

public class EdtClientes extends JFrame {
    private javax.swing.JLabel label_nome;
    private javax.swing.JTextField txtfield_nome;
    private javax.swing.JLabel label_cpf;
    private javax.swing.JTextField txtfield_cpf;
    private javax.swing.JLabel label_email;
    private javax.swing.JTextField txtfield_email;
    private javax.swing.JLabel label_telefone;
    private javax.swing.JTextField txtfield_telefone; 
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_cancelar;
    private TelaClientes parent;
    private long idPessoa;

    
    public EdtClientes(TelaClientes parent, long idPessoa) {
        this.parent = parent;
        this.idPessoa = idPessoa; 
        
        label_nome = new javax.swing.JLabel();
        txtfield_nome = new javax.swing.JTextField();
        label_cpf = new javax.swing.JLabel();
        txtfield_cpf = new javax.swing.JTextField();
        label_email = new javax.swing.JLabel();
        txtfield_email = new javax.swing.JTextField();
        label_telefone = new javax.swing.JLabel();
        txtfield_telefone = new javax.swing.JTextField();   
        
        btn_editar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        ClientesRN clientesRN = new ClientesRN();
        Clientes clienteEdicao = clientesRN.obterClientePorId(idPessoa);
        
        setTitle("Edição de Clientes");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        label_nome.setText("Nome:");
        txtfield_nome.setColumns(20);
        txtfield_nome.setToolTipText("Ex.: Champignon");

        label_cpf.setText("CPF:");
        txtfield_cpf.setColumns(5);

        label_email.setText("Email:");
        txtfield_email.setColumns(5);
        
        label_telefone.setText("Telefone:");
        txtfield_telefone.setColumns(5);

        btn_editar.setText("Editar");
        btn_cancelar.setText("Cancelar");
        
        
        if (clienteEdicao != null) {
            txtfield_nome.setText(clienteEdicao.getNome());
            txtfield_cpf.setText(String.valueOf(clienteEdicao.getCpf()));
            txtfield_email.setText(String.valueOf(clienteEdicao.getEmail()));
            txtfield_telefone.setText(String.valueOf(clienteEdicao.getTelefone()));

        }

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
                                        .addComponent(label_telefone)
                                        .addComponent(txtfield_telefone)
                                        .addComponent(label_email)
                                        .addComponent(txtfield_email)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cancelar)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_editar)
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
                        .addComponent(btn_editar)
                )
                .addContainerGap(20, Short.MAX_VALUE)
        );

        btn_cancelar.addActionListener((ActionEvent e) -> {
            dispose();
        });

        btn_editar.addActionListener((ActionEvent e) -> {
            if (clienteEdicao != null) {
                String nome = txtfield_nome.getText().trim();
                String cpfText = txtfield_cpf.getText().trim();
                String emailText = txtfield_email.getText().trim();
                String telText = txtfield_telefone.getText().trim();

                if (!nome.isEmpty()) {
                   
                    Enderecos endereco = null;
                    String cpf = cpfText;
                    String email = emailText;
                    String telefone = telText;

                    clienteEdicao.setNome(nome);
                    clienteEdicao.setCpf(cpf);
                    clienteEdicao.setEmail(email );

                    clientesRN.editarCliente(clienteEdicao);
                    parent.listarClientes();

                    dispose();
                     
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, insira um nome !", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
            }
        });       
        
        pack();
    }  
}


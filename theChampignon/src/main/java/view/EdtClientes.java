package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;
import javax.swing.text.MaskFormatter;
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
    private javax.swing.JLabel label_endereco;
    private javax.swing.JLabel label_txtEndereco;
    
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_endereco;

    private TelaClientes parent;
    private Enderecos endereco;
    private final long idPessoa;

    
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
        label_endereco = new javax.swing.JLabel();
        label_txtEndereco = new javax.swing.JLabel(); 
       
        btn_editar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_endereco = new javax.swing.JButton();

        ClientesRN clientesRN = new ClientesRN();
        Clientes clienteEdicao = clientesRN.obterClientePorId(idPessoa);
        
        setTitle("Edição de Cliente");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        label_nome.setText("Nome:");
        txtfield_nome.setColumns(20);

        label_cpf.setText("CPF:");
        txtfield_cpf = new JFormattedTextField(createCPFFormatter());
        txtfield_cpf.setColumns(10);
        
        label_email.setText("Email:");
        txtfield_email.setColumns(10);
        
        label_telefone.setText("Telefone:");
        txtfield_telefone = new JFormattedTextField(createTelefoneFormatter());
        txtfield_telefone.setColumns(10);
        
        label_endereco.setText("Endereco: ");
        label_txtEndereco.setText("Cidade, Rua, Número");

        btn_editar.setText("Editar");
        btn_cancelar.setText("Cancelar");
        btn_endereco.setText("Editar Endereço");
        
        
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
                                .addGroup(layout.createSequentialGroup()
                                          .addComponent(label_endereco)
                                          .addComponent(label_txtEndereco)
                                          .addComponent(btn_endereco)
                                )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(btn_cancelar)
                                .addComponent(btn_editar)
                        )
                        .addContainerGap(20, Short.MAX_VALUE)
        );
                
        btn_endereco.addActionListener((ActionEvent e) -> {
            EdtEnderecos tela = new EdtEnderecos(this, null,clienteEdicao.getEndereco().getIdEndereco());
            tela.setVisible(true);                    
        });

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
    
    private MaskFormatter createCPFFormatter() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("###.###.###-##");
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


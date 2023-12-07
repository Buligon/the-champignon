package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.text.MaskFormatter;
import model.rn.ClientesRN;
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
    private javax.swing.JLabel label_nascimento;
    private javax.swing.JTextField txtfield_nascimento;
    
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
        label_nascimento = new javax.swing.JLabel();
        txtfield_nascimento = new javax.swing.JTextField(); 
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
        
        label_nascimento.setText("Nascimento:");
        txtfield_nascimento = new JFormattedTextField(createAdmissaoFormatter());
        txtfield_nascimento.setColumns(10);

        btn_editar.setText("Editar");
        btn_cancelar.setText("Cancelar");
        btn_endereco.setText("Editar Endereço");
        
        
        if (clienteEdicao != null) {
            txtfield_nome.setText(clienteEdicao.getNome());
            txtfield_cpf.setText(String.valueOf(clienteEdicao.getCpf()));
            txtfield_email.setText(String.valueOf(clienteEdicao.getEmail()));
            txtfield_telefone.setText(String.valueOf(clienteEdicao.getTelefone()));
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            txtfield_nascimento.setText(formato.format(clienteEdicao.getNascimento()));
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addContainerGap(35, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(label_nome)
                            .addComponent(txtfield_nome)
                            .addGap(10)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                    .addComponent(label_cpf)
                                    .addComponent(txtfield_cpf)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                    .addComponent(label_nascimento)
                                    .addComponent(txtfield_nascimento)
                                )
                            )
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
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(label_cpf)
                            .addComponent(label_nascimento)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(txtfield_cpf)
                            .addComponent(txtfield_nascimento)
                        )
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
            EdtEnderecos tela = new EdtEnderecos(this, null, null,clienteEdicao.getEndereco().getIdEndereco());
            tela.setVisible(true);                    
        });

        btn_cancelar.addActionListener((ActionEvent e) -> {
            dispose();
        });

        btn_editar.addActionListener((ActionEvent e) -> {
            if (clienteEdicao != null) {
                
                Calendar calendar = Calendar.getInstance();
                
                String nome = txtfield_nome.getText().trim();
                String cpf = txtfield_cpf.getText().trim();
                String email = txtfield_email.getText().trim();
                String telefone = txtfield_telefone.getText().trim();
                
                //NASCIMENTO
                String[] data = txtfield_nascimento.getText().split("/");

                Integer dia = Integer.parseInt(data[0]);
                Integer mes = Integer.parseInt(data[1]);
                Integer ano = Integer.parseInt(data[2]);

                calendar.set(Calendar.DAY_OF_MONTH, dia);
                calendar.set(Calendar.MONTH, mes-1);
                calendar.set(Calendar.YEAR, ano);

                Date nascimento = calendar.getTime();
                
                if (!nome.isEmpty()) {
                    
                    clienteEdicao.setNome(nome);
                    clienteEdicao.setCpf(cpf);
                    clienteEdicao.setEmail(email);
                    clienteEdicao.setTelefone(telefone);
                    clienteEdicao.setNascimento(nascimento);

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
    
    private MaskFormatter createAdmissaoFormatter() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("##/##/####");
            formatter.setPlaceholderCharacter(' ');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }
}


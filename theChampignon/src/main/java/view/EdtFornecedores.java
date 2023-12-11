package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.text.MaskFormatter;
import model.rn.FornecedoresRN;
import model.vo.Fornecedores;
import model.vo.Enderecos;

public class EdtFornecedores extends JFrame {
    private javax.swing.JLabel label_razaoSocial;
    private javax.swing.JTextField txtfield_razaoSocial;
    private javax.swing.JLabel label_cnpj;
    private javax.swing.JTextField txtfield_cnpj;
    private javax.swing.JLabel label_email;
    private javax.swing.JTextField txtfield_email;
    private javax.swing.JLabel label_telefone;
    private javax.swing.JTextField txtfield_telefone; 
    private javax.swing.JLabel label_endereco;
    private javax.swing.JLabel label_txtEndereco;
    
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_endereco;

    private TelaFornecedores parent;
    private Enderecos endereco;
    private final long idPessoa;

    
    public EdtFornecedores(TelaFornecedores parent, long idPessoa) {
        this.parent = parent;
        this.idPessoa = idPessoa; 
        
        label_razaoSocial = new javax.swing.JLabel();
        txtfield_razaoSocial = new javax.swing.JTextField();
        label_cnpj = new javax.swing.JLabel();
        txtfield_cnpj = new javax.swing.JTextField();
        label_email = new javax.swing.JLabel();
        txtfield_email = new javax.swing.JTextField();
        label_telefone = new javax.swing.JLabel();
        txtfield_telefone = new javax.swing.JTextField();  
        label_endereco = new javax.swing.JLabel();
        label_txtEndereco = new javax.swing.JLabel(); 
        btn_editar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_endereco = new javax.swing.JButton();

        FornecedoresRN fornecedorsRN = new FornecedoresRN();
        Fornecedores fornecedorEdicao = fornecedorsRN.obterFornecedorPorId(idPessoa);
        
        setTitle("Edição de Fornecedor");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        label_razaoSocial.setText("Razao Social:");
        txtfield_razaoSocial.setColumns(20);

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

        btn_editar.setText("Editar");
        btn_cancelar.setText("Cancelar");
        btn_endereco.setText("Editar Endereço");
        
        
        if (fornecedorEdicao != null) {
            txtfield_razaoSocial.setText(fornecedorEdicao.getRazaoSocial());
            txtfield_cnpj.setText(String.valueOf(fornecedorEdicao.getCnpj()));
            txtfield_email.setText(String.valueOf(fornecedorEdicao.getEmail()));
            txtfield_telefone.setText(String.valueOf(fornecedorEdicao.getTelefone()));

        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addContainerGap(35, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(label_razaoSocial)
                            .addComponent(txtfield_razaoSocial)
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
                                    .addComponent(btn_editar)
                            )
                    )
                    .addContainerGap(35, Short.MAX_VALUE)
                );

                layout.setVerticalGroup(layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addComponent(label_razaoSocial)
                        .addComponent(txtfield_razaoSocial)
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
                                .addComponent(btn_editar)
                        )
                        .addContainerGap(20, Short.MAX_VALUE)
        );
                
        btn_endereco.addActionListener((ActionEvent e) -> {
            EdtEnderecos tela = new EdtEnderecos(null, null, this, fornecedorEdicao.getEndereco().getIdEndereco());
            tela.setVisible(true);                    
        });

        btn_cancelar.addActionListener((ActionEvent e) -> {
            dispose();
        });

        btn_editar.addActionListener((ActionEvent e) -> {
            if (fornecedorEdicao != null) {
                
                Calendar calendar = Calendar.getInstance();
                
                String razaoSocial = txtfield_razaoSocial.getText().trim();
                String cnpj = txtfield_cnpj.getText().trim();
                String email = txtfield_email.getText().trim();
                String telefone = txtfield_telefone.getText().trim();                                
                
                if (!razaoSocial.isEmpty()) {
                    
                    fornecedorEdicao.setRazaoSocial(razaoSocial);
                    fornecedorEdicao.setCnpj(cnpj.replaceAll("[^0-9]", ""));
                    fornecedorEdicao.setEmail(email);
                    fornecedorEdicao.setTelefone(telefone.replaceAll("[^0-9]", ""));

                    fornecedorsRN.editarFornecedor(fornecedorEdicao);
                    parent.listarFornecedores();

                    dispose();
                     
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, insira um razaoSocial !", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
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
    
    private MaskFormatter createDateFormatter() {
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


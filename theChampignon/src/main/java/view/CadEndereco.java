package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.List;
import javax.swing.text.MaskFormatter;
import model.rn.EnderecoRN;
import model.vo.Enderecos;

public class CadEndereco extends JFrame {

    private javax.swing.JLabel label_pais;
    private javax.swing.JTextField txtfield_pais;
    private javax.swing.JLabel label_estado;
    private javax.swing.JTextField txtfield_estado;
    private javax.swing.JLabel label_cidade;
    private javax.swing.JTextField txtfield_cidade;
    private javax.swing.JLabel label_rua;
    private javax.swing.JTextField txtfield_rua; 
    private javax.swing.JLabel label_numero;
    private javax.swing.JTextField txtfield_numero;
    private javax.swing.JLabel label_logradouro;
    private javax.swing.JTextField txtfield_logradouro;
    private javax.swing.JLabel label_bairro;
    private javax.swing.JTextField txtfield_bairro;
    private javax.swing.JLabel label_cep;
    private javax.swing.JTextField txtfield_cep;
    
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_cancelar;

    private CadClientes parentCliente;
    private CadFuncionarios parentFuncionario;
    private CadFornecedores parentFornecedores;
    
    private Enderecos endereco;
    
    public CadEndereco(CadClientes parentCliente, CadFuncionarios parentFuncionario, CadFornecedores parentFornecedores) {
        this.parentCliente = parentCliente;
        this.parentFuncionario = parentFuncionario;
        this.parentFornecedores = parentFornecedores;
        
        label_pais = new javax.swing.JLabel();
        txtfield_pais = new javax.swing.JTextField();
        label_estado = new javax.swing.JLabel();
        txtfield_estado = new javax.swing.JTextField();
        label_cidade = new javax.swing.JLabel();
        txtfield_cidade = new javax.swing.JTextField();
        label_bairro = new javax.swing.JLabel();
        txtfield_bairro = new javax.swing.JTextField();
        label_rua = new javax.swing.JLabel();
        txtfield_rua = new javax.swing.JTextField();  
        label_numero = new javax.swing.JLabel();
        txtfield_numero = new javax.swing.JTextField(); 
        label_logradouro = new javax.swing.JLabel();
        txtfield_logradouro = new javax.swing.JTextField(); 
        label_cep = new javax.swing.JLabel();
        txtfield_cep = new javax.swing.JTextField(); 
       
        btn_adicionar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        
        setTitle("Cadastro de Endereco");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        label_pais.setText("País:");
        txtfield_pais.setColumns(10);

        label_estado.setText("Estado/UF:");
        txtfield_estado.setColumns(10);
        
        label_cidade.setText("Cidade:");
        txtfield_cidade.setColumns(10);
        
        label_bairro.setText("Bairro: ");
        txtfield_bairro.setColumns(10);
        
        label_rua.setText("Rua:");
        txtfield_rua.setColumns(10);
        
        label_numero.setText("Número: ");
        txtfield_numero.setColumns(10);
        
        label_logradouro.setText("Logradouro: ");
        txtfield_logradouro.setColumns(10);
        
        label_cep.setText("CEP: ");
        txtfield_cep = new JFormattedTextField(createCEPFormatter());
        txtfield_cep.setColumns(10);

        btn_adicionar.setText("Adicionar");
        btn_cancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label_pais)
                        .addComponent(txtfield_pais)
                        .addComponent(label_estado)   
                        .addComponent(txtfield_estado)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(label_cidade)
                                        .addComponent(txtfield_cidade)
                                        .addComponent(label_bairro)
                                        .addComponent(txtfield_bairro)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(label_numero)
                                        .addComponent(txtfield_numero)
                                        .addComponent(label_rua)
                                        .addComponent(txtfield_rua)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                    .addComponent(label_logradouro)
                                    .addComponent(txtfield_logradouro)
                                    .addComponent(label_cep)
                                    .addComponent(txtfield_cep)
                                    
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
                .addComponent(label_pais)
                .addComponent(txtfield_pais)
                .addComponent(label_estado)   
                .addComponent(txtfield_estado)
                .addGroup(layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label_cidade)
                                .addComponent(txtfield_cidade)
                                .addComponent(label_bairro)
                                .addComponent(txtfield_bairro)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label_numero)
                                .addComponent(txtfield_numero)
                                .addComponent(label_rua)
                                .addComponent(txtfield_rua)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label_logradouro)
                                .addComponent(txtfield_logradouro)
                                .addComponent(label_cep)
                                .addComponent(txtfield_cep)
                        )
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(btn_cancelar)
                        .addComponent(btn_adicionar)
                )
                .addContainerGap(35, Short.MAX_VALUE)
            )
        );
        
        
        btn_cancelar.addActionListener((ActionEvent e) -> {
            dispose();
        });
        
        btn_adicionar.addActionListener((ActionEvent e) -> {
            String pais = txtfield_pais.getText().trim();
            String estado  = txtfield_estado.getText().trim();
            String cidade  = txtfield_cidade.getText().trim();
            String bairro  = txtfield_bairro.getText().trim();
            String rua  = txtfield_rua.getText().trim();
            String numeroTxt  = txtfield_numero.getText().trim();
            String logradouro  = txtfield_logradouro.getText().trim();
            String cep = txtfield_cep.getText().trim();

            try {
                
                Integer numero = Integer.parseInt(numeroTxt);
                
                EnderecoRN EnderecosRN = new EnderecoRN();
                endereco = EnderecosRN.adicionarEndereco(pais,  estado,  cidade,  rua, logradouro,  numero,  cep, bairro);

                dispose();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos para Numero", "Atenção", JOptionPane.WARNING_MESSAGE);
            }
            
        });


        pack();
       
    }
     public Enderecos getEndereco() {
        return endereco;
     }
     
     private MaskFormatter createCEPFormatter() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("#####-###");
            formatter.setPlaceholderCharacter(' '); 
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }
}

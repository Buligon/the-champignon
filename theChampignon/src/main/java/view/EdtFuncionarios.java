package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.text.MaskFormatter;
import model.rn.FuncionariosRN;
import model.vo.Funcionarios;
import model.vo.Enderecos;

public class EdtFuncionarios extends JFrame {
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
    
    private javax.swing.JLabel label_salario;
    private javax.swing.JTextField txtfield_salario;
    
    private javax.swing.JLabel label_admissao;
    private javax.swing.JTextField txtfield_admissao;
    
    private javax.swing.JLabel label_demissao;
    private javax.swing.JTextField txtfield_demissao;
    
    
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_endereco;

    private TelaFuncionarios parent;
    private Enderecos endereco;
    private final long idPessoa;

    
    public EdtFuncionarios(TelaFuncionarios parent, long idPessoa) {
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
       
        label_salario = new javax.swing.JLabel();
        txtfield_salario = new javax.swing.JTextField();
        
        label_admissao = new javax.swing.JLabel();
        txtfield_admissao = new javax.swing.JTextField();
        
        label_demissao = new javax.swing.JLabel();
        txtfield_demissao = new javax.swing.JTextField();        
       
        btn_editar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_endereco = new javax.swing.JButton();

        FuncionariosRN funcionariosRN = new FuncionariosRN();
        Funcionarios funcionarioEdicao = funcionariosRN.obterFuncionarioPorId(idPessoa);
        
        setTitle("Edição de Funcionário");
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
        
        label_salario.setText("Salario:");
        txtfield_salario.setColumns(10);
        
        label_admissao.setText("Admissão:");
        txtfield_admissao = new JFormattedTextField(createDateFormatter());
        txtfield_admissao.setColumns(10);
        
        label_demissao.setText("Demissão:");
        txtfield_demissao = new JFormattedTextField(createDateFormatter());
        txtfield_demissao.setColumns(10);

        btn_editar.setText("Editar");
        btn_cancelar.setText("Cancelar");
        btn_endereco.setText("Editar Endereço");
        
        
        if (funcionarioEdicao != null) {
            txtfield_nome.setText(funcionarioEdicao.getNome());
            txtfield_cpf.setText(String.valueOf(funcionarioEdicao.getCpf()));
            txtfield_email.setText(String.valueOf(funcionarioEdicao.getEmail()));
            txtfield_telefone.setText(String.valueOf(funcionarioEdicao.getTelefone()));
            txtfield_salario.setText(String.valueOf(funcionarioEdicao.getSalario()));
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            txtfield_admissao.setText(formato.format(funcionarioEdicao.getAdmissao()));
            
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup()
        .addContainerGap(35, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(label_nome)
            .addComponent(txtfield_nome)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(label_cpf)
                    .addComponent(txtfield_cpf)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(label_salario)
                    .addComponent(txtfield_salario)
                )
            )
            
            .addGap(10)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(label_admissao)
                    .addComponent(txtfield_admissao)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(label_demissao)
                    .addComponent(txtfield_demissao)
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
            .addComponent(label_salario)
        )
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(txtfield_cpf)
            .addComponent(txtfield_salario)
        ) 
           
        .addGap(10)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(label_admissao)
            .addComponent(label_demissao)
        )
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(txtfield_admissao)
            .addComponent(txtfield_demissao)
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
            EdtEnderecos tela = new EdtEnderecos(null,this,null,funcionarioEdicao.getEndereco().getIdEndereco());
            tela.setVisible(true);                    
        });

        btn_cancelar.addActionListener((ActionEvent e) -> {
            dispose();
        });

        btn_editar.addActionListener((ActionEvent e) -> {
            if (funcionarioEdicao != null) {
                
                Calendar calendar = Calendar.getInstance();
                
                String nome = txtfield_nome.getText().trim();
                String cpf = txtfield_cpf.getText().trim();
                String email = txtfield_email.getText().trim();
                String telefone= txtfield_telefone.getText().trim();
                String salText = txtfield_salario.getText().trim();
                //ADMISSÃO
                String[] data = txtfield_admissao.getText().split("/");

                Integer dia = Integer.parseInt(data[0]);
                Integer mes = Integer.parseInt(data[1]);
                Integer ano = Integer.parseInt(data[2]);

                calendar.set(Calendar.DAY_OF_MONTH, dia);
                calendar.set(Calendar.MONTH, mes-1);
                calendar.set(Calendar.YEAR, ano);
                
                Date admissao = calendar.getTime();
                
                //Demissao
                data = txtfield_admissao.getText().split("/");

                dia = Integer.parseInt(data[0]);
                mes = Integer.parseInt(data[1]);
                ano = Integer.parseInt(data[2]);

                calendar.set(Calendar.DAY_OF_MONTH, dia);
                calendar.set(Calendar.MONTH, mes-1);
                calendar.set(Calendar.YEAR, ano);
                
                Date demissao = calendar.getTime();
                                             
                if (!nome.isEmpty()) {
                   
                    float salario = Float.parseFloat(salText);
                    

                    funcionarioEdicao.setNome(nome);
                    funcionarioEdicao.setCpf(cpf.replaceAll("[^0-9]", ""));
                    funcionarioEdicao.setEmail(email);
                    funcionarioEdicao.setTelefone(telefone.replaceAll("[^0-9]", ""));
                    funcionarioEdicao.setSalario(salario);
                    funcionarioEdicao.setAdmissao(admissao);
                    funcionarioEdicao.setDemissao(demissao);

                    funcionariosRN.editarFuncionario(funcionarioEdicao);
                    parent.listarFuncionarios();

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


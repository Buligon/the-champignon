package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import model.rn.UnidadesRN;

public class CadUnidades extends JFrame {
    private javax.swing.JLabel label_desc;
    private javax.swing.JTextField txtfield_desc;
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_cancelar;
    private TelaUnidades parent;
   
    public CadUnidades(TelaUnidades parent) {
        this.parent = parent;
        label_desc = new javax.swing.JLabel();
        txtfield_desc = new javax.swing.JTextField();
        btn_adicionar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        
        setTitle("Cadstro de Unidade");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        label_desc.setText("Descrição");
        
        txtfield_desc.setColumns(10);
        txtfield_desc.setToolTipText("Ex.: Caixa");

        btn_adicionar.setText("Adicionar");
        btn_cancelar.setText("Cancelar");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtfield_desc)
                    .addComponent(label_desc)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_cancelar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_adicionar)
                    )
                )
                .addContainerGap(35, Short.MAX_VALUE)
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addContainerGap(20, Short.MAX_VALUE)
            .addComponent(label_desc)
            .addComponent(txtfield_desc)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btn_cancelar)
                .addComponent(btn_adicionar)
            )
            .addContainerGap(20, Short.MAX_VALUE)
        );

        btn_cancelar.addActionListener((ActionEvent e) -> {
            dispose();
        });

        btn_adicionar.addActionListener((ActionEvent e) -> {
            String descricao = txtfield_desc.getText();
            
            UnidadesRN unidadesRN = new UnidadesRN();
            unidadesRN.adicionarUnidade(descricao);
            
            parent.listarUnidades();
            
            dispose();
        });

        pack();
        setVisible(true);
    }
}

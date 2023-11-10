package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import model.rn.UnidadesRN;

public class EdtUnidades extends JFrame {
    private JLabel label_desc;
    private JTextField txtfield_desc;
    private JButton btn_salvar;
    private JButton btn_cancelar;
    private TelaUnidades parent;
    private long idUnidade;

    public EdtUnidades(TelaUnidades parent, long idUnidade, String descricaoAtual) {
        this.parent = parent;
        this.idUnidade = idUnidade;

        label_desc = new JLabel();
        txtfield_desc = new JTextField();
        btn_salvar = new JButton();
        btn_cancelar = new JButton();

        setTitle("Editar Unidade");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        label_desc.setText("Nova Descrição");
        txtfield_desc.setText(descricaoAtual);

        txtfield_desc.setColumns(10);

        btn_salvar.setText("Salvar");
        btn_cancelar.setText("Cancelar");

        GroupLayout layout = new GroupLayout(getContentPane());
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
                        .addComponent(btn_salvar)
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
                .addComponent(btn_salvar)
            )
            .addContainerGap(20, Short.MAX_VALUE)
        );

        btn_cancelar.addActionListener((ActionEvent e) -> {
            dispose();
        });

        btn_salvar.addActionListener((ActionEvent e) -> {
            String descricaoNova = txtfield_desc.getText().trim();
            
            if (!descricaoNova.isEmpty()) {
                UnidadesRN unidadesRN = new UnidadesRN();
                unidadesRN.editarUnidade(idUnidade, descricaoNova);

                parent.listarUnidades();

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, insira uma descrição!", "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        });

        pack();
    }
}

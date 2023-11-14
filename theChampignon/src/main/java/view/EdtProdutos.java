package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import model.rn.EspeciesRN;
import model.rn.ProdutosRN;
import model.rn.UnidadesRN;
import model.vo.Especies;
import model.vo.Produtos;
import model.vo.Unidades;

public class EdtProdutos extends JFrame {
    private javax.swing.JLabel label_desc;
    private javax.swing.JTextField txtfield_desc;
    private javax.swing.JLabel label_custo;
    private javax.swing.JTextField txtfield_custo;
    private javax.swing.JLabel label_valor;
    private javax.swing.JTextField txtfield_valor;
    private javax.swing.JLabel label_unidades;
    private javax.swing.JComboBox<String> combo_unidades;
    private javax.swing.JLabel label_especies;
    private javax.swing.JComboBox<String> combo_especies;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_cancelar;
    private TelaProdutos parent;
    private long idProduto;

    public EdtProdutos(TelaProdutos parent, long idProduto) {
        this.parent = parent;
        this.idProduto = idProduto;
        label_desc = new javax.swing.JLabel();
        txtfield_desc = new javax.swing.JTextField();
        label_custo = new javax.swing.JLabel();
        txtfield_custo = new javax.swing.JTextField();
        label_valor = new javax.swing.JLabel();
        txtfield_valor = new javax.swing.JTextField();
        label_unidades = new javax.swing.JLabel();
        combo_unidades = new javax.swing.JComboBox<String>();
        label_especies = new javax.swing.JLabel();
        combo_especies = new javax.swing.JComboBox<String>();
        btn_editar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        ProdutosRN produtosRN = new ProdutosRN();
        Produtos produtoEdicao = produtosRN.obterProdutoPorId(idProduto);
        
        setTitle("Edição de Produto");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        label_desc.setText("Descrição:");
        txtfield_desc.setColumns(20);
        txtfield_desc.setToolTipText("Ex.: Champignon");

        label_custo.setText("Custo:");
        txtfield_custo.setColumns(5);

        label_valor.setText("Valor:");
        txtfield_valor.setColumns(5);

        label_unidades.setText("Unidades:");
        preencherComboBoxUnidades();

        label_especies.setText("Espécies:");
        preencherComboBoxEspecies();

        btn_cancelar.setText("Cancelar");
        btn_editar.setText("Editar");
        
        
        if (produtoEdicao != null) {
            txtfield_desc.setText(produtoEdicao.getDescricao());
            txtfield_custo.setText(String.valueOf(produtoEdicao.getCusto()));
            txtfield_valor.setText(String.valueOf(produtoEdicao.getValor()));
            combo_unidades.setSelectedItem(produtoEdicao.getUnidade().getDescricao());
            combo_especies.setSelectedItem(produtoEdicao.getEspecie().getDescricao());
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label_desc)
                        .addComponent(txtfield_desc)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(label_custo)
                                        .addComponent(txtfield_custo)
                                        .addComponent(label_valor)
                                        .addComponent(txtfield_valor)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(label_unidades)
                                        .addComponent(combo_unidades)
                                        .addComponent(label_especies)
                                        .addComponent(combo_especies)
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
                .addComponent(label_desc)
                .addComponent(txtfield_desc)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label_custo)
                                .addComponent(txtfield_custo)
                                .addComponent(label_valor)
                                .addComponent(txtfield_valor)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label_unidades)
                                .addComponent(combo_unidades)
                                .addComponent(label_especies)
                                .addComponent(combo_especies)
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
            if (produtoEdicao != null) {
                String descricao = txtfield_desc.getText().trim();
                String custoText = txtfield_custo.getText().trim();
                String valorText = txtfield_valor.getText().trim();
                String unidadeSelecionada = (String) combo_unidades.getSelectedItem();
                String especieSelecionada = (String) combo_especies.getSelectedItem();

                if (!descricao.isEmpty()) {
                    try {
                        float custo = Float.parseFloat(custoText);
                        float valor = Float.parseFloat(valorText);

                        produtoEdicao.setDescricao(descricao);
                        produtoEdicao.setCusto(custo);
                        produtoEdicao.setValor(valor);

                        UnidadesRN unidadesRN = new UnidadesRN();
                        Unidades unidade = unidadesRN.obterUnidadePelaDescricao(unidadeSelecionada);
                        produtoEdicao.setUnidade(unidade);

                        EspeciesRN especiesRN = new EspeciesRN();
                        Especies especie = especiesRN.obterEspeciePelaDescricao(especieSelecionada);
                        produtoEdicao.setEspecie(especie);

                        produtosRN.editarProduto(produtoEdicao);
                        parent.listarProdutos();

                        dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos para Custo e Valor.", "Atenção", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, insira uma descrição!", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
            }
        });       
        
        pack();
    }
    
    private void preencherComboBoxUnidades() {
        UnidadesRN unidadesRN = new UnidadesRN();
        List<Unidades> unidadesList = unidadesRN.listarUnidades();

        List<String> descricaoList = unidadesList.stream().map(Unidades::getDescricao).toList();

        combo_unidades.setModel(new DefaultComboBoxModel<>(descricaoList.toArray(String[]::new)));
    }
    
    private void preencherComboBoxEspecies() {
        EspeciesRN especiesRN = new EspeciesRN();
        List<Especies> especiesList = especiesRN.listarEspecies();

        List<String> descricaoList = especiesList.stream().map(Especies::getDescricao).toList();

        combo_especies.setModel(new DefaultComboBoxModel<>(descricaoList.toArray(String[]::new)));
    }
    
}

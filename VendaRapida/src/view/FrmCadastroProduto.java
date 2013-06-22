/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ProdutoController;
import controller.impl.ProdutoControllerImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Produto;

/**
 *
 * @author Maicon
 */
public class FrmCadastroProduto extends javax.swing.JDialog {

    private ProdutoController produtoController = new ProdutoControllerImpl();

    /**
     * Creates new form FrmCadastroProduto
     */
    public FrmCadastroProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbConfirmarSenha = new javax.swing.JLabel();
        btLimpar = new javax.swing.JButton();
        btOk = new javax.swing.JButton();
        lbSenha = new javax.swing.JLabel();
        lbNome = new javax.swing.JLabel();
        edReferencia = new javax.swing.JTextField();
        lbLogin = new javax.swing.JLabel();
        lbNovoUsuario = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        edValor = new javax.swing.JTextField();
        edCodigoBarras = new javax.swing.JTextField();
        edDescricao = new javax.swing.JTextField();
        btExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbConfirmarSenha.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbConfirmarSenha.setText("Valor:");

        btLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpar.png"))); // NOI18N
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });
        btLimpar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btLimparKeyPressed(evt);
            }
        });

        btOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirma.png"))); // NOI18N
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });
        btOk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btOkKeyPressed(evt);
            }
        });

        lbSenha.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbSenha.setText("Descrição:");

        lbNome.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbNome.setText("Código de Barras:");
        lbNome.setName(""); // NOI18N

        edReferencia.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        edReferencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edReferenciaFocusLost(evt);
            }
        });

        lbLogin.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbLogin.setText("Referência:");

        lbNovoUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbNovoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/product-icon.png"))); // NOI18N
        lbNovoUsuario.setText("Cadastro de Produto");

        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search_16.png"))); // NOI18N
        btPesquisar.setToolTipText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        edValor.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        edCodigoBarras.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        edDescricao.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/remove-icon.png"))); // NOI18N
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        btExcluir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btExcluirKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbNovoUsuario)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btOk, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbSenha)
                            .addComponent(lbConfirmarSenha)
                            .addComponent(lbNome)
                            .addComponent(lbLogin))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(edValor, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(edReferencia)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(edCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNovoUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(edReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(edCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSenha)
                    .addComponent(edDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbConfirmarSenha)
                    .addComponent(edValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btOk)
                    .addComponent(btLimpar)
                    .addComponent(btExcluir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getRootPane().setDefaultButton(btOk);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btLimparActionPerformed

    private void limpar() {
        this.edReferencia.setText("");
        this.edCodigoBarras.setText("");
        this.edDescricao.setText("");
        this.edValor.setText("");
        this.edReferencia.setEnabled(true);
    }

    private void btLimparKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btLimparKeyPressed
    }//GEN-LAST:event_btLimparKeyPressed

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        Produto produto = new Produto();
        produto.setCodigoBarrra(edCodigoBarras.getText());
        produto.setDescricao(edDescricao.getText());
        produto.setReferencia(edReferencia.getText());
        produto.setValor(edValor.getText().isEmpty() ? 0d : Double.parseDouble(edValor.getText()));
        try {
            if (produtoController.buscar(produto.getReferencia()) == null) {
                produtoController.salvar(produto);
                JOptionPane.showMessageDialog(null, "Produto Salvo Com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                produtoController.editar(produto);
                JOptionPane.showMessageDialog(null, "Produto Editado Com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
            limpar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btOkActionPerformed

    private void btOkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btOkKeyPressed
    }//GEN-LAST:event_btOkKeyPressed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        buscar();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void edReferenciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edReferenciaFocusLost
        buscar();
    }//GEN-LAST:event_edReferenciaFocusLost

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        try {
            int excluir = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir este produto?", "Excluir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (excluir == JOptionPane.OK_OPTION) {
                produtoController.excluir(edReferencia.getText());
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limpar();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btExcluirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btExcluirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btExcluirKeyPressed

    private void buscar() {
        if (!edReferencia.getText().isEmpty()) {
            try {
                Produto produto = produtoController.buscar(edReferencia.getText());
                if (produto != null) {
                    this.edReferencia.setText(produto.getReferencia());
                    this.edCodigoBarras.setText(produto.getCodigoBarrra());
                    this.edDescricao.setText(produto.getDescricao());
                    this.edValor.setText(String.valueOf(produto.getValor()));

                    this.edReferencia.setEnabled(false);
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btOk;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JTextField edCodigoBarras;
    private javax.swing.JTextField edDescricao;
    private javax.swing.JTextField edReferencia;
    private javax.swing.JTextField edValor;
    private javax.swing.JLabel lbConfirmarSenha;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNovoUsuario;
    private javax.swing.JLabel lbSenha;
    // End of variables declaration//GEN-END:variables
}

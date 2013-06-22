/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author reinoldo
 */
public class FrmCadastroFornecedor extends javax.swing.JDialog {

    /**
     * Creates new form FrmCadastroFornecedor
     */
    public FrmCadastroFornecedor() {
        initComponents();
        setModal(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btLimpar = new javax.swing.JButton();
        btPesquisar = new javax.swing.JButton();
        btOk = new javax.swing.JButton();
        lbComissao = new javax.swing.JLabel();
        edComissao = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        lbNovoUsuario = new javax.swing.JLabel();
        edConfirmarSenha = new javax.swing.JPasswordField();
        lbSenha = new javax.swing.JLabel();
        edLogin = new javax.swing.JTextField();
        lbLogin = new javax.swing.JLabel();
        edNome = new javax.swing.JTextField();
        lbConfirmarSenha = new javax.swing.JLabel();
        edSenha = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        edSenha1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-icon.png"))); // NOI18N
        btPesquisar.setToolTipText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
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

        lbComissao.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbComissao.setText("Telefone:");

        edComissao.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        lbNome.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbNome.setText("Nome:");
        lbNome.setName(""); // NOI18N

        lbNovoUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbNovoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cadastroCliente.png"))); // NOI18N
        lbNovoUsuario.setText("Cadastro de Fornecedor");

        edConfirmarSenha.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        lbSenha.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbSenha.setText("Endereço:");

        edLogin.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        lbLogin.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbLogin.setText("Código:");

        edNome.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        lbConfirmarSenha.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbConfirmarSenha.setText("E-mail:");

        edSenha.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        jLabel1.setText("Contato:");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel2.setText("CPF/CNPJ:");

        edSenha1.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbNovoUsuario))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbNome)
                            .addComponent(lbLogin))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edNome, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(edLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(edSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(edSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btOk, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(lbSenha)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbConfirmarSenha)
                                    .addComponent(lbComissao))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edComissao, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNovoUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(edLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSenha))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbConfirmarSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edComissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbComissao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btOk)
                    .addComponent(btLimpar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getRootPane().setDefaultButton(btOk);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        edNome.setText("");
        edLogin.setText("");
        edSenha.setText("");
        edConfirmarSenha.setText("");
        edComissao.setText("");
        cbAdministrador.setSelected(false);
        cbVendeProduto.setSelected(false);
        cbCadastraProduto.setSelected(false);
        edNome.requestFocus();
    }//GEN-LAST:event_btLimparActionPerformed

    private void btLimparKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btLimparKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        btLimparActionPerformed(null);
    }//GEN-LAST:event_btLimparKeyPressed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        carregarTransacao();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        try {
            Usuario usuario = new Usuario();
            usuario.setNome(edNome.getText());
            usuario.setLogin(edLogin.getText());
            usuario.setSenha(new String(edSenha.getPassword()));

            //Tava dando erro "empty String" quando deixava o campo comissão vazio.
            if (edComissao.getText().trim().equals(""))
            usuario.setComissao(0);
            else
            usuario.setComissao(Double.parseDouble(edComissao.getText()));
            usuario.setAdministrador(cbAdministrador.isSelected());
            usuario.setVendeProduto(cbVendeProduto.isSelected());
            usuario.setCadastraProduto(cbCadastraProduto.isSelected());
            usuarioController.salvar(usuario, new String(edConfirmarSenha.getPassword()));
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            btLimparActionPerformed(null);
            edNome.requestFocus();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btOkActionPerformed

    private void btOkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btOkKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        btOkActionPerformed(null);
    }//GEN-LAST:event_btOkKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCadastroFornecedor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btOk;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JTextField edComissao;
    private javax.swing.JPasswordField edConfirmarSenha;
    private javax.swing.JTextField edLogin;
    private javax.swing.JTextField edNome;
    private javax.swing.JPasswordField edSenha;
    private javax.swing.JPasswordField edSenha1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbComissao;
    private javax.swing.JLabel lbConfirmarSenha;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNovoUsuario;
    private javax.swing.JLabel lbSenha;
    // End of variables declaration//GEN-END:variables
}
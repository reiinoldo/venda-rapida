/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.FornecedorController;
import controller.impl.FornecedorControllerImpl;
import controller.impl.RegraNegocioException;
import java.awt.Frame;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import model.Fornecedor;

/**
 *
 * @author reinoldo
 */
public class FrmCadastroFornecedor extends javax.swing.JDialog {

    /**
     * Creates new form FrmCadastroFornecedor
     */
    
    FornecedorController fornecedorController = new FornecedorControllerImpl();
    
    public FrmCadastroFornecedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void pesquisar(int id){
        try {                
            Fornecedor fornecedor = fornecedorController.buscar(id);
            if (fornecedor == null)
                throw new RegraNegocioException("Fornecedor não encontrado");
            edCodigo.setText(String.valueOf(fornecedor.getId()));
            edNome.setText(fornecedor.getNome());
            edCpfCnpj.setText(fornecedor.getCpfCnpj());
            edEndereco.setText(fornecedor.getEndereco());            
            edEmail.setText(fornecedor.getEmail());
            edTelefone.setText(fornecedor.getTelefone());
            edCodigo.setEnabled(false);        
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);         
        } 
    }
    
    private void carregarTransacao(){
        try {
            Fornecedor fornecedor = new Fornecedor();            
            new FrmConsultaForncedor((Frame) this.getParent(), true, fornecedor).setVisible(true);                        
            pesquisar(fornecedor.getId());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbContato = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        edTelefone = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        lbTelefone = new javax.swing.JLabel();
        lbEndereco = new javax.swing.JLabel();
        lbNovoFornecedor = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        edNome = new javax.swing.JTextField();
        lbCodigo = new javax.swing.JLabel();
        edCodigo = new javax.swing.JTextField();
        lbCpfCnpj = new javax.swing.JLabel();
        btLimpar = new javax.swing.JButton();
        btPesquisar = new javax.swing.JButton();
        btOk = new javax.swing.JButton();
        edEmail = new javax.swing.JTextField();
        edCpfCnpj = new javax.swing.JTextField();
        edEndereco = new javax.swing.JTextField();
        btExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbContato.setText("Contato:");

        edTelefone.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        edTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edTelefoneActionPerformed(evt);
            }
        });

        lbNome.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbNome.setText("Nome:");
        lbNome.setName(""); // NOI18N

        lbTelefone.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbTelefone.setText("Telefone:");

        lbEndereco.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbEndereco.setText("Endereço:");

        lbNovoFornecedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbNovoFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/motorista.png"))); // NOI18N
        lbNovoFornecedor.setText("Cadastro de Fornecedor");

        lbEmail.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbEmail.setText("E-mail:");

        edNome.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        lbCodigo.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbCodigo.setText("Código:");

        edCodigo.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        edCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edCodigoActionPerformed(evt);
            }
        });
        edCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edCodigoFocusLost(evt);
            }
        });

        lbCpfCnpj.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbCpfCnpj.setText("CPF/CNPJ:");

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

        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search_16.png"))); // NOI18N
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

        edEmail.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        edCpfCnpj.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        edEndereco.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/remove-icon.png"))); // NOI18N
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btOk, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCpfCnpj, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbNome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbEndereco, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbCodigo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTelefone, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edTelefone)
                            .addComponent(edEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                            .addComponent(edCpfCnpj, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(edEndereco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(edCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(edNome)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lbContato))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbNovoFornecedor)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNovoFornecedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edCodigo)
                    .addComponent(lbCodigo)
                    .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edNome)
                    .addComponent(lbNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCpfCnpj)
                    .addComponent(edCpfCnpj))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEndereco)
                    .addComponent(edEndereco))
                .addGap(18, 18, 18)
                .addComponent(lbContato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEmail)
                    .addComponent(edEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTelefone)
                    .addComponent(edTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        getRootPane().setDefaultButton(btOk);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edCodigoActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        edCodigo.setText("");
        edNome.setText("");        
        edCpfCnpj.setText("");
        edEndereco.setText("");
        edEmail.setText("");
        edTelefone.setText("");        
        edCodigo.requestFocus();
        edCodigo.setEnabled(true);
    }//GEN-LAST:event_btLimparActionPerformed

    private void btLimparKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btLimparKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        btLimparActionPerformed(null);
    }//GEN-LAST:event_btLimparKeyPressed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        carregarTransacao();
        //if(edCodigo.getText().isEmpty()){
        //    JOptionPane.showMessageDialog(null, "Aguarde! Estamos mudando o Mundo!Nhác Nhác...", "Alerta", JOptionPane.WARNING_MESSAGE);
        //}else{
        //    pesquisar();
        //}        
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        try {
            
            int id = Integer.parseInt(edCodigo.getText());
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(id);
            fornecedor.setNome(edNome.getText());      
            fornecedor.setCpfCnpj(edCpfCnpj.getText());
            fornecedor.setEndereco(edEndereco.getText());           
            fornecedor.setEmail(edEmail.getText());
            fornecedor.setTelefone(edTelefone.getText());            
            
            Fornecedor fornAux = fornecedorController.buscar(id);
            if(fornAux == null){
                fornecedorController.salvar(fornecedor);
                JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);   
            }else{
                fornecedorController.editar(fornecedor);
                JOptionPane.showMessageDialog(null, "Fornecedor alterado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);   
            }                
            
            btLimparActionPerformed(null);            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btOkActionPerformed

    private void btOkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btOkKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        btOkActionPerformed(null);
    }//GEN-LAST:event_btOkKeyPressed

    private void edCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edCodigoFocusLost
        if(edCodigo.getText().isEmpty()){
            try {
                edCodigo.setText(String.valueOf(fornecedorController.incrementar()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            edCodigo.setEnabled(false);
        }else{
            int id = Integer.parseInt(edCodigo.getText());                    
            pesquisar(id);
        }
    }//GEN-LAST:event_edCodigoFocusLost

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        try {            
            fornecedorController.excluir(Integer.parseInt(edCodigo.getText()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        btLimparActionPerformed(null);
    }//GEN-LAST:event_btExcluirActionPerformed

    private void edTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edTelefoneActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btOk;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JTextField edCodigo;
    private javax.swing.JTextField edCpfCnpj;
    private javax.swing.JTextField edEmail;
    private javax.swing.JTextField edEndereco;
    private javax.swing.JTextField edNome;
    private javax.swing.JTextField edTelefone;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbContato;
    private javax.swing.JLabel lbCpfCnpj;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEndereco;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNovoFornecedor;
    private javax.swing.JLabel lbTelefone;
    // End of variables declaration//GEN-END:variables
}

package view;

import controller.ClienteController;
import controller.impl.ClienteControllerImpl;
import controller.impl.RegraNegocioException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import net.sf.jasperreports.engine.JRException;
import view.util.ViewUtil;
import view.util.ViewUtil.GeradorNomePDF;

public class FrmConsultaCliente extends javax.swing.JDialog {

    private DefaultTableModel dtm;
    private Cliente cliente;
    private Cliente clienteSelecionado;
    private List<Cliente> listaClientesBuscados;
    private ClienteController clienteController = new ClienteControllerImpl();

    public FrmConsultaCliente(java.awt.Frame parent, boolean modal, Cliente clienteSelecionado) {
        super(parent, modal);
        this.clienteSelecionado = clienteSelecionado;
        listaClientesBuscados = new ArrayList<Cliente>();
        initComponents();
        setLocationRelativeTo(null);
    }

    public void carregarCliente() {
        cliente = new Cliente();
        if (!edNome.getText().isEmpty())
            cliente.setNome(edNome.getText());
        if (!edCPFCNPJ.getText().isEmpty())
            cliente.setCpfCnpj(edCPFCNPJ.getText());
        try {
            listaClientesBuscados = clienteController.listar(cliente);
            carregarGrid();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void carregarGrid() {
        Vector<Vector> dados = new Vector<Vector>();

        for (Cliente cliente : listaClientesBuscados) {
            Vector registroDb = new Vector();

            registroDb.add(cliente.getId());
            registroDb.add(cliente.getNome());
            registroDb.add(cliente.getCpfCnpj());
            registroDb.add(cliente.getTelefone());
            registroDb.add(cliente.getEmail());

            dados.add(registroDb);
        }

        dtm = (DefaultTableModel) tabelaConsulta.getModel();
        dtm.setRowCount(0);

        for (Vector v : dados) {
            dtm.addRow(v);
        }
    }

    public void carregarEdicao() {
        try {
            if (tabelaConsulta.getSelectedRow() != -1) {
                setProdutoRetorno(listaClientesBuscados.get(tabelaConsulta.getSelectedRow()));
                this.dispose();
            } else {
                throw new RegraNegocioException("Favor selecionar um Cliente.");
            }
        } catch (RegraNegocioException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setProdutoRetorno(Cliente cliente) {
        if (clienteSelecionado != null) {
            clienteSelecionado.setId(cliente.getId());
            clienteSelecionado.setNome(cliente.getNome());
            clienteSelecionado.setCpfCnpj(cliente.getCpfCnpj());
            clienteSelecionado.setEndereco(cliente.getEndereco());
            clienteSelecionado.setTelefone(cliente.getTelefone());
            clienteSelecionado.setEmail(cliente.getEmail());
        }
    }

    private void limpar() {
        edNome.setText("");
        edCPFCNPJ.setText("");
        listaClientesBuscados = new ArrayList<Cliente>();

        dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) tabelaConsulta.getModel();

        dtm.setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cadastroAluno = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btConfirma = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        btPesquisar = new javax.swing.JButton();
        lbImg = new javax.swing.JLabel();
        btGerarPDF = new javax.swing.JButton();
        lbNome = new javax.swing.JLabel();
        edNome = new javax.swing.JTextField();
        lbCPFCNPJ = new javax.swing.JLabel();
        edCPFCNPJ = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConsulta = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cadastroAluno.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        cadastroAluno.setText("Consulta de Clientes");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btConfirma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirma.png"))); // NOI18N
        btConfirma.setToolTipText("Confirma");
        btConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfirmaActionPerformed(evt);
            }
        });

        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sair.png"))); // NOI18N
        btSair.setToolTipText("Cancelar");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });
        btSair.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btSairKeyPressed(evt);
            }
        });

        btLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpar.png"))); // NOI18N
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });

        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-icon.png"))); // NOI18N
        btPesquisar.setToolTipText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        lbImg.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lbImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/passageiros.png"))); // NOI18N

        btGerarPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf_file.png"))); // NOI18N
        btGerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGerarPDFActionPerformed(evt);
            }
        });

        lbNome.setText("Nome:");

        lbCPFCNPJ.setText("CPF/CNPJ:");

        tabelaConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "CPF/CNPJ", "Telefone", "E-mail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaConsulta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelaConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaConsultaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaConsulta);
        tabelaConsulta.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabelaConsulta.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabelaConsulta.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabelaConsulta.getColumnModel().getColumn(4).setPreferredWidth(70);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lbImg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cadastroAluno)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(47, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btSair)
                            .addComponent(btPesquisar)
                            .addComponent(btConfirma)
                            .addComponent(btLimpar)
                            .addComponent(btGerarPDF))
                        .addGap(4, 4, 4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(lbNome)
                        .addGap(66, 66, 66)
                        .addComponent(edNome)
                        .addGap(25, 25, 25)
                        .addComponent(lbCPFCNPJ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(cadastroAluno))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbImg)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(edNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCPFCNPJ)
                    .addComponent(edCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btConfirma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btGerarPDF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSair)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmaActionPerformed
        carregarEdicao();
    }//GEN-LAST:event_btConfirmaActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void btSairKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btSairKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSairKeyPressed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btLimparActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        carregarCliente();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btGerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGerarPDFActionPerformed
        if (!listaClientesBuscados.isEmpty()) {
            try {
                String path = null;
                try {
                    path = ViewUtil.createFileChooserToSavePDF(this, GeradorNomePDF.CLIENTES);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                if (path != null) {
                    clienteController.gerarRelatorio(listaClientesBuscados, path);
                    int abrir = JOptionPane.showConfirmDialog(null, "PDF Gerado Com Sucesso em '" + path + "'. \nDeseja abrí-lo?", "Sucesso", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (abrir == JOptionPane.OK_OPTION) {
                        java.awt.Desktop.getDesktop().open(new File(path));
                    }
                }
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar relatório, causa: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo, causa: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Necessita-se ao menos de um registro para gerar o PDF.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btGerarPDFActionPerformed

    private void tabelaConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaConsultaMouseClicked
        if (evt.getClickCount() == 2) {
            carregarEdicao();
        }
    }//GEN-LAST:event_tabelaConsultaMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConfirma;
    private javax.swing.JButton btGerarPDF;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btSair;
    private javax.swing.JLabel cadastroAluno;
    private javax.swing.JTextField edCPFCNPJ;
    private javax.swing.JTextField edNome;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCPFCNPJ;
    private javax.swing.JLabel lbImg;
    private javax.swing.JLabel lbNome;
    private javax.swing.JTable tabelaConsulta;
    // End of variables declaration//GEN-END:variables
}

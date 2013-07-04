package view;

import controller.VendaController;
import controller.dao.util.StringUtil;
import controller.impl.VendaControllerImpl;
import controller.relatorio.ComposicaoRelatorio;
import controller.relatorio.TipoRelatorio;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Usuario;
import model.Venda;
import net.sf.jasperreports.engine.JRException;
import view.util.ViewUtil;

public class FrmConsultaVendas extends javax.swing.JDialog {

    private DefaultTableModel dtm;
    private Venda venda;
    private Venda vendaSelecionada;
    private List<Venda> listaVendasBuscadas;
    private VendaController vendaController = new VendaControllerImpl();
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public FrmConsultaVendas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        listaVendasBuscadas = new ArrayList<Venda>();
        initComponents();
        setLocationRelativeTo(null);
        limpar();
    }

    public void carregarVenda() throws ParseException {
        venda = new Venda();
        if (!edCodigoCliente.getText().isEmpty()) {
            venda.setIdCliente(Integer.parseInt(edCodigoCliente.getText()));
        }
        if (!edLoginUsuario.getText().isEmpty()) {
            venda.setLoginUsuario(edLoginUsuario.getText());
        }
        if (!edDataInicial.getText().equals("  /  /    ")) {
            try {
                venda.setDataVenda(format.parse(edDataInicial.getText()));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        try {
            double valorInicial = StringUtil.getValorR$(edValorInicial.getText());
            double valorFinal = StringUtil.getValorR$(edValorFinal.getText());
            String dataAux = edDataInicial.getText().replace('/', ' ');
            if (dataAux.trim().equals("")) {
                venda.setDataVenda(null);
            } else {
                venda.setDataVenda(format.parse(edDataInicial.getText()));
            }

            Date dataFinal = null;
            dataAux = edDataFinal.getText().replace('/', ' ');
            if (!dataAux.trim().equals("")) {
                dataFinal = format.parse(edDataFinal.getText());
            }
            try {
                listaVendasBuscadas = vendaController.listar(venda, dataFinal, valorInicial, valorFinal);
                carregarGrid();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Valor inválido.\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void carregarGrid() {
        Vector<Vector> dados = new Vector<Vector>();

        for (Venda v : listaVendasBuscadas) {
            Vector registroDb = new Vector();

            registroDb.add(v.getCodigoVenda());
            registroDb.add(v.getIdCliente());
            registroDb.add(v.getDataVenda());
            registroDb.add(v.getLoginUsuario());
            registroDb.add(StringUtil.getR$FormmatedFromDouble(v.getDesconto()));
            registroDb.add(StringUtil.getR$FormmatedFromDouble(v.getValorTotalComDesconto()));

            dados.add(registroDb);
        }

        dtm = (DefaultTableModel) tabelaConsulta.getModel();
        dtm.setRowCount(0);

        for (Vector v : dados) {
            dtm.addRow(v);
        }
    }

    private void limpar() {
        edCodVenInicial.setText("0");
        edCodVenFinal.setText("999999999");
        edCodigoCliente.setText("");
        edLoginUsuario.setText("");
        edValorFinal.setText("9999999999,99");
        edValorInicial.setText("0,00");
        edValorInicial.setText("0,00");
        edDataInicial.setText("");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        edDataFinal.setText(String.valueOf(format.format(calendar.getTime())));
        listaVendasBuscadas = new ArrayList<Venda>();

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

        lbHeader = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConsulta = new javax.swing.JTable();
        btSair = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        btPesquisar = new javax.swing.JButton();
        lbImgHeader = new javax.swing.JLabel();
        lbCodigoCliente = new javax.swing.JLabel();
        edCodigoCliente = new javax.swing.JTextField();
        lbValorInicial = new javax.swing.JLabel();
        lbValorFinal = new javax.swing.JLabel();
        edValorInicial = new javax.swing.JFormattedTextField();
        edValorFinal = new javax.swing.JFormattedTextField();
        btGerarPDF = new javax.swing.JButton();
        lbCodVenInicial = new javax.swing.JLabel();
        edCodVenInicial = new javax.swing.JTextField();
        edCodVenFinal = new javax.swing.JTextField();
        lbCodVenFinal = new javax.swing.JLabel();
        edDataInicial = new javax.swing.JFormattedTextField();
        edDataFinal = new javax.swing.JFormattedTextField();
        lbDataInicial = new javax.swing.JLabel();
        lbDataFinal = new javax.swing.JLabel();
        lbCodigoUsuario = new javax.swing.JLabel();
        edLoginUsuario = new javax.swing.JTextField();
        btPesquisarUsuario = new javax.swing.JButton();
        btPesquisarCliente = new javax.swing.JButton();
        btVerItens = new javax.swing.JButton();
        cbImprimirProdutos = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbHeader.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lbHeader.setText("Consulta de Vendas Detalhada");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tabelaConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cliente", "Usuário", "Data", "Desconto", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
        );

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

        lbImgHeader.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lbImgHeader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/shopping_cart_48.png"))); // NOI18N

        lbCodigoCliente.setText("Código Cliente:");

        lbValorInicial.setText("Valor Inicial:");

        lbValorFinal.setText("Valor Final:");

        edValorInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        edValorInicial.setText("0,00");
        edValorInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edValorInicialFocusLost(evt);
            }
        });

        edValorFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        edValorFinal.setText("9999999999,99");
        edValorFinal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edValorFinalFocusLost(evt);
            }
        });

        btGerarPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/relatorio.png"))); // NOI18N
        btGerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGerarPDFActionPerformed(evt);
            }
        });

        lbCodVenInicial.setText("Código Venda Inicial:");

        lbCodVenFinal.setText("Código Venda Final:");

        try {
            edDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        edDataInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edDataInicialFocusLost(evt);
            }
        });

        try {
            edDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        edDataFinal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edDataFinalFocusLost(evt);
            }
        });

        lbDataInicial.setText("Data Inicial:");

        lbDataFinal.setText("Data Final:");

        lbCodigoUsuario.setText("Login Vendedor:");

        btPesquisarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search_16.png"))); // NOI18N
        btPesquisarUsuario.setToolTipText("Pesquisar");
        btPesquisarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarUsuarioActionPerformed(evt);
            }
        });

        btPesquisarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search_16.png"))); // NOI18N
        btPesquisarCliente.setToolTipText("Pesquisar");
        btPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarClienteActionPerformed(evt);
            }
        });

        btVerItens.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/shopping_cart.png"))); // NOI18N
        btVerItens.setToolTipText("Cancelar");
        btVerItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVerItensActionPerformed(evt);
            }
        });
        btVerItens.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btVerItensKeyPressed(evt);
            }
        });

        cbImprimirProdutos.setText("Imprimir Produtos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbImgHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHeader)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btGerarPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btVerItens, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbCodigoCliente)
                                    .addComponent(lbCodVenInicial)
                                    .addComponent(lbCodigoUsuario))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edCodigoCliente)
                                    .addComponent(edLoginUsuario)
                                    .addComponent(edCodVenInicial))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCodVenFinal)
                                    .addComponent(btPesquisarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btPesquisarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbValorInicial)
                                    .addComponent(lbDataInicial))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbImprimirProdutos)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(edDataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                            .addComponent(edValorInicial))
                                        .addGap(72, 72, 72)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbValorFinal, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbDataFinal, javax.swing.GroupLayout.Alignment.TRAILING))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(edValorFinal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(edCodVenFinal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                        .addGap(251, 251, 251)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lbHeader))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbImgHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodVenInicial)
                    .addComponent(edCodVenInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edCodVenFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodVenFinal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigoCliente)
                    .addComponent(edCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCodigoUsuario)
                        .addComponent(edLoginUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btPesquisarUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbValorInicial)
                    .addComponent(lbValorFinal)
                    .addComponent(edValorInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edValorFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDataInicial)
                    .addComponent(lbDataFinal)
                    .addComponent(edDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(cbImprimirProdutos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btVerItens, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btGerarPDF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSair)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        try {
            carregarVenda();
        } catch (ParseException ex) {
            Logger.getLogger(FrmConsultaVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void edValorFinalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edValorFinalFocusLost
        if (edValorFinal.getText().equals("")) {
            edValorFinal.setText("9999999999,99");
        }
    }//GEN-LAST:event_edValorFinalFocusLost

    private void edValorInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edValorInicialFocusLost
        if (edValorInicial.getText().equals("")) {
            edValorInicial.setText("0,00");
        }
    }//GEN-LAST:event_edValorInicialFocusLost

    private void btGerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGerarPDFActionPerformed
        if (!listaVendasBuscadas.isEmpty()) {
            ComposicaoRelatorio composicao;
            if (cbImprimirProdutos.isSelected()) {
                composicao = new ComposicaoRelatorio(listaVendasBuscadas, TipoRelatorio.VENDAS_COM_ITENS);
            } else {
                composicao = new ComposicaoRelatorio(listaVendasBuscadas, TipoRelatorio.VENDAS);
            }
            new FrmGerarRelatorio((Frame) getParent(), true, composicao).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Necessita-se ao menos de um registro para gerar um relatório.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btGerarPDFActionPerformed

    private void tabelaConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaConsultaMouseClicked
        if (evt.getClickCount() == 2) {
            carregarFormItens();
        }
    }//GEN-LAST:event_tabelaConsultaMouseClicked

    public void carregarFormItens() {
        if (tabelaConsulta.getSelectedRow() > -1) {
            new FrmConsultaItensVenda((Frame) this.getParent(), true, listaVendasBuscadas.get(tabelaConsulta.getSelectedRow())).setVisible(true);
        }
    }

    private void edDataInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edDataInicialFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_edDataInicialFocusLost

    private void edDataFinalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edDataFinalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_edDataFinalFocusLost

    private void btPesquisarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarUsuarioActionPerformed

        try {
            Usuario usuario = new Usuario();
            new FrmConsultaUsuario((Frame) this.getParent(), true, usuario).setVisible(true);
            if (!usuario.getLogin().isEmpty()) {
                edLoginUsuario.setText(usuario.getLogin());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        //if(edCodigo.getText().isEmpty()){
        //    JOptionPane.showMessageDialog(null, "Aguarde! Estamos mudando o Mundo!Nhác Nhác...", "Alerta", JOptionPane.WARNING_MESSAGE);
        //}else{
        //    pesquisar();
        //}
    }//GEN-LAST:event_btPesquisarUsuarioActionPerformed

    private void btPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarClienteActionPerformed
        try {
            Cliente cliente = new Cliente();
            new FrmConsultaCliente((Frame) this.getParent(), true, cliente).setVisible(true);
            if (cliente.getId() != 0) {
                edCodigoCliente.setText(String.valueOf(cliente.getId()));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btPesquisarClienteActionPerformed

    private void btVerItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVerItensActionPerformed
        carregarFormItens();
    }//GEN-LAST:event_btVerItensActionPerformed

    private void btVerItensKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btVerItensKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btVerItensKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGerarPDF;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btPesquisarCliente;
    private javax.swing.JButton btPesquisarUsuario;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btVerItens;
    private javax.swing.JCheckBox cbImprimirProdutos;
    private javax.swing.JTextField edCodVenFinal;
    private javax.swing.JTextField edCodVenInicial;
    private javax.swing.JTextField edCodigoCliente;
    private javax.swing.JFormattedTextField edDataFinal;
    private javax.swing.JFormattedTextField edDataInicial;
    private javax.swing.JTextField edLoginUsuario;
    private javax.swing.JFormattedTextField edValorFinal;
    private javax.swing.JFormattedTextField edValorInicial;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCodVenFinal;
    private javax.swing.JLabel lbCodVenInicial;
    private javax.swing.JLabel lbCodigoCliente;
    private javax.swing.JLabel lbCodigoUsuario;
    private javax.swing.JLabel lbDataFinal;
    private javax.swing.JLabel lbDataInicial;
    private javax.swing.JLabel lbHeader;
    private javax.swing.JLabel lbImgHeader;
    private javax.swing.JLabel lbValorFinal;
    private javax.swing.JLabel lbValorInicial;
    private javax.swing.JTable tabelaConsulta;
    // End of variables declaration//GEN-END:variables
}

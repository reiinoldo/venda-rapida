/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import controller.BussinessException;
//import controller.TransacaoController;
import controller.dao.util.ConnectionMySql;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import model.FormaPagamento;
//import model.TipoPagamento;
//import model.Transacao;
import model.Usuario;

/**
 *
 * @author pc
 */
public class FrmBaseConsulta extends javax.swing.JDialog {

    private DefaultTableModel dtm;
//    private Transacao transacao;
//    private Transacao transacaoSelecionada;
//    private List<Transacao> listaTransacoesBuscadas;
//    private TransacaoController transacaoController = new TransacaoController();
    private Usuario usuario;
//    private TelaPrincipalControleFinancas telaPrincipal;
    private Double valorTotalReceita;
    private Double valorTotalDespesa;
    private Double valorTotalSaldo;
    private DecimalFormat dec = new DecimalFormat("#,##0.00");

    /**
     * Creates new form TelaConsulta
     */
//    public FrmBaseConsulta(java.awt.Frame parent, boolean modal, Usuario usuario, TelaPrincipalControleFinancas telaPrincipal) {
//        super(parent, modal);
//
//        this.usuario = usuario;
//        this.telaPrincipal = telaPrincipal;
//
//        initComponents();
//        setLocationRelativeTo(null);
//        setFormasDePagamento();
//
//        Image image = Toolkit.getDefaultToolkit().getImage("src/img/dinheiro.png");
//        setIconImage(image);
//
//        limpar();
//    }
//
//    private void setFormasDePagamento() {
//        for (FormaPagamento forma : FormaPagamento.values()) {
//            cbFormaPagamento.addItem(forma.getForma());
//        }
//    }

//    public void carregarTransacao() {
//        if (transacaoController.verificarAntesDeBuscar(edDataInicial.getText(), edDataFinal.getText(), edValorInicial.getText(), edValorFinal.getText())) {
//            transacao = new Transacao();
//            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//            if (!edDataInicial.getText().equals("  /  /    ")) {
//                try {
//                    transacao.setData(format.parse(edDataInicial.getText()));
//                } catch (ParseException ex) {
//                    Logger.getLogger(FrmBaseConsulta.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//            if (!edDestinatario.getText().equals("")) {
//                transacao.setDestinatario(edDestinatario.getText());
//            }
//
//            if (cbFormaPagamento.getSelectedIndex() == 0) {
//                transacao.setFormaPagamento(null);
//            } else {
//                transacao.setFormaPagamento(FormaPagamento.getFormaPagamento(cbFormaPagamento.getSelectedIndex() - 1));
//            }
//
//            if (!edLocal.getText().equals("")) {
//                transacao.setLocalTransacao(edLocal.getText());
//            }
//            if (!edObservacao.getText().equals("")) {
//                transacao.setObservacao(edObservacao.getText());
//            }
//
//            if (cbTipoPagamento.getSelectedIndex() == 0) {
//                transacao.setTipoPagamento(null);
//            } else {
//                transacao.setTipoPagamento(TipoPagamento.getTipoPagamento(cbTipoPagamento.getSelectedIndex() - 1));
//            }
//            transacao.setUsuario(usuario);
//            if (!edValorInicial.getText().equals("")) {
//                transacao.setValor(transacaoController.getRealDouble(edValorInicial.getText()));
//            }
//            try {
//                listaTransacoesBuscadas = transacaoController.buscar(transacao, !edDataFinal.getText().equals("  /  /    ") ? format.parse(edDataFinal.getText()) : null,
//                        !edValorFinal.getText().equals("") ? transacaoController.getRealDouble(edValorFinal.getText()) : null);
//                carregarGrid();
//            } catch (SQLException ex) {
//                Logger.getLogger(FrmBaseConsulta.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ParseException ex) {
//                Logger.getLogger(FrmBaseConsulta.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                try {
//                    if (ConnectionMySql.connection != null) {
//                        ConnectionMySql.closeConnection();
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//        }
//    }
//
//    public void carregarGrid() {
//        Vector<Vector> dados = new Vector<Vector>();
//        valorTotalReceita = new Double(0);
//        valorTotalDespesa = new Double(0);
//        valorTotalSaldo = new Double(0);
//
//        for (Transacao tran : listaTransacoesBuscadas) {
//            Vector registroDb = new Vector();
//
//            registroDb.add(tran.getTipoPagamento().getTipo());
//            registroDb.add(tran.getFormaPagamento().getForma());
//            registroDb.add(tran.getDestinatario());
//
//            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//            registroDb.add(format.format(tran.getData()));
//            registroDb.add(tran.getLocalTransacao());
//            registroDb.add(tran.getObservacao());
//            registroDb.add(dec.format(tran.getValor()));
//
//            if (tran.getTipoPagamento().ordinal() == 0) {
//                valorTotalReceita += tran.getValor();
//                valorTotalSaldo += tran.getValor();
//            } else {
//                valorTotalDespesa += tran.getValor();
//                valorTotalSaldo -= tran.getValor();
//            }
//
//            dados.add(registroDb);
//        }
//
//        formatarLabels();
//        dtm = (DefaultTableModel) tabelaConsulta.getModel();
//        dtm.setRowCount(0);
//
//        for (Vector v : dados) {
//            dtm.addRow(v);
//        }
//
//    }
//
//    public void formatarLabels() {
//        
//        lbValorTotalReceitas.setText(String.valueOf(dec.format(valorTotalReceita)));
//        lbValorTotaDespesas.setText(String.valueOf(dec.format(valorTotalDespesa)));
//        lbValorSaldoTotal.setText(String.valueOf(dec.format(valorTotalSaldo)));
//        if (valorTotalSaldo > 0) {
//            lbValorSaldoTotal.setForeground(new Color(0, 102, 0));//verde
//        } else if (valorTotalSaldo < 0) {
//            lbValorSaldoTotal.setForeground(new Color(255, 0, 0));//vermelho
//        } else {
//            lbValorSaldoTotal.setForeground(new Color(0, 0, 0));//preto
//        }
//    }
//
//    private void limpar() {
//        transacaoSelecionada = new Transacao();
//
//        cbFormaPagamento.setSelectedIndex(0);
//        cbTipoPagamento.setSelectedIndex(0);
//        edDataInicial.setText("");
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        edDataFinal.setText(String.valueOf(format.format(new Date().getTime())));
//        edDestinatario.setText("");
//        lbDestinatario.setText("Quem:");
//        edLocal.setText("");
//        edValorInicial.setText("");
//        edValorFinal.setText("");
//        edObservacao.setText("");
//
//
//        dtm = new DefaultTableModel();
//        dtm = (DefaultTableModel) tabelaConsulta.getModel();
//
//        dtm.setRowCount(0);
//
//        valorTotalReceita = new Double(0);
//        valorTotalDespesa = new Double(0);
//        valorTotalSaldo = new Double(0);
//        formatarLabels();
//    }
//
//    public void carregarEdicao() {
//        try {
//            if (tabelaConsulta.getSelectedRow() != -1) {
//                transacaoSelecionada = listaTransacoesBuscadas.get(tabelaConsulta.getSelectedRow());
//                telaPrincipal.editarCampos(transacaoSelecionada);
//                this.dispose();
//            } else {
//                throw new BussinessException("Favor selecionar uma linha da lista para editar.");
//            }
//        } catch (BussinessException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        lbDestinatario = new javax.swing.JLabel();
        edLocal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        edDataInicial = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        cbFormaPagamento = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        edDestinatario = new javax.swing.JTextField();
        cbTipoPagamento = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConsulta = new javax.swing.JTable();
        btSair = new javax.swing.JButton();
        onibus = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        btConfirma = new javax.swing.JButton();
        cadastroAluno = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btLimpar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        edDataFinal = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        edObservacao = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        lbValorTotalReceitas = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbValorTotaDespesas = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbValorSaldoTotal = new javax.swing.JLabel();
        edValorInicial = new javax.swing.JFormattedTextField();
        edValorFinal = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consuta Transações");

        jLabel8.setText("Data Inicial:");

        lbDestinatario.setText("Destinatário:");

        jLabel11.setText("Valor Inicial:");

        try {
            edDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        edDataInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edDataInicialActionPerformed(evt);
            }
        });
        edDataInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edDataInicialFocusLost(evt);
            }
        });

        jLabel3.setText("Forma de Pagamento:");

        cbFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));

        jLabel9.setText("Local:");

        cbTipoPagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Receita", "Despesa" }));
        cbTipoPagamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoPagamentoItemStateChanged(evt);
            }
        });
        cbTipoPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoPagamentoActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo de Pagamento:");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tabelaConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo Pagamento", "Forma Pagamento", "Destinatário", "Data", "Local", "Observacao", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
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

        onibus.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        onibus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/relatorio.png"))); // NOI18N

        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-icon.png"))); // NOI18N
        btPesquisar.setToolTipText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        btConfirma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirma.png"))); // NOI18N
        btConfirma.setToolTipText("Confirma");
        btConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfirmaActionPerformed(evt);
            }
        });

        cadastroAluno.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        cadastroAluno.setText("Consulta Transações");

        jLabel13.setText("Valor Final:");

        btLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpar.png"))); // NOI18N
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });

        jLabel10.setText("Observação:");

        try {
            edDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        edDataFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edDataFinalActionPerformed(evt);
            }
        });
        edDataFinal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edDataFinalFocusLost(evt);
            }
        });

        jLabel12.setText("Data Final:");

        edObservacao.setColumns(20);
        edObservacao.setLineWrap(true);
        edObservacao.setRows(5);
        jScrollPane2.setViewportView(edObservacao);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Valor Total de Receitas:");

        lbValorTotalReceitas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbValorTotalReceitas.setForeground(new java.awt.Color(0, 102, 0));
        lbValorTotalReceitas.setText("valorTotalReceitas");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Valor Total de Despesas:");

        lbValorTotaDespesas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbValorTotaDespesas.setForeground(new java.awt.Color(255, 0, 0));
        lbValorTotaDespesas.setText("valorTotalDespesas");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Saldo Total:");

        lbValorSaldoTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbValorSaldoTotal.setForeground(new java.awt.Color(0, 0, 204));
        lbValorSaldoTotal.setText("valorSaldoTotal");

        edValorInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        edValorInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edValorInicialFocusLost(evt);
            }
        });

        edValorFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        edValorFinal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edValorFinalFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(onibus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cadastroAluno)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(lbValorTotalReceitas))
                                .addGap(152, 152, 152)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbValorTotaDespesas)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbValorSaldoTotal)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(edLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbDestinatario)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(edDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(116, 116, 116))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(edValorInicial)
                                            .addComponent(edDataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(edDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                            .addComponent(edValorFinal)))))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btConfirma)
                                .addComponent(btPesquisar, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(btLimpar)
                            .addComponent(btSair))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(onibus)
                            .addComponent(cadastroAluno))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(cbTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbDestinatario)
                                    .addComponent(edDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(edLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(cbFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(edDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(edDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel13)
                                        .addComponent(edValorFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(edValorInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btConfirma)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btLimpar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btSair))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbValorTotalReceitas)
                            .addComponent(lbValorTotaDespesas))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbValorSaldoTotal)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edDataInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edDataInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edDataInicialActionPerformed

    private void cbTipoPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoPagamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoPagamentoActionPerformed

    private void tabelaConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaConsultaMouseClicked
        if (evt.getClickCount() == 2) {
            //carregarEdicao();
        }
    }//GEN-LAST:event_tabelaConsultaMouseClicked

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void btSairKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btSairKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSairKeyPressed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
//        carregarTransacao();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmaActionPerformed
//        carregarEdicao();
    }//GEN-LAST:event_btConfirmaActionPerformed

    private void edDataFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edDataFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edDataFinalActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
//        limpar();
    }//GEN-LAST:event_btLimparActionPerformed

    private void edDataInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edDataInicialFocusLost
        if(edDataInicial.getText().equals("  /  /    ")){
            edDataInicial.setValue(null);
        }
    }//GEN-LAST:event_edDataInicialFocusLost

    private void edDataFinalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edDataFinalFocusLost
        if(edDataFinal.getText().equals("  /  /    ")){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            edDataFinal.setValue(format.format(new Date().getTime()));
        }
    }//GEN-LAST:event_edDataFinalFocusLost

    private void cbTipoPagamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoPagamentoItemStateChanged
        if(cbTipoPagamento.getSelectedIndex() == 1){
            lbDestinatario.setText("De quem:");
        } else if(cbTipoPagamento.getSelectedIndex() == 2){
            lbDestinatario.setText("Para quem:");
        } else {
            lbDestinatario.setText("Quem:");
        }
    }//GEN-LAST:event_cbTipoPagamentoItemStateChanged

    private void edValorInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edValorInicialFocusLost
        if(edValorInicial.getText().equals("")){
            edValorInicial.setValue(null);
        }
    }//GEN-LAST:event_edValorInicialFocusLost

    private void edValorFinalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edValorFinalFocusLost
        if(edValorFinal.getText().equals("")){
            edValorFinal.setValue(null);
        }
    }//GEN-LAST:event_edValorFinalFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConfirma;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btSair;
    private javax.swing.JLabel cadastroAluno;
    private javax.swing.JComboBox cbFormaPagamento;
    private javax.swing.JComboBox cbTipoPagamento;
    private javax.swing.JFormattedTextField edDataFinal;
    private javax.swing.JFormattedTextField edDataInicial;
    private javax.swing.JTextField edDestinatario;
    private javax.swing.JTextField edLocal;
    private javax.swing.JTextArea edObservacao;
    private javax.swing.JFormattedTextField edValorFinal;
    private javax.swing.JFormattedTextField edValorInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDestinatario;
    private javax.swing.JLabel lbValorSaldoTotal;
    private javax.swing.JLabel lbValorTotaDespesas;
    private javax.swing.JLabel lbValorTotalReceitas;
    private javax.swing.JLabel onibus;
    private javax.swing.JTable tabelaConsulta;
    // End of variables declaration//GEN-END:variables
}

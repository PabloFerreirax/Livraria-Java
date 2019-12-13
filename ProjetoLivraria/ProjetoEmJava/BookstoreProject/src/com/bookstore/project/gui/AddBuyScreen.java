/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstore.project.gui;
import com.bookstore.project.dao.BookJpaController;
import com.bookstore.project.dao.BuyJpaController;
import com.bookstore.project.dao.ClientJpaController;
import com.bookstore.project.dao.PaymentMethodJpaController;
import com.bookstore.project.dao.PurchaseItemJpaController;
import com.bookstore.project.dao.exceptions.PreexistingEntityException;
import com.bookstore.project.model.Book;
import com.bookstore.project.model.Buy;
import com.bookstore.project.model.Client;
import com.bookstore.project.model.PaymentMethod;
import com.bookstore.project.model.PurchaseItem;
import com.bookstore.project.useful.ErrorHandling;
import com.bookstore.project.useful.TableModelBag;
import java.awt.Image;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
/**
 *
 * @author pablo
 */
public class AddBuyScreen extends javax.swing.JInternalFrame {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookstoreProjectPU");
    BookJpaController bookController = new BookJpaController(emf);
    BuyJpaController buyController = new BuyJpaController(emf);
    PaymentMethodJpaController payController = new PaymentMethodJpaController(emf);
    PurchaseItemJpaController purchaseController = new PurchaseItemJpaController(emf);
    ClientJpaController clientController = new ClientJpaController(emf);
    Buy buy = new Buy();
    Book book = new Book();
    PaymentMethod pay = new PaymentMethod();
    Client client = new Client();
    PurchaseItem item = new PurchaseItem();
    ErrorHandling errHandling = new ErrorHandling();
    TableModelBag modelBag = new TableModelBag();
    
    
    
    public AddBuyScreen() {
        initComponents();

        tableBag.setModel(modelBag);
        
        for(Client c: clientController.findClientEntities()){
            boxClient.addItem(c);
        }
        
        for(PaymentMethod p: payController.findPaymentMethodEntities()){
            boxPay.addItem(p);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        ImageIcon icon = new ImageIcon(getClass().getResource("/com/bookstore/project/img/background_internal.jpg"));
        Image image = icon.getImage();
        jPanel1 = new javax.swing.JPanel(){
            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        search = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        textID = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        textNm = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        textVal = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        addBag = new javax.swing.JButton();
        textSearch = new javax.swing.JFormattedTextField();
        textQtd = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBag = new javax.swing.JTable();
        removeBag = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        boxPay = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        addAllItens = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        textTotal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        boxClient = new javax.swing.JComboBox<Object>();
        textMoneyClient = new javax.swing.JTextField();
        textChange = new javax.swing.JFormattedTextField();

        setClosable(true);
        setTitle("Adicionar Compra");

        jLabel1.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 66)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bookstore/project/img/counter.png"))); // NOI18N
        jLabel1.setText("Comprar");

        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(null, "Buscar Livro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 14)))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel11.setText("Barra de Pesquisa por ID: ");

        search.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        search.setText("Buscar");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel24.setText("ID:");

        textID.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        textID.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel25.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel25.setText("Nome Livro:");

        textNm.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        textNm.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel27.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel27.setText("Valor Unid:");

        textVal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        textVal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel28.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel28.setText("Quantidade:");

        addBag.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        addBag.setText("Confirmar Item");
        addBag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBagActionPerformed(evt);
            }
        });

        textSearch.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        textSearch.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        textQtd.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(search)
                        .addGap(385, 385, 385))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(textNm, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27)
                            .addComponent(textVal, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(221, 221, 221))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addBag)
                .addGap(39, 39, 39))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(search)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textNm, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textVal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(textQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addBag)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(null, "Carrinho", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 14)))); // NOI18N

        tableBag.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome Livro", "Valor", "Quantidade", "Total"
            }
        ));
        jScrollPane1.setViewportView(tableBag);

        removeBag.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        removeBag.setText("Remover Item");
        removeBag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBagActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(removeBag)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(removeBag)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(null, "Controle Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 14)))); // NOI18N

        boxPay.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel26.setText("Forma de Pagamento:");

        addAllItens.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        addAllItens.setText("Finalizar Compra");
        addAllItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAllItensActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel2.setText("Total Compra:");

        jLabel21.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel21.setText("Dinheiro Cliente:");

        jLabel22.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel22.setText("Troco:");

        textTotal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        textTotal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel3.setText("Nome do Cliente:");

        boxClient.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        textMoneyClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        textMoneyClient.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        textChange.setEditable(false);
        textChange.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤¤#,##0.00"))));
        textChange.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textChange.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textMoneyClient, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textChange, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boxPay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boxClient, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addAllItens, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(boxClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(27, 27, 27)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel21)
                        .addComponent(jLabel22)
                        .addComponent(textMoneyClient, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textChange, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addAllItens)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(253, 253, 253))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 840, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public double ValBuyTotal(){
        // Este metodo retorna um total de um item adicionado ao carrinho
        // Mostrado na ultima coluna da tabela, e, é calculado por unidade * quantidade.
        String valUnity  = textVal.getText();
        Double valUnityConvert = Double.valueOf(valUnity);
        
        String qtdUnity = textQtd.getText();
        Double qtdUnityConvert = Double.valueOf(qtdUnity);
        
        Double total = valUnityConvert * qtdUnityConvert;
        
        return total;
    }
    
    private void addBagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBagActionPerformed
        //Adiciona o item ao carrinho
        tableBag.setModel(modelBag); //Seta o modelo da tabela, criada no pacote useful.
        
        Book b = new Book();
        PurchaseItem i = new PurchaseItem();

        b.setIdLivro(Integer.valueOf(textID.getText()));
        b.setNmLivro(textNm.getText());
        b.setValorLivro(Double.valueOf(textVal.getText()));
        i.setQtdCompra(Integer.valueOf(textQtd.getText()));
        i.setValorCompra(ValBuyTotal());
        
        modelBag.addRowBok(b);
        modelBag.addRowItem(i);
        
        somarValores();
        
    }//GEN-LAST:event_addBagActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        //Metodo que faz a busca por ID do livro.
        try {
        Book id = bookController.findBook(Integer.valueOf(textSearch.getText()));
        
        textID.setText(String.valueOf(id.getIdLivro()));
        textNm.setText(id.getNmLivro());
        textVal.setText(String.valueOf(id.getValorLivro()));
        } catch (Exception e) {
            errHandling.showMsgError("Erro ao Salvar", "Valor não registrado");
        }
        
        
    }//GEN-LAST:event_searchActionPerformed

    private void removeBagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBagActionPerformed
        //Remove a linha selecionada do carrinho de compras
        if(tableBag.getSelectedRow() != -1){
            modelBag.removeRowBok(tableBag.getSelectedRow());
        }
        
    }//GEN-LAST:event_removeBagActionPerformed

    private void addAllItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAllItensActionPerformed
        //Adiciona todos os itens do carrinho no banco
        
        textChange.setText(String.valueOf(changeValue())); // Retorna o troco do cliente.
                
        //Faz a captura do ID do cliente e do metodo de pagamento
        Client clientId = (Client) boxClient.getSelectedItem();
        int clientIdConvert = clientId.getIdCliente();
        
        PaymentMethod payId = (PaymentMethod) boxPay.getSelectedItem();
        int payIdConvert = payId.getIdFormaPagamento();
        
        int i;
        
        try {
            checkValueBuy();
            
            client.setIdCliente(clientIdConvert);
            pay.setIdFormaPagamento(payIdConvert);
            
            buy.setDataCompra(returnDate());
            buy.setIdFormaPagamento(pay);
            buy.setIdCliente(payIdConvert);
            buy.setValorTotal(Double.parseDouble(textTotal.getText()));
            buyController.create(buy);
            
            //Um laço de repetição criado para adicionar todos os valores das linhas no banco de dados.
            //Onde o Numero de linhas é a condição de parada
            for(i = 0; i < tableBag.getRowCount(); i++ ){
                Integer bb = (Integer) tableBag.getValueAt(i, 0);
                
                book.setIdLivro(bb);
                
                item.setIdLivro(book);
                item.setIdItemCompra(buy);
                item.setQtdCompra((int) tableBag.getValueAt(i, 3));
                item.setValorCompra((Double) tableBag.getValueAt(i, 4));
                item.setValorUni((Double) tableBag.getValueAt(i, 2));
                purchaseController.create(item);
            }
            
            restValues(); // Limpa tudo
      
            errHandling.showMsg("Salvo com sucesso", "Compra foi salvo.");
        } catch (Exception e) {
            errHandling.showMsgError("Erro ao Salvar", "Erro causado por: " + e.getMessage());
        }
        
    }//GEN-LAST:event_addAllItensActionPerformed

    private void restValues(){
        //Reseta todos os valores e limpa para uma futura nova compra
        modelBag.limpar(); // Limpa tabela
        textMoneyClient.setText("");
        textSearch.setText("");
        textQtd.setText("");
        textTotal.setText("");
        textChange.setText("");
        textID.setText("");
        textNm.setText("");
        textVal.setText("");
    }
    
     private void checkValueBuy() throws PreexistingEntityException{
         //Trata as de checar se contem valores em determinados campos de texto
        if(textMoneyClient.getText().length()<=0)
            throw new PreexistingEntityException("Informe o Dinheiro do cliente");
     }
     
    public Date returnDate(){
        //Metodo para capturar a data atual do sistema.
        Date dataAtual = new Date();
        return dataAtual;
    }
    
    
    public void somarValores() {
        //Captura o a soma total da compra, e mostrar em um textTotal.
        double somaTotal = 0;
        for (int i = 0; i < tableBag.getRowCount(); i++) {
            somaTotal += Double.parseDouble(tableBag.getValueAt(i, 4).toString());
            textTotal.setText(String.valueOf(somaTotal));
        }
    }
    
    public String changeValue(){
        //Faz uma conta de Dinheiro do cliente - total da compra resultando no troco a ser devolvido.
        try {
            Double chance;
            String moneyClient = textMoneyClient.getText();
            Double moneyCientConvert = Double.valueOf(moneyClient);

            DecimalFormat dc = new DecimalFormat("#.##");  
            dc.setMinimumFractionDigits(2);
            dc.setMaximumFractionDigits(2);

            String totalValue = textTotal.getText();
            Double totalValueConvert = Double.valueOf(totalValue);

            chance = moneyCientConvert - totalValueConvert;

            return dc.format(chance);
        } catch (Exception e) {
            
        }
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAllItens;
    private javax.swing.JButton addBag;
    private javax.swing.JComboBox<Object> boxClient;
    private javax.swing.JComboBox boxPay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton removeBag;
    private javax.swing.JButton search;
    private javax.swing.JTable tableBag;
    private javax.swing.JFormattedTextField textChange;
    private javax.swing.JLabel textID;
    private javax.swing.JTextField textMoneyClient;
    private javax.swing.JLabel textNm;
    private javax.swing.JFormattedTextField textQtd;
    private javax.swing.JFormattedTextField textSearch;
    private javax.swing.JLabel textTotal;
    private javax.swing.JLabel textVal;
    // End of variables declaration//GEN-END:variables
}

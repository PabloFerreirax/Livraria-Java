/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstore.project.gui;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.Timer;
/**
 *
 * @author pablo
 */
public class MainScreen extends javax.swing.JFrame {

    public MainScreen() {
        initComponents();
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/com/bookstore/project/img/background.jpg"));
        Image image = icon.getImage();
        DesktopScreen = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
        };
        textHora = new javax.swing.JLabel();
        textDate = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        addBuyScreen = new javax.swing.JMenuItem();
        addClientScreen = new javax.swing.JMenuItem();
        addBookScreen = new javax.swing.JMenuItem();
        addPCompanyScreen = new javax.swing.JMenuItem();
        addAuthorScreen = new javax.swing.JMenuItem();
        addCategoryScreen = new javax.swing.JMenuItem();
        addPayMethodScreen = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Livraria - Livro Aberto");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        textHora.setFont(new java.awt.Font("Bernard MT Condensed", 0, 24)); // NOI18N
        textHora.setForeground(new java.awt.Color(255, 255, 255));
        textHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textHora.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textDate.setFont(new java.awt.Font("Bernard MT Condensed", 0, 24)); // NOI18N
        textDate.setForeground(new java.awt.Color(255, 255, 255));
        textDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textDate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout DesktopScreenLayout = new javax.swing.GroupLayout(DesktopScreen);
        DesktopScreen.setLayout(DesktopScreenLayout);
        DesktopScreenLayout.setHorizontalGroup(
            DesktopScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DesktopScreenLayout.createSequentialGroup()
                .addContainerGap(716, Short.MAX_VALUE)
                .addComponent(textDate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textHora, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        DesktopScreenLayout.setVerticalGroup(
            DesktopScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DesktopScreenLayout.createSequentialGroup()
                .addContainerGap(506, Short.MAX_VALUE)
                .addGroup(DesktopScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textHora, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(textDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        DesktopScreen.setLayer(textHora, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopScreen.setLayer(textDate, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DesktopScreen)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DesktopScreen)
        );

        jMenu1.setBorder(null);
        jMenu1.setText("Adicionar");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N

        addBuyScreen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        addBuyScreen.setFont(new java.awt.Font("Century Schoolbook", 1, 15)); // NOI18N
        addBuyScreen.setText("Compra");
        addBuyScreen.setContentAreaFilled(false);
        addBuyScreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBuyScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBuyScreenActionPerformed(evt);
            }
        });
        jMenu1.add(addBuyScreen);

        addClientScreen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        addClientScreen.setFont(new java.awt.Font("Century Schoolbook", 1, 15)); // NOI18N
        addClientScreen.setText("Cliente");
        addClientScreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addClientScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClientScreenActionPerformed(evt);
            }
        });
        jMenu1.add(addClientScreen);

        addBookScreen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        addBookScreen.setFont(new java.awt.Font("Century Schoolbook", 1, 15)); // NOI18N
        addBookScreen.setText("Livro");
        addBookScreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBookScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookScreenActionPerformed(evt);
            }
        });
        jMenu1.add(addBookScreen);

        addPCompanyScreen.setFont(new java.awt.Font("Century Schoolbook", 1, 15)); // NOI18N
        addPCompanyScreen.setText("Editora");
        addPCompanyScreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addPCompanyScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPCompanyScreenActionPerformed(evt);
            }
        });
        jMenu1.add(addPCompanyScreen);

        addAuthorScreen.setFont(new java.awt.Font("Century Schoolbook", 1, 15)); // NOI18N
        addAuthorScreen.setText("Autor");
        addAuthorScreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addAuthorScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAuthorScreenActionPerformed(evt);
            }
        });
        jMenu1.add(addAuthorScreen);

        addCategoryScreen.setFont(new java.awt.Font("Century Schoolbook", 1, 15)); // NOI18N
        addCategoryScreen.setText("Categoria");
        addCategoryScreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addCategoryScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCategoryScreenActionPerformed(evt);
            }
        });
        jMenu1.add(addCategoryScreen);

        addPayMethodScreen.setFont(new java.awt.Font("Century Schoolbook", 1, 15)); // NOI18N
        addPayMethodScreen.setText("F. Pagamento");
        addPayMethodScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPayMethodScreenActionPerformed(evt);
            }
        });
        jMenu1.add(addPayMethodScreen);

        jMenuBar1.add(jMenu1);

        jMenu3.setBorder(null);
        jMenu3.setText("Relatorio");
        jMenu3.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addAuthorScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAuthorScreenActionPerformed
        AddAuthorScreen addAuthor = new AddAuthorScreen();
        DesktopScreen.add(addAuthor);
        addAuthor.setVisible(true);
    }//GEN-LAST:event_addAuthorScreenActionPerformed

    private void addCategoryScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCategoryScreenActionPerformed
        AddCategoryScreen addCategory = new AddCategoryScreen();
        DesktopScreen.add(addCategory);
        addCategory.setVisible(true);
    }//GEN-LAST:event_addCategoryScreenActionPerformed

    private void addPCompanyScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPCompanyScreenActionPerformed
        AddPublishingCompanyScreen addPCompaney = new AddPublishingCompanyScreen();
        DesktopScreen.add(addPCompaney);
        addPCompaney.setVisible(true);
    }//GEN-LAST:event_addPCompanyScreenActionPerformed

    private void addClientScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClientScreenActionPerformed
        AddClientScreen addClient = new AddClientScreen();
        DesktopScreen.add(addClient);
        addClient.setVisible(true);
    }//GEN-LAST:event_addClientScreenActionPerformed

    private void addBookScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookScreenActionPerformed
        AddBookScreen addBook = new AddBookScreen();
        DesktopScreen.add(addBook);
        addBook.setVisible(true);
    }//GEN-LAST:event_addBookScreenActionPerformed

    private void addBuyScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBuyScreenActionPerformed
        AddBuyScreen addBuy = new AddBuyScreen();
        DesktopScreen.add(addBuy);
        addBuy.setVisible(true);
    }//GEN-LAST:event_addBuyScreenActionPerformed

    private void addPayMethodScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPayMethodScreenActionPerformed
        AddPaymentMethodScreen addPay = new AddPaymentMethodScreen();
        DesktopScreen.add(addPay);
        addPay.setVisible(true);
    }//GEN-LAST:event_addPayMethodScreenActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //Pega data e exibe data e hora do sistema
        Date dateSystem = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        textDate.setText(format.format(dateSystem));
        
        Timer timer = new Timer(1000, new hora());
        timer.start();
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }
    // Esta é uma simples classe para pegar a hora do sistema.
    class hora implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            textHora.setText(String.format("%1$tH:%1$tM:%1$tS", now));
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopScreen;
    private javax.swing.JMenuItem addAuthorScreen;
    private javax.swing.JMenuItem addBookScreen;
    private javax.swing.JMenuItem addBuyScreen;
    private javax.swing.JMenuItem addCategoryScreen;
    private javax.swing.JMenuItem addClientScreen;
    private javax.swing.JMenuItem addPCompanyScreen;
    private javax.swing.JMenuItem addPayMethodScreen;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel textDate;
    private javax.swing.JLabel textHora;
    // End of variables declaration//GEN-END:variables
}

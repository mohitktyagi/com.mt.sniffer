/**
 * 
 */
package com.mt.sniffer.ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.mt.sniffer.controler.SearchControler;
import com.mt.sniffer.file.handler.TokenLocation;
import com.mt.sniffer.runner.ResultSet;

/**
 *
 * @author mohitkumar
 */
public class SnifferFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public SnifferFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        tokenjTextField1 = new javax.swing.JTextField();
        tokenjLabel1 = new javax.swing.JLabel();
        dirjLabel2 = new javax.swing.JLabel();
        dirjTextField2 = new javax.swing.JTextField();
        browserjButton1 = new javax.swing.JButton();
        searchjButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultjTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tokenjLabel1.setText("Token : ");

        dirjLabel2.setText("Directory : ");

        browserjButton1.setText("Brows");
        browserjButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browserjButton1ActionPerformed(evt);
            }
        });

        searchjButton2.setText("Search");
        searchjButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchjButton2ActionPerformed(evt);
            }
        });

        resultjTextArea1.setColumns(20);
        resultjTextArea1.setRows(5);
        jScrollPane1.setViewportView(resultjTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tokenjLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dirjLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dirjTextField2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(browserjButton1))
                                    .addComponent(tokenjTextField1)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchjButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tokenjLabel1)
                    .addComponent(tokenjTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dirjTextField2)
                    .addComponent(browserjButton1)
                    .addComponent(dirjLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchjButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void browserjButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                                
        JFileChooser chooser = new JFileChooser();
	        chooser.setMultiSelectionEnabled(false);
	        chooser.setCurrentDirectory(new java.io.File("."));
	        chooser.setDialogTitle("choosertitle");
	        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        chooser.setAcceptAllFileFilterUsed(false);

	        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	         // System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
	         // System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
                  this.dirjTextField2.setText(chooser.getSelectedFile().getAbsolutePath());
	        } else {
	         // System.out.println("No Selection ");
	        }
    } 

    private void searchjButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    	this.resultjTextArea1.setText(null);
    	String token = this.tokenjTextField1.getText();
    	String dir = this.dirjTextField2.getText();
    	if(token == null || token.equals("")){
    		JOptionPane.showMessageDialog(null, "Toekn to be searched , must not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	if(dir == null || dir.equals("")){
    		JOptionPane.showMessageDialog(null, "Directory  to be searched in , must not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
    		return;
    		
    	}else{
    		
    		File file = new File(dir);
    		if(!file.exists()){
    			JOptionPane.showMessageDialog(null, "Directory  doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    	}
    	long startTime = System.currentTimeMillis();
    	ResultSet resultSet = SearchControler.search(dir, token);
    	StringBuilder sb = new StringBuilder();
    	 if(resultSet.hasNext()){
 			TokenLocation tlok=null;
 			while(resultSet.hasNext()){
 				tlok = resultSet.next();
 				//System.out.println(tlok.getFilePath());
 				sb.append(tlok.getFilePath()).append("\n");
 			}
 			
 		}else{
 			//System.out.println("Given token : "+args[0]+" not found in "+args[1]);
 			sb.append("Given token : "+token+" not found in "+dir);
 		}
    	 this.resultjTextArea1.setText(sb.toString());
    	 long endTime = System.currentTimeMillis();
    		double totalTimeInSec = (endTime-startTime)/1000.00;
    		System.out.println("Total time took "+totalTimeInSec +" Second(s)");
    } 

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
            java.util.logging.Logger.getLogger(SnifferFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SnifferFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SnifferFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SnifferFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SnifferFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton browserjButton1;
    private javax.swing.JLabel dirjLabel2;
    private javax.swing.JTextField dirjTextField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea resultjTextArea1;
    private javax.swing.JButton searchjButton2;
    private javax.swing.JLabel tokenjLabel1;
    private javax.swing.JTextField tokenjTextField1;
    // End of variables declaration                   
}
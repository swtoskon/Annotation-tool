/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spitablepanel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeArray.map;
import static jdk.nashorn.internal.objects.NativeDebug.map;


/**
 *
 * @author swtoskon
 */
public class RelevantFrame extends javax.swing.JFrame {
    /**
     * Creates new form RelevantFrame
     */
    private MainFrame f1;
    Map<String, Object> map ;
    Writer writer;
    public RelevantFrame(MainFrame f) {
        initComponents();
        setSize(300, 150);  
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setTitle("Check Relevance");
        f1=f;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jRadioButton1.setText("Relevant");

        jRadioButton2.setText("Irrelevant");

        jButton1.setText("Check");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Check");

        jRadioButton3.setText("Least Relevant");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jButton1)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addContainerGap(200, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            
        if(jRadioButton1.isSelected()){
          JOptionPane.showMessageDialog(this, "You choose Relevant");
      
          if(!f1.relevants.get(f1.counter).contains(f1.aList.get(f1.counter).get(f1.counter1))){
           f1.relevants.get(f1.counter).add(f1.aList.get(f1.counter).get(f1.counter1));
          }
          if(f1.irrelevants.get(f1.counter).contains(f1.aList.get(f1.counter).get(f1.counter1))){
           f1.irrelevants.get(f1.counter).remove(f1.aList.get(f1.counter).get(f1.counter1));
          }
            ArrayList<String> d = null;
           f1.getjLabel1().setText("You have found "+f1.relevants.get(f1.counter).size()+" candicate relevant documents for this theme");
           f1.getjButton5().setBackground(Color.green);
           f1.getjLabel4().setVisible(true);
              try {
                  f1.save(f1.counter,f1.counter1,1);
              } catch (IOException ex) {
                  Logger.getLogger(RelevantFrame.class.getName()).log(Level.SEVERE, null, ex);
              }
                  jRadioButton1.setSelected(false);
          jRadioButton2.setSelected(false); 
            this.dispose();
         }else if (jRadioButton2.isSelected()){
           JOptionPane.showMessageDialog(this, "You choose Irrelevant");
           if(!f1.irrelevants.get(f1.counter).contains(f1.aList.get(f1.counter).get(f1.counter1))){
           f1.irrelevants.get(f1.counter).add(f1.aList.get(f1.counter).get(f1.counter1));
          }
           if(f1.relevants.get(f1.counter).contains(f1.aList.get(f1.counter).get(f1.counter1))){
           f1.relevants.get(f1.counter).remove(f1.aList.get(f1.counter).get(f1.counter1));
          }
            f1.getjLabel1().setText("You have found "+f1.relevants.get(f1.counter).size()+" candicate relevant documents for this theme");
           f1.getjButton5().setBackground(Color.green);
           f1.getjLabel4().setVisible(true);
            
            try {
                f1.save(f1.counter,f1.counter1,2);
            } catch (IOException ex) {
                Logger.getLogger(RelevantFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
             jRadioButton2.setSelected(false); 
            jRadioButton1.setSelected(false);
         this.dispose();
         }
         else if (jRadioButton3.isSelected()){
             JOptionPane.showMessageDialog(this, "You choose Least Relevant");
             jRadioButton2.setSelected(false); 
            jRadioButton1.setSelected(false);
             if(!f1.relevants.get(f1.counter).contains(f1.aList.get(f1.counter).get(f1.counter1))){
           f1.relevants.get(f1.counter).add(f1.aList.get(f1.counter).get(f1.counter1));
          }
          if(f1.irrelevants.get(f1.counter).contains(f1.aList.get(f1.counter).get(f1.counter1))){
           f1.irrelevants.get(f1.counter).remove(f1.aList.get(f1.counter).get(f1.counter1));
          }
           f1.getjLabel1().setText("You have found "+f1.relevants.get(f1.counter).size()+" candicate relevant documents for this theme");
           f1.getjButton5().setBackground(Color.green);
           f1.getjLabel4().setVisible(true);
            try {
                f1.save(f1.counter,f1.counter1,3);
            } catch (IOException ex) {
                Logger.getLogger(RelevantFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
         this.dispose();
         }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) 
    { 
  
        // Create a new ArrayList 
        ArrayList<T> newList = new ArrayList<T>(); 
  
        // Traverse through the first list 
        for (T element : list) { 
  
            // If this element is not present in newList 
            // then add it 
            if (!newList.contains(element)) { 
  
                newList.add(element); 
            } 
        } 
  
        // return the new list 
        return newList; 
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
            java.util.logging.Logger.getLogger(RelevantFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelevantFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelevantFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelevantFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    // End of variables declaration//GEN-END:variables
}

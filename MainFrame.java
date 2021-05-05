/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spitablepanel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

 /*
 * @author swtoskon
 */

public class MainFrame extends javax.swing.JFrame {
       public static int counter =0;
      public  int counter1 =0;
      static int seconds = 0;
      static int minutes = 0;
      static int hours = 0;
      static int milliseconds = 0;
     public ArrayList<String> list=new ArrayList<String>();
     public ArrayList<ArrayList<String> > aList =  
                  new ArrayList<ArrayList<String> >(12);
     public static ArrayList<ArrayList <String >> relevants =  
                  new ArrayList<ArrayList <String> > (10);
     public static ArrayList<ArrayList <String >> irrelevants =  
                  new ArrayList<ArrayList <String> > (10);
    /**
     * Creates new form NewJFrame
     */
    public MainFrame() throws IOException, ParseException {
        
        initComponents();
          /* Create and display the form */
          //InputStream in1 = getClass().getResourceAsStream("newjson.json");
        //InputStreamReader isReader1 = new InputStreamReader(in1);
        File f = new File("output_data.json");
        String s= "input_data.json";
if(f.exists() && !f.isDirectory()) { 
    s="output_data.json";
  //System.out.println("ok");
}

         BufferedReader reader1 = new BufferedReader(new FileReader(s));
        StringBuffer sb1 = new StringBuffer();
           String str1;
      while((str1 = reader1.readLine())!= null){
         sb1.append(str1);
         sb1.append("\n");
                 
      }
         String jsonString= sb1.toString();
         
            
          for(int i=0; i<10; i++){
                  ArrayList<String> a = new ArrayList<String>(); 
                  relevants.add(a);
              
          }
            for(int i=0; i<10; i++){
                  ArrayList<String> b = new ArrayList<String>(); 
                  irrelevants.add(b);
              
          }
            for(int i=0; i<12; i++){
                  ArrayList<String> c = new ArrayList<String>(); 
                  aList.add(c);
              
          }
            
        // System.err.println(jsonString);
         JsonArray result=null;
          try {
              
                        Gson gson = new Gson();
             JsonObject body = gson.fromJson(jsonString, JsonObject.class);
               result = body.getAsJsonArray("data");
               JsonElement hours1 =body.get("hours");
               JsonElement minutes1 =body.get("minutes");
               JsonElement seconds1 =body.get("seconds");
                hours =hours1.getAsInt();
               seconds = seconds1.getAsInt();
               minutes = minutes1.getAsInt();
               //System.out.println(seconds1.getAsString());
             for (int i =0; i < result.size(); i++){
                  JsonObject firstResult = result.get(i).getAsJsonObject();
                 JsonElement doc = firstResult.get("document");
                 list.add(doc.getAsString());
                  JsonArray result2 = firstResult.getAsJsonArray("temp");
                  for(int j=0; j<result2.size(); j++){
                 JsonObject secondResult = result2.get(j).getAsJsonObject();
                 JsonObject thirdResult = secondResult.getAsJsonObject("result");
                 JsonElement select = thirdResult.get("selected");
                  JsonElement str=secondResult.get("document");
                  aList.get(i).add(str.getAsString());
                  if(select.getAsInt()==1 || select.getAsInt()==3){
                      relevants.get(i).add(str.getAsString());
                  }
                  else if(select.getAsInt()==2){
                      irrelevants.get(i).add(str.getAsString());
                  }
                 //System.out.println(str.getAsString());
             }}
            // System.out.println(relevants);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*
         File folder = new File("/Users/swtoskon/Evaluation/src/evaluation/files/");
          for (final File fileEntry : folder.listFiles()) {
            list.add(fileEntry.getName());
    }*/    
        /*  for(int i=0; i<10; i++){
                  System.out.println(aList.get(i));
              
          }
          */
       
               
         InputStream in = getClass().getResourceAsStream("files/"+list.get(0));
        InputStreamReader isReader = new InputStreamReader(in);
         BufferedReader reader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
      String str;
      while((str = reader.readLine())!= null){
         sb.append(str);
         sb.append("\n");
                 
      }
      str =sb.toString();
      str = str.replaceAll("\\<.*?\\>", "");
      label1.setText("1/5");
      jLabel5.setText(counter1+1 + "/12");
      InputStream in2 = getClass().getResourceAsStream("files/"+aList.get(0).get(0));
        InputStreamReader isReader2 = new InputStreamReader(in2);
         BufferedReader reader2 = new BufferedReader(isReader2);
        StringBuffer sb2 = new StringBuffer();
      String str2;
      while((str2 = reader2.readLine())!= null){
         sb2.append(str2);
         sb2.append("\n");
                 
      }
      str2 =sb2.toString();
      str2 = str2.replaceAll("\\<.*?\\>", "");
      if(relevants.get(counter).contains(aList.get(counter).get(0)) || irrelevants.get(counter).contains(aList.get(counter).get(0))){
      jButton5.setBackground(Color.green);
      }
      else{
          jButton5.setBackground(Color.red);
      }
      jTextArea2.read(new StringReader(str),null);
      jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setEditable(false);
        jTextArea1.read(new StringReader(str2),null);
      jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setEditable(false);
        jLabel1.setText("You have found "+relevants.get(counter).size()+" candicate relevant documents for this theme");
         Thread time = new Thread(){
         public  void run() {
         
                for (;;) {
               
                 
            
                try {
                    sleep(0,1);
                    if (milliseconds>1000){
                        milliseconds = 0;
                        seconds ++;
                    }
                    if (seconds>59){
                        seconds = 0;
                        minutes ++;
                    }
                    if (minutes>59){
                        minutes = 0;
                        seconds=0;
                        hours ++;
                    }
                   milliseconds++;
                   jLabel3.setText(String.format("%02d", hours)+ ":"+String.format("%02d", minutes)+":"+String.format("%02d", seconds));   
                } catch (Exception e) {
                }
       
            }
             
         }
     };
     time.start();
    }     

    public JLabel getjLabel4() {
        return jLabel4;
    }
        
        
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        label1 = new java.awt.Label();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("First Step of evaluation");
        setFocusable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                hand(evt);
            }
        });

        jButton1.setText("Previus");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Next");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane1.setViewportView(jTextArea2);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("CANDICATES RELEVANT DOCUMENTS");

        jButton3.setText("Previus Candicate Relevant Document");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Next Candicate Relevant Document");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Click for check relevance");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel3.setText("jLabel3");

        label1.setText("label1");

        jLabel5.setText("ok");
        jLabel5.setPreferredSize(new java.awt.Dimension(42, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jButton4)
                .addGap(37, 37, 37)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 503, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(76, 76, 76))
            .addGroup(layout.createSequentialGroup()
                .addGap(557, 557, 557)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(273, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
    JScrollBar horizontalScrollBar = jScrollPane1.getHorizontalScrollBar();
    verticalScrollBar.setValue(verticalScrollBar.getMinimum());
    horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());
    JScrollBar verticalScrollBar1 = jScrollPane2.getVerticalScrollBar();
    JScrollBar horizontalScrollBar1 = jScrollPane2.getHorizontalScrollBar();
    verticalScrollBar1.setValue(verticalScrollBar1.getMinimum());
    horizontalScrollBar1.setValue(horizontalScrollBar1.getMinimum());
        counter--;
       counter1=0;
       jLabel5.setText(counter1+1 + "/12");
       jButton3.setEnabled(false);
       jButton4.setEnabled(true);
        InputStream in = getClass().getResourceAsStream("files/" + list.get(counter)); 
        InputStream in1 = getClass().getResourceAsStream("files/" + aList.get(counter).get(0)); 
      String str=retText(in);
       String str1=retText(in1);
        try {
            jTextArea2.read(new StringReader(str),null);
            jTextArea1.read(new StringReader(str1),null);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setEditable(false);
        label1.setText(counter+1 + "/5");
      jLabel1.setText("You have found "+relevants.get(counter).size()+" candicate relevant documents for this theme");
          if(counter==0){
             jButton1.setEnabled(false);
             
         }
         else{
             jButton1.setEnabled(true);
             if(jButton2.isEnabled()==false){
                 jButton2.setEnabled(true);
             }
         }
          if( relevants.get(counter).contains(aList.get(counter).get(counter1)) || irrelevants.get(counter).contains(aList.get(counter).get(counter1))){
            getjLabel4().setVisible(true);
             jButton5.setBackground(Color.green);
         }
         else{
             getjLabel4().setVisible(false);
              jButton5.setBackground(Color.red);
         }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
    JScrollBar horizontalScrollBar = jScrollPane1.getHorizontalScrollBar();
    verticalScrollBar.setValue(verticalScrollBar.getMinimum());
    horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());
    JScrollBar verticalScrollBar1 = jScrollPane2.getVerticalScrollBar();
    JScrollBar horizontalScrollBar1 = jScrollPane2.getHorizontalScrollBar();
    verticalScrollBar1.setValue(verticalScrollBar1.getMinimum());
    horizontalScrollBar1.setValue(horizontalScrollBar1.getMinimum());
        counter++;
        counter1=0;
        jLabel5.setText(counter1+1 + "/12");
         jButton3.setEnabled(false);
         jButton4.setEnabled(true);
         InputStream in = getClass().getResourceAsStream("files/" + list.get(counter));
         InputStream in1 = getClass().getResourceAsStream("files/" + aList.get(counter).get(0));
         String str=retText(in);
       String str1=retText(in1);
        try {
            jTextArea2.read(new StringReader(str),null);
            jTextArea1.read(new StringReader(str1),null);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setEditable(false);
        label1.setText(counter+1 + "/5");
        jLabel1.setText("You have found "+relevants.get(counter).size()+" candicate relevant documents for this theme");
        if(counter==4){
             jButton2.setEnabled(false);
             if(jButton1.isEnabled()==false){
                 jButton1.setEnabled(true);
             }
         }
         else{
             jButton2.setEnabled(true);
             jButton1.setEnabled(true);
         }
      if( relevants.get(counter).contains(aList.get(counter).get(counter1)) || irrelevants.get(counter).contains(aList.get(counter).get(counter1))){
            getjLabel4().setVisible(true);
             jButton5.setBackground(Color.green);
         }
         else{
             getjLabel4().setVisible(false);
              jButton5.setBackground(Color.red);
         }
    }//GEN-LAST:event_jButton2ActionPerformed

    public JButton getjButton5() {
        return jButton5;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }
     private String retText(InputStream in){
         InputStreamReader isReader = new InputStreamReader(in);
         BufferedReader reader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
         String str;
        try {
            while((str = reader.readLine())!= null){
                sb.append(str);
                sb.append("\n");
                
            } } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
      str =sb.toString();
      str = str.replaceAll("\\<.*?\\>", "");
       return str;
    
}
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      
    JScrollBar verticalScrollBar1 = jScrollPane2.getVerticalScrollBar();
    JScrollBar horizontalScrollBar1 = jScrollPane2.getHorizontalScrollBar();
    verticalScrollBar1.setValue(verticalScrollBar1.getMinimum());
    horizontalScrollBar1.setValue(horizontalScrollBar1.getMinimum());
        
        counter1--;
        InputStream in = getClass().getResourceAsStream("files/" + aList.get(counter).get(counter1)); 
      String str=retText(in);
      try {
             jTextArea1.read(new StringReader(str),null);
         } catch (IOException ex) {
             Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
      if(counter1==0){
             jButton3.setEnabled(false);
             
         }
         else{
             jButton3.setEnabled(true);
             if(jButton4.isEnabled()==false){
                 jButton4.setEnabled(true);
             }
         }
      jLabel1.setText("You have found "+relevants.get(counter).size()+" candicate relevant documents for this theme");
      //System.err.println(relevants.get(counter));
       if( relevants.get(counter).contains(aList.get(counter).get(counter1)) || irrelevants.get(counter).contains(aList.get(counter).get(counter1))){
            getjLabel4().setVisible(true);
             jButton5.setBackground(Color.green);
         }
         else{
             getjLabel4().setVisible(false);
              jButton5.setBackground(Color.red);
         }
        jLabel5.setText(counter1+1 + "/12");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
           JScrollBar verticalScrollBar1 = jScrollPane2.getVerticalScrollBar();
    JScrollBar horizontalScrollBar1 = jScrollPane2.getHorizontalScrollBar();
    verticalScrollBar1.setValue(verticalScrollBar1.getMinimum());
    horizontalScrollBar1.setValue(horizontalScrollBar1.getMinimum());
        counter1++;
        InputStream in = getClass().getResourceAsStream("files/" + aList.get(counter).get(counter1)); 
         String str=retText(in);
          
        
         try {
             jTextArea1.read(new StringReader(str),null);
         } catch (IOException ex) {
             Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
          if(counter1==11){
             jButton4.setEnabled(false);
             if(jButton3.isEnabled()==false){
                 jButton3.setEnabled(true);
             }
         }
         else{
             jButton4.setEnabled(true);
             jButton3.setEnabled(true);
         }
         jLabel1.setText("You have found "+relevants.get(counter).size()+" candicate relevant documents for this theme");
       
      if( relevants.get(counter).contains(aList.get(counter).get(counter1)) || irrelevants.get(counter).contains(aList.get(counter).get(counter1))){
            getjLabel4().setVisible(true);
             jButton5.setBackground(Color.green);
         }
         else{
             getjLabel4().setVisible(false);
              jButton5.setBackground(Color.red);
         }
       jLabel5.setText(counter1+1 + "/12");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
              RelevantFrame rf = new RelevantFrame(this);
         
    }//GEN-LAST:event_jButton5ActionPerformed

    private void hand(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_hand
       int a=JOptionPane.showConfirmDialog(this,"Are you sure?"); 
       if(a==JOptionPane.YES_OPTION){  
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
} 
       else if(a==JOptionPane.NO_OPTION){
           setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       }
    }//GEN-LAST:event_hand


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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    
          
        java.awt.EventQueue.invokeLater(new Runnable() {
            
    
         public void run() {
             try {
                 new MainFrame().setVisible(true);
                 
             } catch (IOException ex) {
                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ParseException ex) {
                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
                
            }
         
        });
    }
public static String readJsonFile(String filePath) {
    String jsonData = "";
    BufferedReader br = null;
    try {
        String line;
        br = new BufferedReader(new FileReader(filePath));
        while ((line = br.readLine()) != null) {
            jsonData += line + "\n";
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (br != null) {
                br.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    return jsonData;
}

public  void save(int counter,int counter1, int k) throws IOException{
      /* Create and display the form */
            File f = new File("output_data.json");
        String s= "input_data.json";
if(f.exists() && !f.isDirectory()) { 
    s="output_data.json";
  //System.out.println("ok");
}
        BufferedReader reader1 = new BufferedReader(new FileReader(s));
        StringBuffer sb1 = new StringBuffer();
           String str3;
      while((str3 = reader1.readLine())!= null){
         sb1.append(str3);
         sb1.append("\n");
                 
      }
         String jsonString= sb1.toString();
         //System.err.println(jsonString);
    try {
            Gson gson = new Gson();
            JsonObject body = gson.fromJson(jsonString, JsonObject.class);
             JsonArray result = body.getAsJsonArray("data");
             body.addProperty("seconds", seconds);
             body.addProperty("hours", hours);
             body.addProperty("minutes", minutes);
            //System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(body));
            for (int i =0; i < result.size(); i++){
              
                 JsonObject firstResult = result.get(i).getAsJsonObject();
                  JsonArray result2 = firstResult.getAsJsonArray("temp");
                  for(int j=0; j<result2.size(); j++){
                 JsonObject secondResult = result2.get(j).getAsJsonObject();
                 JsonObject thirdResult = secondResult.getAsJsonObject("result");
                 JsonElement select = thirdResult.get("selected");
                  JsonElement str=secondResult.get("document");
                 JsonElement str1=firstResult.get("document");
                  
                  //System.err.println(relevants.get(counter));
              
              if(i==counter && j==counter1){
                  if(relevants.get(i).contains(str.getAsString())) {
                System.err.println(k);
                //System.err.println(str.getAsString());
               thirdResult.addProperty("selected", k);
              }             
              if(irrelevants.get(i).contains(str.getAsString()) ){
                  
               thirdResult.addProperty("selected", k);
              }
              
              }
              
                  }
            } 
            try (Writer writer = new FileWriter("output_data.json")) {
                 Gson gson1 = new GsonBuilder()
                .setPrettyPrinting()
                .create(); 
   gson1.toJson(body, writer);
}
              //System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(body));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    
    
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables
}

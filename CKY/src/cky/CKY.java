/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cky;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javafx.scene.control.ComboBox;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author thien
 */
public class CKY extends JFrame{
    JPanel panel_sentence = new JPanel();
    JLabel label_sentence = new JLabel("Sentence: ");
    JTextField text_sentence = new JTextField("");
   // public static JComboBox box_sentence;
    JButton add = new JButton("Add");
    JButton start = new JButton("Run");
    JButton _tree = new JButton("Tree"); 
    JLabel label_cnf = new JLabel("CNF: ");
    JTextArea text_cnf = new JTextArea();
    JPanel panel_cky = new JPanel();
    public static JTextPane [][]label_cky = {{},{}};
    String [][]cky = {};
    String sentences = "";
    public static String []words = {};
    boolean _check = false;
    
    
  //  public static String pathText = "text/text.txt";
   // public static String pathSentence = "text/sentences.txt";
    public static String pathCNF = "text/XSCNF.txt";
    
    
    // schedules the task to be run in an interval 
    
    /**
     * @param args the command line arguments
     */
    public CKY(){
        this.setTitle("Demo thuật toán CKY");
        this.setSize(1018, 1040);
        this.setLocation(400, 0);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public List<String> check(String a){
        List<String> ref = new Vector<String>();
        String []w = a.split(" ");
        for(String t : w){
            if(!t.equals("/")) ref.add(t);
        }
        return ref;
    }
    public static List<String> kq(String a){
        String []words = a.split(" ");
        List<String> e = new ArrayList<String>();
        for(int i = 0; i < words.length; i++){
            if(!(words[i].equals("->"))){
                e.add(words[i]);
            }
        }
        return e;
    }
    public static String tl(String[] a, String b){
        String []words = b.split(" ");
        String t = "";
        for(int i = 0; i < a.length; i++){
            if(words.length == 1 && kq(a[i]).size() == 2 && kq(a[i]).get(1).equals(b)){
                if(t != "") t += " / ";
                t += kq(a[i]).get(0);
            }
            if(words.length > 1 && kq(a[i]).size() - 1 == words.length){
                String k = kq(a[i]).get(1) + " " + kq(a[i]).get(2);
                if(k.equals(b)){
                    t += kq(a[i]).get(0);
                }
            }
        }
        return t;
    }
    public List<String> readFile(String path){
        List<String> res = new Vector<String>();
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(path));
            String str;
            while ((str = in.readLine()) != null)
                res.add(str);
            in.close();
        } catch (IOException e) {
        }
        return res;
    }
    public static String find(String path, String text){
        String t = "";
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(path));
            String str;
            while ((str = in.readLine()) != null)
                if(str.equals(text)){
                    String s;
                    while(!(s = in.readLine()).equals("end")){
                        t += s + "\n"; 
                    }
                }
            in.close();
        } catch (IOException e) {
        }
        return t;
    }
    public void initGUI(){
        label_sentence.setSize(70,10);
        label_sentence.setLocation(50,20);
        label_sentence.setVisible(true);
        panel_sentence.add(label_sentence);
        
        text_sentence.setSize(700,30);
        text_sentence.setLocation(130,10);
        text_sentence.setVisible(true);
        panel_sentence.add(text_sentence);
        
       // repaint();
        List<String> e = readFile(pathCNF);
        String t = "";
        for(int i = 0; i < e.size(); i++)
            t += e.get(i) + "\n";
        text_cnf.setText(t);
        text_cnf.setBorder(BorderFactory.createLineBorder(Color.black));
        text_cnf.setBounds(130, 50, 700, 210);
        JScrollPane pane =  new JScrollPane(text_cnf);
        pane.setBounds(130, 50, 700, 210);
        text_cnf.setPreferredSize(new Dimension(100,text_cnf.getHeight()+ 2000));
        panel_sentence.add(pane);
        repaint();
        
        /*box_sentence = new JComboBox();
        List<String> e = readFile(pathSentence);
        String []e1 = new String[e.size()];
        for(int i = 0; i < e.size(); i++){
            e1[i] = e.get(i);
            box_sentence.addItem(e.get(i));
        }
        box_sentence.setEditable(true);
        box_sentence.setSelectedItem(0);
        box_sentence.setBounds(130, 10, 700, 30);
        box_sentence.setBorder(BorderFactory.createLineBorder(Color.black));  
        box_sentence.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                panel_cky.removeAll();
               // text_cnf.setText(find(pathText, (String) box_sentence.getItemAt
                 //       (box_sentence.getSelectedIndex())));
                List<String> temp = readFile(pathCNF); 
                String temp1 = "";
                for(int i = 0; i < temp.size(); i++)
                    temp1 += temp.get(i) + "\n";
                text_cnf.setText(temp1);
            }
        });
        panel_sentence.add(box_sentence);
        repaint();*/
        
        add.setSize(60,30);
        add.setLocation(850,10);
        add.setVisible(true);
        panel_sentence.add(add);
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new add().show();
            }
        });
        
     /*   _tree.setSize(60,30);
        _tree.setLocation(850,50);
        _tree.setVisible(true);
        panel_sentence.add(_tree);
        _tree.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Click tree");
                JFrame f = new JFrame();
                f.getContentPane().add(new tree());
                f.setSize(300, 200);
                f.setVisible(true);
            }
        });*/
        
        label_cnf.setSize(70,10);
        label_cnf.setLocation(50,50);
        label_cnf.setVisible(true);
        panel_sentence.add(label_cnf);
        
        panel_sentence.setSize(1000,300);
        panel_sentence.setLocation(0,0);
        panel_sentence.setVisible(true);
        panel_sentence.setLayout(null);
        
        start.setSize(100,30);
        start.setLocation(400,265);
        start.setVisible(true);
        panel_sentence.add(start);
        
        panel_cky.setSize(1000,700);
        panel_cky.setLocation(0,300);
        panel_cky.setVisible(true);
        panel_cky.setLayout(null);
          
        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              /*  if (box_sentence.getSelectedIndex() != -1) {                     
                    sentences = (String) box_sentence.getItemAt
                        (box_sentence.getSelectedIndex());             
                }*/
                panel_cky.removeAll();
                sentences = text_sentence.getText();
                initGUICKY();
                
                String temp = text_cnf.getText();
                String []cnf = temp.split("\n");
                
                String []words_1 = sentences.split(" ");
                cky = new String[words_1.length][words_1.length];
                for(int i = 0; i < words_1.length; i++){
                    for(int j = 0; j < words_1.length; j++)
                        cky[i][j] = "";
                }
                Thread t1 = new Thread(){
                    public void run(){
                        for(int i = 0; i < words_1.length; i++){
                            cky[i][i] = tl(cnf, words_1[i]);
                            label_cky[i + 1][i + 1].setBackground(Color.red);
                             try{
                                  sleep(500);
                            }catch ( InterruptedException ea){}; 
                            label_cky[i+1][i+1].setText(cky[i][i] + "\n" + "[" + Integer.toString(i) + ", " + Integer.toString(i + 1) + "]");
                            label_cky[i + 1][i + 1].setBackground(Color.white);
                            for(int j = i - 1; j >= 0; j--){
                                for(int k = j; k <  i; k++){
                                    System.out.println((j + 1) + " "+ (k + 1)+" "+ (i + 1));
                                    List<String> t1 = check(cky[j][k]);
                                    List<String> t2 = check(cky[k+1][i]);
                                    label_cky[j + 1][k + 1].setBackground(Color.yellow);
                                            label_cky[k + 2][i + 1].setBackground(Color.yellow);
                                            label_cky[j + 1][i + 1].setBackground(Color.red);
                                    for(int p = 0; p < t1.size(); p++){
                                        for(int q = 0; q < t2.size(); q++){
                                            
                                            String temp_1 = t1.get(p) + " " + t2.get(q);
                                            
                                            try{
                                                sleep(500);
                                            }catch(InterruptedException ea){};
                                            if(tl(cnf, temp_1) != ""){
                                                label_cky[j + 1][k + 1].setBackground(Color.green);
                                                label_cky[k + 2][i + 1].setBackground(Color.green);
                                                label_cky[j + 1][i + 1].setBackground(Color.green);
                                                try{
                                                    sleep(500);
                                                }catch(InterruptedException ea){};
                                                cky[j][i] = tl(cnf, temp_1);
                                                label_cky[j+1][i+1].setText(cky[j][i] + "\n" + "[" + Integer.toString(j) + ", " + Integer.toString(k + 1) + "] + " + "[" + Integer.toString(k +1) + ", " + Integer.toString(i + 1) + "]");
                                            
                                            }
                                            
                                            if(tl(cnf, temp_1) == "" && cky[j][i] == "") cky[j][i] = "";
                                            
                                        }
                                    }
                                    label_cky[j + 1][i + 1].setBackground(Color.white);
                                    label_cky[j + 1][k + 1].setBackground(Color.white);
                                            label_cky[k + 2][i + 1].setBackground(Color.white);
                                }
                            }
                        }
                    }
                };
                t1.start();
            }
        });
        
        repaint();
        this.add(panel_sentence);
        this.add(panel_cky);
    }
    public void initGUICKY(){
        
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        
        words = sentences.split(" ");

        label_cky = new JTextPane[words.length + 1][words.length + 1];
        
        int w = panel_cky.getWidth() / (words.length + 1);
        int h = panel_cky.getHeight() / (words.length + 1);
        int x = 0;
        int y = 0;

        for(int i = 0; i <= words.length; i++){
            for(int j = 0; j <= words.length; j++){
                label_cky[i][j] = new JTextPane();
                label_cky[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                label_cky[i][j].setSize(w, h);
                label_cky[i][j].setLocation(x,y);
                label_cky[i][j].setBackground(Color.white);
                label_cky[i][j].setVisible(true);
                label_cky[i][j].setParagraphAttributes(attribs, true);
                label_cky[i][j].setEditable(false);

                x += w;

                if(i == 0){
                    String t = "";
                    if(j !=0 )
                        t +=words[j-1] + "\n";
                    t += Integer.toString(j);
                    label_cky[i][j].setText(t);
                    label_cky[i][j].setBackground(Color.gray);
                }
                if(j == 0 && i > 0){
                    label_cky[i][j].setText(Integer.toString(i - 1));
                    label_cky[i][j].setBackground(Color.gray);
                }
                panel_cky.add(label_cky[i][j]);
            }
            y += h;
            x = 0;
        }
      repaint();
    }  
    public static void main(String[] args) {
        
        new CKY().initGUI();
    }
}

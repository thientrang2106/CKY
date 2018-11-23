/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cky;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author thien
 */
public class tree extends JPanel {
    int len = CKY.words.length;
    public String[] getT(String b){
        String []a = b.split("\n");
        return a;
    }
    public List<Integer> getV(String b){
        String []a = b.split("\n");
        List<Integer> num = new Vector<Integer>();
        Pattern pattern = Pattern.compile("\\d+"); 
        
        for(int i = 1; i < a.length; i+=2){
            String t = a[i];
           // System.out.println(t);
            for(int j = 0; j < t.length(); j++){
               // System.out.println(t.charAt(j));
                Matcher matcher = pattern.matcher(String.valueOf(t.charAt(j)));
                if(matcher.matches()){
                    if(Integer.parseInt(String.valueOf(t.charAt(j))) >= 0 && Integer.parseInt(String.valueOf(t.charAt(j))) <= len) 
                    num.add(Integer.parseInt(String.valueOf(t.charAt(j))));
                }
                
            }
            
        }
        return num;
    }
    public void drawNote(Graphics2D g2,String b, int x, int y){
        String []a = getT(b);
        String t = a[0];
            
        if(a.length > 2){
            for(int i = 2; i < a.length; i +=2){
                t += " / " + a[i]; 
            }
        }
        
        g2.drawOval(x, y, 20, 20);
        g2.drawString(t, x + 10, y + 15);
    }
    public void ve(Graphics2D g2){
        drawNote(g2, CKY.label_cky[1][len].getText(), 50, 10);
        
        List<Integer> num = getV(CKY.label_cky[1][len].getText());
        for(int i = 0; i < num.size(); i++){
            
        }
    }
    public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
      Font font = new Font("Serif", Font.PLAIN, 11);
      g2.setFont(font);
      //g2.drawString("Text", 40, 120);
      ve(g2);
   }
   public static void main(String[] args) {
      
   }
}

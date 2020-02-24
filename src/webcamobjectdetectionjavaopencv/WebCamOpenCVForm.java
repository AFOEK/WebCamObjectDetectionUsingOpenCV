package webcamobjectdetectionjavaopencv;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.lang.Math;
import java.io.RandomAccessFile;

public class WebCamOpenCVForm extends javax.swing.JFrame 
{
    //private JPanel contentPane1, contentPane2;
    private VideoCap vc;
    private BufferedImage Original, Process, FirstFrame;
    private short R, G, B;
    private int Temp, PixelColor, size;
    private byte buff[];
    private RandomAccessFile f;
    
    public WebCamOpenCVForm()
    {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(0,0,1280,720);
        /*contentPane1= new JPanel();
        contentPane2= new JPanel();
        contentPane1.setBorder(new EmptyBorder(0,0,0,0));
        contentPane2.setBorder(new EmptyBorder(0,0,0,0));
        contentPane1.setLocation(0, 0);
        contentPane2.setLocation(0,1900);
        setContentPane(contentPane1);
        setContentPane(contentPane2);
        contentPane1.setSize(640, 480);
        contentPane2.setSize(640, 480);*/
        this.setSize(1280, 800); 
        Original=new BufferedImage(640, 480, BufferedImage.TYPE_3BYTE_BGR);
        Process=new BufferedImage(640, 480, BufferedImage.TYPE_3BYTE_BGR);
        FirstFrame=new BufferedImage(640, 480, BufferedImage.TYPE_3BYTE_BGR);
        size=Process.getHeight()*Process.getWidth()*3;
        buff=new byte[size];
        vc= new VideoCap();
        FirstFrame=CopyBufferedImage(vc.getOneFrame());
        new MyThread().start();
        //open file sama try new
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        int x,y, alamat;
        alamat=0;
        for(y=0;y<Process.getHeight();y++)
        {
            for(x=0;x<Process.getWidth();x++)
            {
                PixelColor=Process.getRGB(x, y)+16777216;
                R=(short)(PixelColor/65536);
                Temp=(int)(PixelColor%65536);
                G=(short)(Temp/256);
                B=(short)(Temp%256);
                buff[alamat]=(byte)B; alamat++;
                buff[alamat]=(byte)G; alamat++;
                buff[alamat]=(byte)R; alamat++;
            }
        }
        try{
            f=new RandomAccessFile("/home/afoek/Saved.raw","rw");
            /*f.write(Process.getHeight());
            f.write(Process.getWidth());*/
            f.write(buff,0,size);
            f.close();
        }
        catch(IOException ex)
        {
            
        }
    }//GEN-LAST:event_formMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        //fclose() pake try
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) 
    {
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
            java.util.logging.Logger.getLogger(WebCamOpenCVForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WebCamOpenCVForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WebCamOpenCVForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WebCamOpenCVForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try
                {
                    WebCamOpenCVForm frame = new WebCamOpenCVForm();
                    frame.setVisible(true);
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }
    public void paint(Graphics g)
    {
        /*g=contentPane1.getGraphics();
        g=contentPane2.getGraphics();*/
        Original=CopyBufferedImage(vc.getOneFrame());
        //Process=CopyBufferedImage(vc.getOneFrame());
        g.drawImage(Original,0,0,this);
        g.drawImage(Process,645,0,this);
    }
    
    public BufferedImage CopyBufferedImage(BufferedImage bi)
    {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster rt = bi.copyData(null);
        return new BufferedImage(cm, rt, isAlphaPremultiplied,null);
    }
        
    class MyThread extends Thread
    {
        @Override
        public void run()
        {
            int z, y, x, Color, FirstColor, ProcessColor, TempColor;
            boolean diff;
            short Threshold=30, R, G, B, BF, GF, RF, BP, GP, RP, AF, AP;
            for(;;){
                Process=CopyBufferedImage(Original);
                //Original=CopyBufferedImage(Process);
//                for(y=0;y<Process.getHeight();y++)
//                {
//                    z=Process.getWidth()-1;
//                    for(x=0;x<Process.getWidth()/2;x++)
//                    {
//                        Color=Process.getRGB(x, y);
//                        Process.setRGB(x, y, Process.getRGB(z, y));
//                        Process.setRGB(z,y,Color);
//                        z--;
//                    }
//                }
                diff=false;
                for(y=0;y<Process.getHeight();y++)
                {
                    for(x=0;x<Process.getWidth();x++)
                    {
                        FirstColor=FirstFrame.getRGB(x, y)+16777216;
                        ProcessColor=Original.getRGB(x, y);
                        AF=(short)(FirstColor/16777216);
                        FirstColor%=16777216;
                        BF=(short)(FirstColor/65536);
                        TempColor=FirstColor%65536;
                        GF=(short)(TempColor/256);
                        RF=(short)(TempColor%256);
                        BF=(short)((BF+256)%256);
                        GF=(short)((GF+256)%256);
                        RF=(short)((RF+256)%256);
                        
                        AP=(short)(ProcessColor/16777216);
                        ProcessColor%=16777216;
                        BP=(short)(ProcessColor/65536);
                        TempColor=ProcessColor%65536;
                        GP=(short)(TempColor/256);
                        RP=(short)(TempColor%256);
                        BP=(short)((BP+256)%256);
                        GP=(short)((GP+256)%256);
                        RP=(short)((RP+256)%256);
                        
                        
                        /*RF=(short)(FirstColor/65536);
                        TempColor=(int)(FirstColor%65536);
                        GF=(short)(TempColor/256);
                        BF=(short)(TempColor%256);
                        
                        TempColor=ProcessColor/16777216;
                        RP=(short)(ProcessColor/65536);
                        TempColor=(int)(ProcessColor%65536);
                        GP=(short)(TempColor/256);
                        BP=(short)(TempColor%256);*/
                        
                        R=(short) Math.abs(RF-RP); if (R<0) R+=255;
                        G=(short) Math.abs(GF-GP); if (G<0) G+=255;
                        B=(short) Math.abs(BF-BP); if (B<0) B+=255;
                        if(R>Threshold || G>Threshold || B>Threshold)
                        {
                            diff=true;
                        }else{
                            //TempColor=B*65536+G*256+R; //this temp color are original
                            TempColor=16777215;//this temp color is white
                            Process.setRGB(x, y, TempColor);
                        }
                        //buffernya sini
                    }
                }
                repaint();
                try
                {
                    //write file
                    Thread.sleep(50);
                }
                catch (InterruptedException ie)
                {
                
                }
            }
        }
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

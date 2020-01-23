package webcamobjectdetectionjavaopencv;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class WebCamOpenCVForm extends javax.swing.JFrame 
{
    private JPanel contentPane1, contentPane2;
    private VideoCap vc;
    private BufferedImage Original, Process;
    
    public WebCamOpenCVForm()
    {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,1280,720);
        contentPane1= new JPanel();
        contentPane2= new JPanel();
        contentPane1.setBorder(new EmptyBorder(0,0,0,0));
        contentPane2.setBorder(new EmptyBorder(0,0,0,0));
        contentPane2.setLocation(1900,0);
        setContentPane(contentPane1);
        setContentPane(contentPane2);
        
        Original=new BufferedImage(640, 480, BufferedImage.TYPE_3BYTE_BGR);
        Process=new BufferedImage(640, 480, BufferedImage.TYPE_3BYTE_BGR);
        vc= new VideoCap();
        new MyThread().start();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        g=contentPane1.getGraphics();
        g=contentPane2.getGraphics();
        Original=CopyBufferedImage(vc.getOneFrame());
        Process=CopyBufferedImage(vc.getOneFrame());
        g.drawImage(Original,0,0,contentPane1);
        g.drawImage(Process,0,0,contentPane2);
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
            for(;;){
                Process=CopyBufferedImage(Original);
                //Original=CopyBufferedImage(Process);
                //buat program deteksi di sini
                repaint();
                try
                {
                    Thread.sleep(30);
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

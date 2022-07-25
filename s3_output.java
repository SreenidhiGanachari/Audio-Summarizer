package s3;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class Swing implements ActionListener {
    JFrame frame=new JFrame();
    JButton button=new JButton("Get Presigned Amazon S3 Object");

    Swing(){
        prepareGUI();
        buttonProperties();
    }

    public void prepareGUI(){
        frame.setTitle("My Window");
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setBounds(200,200,400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void buttonProperties(){
        button.setBounds(130,200,200,100);
        frame.add(button);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get a presigned PDF from an Amazon S3 bucket.
        try {
           
            URL url = new URL("Enter URL");
            InputStream in;
            in = url.openStream();
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Sreenidhi\\my-project\\audio-project.txt");
            System.out.println("reading from resource and writing to file");
            int length;
            byte[] buffer = new byte[1024];// buffer for portion of data from connection
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            in.close();
            System.out.println("File downloaded");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

public class s3{
    public static void main(String[] args) {
        new Swing();
    }
}
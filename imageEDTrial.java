//package JavaDB_001;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.HTMLDocument.Iterator;
import java.awt.image.BufferedImage; import java.awt.image.DataBufferByte; import java.io.File;
import javax.imageio.ImageIO;



public class imageEDTrial extends JFrame{
	int flag=0;
    JButton button1 ;
    JTextField text1;
    JTextField text2;
    JTextField text3;
    JTextField text4;
    JButton button4;
    JButton button3;
    JLabel label;
    JButton button2;
    public imageEDTrial(){
    super("Set Picture Into A JLabel Using JFileChooser In Java");
    button1 = new JButton("Browse");
text1=new JTextField();
text1.setBounds(500, 300, 90, 30);
text2=new JTextField();
text2.setBounds(500, 270, 90, 30);

text3=new JTextField();
text3.setBounds(500, 240, 90, 30);
text4=new JTextField();
    button1.setBounds(90,300,100,40);
    button4=new JButton("Compress");
    button2=new JButton("Resize");
    button2.setBounds(200,270,100,40);
    button4.setBounds(200,350,100,40);
    button3=new JButton("Crop");
    button3.setBounds(200, 310, 100, 40);
    label = new JLabel();
    label.setBounds(10,10,670,250);
    add(button1);
    add(text1);
    add(text2);
    add(text3);
    String getValueL=text1.getText();
    String getValueB=text2.getText();
    String getValueH=text3.getText();
  
 // add(text1);
    //add(button2);
    add(label);
    
    
    button1.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        
          JFileChooser file = new JFileChooser();
          file.setCurrentDirectory(new File(System.getProperty("user.home")));
          //filter the files
          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpeg","gif","png", "jpg");
          file.addChoosableFileFilter(filter);
          int result = file.showSaveDialog(null);
           //if the user click on save in Jfilechooser
          if(result == JFileChooser.APPROVE_OPTION){
              File selectedFile = file.getSelectedFile();
              String path = selectedFile.getAbsolutePath();
              label.setIcon(ResizeImage(path));
              add(button2);
              add(button3);
              add(button4);
              BufferedImage img=null;
             // add(text1);
              
              button3.addActionListener(new ActionListener() {

                  public void actionPerformed(ActionEvent e) {
                	  BufferedImage img = null;
                	  try {
  						img = ImageIO.read(new File(path));
  						File inputFile=new File(path);
  						BufferedImage inputImage=ImageIO.read(inputFile);
  						//inputImage.
  						BufferedImage croppedImage = img.getSubimage(50, 50, 100, 100);
  						JLabel picLabel = new JLabel(new ImageIcon(croppedImage));
						   JOptionPane.showMessageDialog(null, picLabel, "About", JOptionPane.PLAIN_MESSAGE, null);
  						  // BufferedImage tempJPG = null;
  						   
  						   img=ImageIO.read(inputFile);
  						   //img = ImageIO.read(new File(path+listOfFiles[i].getName()));
  						  
  						  // Image newImg = tempJPG.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
  						
  					//	BufferedImage imgNew= new BufferedImage(getValueL, getValueB,)
  						img.createGraphics();
  						
  						//BufferedImage resizeImage=resize(img);
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} 

                           
                  }
                  }
              
              );
              button4.addActionListener(new ActionListener() {

                  public void actionPerformed(ActionEvent e) {
                	  BufferedImage img = null;
//                	  ArrayList al=new ArrayList();
                	  try {
						img = ImageIO.read(new File(path));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						File inputFile=new File(path);
						BufferedImage inputImage;
						try {
							inputImage = ImageIO.read(inputFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				//		main2(null);
						java.util.Iterator<ImageWriter> writers; 
						BufferedImage bufferedImage=null; 
						ImageOutputStream imageOutputStream=null; 
						ImageWriter imageWriter; 
						ImageWriteParam pngparams; 

						// Read an image from the disk (First Argument) 
						
						// bufferedImage = ImageIO.read(new File(args[0])); 
						try {
							bufferedImage = ImageIO.read(new File(path));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						// Get all the PNG writers 
						//writers = ImageIO.getImageWritersByFormatName("jpg"); 

					writers = ImageIO.getImageWritersByFormatName("jpg"); 

						// Fetch the first writer in the list 
						imageWriter = (ImageWriter) writers.next(); 


						// Just to confirm that the writer in use is CLibPNGImageWriter 
						System.out.println("\n Writer used : " 
						+ imageWriter.getClass().getName() + "\n"); 

						// Specify the parameters according to those the output file will be 
						// written 

						// Get Default parameters 
						pngparams = imageWriter.getDefaultWriteParam(); 

						// Define compression mode 
						pngparams 
						.setCompressionMode(javax.imageio.ImageWriteParam.MODE_EXPLICIT); 

						// Define compression quality 
						pngparams.setCompressionQuality(0.5F); 

						// Define progressive mode 
						pngparams 
						.setProgressiveMode(javax.imageio.ImageWriteParam.MODE_COPY_FROM_METADATA); 

						// Deine destination type - used the ColorModel and SampleModel of the 
						// Input Image 
						pngparams.setDestinationType(new ImageTypeSpecifier(bufferedImage 
						.getColorModel(), bufferedImage.getSampleModel())); 

						// Set the output stream to Second Argument 
						// imageOutputStream = ImageIO.createImageOutputStream( new 
						// FileOutputStream(args[1]) ); 
						try {
							imageOutputStream = ImageIO 
							.createImageOutputStream(new FileOutputStream(path));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						imageWriter.setOutput(imageOutputStream); 

						// Write the changed Image 
						try {
							imageWriter.write(null, new IIOImage(bufferedImage, null, null), 
							pngparams);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						JLabel picLabel = new JLabel(new ImageIcon(bufferedImage));
						   JOptionPane.showMessageDialog(null, picLabel, "About", JOptionPane.PLAIN_MESSAGE, null);

						// Close the streams 
						try {
							imageOutputStream.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						imageWriter.dispose(); 
						
                	  
                           
                  }
                  }
              
              );
              
          
              
              
              
              button2.addActionListener(new ActionListener() {

                  public void actionPerformed(ActionEvent e) {
                	
                	  BufferedImage img = null;
                	  try {
						img = ImageIO.read(new File(path));
						File inputFile=new File(path);
						BufferedImage inputImage=ImageIO.read(inputFile);
						//inputImage.
						
						   BufferedImage tempJPG = null;
						   
						   img=ImageIO.read(inputFile);
						   //img = ImageIO.read(new File(path+listOfFiles[i].getName()));
						   tempJPG = resizeImage(img, 300, 250);
						   JLabel picLabel = new JLabel(new ImageIcon(tempJPG));
						   JOptionPane.showMessageDialog(null, picLabel, "About", JOptionPane.PLAIN_MESSAGE, null);
						  // Image newImg = tempJPG.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
						
					//	BufferedImage imgNew= new BufferedImage(getValueL, getValueB,)
						img.createGraphics();
						
						//BufferedImage resizeImage=resize(img);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 

                           
                  }
                  }
              
              );
             
              
          }
           //if the user click on save in Jfilechooser


          else if(result == JFileChooser.CANCEL_OPTION){
              System.out.println("No File Select");
          }
        }
    });
    
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setSize(700,400);
    setVisible(true);
    }
    
    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;

        
    }

     
     // Methode to resize imageIcon with the same size of a Jlabel
    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    public static void main(String[] args){
        new imageEDTrial();
    }
   
public static void main2(String[] args)throws IOException{
	

		/* 
		* if (args.length < 2) { // Exit if both the arguments (Input File and 
		* Output File) are not provided 
		* System.err.println("Usage: java TestWriter c:\\in.png c:\\out.png"); 
		* return; } 
		*/ 

}
}

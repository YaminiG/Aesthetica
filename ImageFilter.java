import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;
public class ImageFilter extends Frame implements WindowListener, ActionListener{
Choice mode=new Choice();
Button appb=new Button("Apply");
Button saveb=new Button("Save");
FileDialog fd=new FileDialog(this, "Choose an image file", FileDialog.LOAD);
FileDialog fd2=new FileDialog(this, "Save the image", FileDialog.SAVE);
GraphDisplay gd1;
GraphDisplay gd2;
GraphDisplay gd3;
ImageDisplay id;
public static void main(String[] args){
ImageFilter imgf=new ImageFilter();
}
public ImageFilter(){
gd1=new GraphDisplay();
gd2=new GraphDisplay();
gd3=new GraphDisplay();
id=new ImageDisplay(gd1, gd2, gd3);
setLayout(new BorderLayout());
add(id, BorderLayout.CENTER);
Panel leftp=new Panel(new GridLayout(3, 1));
leftp.add(gd1);
leftp.add(gd2);
leftp.add(gd3);
Panel leftside=new Panel(new BorderLayout());
leftside.add(leftp, BorderLayout.CENTER);
Panel bottomp=new Panel(new GridLayout(1, 2));
bottomp.add(appb);
bottomp.add(saveb);
saveb.addActionListener(this);
appb.addActionListener(this);
mode.add("RGB");
mode.add("HSB");
leftside.add(mode, BorderLayout.NORTH);
leftside.add(bottomp, BorderLayout.SOUTH);
add(leftside, BorderLayout.WEST);
fd.setVisible(true);
File f=new File(fd.getDirectory()+fd.getFile());
if(f.exists()){
id.open(f);
}
setSize(600, 400);
setVisible(true);
addWindowListener(this);
}
public void windowOpened(WindowEvent e){}
public void windowClosed(WindowEvent e){}
public void windowClosing(WindowEvent e){
((Frame)e.getSource()).dispose();
}
public void windowIconified(WindowEvent e){}
public void windowDeiconified(WindowEvent e){}
public void windowActivated(WindowEvent e){}
public void windowDeactivated(WindowEvent e){}
public void actionPerformed(ActionEvent e){
if(e.getSource()==appb){
id.setMode(mode.getSelectedIndex());
id.repaint();
}else if(e.getSource()==saveb){
id.setMode(mode.getSelectedIndex());
try{
fd2.setVisible(true);
BufferedImage product=id.render();
String fname=fd2.getFile();
String ext="jpg";
if(fname.contains(".")){
ext=fname.substring(fname.indexOf(".")+1, fname.length());
}else{
fname+=".jpg";
}
ImageIO.write(product, ext, new File(fd2.getDirectory()+fname));
}catch(Exception ex){
Toolkit.getDefaultToolkit().beep();
}
}
}
}
class GraphDisplay extends Canvas implements MouseListener, MouseMotionListener, ComponentListener{
BufferedImage screen;
int[] px;
int[] py;
boolean down=false;
public Dimension getMinimumSize(){
return new Dimension(50, 50);
}
public Dimension getMaximumSize(){
return new Dimension(1000, 1000);
}
public Dimension getPreferredSize(){
return new Dimension(200, 200);
}
public void refresh(){
Graphics2D g=(Graphics2D)screen.getGraphics();
g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
g.setColor(Color.black);
g.fillRect(0, 0, getWidth(), getHeight());
g.setColor(new Color(100, 100, 100));
for(int i=0; i<8; i++){
g.drawLine(getWidth()*i/8, 0, getWidth()*i/8, getHeight());
g.drawLine(0, getHeight()*i/8, getWidth(), getHeight()*i/8);
}
for(int i=0; i<px.length-1; i++){
g.setColor(Color.white);
g.drawLine(px[i], py[i], px[i+1], py[i+1]);
g.fillOval(px[i]-3, py[i]-3, 6, 6);
}
g.fillOval(px[px.length-1]-3, py[py.length-1]-3, 6, 6);
repaint(0, 0, getWidth(), getHeight());
}
public void update(Graphics g){
paint(g);
}
public void paint(Graphics g){
g.drawImage(screen, 0, 0, this);
}
public GraphDisplay(){
px=new int[2];
py=new int[2];
addMouseListener(this);
addMouseMotionListener(this);
addComponentListener(this);
}
public void mouseClicked(MouseEvent e){}
public void mousePressed(MouseEvent e){
if(e.getButton()==MouseEvent.BUTTON1){
click(e);
down=true;
}else{
px=new int[2];
py=new int[2];
px[0]=0;
py[0]=getHeight();
px[1]=getWidth();
py[1]=0;
refresh();
}
}
public void click(MouseEvent e){
boolean found=false;
for(int i=1; i<px.length-1; i++){
if(e.getX()>px[i]-6&&e.getX()<px[i]+6){
if(true){
py[i]=e.getY();
if(down){
px[i]=e.getX();
}
}
found=true;
}
}
if(!found){
int[] npx=new int[px.length+1];
int[] npy=new int[px.length+1];
int i=0;
while(e.getX()>px[i]){
npx[i]=px[i];
npy[i]=py[i];
i++;
}
npx[i]=e.getX();
npy[i]=e.getY();
i++;
while(i<npx.length){
npx[i]=px[i-1];
npy[i]=py[i-1];
i++;
}
px=npx;
py=npy;
}
refresh();
}
public void mouseReleased(MouseEvent e){
down=false;
}
public void mouseEntered(MouseEvent e){}
public void mouseExited(MouseEvent e){}
public void mouseMoved(MouseEvent e){}
public void mouseDragged(MouseEvent e){
click(e);
}
public void componentHidden(ComponentEvent e){}
public void componentShown(ComponentEvent e){}
public void componentResized(ComponentEvent e){
screen=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
px[0]=0;
py[0]=getHeight();
px[1]=getWidth();
py[1]=0;
refresh();
}
public void componentMoved(ComponentEvent e){}
public float evaluate(float inpt){
inpt*=0.99f;
inpt+=0.005f;
int i=0;
while((float)px[i]/(float)getWidth()<inpt){
i++;
}
float outpt=(float)(getHeight()-py[i-1])/(float)getHeight()+((float)((getHeight()-py[i])-(getHeight()-py[i-1]))/(float)getHeight())/((float)((px[i])-(px[i-1]))/(float)getWidth())*(inpt-((float)px[i-1]/(float)getWidth()));
return outpt;
}
}
class ImageDisplay extends Canvas{
File f;
BufferedImage img;
GraphDisplay gd1;
GraphDisplay gd2;
GraphDisplay gd3;
int mode=0;
public void setMode(int m){
mode=m;
}
public Dimension getMinimumSize(){
return new Dimension(50, 50);
}
public Dimension getMaximumSize(){
return new Dimension(1000, 1000);
}
public Dimension getPreferredSize(){
return new Dimension(200, 200);
}
public BufferedImage render(){
BufferedImage img2=new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
Graphics g=img2.getGraphics();
for(int y=0; y<img2.getHeight(); y++){
for(int x=0; x<img2.getWidth(); x++){
Color tc=new Color(img.getRGB(x, y));
float tr=gd1.evaluate((float)tc.getRed()/255f);
float tg=gd2.evaluate((float)tc.getGreen()/255f);
float tb=gd3.evaluate((float)tc.getBlue()/255f);
float[] vals=Color.RGBtoHSB(tc.getRed(), tc.getGreen(), tc.getBlue(), null);
boolean rgb=(mode==0);
Color newc;
if(rgb==true){
newc=new Color((int)(tr*255f), (int)(tg*255f), (int)(tb*255f));
}else{
newc=new Color(Color.HSBtoRGB(gd1.evaluate(vals[0]), gd2.evaluate(vals[1]), gd3.evaluate(vals[2])));
}
g.setColor(newc);
g.fillRect(x, y, 1, 1);
}
}
return img2;
}
public void update(Graphics g){
paint(g);
}
public void paint(Graphics g){
for(int y=0; y<getHeight(); y++){
for(int x=0; x<getWidth(); x++){
Color tc=new Color(img.getRGB(x*img.getWidth()/getWidth(), y*img.getHeight()/getHeight()));
float tr=gd1.evaluate((float)tc.getRed()/255f);
float tg=gd2.evaluate((float)tc.getGreen()/255f);
float tb=gd3.evaluate((float)tc.getBlue()/255f);
float[] vals=Color.RGBtoHSB(tc.getRed(), tc.getGreen(), tc.getBlue(), null);
boolean rgb=(mode==0);
Color newc;
if(rgb==true){
newc=new Color((int)(tr*255f), (int)(tg*255f), (int)(tb*255f));
}else{
newc=new Color(Color.HSBtoRGB(gd1.evaluate(vals[0]), gd2.evaluate(vals[1]), gd3.evaluate(vals[2])));
}
g.setColor(newc);
g.fillRect(x, y, 1, 1);
}
}
}
public ImageDisplay(GraphDisplay gda, GraphDisplay gdb, GraphDisplay gdc){
gd1=gda;
gd2=gdb;
gd3=gdc;
}
public void open(File file){
f=file;
try{
img=ImageIO.read(f);
}catch(Exception ex){}
repaint(0, 0, getWidth(), getHeight());
}
}
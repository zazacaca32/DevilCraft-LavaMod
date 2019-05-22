package codechicken.nei;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import net.minecraft.client.Minecraft;

public class TMIUninstaller {

   private static File jarFile;
   public static TMIUninstaller.InstallerGui mainframe;


   public static void main(String[] args) {
      jarFile = new File(args[0]);
      TMIUninstaller.InstallerGui.installLnF();
      mainframe = new TMIUninstaller.InstallerGui();
      mainframe.setDefaultCloseOperation(3);
      mainframe.setLocationRelativeTo((Component)null);
      mainframe.pack();
      mainframe.setVisible(true);
      if(!jarFile.exists()) {
         mainframe.getLabelInfo().setText("Invalid Minecraft.jar");
      } else {
         uninstall();
      }

   }

   private static File getJarFile() {
      URL url = Minecraft.class.getProtectionDomain().getCodeSource().getLocation();

      try {
         if(url.getProtocol().equals("jar")) {
            url = new URL(url.getPath().substring(0, url.getPath().indexOf(33)));
         }

         return new File(url.toURI());
      } catch (URISyntaxException var2) {
         return new File(url.getPath());
      } catch (MalformedURLException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static void deleteTMIUninstaller() throws IOException {
      System.out.println("Removing TMI Uninstaller");
      deleteDir(new File(getJarFile().getParentFile(), "TMIUninstaller"), true);
   }

   public static boolean TMIInstalled() {
      File jarFile = getJarFile();
      if(!jarFile.getName().endsWith(".jar")) {
         return false;
      } else {
         try {
            ZipFile e = new ZipFile(jarFile);
            ZipEntry tmi = e.getEntry("mod_TooManyItems.class");
            e.close();
            return tmi != null;
         } catch (Exception var3) {
            return false;
         }
      }
   }

   public static void runTMIUninstaller() throws IOException {
      System.out.println("Installing Uninstaller.");
      File jarFile = getJarFile();
      File uninstDir = new File(jarFile.getParentFile(), "TMIUninstaller");
      if(!uninstDir.exists()) {
         uninstDir.mkdirs();
      }

      System.out.println("Installing Uninstaller: " + uninstDir.getPath());
      FileInputStream fileinputstream = new FileInputStream(jarFile);
      ZipInputStream zipinputstream = new ZipInputStream(fileinputstream);

      while(true) {
         ZipEntry javaBin = zipinputstream.getNextEntry();
         String classpath;
         if(javaBin == null) {
            fileinputstream.close();
            String javaBin1 = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
            classpath = uninstDir.getAbsolutePath();
            String className1 = TMIUninstaller.class.getCanonicalName();
            String jarPath1 = jarFile.getPath();
            System.out.println("Running Process: " + javaBin1 + " -cp \"" + classpath + "\" \"" + className1 + "\" \"" + jarPath1 + "\"");
            ProcessBuilder builder1 = new ProcessBuilder(new String[]{javaBin1, "-cp", classpath, className1, jarPath1});
            builder1.start();
            return;
         }

         classpath = javaBin.getName().replace('\\', '/');
         if(!javaBin.isDirectory() && classpath.replace('/', '.').startsWith(TMIUninstaller.class.getCanonicalName())) {
            File className = new File(uninstDir, classpath);
            System.out.println("Extracting File: " + className.getPath());
            if(!className.getParentFile().exists()) {
               className.getParentFile().mkdirs();
            }

            className.createNewFile();
            FileOutputStream jarPath = new FileOutputStream(className);
            byte[] builder = new byte['\uffff'];
            boolean read = false;

            int read1;
            while((read1 = zipinputstream.read(builder)) != -1) {
               jarPath.write(builder, 0, read1);
            }

            jarPath.close();
         }
      }
   }

   public static void deleteDir(File directory, boolean remove) throws IOException {
      System.out.println("Deleting Dir: " + directory.getPath());
      if(!directory.exists()) {
         if(!remove) {
            directory.mkdirs();
         }

      } else {
         File[] var5;
         int var4 = (var5 = directory.listFiles()).length;

         for(int var3 = 0; var3 < var4; ++var3) {
            File e = var5[var3];
            if(e.isDirectory()) {
               deleteDir(e, true);
            } else if(!e.delete()) {
               throw new IOException("Delete Failed: " + e);
            }
         }

         if(remove) {
            try {
               if(!directory.delete()) {
                  throw new IOException("Delete Failed: " + directory);
               }
            } catch (SecurityException var6) {
               throw var6;
            }
         }

      }
   }

   public static void uninstall() {
      File backupfile = new File(jarFile.getParentFile(), jarFile.getName() + ".bak");

      FileInputStream e1;
      try {
         if(!backupfile.exists()) {
            backupfile.createNewFile();
         }

         FileOutputStream e = new FileOutputStream(backupfile);
         e1 = new FileInputStream(jarFile);
         e.getChannel().transferFrom(e1.getChannel(), 0L, jarFile.length());
         e.close();
         e1.close();
         ZipInputStream jarout1 = new ZipInputStream(new FileInputStream(backupfile));
         ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(jarFile));
         byte[] buffer = new byte[20000];
         boolean read = false;

         while(true) {
            ZipEntry entry = jarout1.getNextEntry();
            if(entry == null) {
               jarout1.close();
               zipout.close();
               mainframe.getLabelInfo().setText("Uninstall Completed. Close this and restart minecraft.");
               break;
            }

            if(!entry.isDirectory()) {
               String name = entry.getName();
               if(!name.startsWith("TMI") && !name.startsWith("_tmi") && !name.startsWith("tmi.png") && !name.startsWith("mod_TooManyItems")) {
                  zipout.putNextEntry(new ZipEntry(name));
                  ZipInputStream in = jarout1;

                  int read1;
                  while((read1 = in.read(buffer)) != -1) {
                     zipout.write(buffer, 0, read1);
                  }

                  zipout.closeEntry();
                  jarout1.closeEntry();
               }
            }
         }
      } catch (Exception var11) {
         var11.printStackTrace();
         mainframe.getLabelInfo().setText("Invalid Minecraft.jar");

         try {
            e1 = new FileInputStream(backupfile);
            FileOutputStream jarout = new FileOutputStream(jarFile);
            jarout.getChannel().transferFrom(e1.getChannel(), 0L, backupfile.length());
            e1.close();
            jarout.close();
         } catch (Exception var10) {
            var10.printStackTrace();
         }
      }

   }

   public static class InstallerGui extends JFrame {

      private static final long serialVersionUID = 1L;
      private JTextField labelInfo;
      private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";


      public InstallerGui() {
         this.setTitle("TMI Uninstaller");
         this.initComponents();
      }

      private void initComponents() {
         this.setLayout(new TMIUninstaller.InstallerGui.ResizeListener((TMIUninstaller.InstallerGui.ResizeListener)null));
         this.add(this.getLabelInfo());
         this.setSize(358, 270);
      }

      public JTextField getLabelInfo() {
         if(this.labelInfo == null) {
            this.labelInfo = new JTextField();
            this.labelInfo.setFont(new Font("Tahoma", 0, 13));
            this.labelInfo.setHorizontalAlignment(0);
            this.labelInfo.setEditable(false);
            this.labelInfo.setText("Uninstalling TMI");
         }

         return this.labelInfo;
      }

      public static void installLnF() {
         try {
            String e = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(e);
         } catch (Exception var1) {
            System.err.println("Cannot install com.sun.java.swing.plaf.windows.WindowsLookAndFeel on this platform:" + var1.getMessage());
         }

      }

      private class ResizeListener implements java.awt.LayoutManager {

         private ResizeListener() {}

         public void addLayoutComponent(String name, Component comp) {}

         public void layoutContainer(Container parent) {
            int w = InstallerGui.this.getContentPane().getWidth();
            InstallerGui.this.labelInfo.setBounds(10, 45, w - 20, 20);
            InstallerGui.this.labelInfo.setScrollOffset(1000);
            InstallerGui.this.labelInfo.update(InstallerGui.this.getGraphics());
         }

         public Dimension minimumLayoutSize(Container parent) {
            return new Dimension(150, 100);
         }

         public Dimension preferredLayoutSize(Container parent) {
            return new Dimension(250, 120);
         }

         public void removeLayoutComponent(Component comp) {}

         // $FF: synthetic method
         ResizeListener(TMIUninstaller.InstallerGui.ResizeListener var2) {
            this();
         }
      }

      public class InstallerListener implements ActionListener {

         public void actionPerformed(ActionEvent e) {}
      }
   }
}

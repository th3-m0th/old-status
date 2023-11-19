package status;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Status extends JFrame {
    private JLabel jLabel1;

    private JScrollPane jScrollPane1;

    private JToggleButton openbttn;

    private JButton savebttn;

    private JTextArea txt;

    public Status() {
        initComponents();
    }

    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jScrollPane1 = new JScrollPane();
        this.txt = new JTextArea();
        this.savebttn = new JButton();
        this.openbttn = new JToggleButton();
        setDefaultCloseOperation(3);
        setTitle("Status");
        setResizable(false);
        this.jLabel1.setFont(new Font("DejaVu Sans Condensed", 0, 14));
        this.jLabel1.setText("What happened today?");
        this.txt.setColumns(20);
        this.txt.setRows(5);
        this.jScrollPane1.setViewportView(this.txt);
        this.savebttn.setFont(new Font("Dialog", 0, 14));
        this.savebttn.setText("Save");
        this.savebttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Status.this.savebttnActionPerformed(evt);
            }
        });
        this.openbttn.setFont(new Font("Dialog", 0, 14));
        this.openbttn.setText("Open");
        this.openbttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Status.this.openbttnActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(this.jScrollPane1, -2, 347, -2)
                                                .addComponent(this.jLabel1))
                                        .addContainerGap(27, 32767))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, 32767)
                                        .addComponent(this.openbttn, -2, 67, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.savebttn, -2, 60, -2)
                                        .addGap(28, 28, 28)))));
        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(this.jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(this.jScrollPane1, -2, 190, -2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.savebttn, -1, -1, 32767)
                                .addComponent(this.openbttn))
                        .addGap(9, 9, 9)));
        pack();
    }

    private void savebttnActionPerformed(ActionEvent evt) {
        PrintWriter pw = null;
        try {
            LocalDate now = LocalDate.now();
            String Namefile = now.toString() + ".txt";
            System.out.println(Namefile);
            String Texto = this.txt.getText();
            File p = new File("Status");
            p.mkdir();
            File file = new File("Status//" + Namefile);
            pw = new PrintWriter(file);
            pw.println(Texto);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error, try again.", "Error", 0);
        } finally {
            pw.close();
        }
    }

    private void openbttnActionPerformed(ActionEvent evt) {
        JFileChooser ch = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Status Files", new String[] { "txt" });
        ch.addChoosableFileFilter(filter);
        ch.setCurrentDirectory(new File(System.getProperty("user.dir") + "//Status"));
        ch.showOpenDialog(this);
        File file = ch.getSelectedFile();
        try {
            Scanner scann = new Scanner(file);
            while (scann.hasNextLine())
                this.txt.setText(scann.nextLine());
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error, file not found, try again.", "Error", 0);
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Status.class.getName()).log(Level.SEVERE, (String)null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new Status()).setVisible(true);
            }
        });
    }
}

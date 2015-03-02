/*
 * Copyright (C) 2015 Thigale Sameer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package beproject;

import static beproject.Initializer.inConn;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.Timer;
import javax.swing.UIManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;
import twitter4j.TwitterException;
/**
 *Main JFrame Form that creates that displays GUI
 * @author Thigale Sameer
 */
public class MainGUI extends javax.swing.JFrame {
    private Thread twitterThread;
    private Regression myRegression;
    private myTwitter myTwitterObj;
    private Statement stmt;
    private final List<String> actorList=new ArrayList<>();
    static MainGUI formRef;
    private static int tweetShownCount=30;
    private String movieName;
    private ChartPanel pieChart;
    private TimeSeries rate;
    private ChartPanel countryPieChart;
    private ChartPanel timeLineChart;
    private JScrollPane tagCloudPanel;
    private JLabel mapLabel;
    /**
     * Creates new form MainGUI
     */
    public MainGUI() {
        initComponents();
        UIManager.put("nimbusBase", new Color(2, 88, 141));
        UIManager.put("nimbusBlueGrey", new Color(64, 93, 155));
        UIManager.put("control", new Color(233, 234, 237));
        try {
            this.setIconImage(ImageIO.read(this.getClass().getResourceAsStream("icon.png")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        isSequelButtonGroup = new javax.swing.ButtonGroup();
        categoryButtonGroup = new javax.swing.ButtonGroup();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        movieNameTextField = new javax.swing.JTextField();
        releaseDateTextField = new javax.swing.JFormattedTextField();
        isSequelYesRadioButton = new javax.swing.JRadioButton();
        isSequelNoRadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cat1CheckBox = new javax.swing.JCheckBox();
        cat2CheckBox = new javax.swing.JCheckBox();
        cat3CheckBox = new javax.swing.JCheckBox();
        cat4CheckBox = new javax.swing.JCheckBox();
        cat5CheckBox = new javax.swing.JCheckBox();
        scheduleButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        actorTwitterHandleTextField = new javax.swing.JTextField();
        actorNameTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        theatreTextField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        predictionPanel = new javax.swing.JPanel();
        movieNamesComboBox = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        predictionTextArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        getPredictionButton = new javax.swing.JButton();
        hypeAnalysisPanel = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        sentimentPanel = new javax.swing.JPanel();
        countryPanel = new javax.swing.JPanel();
        timeLinePanel = new javax.swing.JPanel();
        tweetsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tweetTable = new javax.swing.JTable();
        nextButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();
        liveTweetsAnalysisPanel = new javax.swing.JPanel();
        mapPanel = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        twitterStatusLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        internetConnectionLabel = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        databaseConnectionLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        startStreamingCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        helpMenu = new javax.swing.JMenu();
        ContentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Hype Analyzer");
        setBounds(new java.awt.Rectangle(0, 0, 500, 500));
        setMinimumSize(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jSplitPane2.setDividerLocation(450);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        movieNameTextField.setColumns(5);
        movieNameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movieNameTextFieldMouseClicked(evt);
            }
        });
        movieNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieNameTextFieldActionPerformed(evt);
            }
        });
        movieNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                movieNameTextFieldFocusGained(evt);
            }
        });
        jPanel2.add(movieNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 137, 30));

        try {
            releaseDateTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel2.add(releaseDateTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 137, 32));

        isSequelButtonGroup.add(isSequelYesRadioButton);
        isSequelYesRadioButton.setText("Yes");
        isSequelYesRadioButton.setName("isSequelRadio"); // NOI18N
        isSequelYesRadioButton.setOpaque(false);
        isSequelYesRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isSequelYesRadioButtonActionPerformed(evt);
            }
        });
        jPanel2.add(isSequelYesRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, -1, -1));

        isSequelButtonGroup.add(isSequelNoRadioButton);
        isSequelNoRadioButton.setSelected(true);
        isSequelNoRadioButton.setText("No");
        isSequelNoRadioButton.setName("isSequelRadio"); // NOI18N
        jPanel2.add(isSequelNoRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Is Sequel?");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("Category"); // NOI18N

        cat1CheckBox.setBackground(new java.awt.Color(255, 255, 255));
        cat1CheckBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cat1CheckBox.setText("Action");

        cat2CheckBox.setBackground(new java.awt.Color(255, 255, 255));
        cat2CheckBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cat2CheckBox.setText("Animation");

        cat3CheckBox.setBackground(new java.awt.Color(255, 255, 255));
        cat3CheckBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cat3CheckBox.setText("Thriller");

        cat4CheckBox.setBackground(new java.awt.Color(255, 255, 255));
        cat4CheckBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cat4CheckBox.setText("Romance");

        cat5CheckBox.setBackground(new java.awt.Color(255, 255, 255));
        cat5CheckBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cat5CheckBox.setText("Comedy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cat5CheckBox)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cat3CheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                                .addComponent(cat4CheckBox))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cat1CheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cat2CheckBox)))
                        .addGap(111, 111, 111))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cat1CheckBox)
                    .addComponent(cat2CheckBox))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cat4CheckBox)
                    .addComponent(cat3CheckBox))
                .addGap(18, 18, 18)
                .addComponent(cat5CheckBox)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 430, 130));

        scheduleButton.setText("Schedule");
        scheduleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleButtonActionPerformed(evt);
            }
        });
        jPanel2.add(scheduleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Release Date:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, -1, -1));

        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Number of theatres movie is to be released:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        actorNameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actorNameTextFieldMouseClicked(evt);
            }
        });
        actorNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actorNameTextFieldActionPerformed(evt);
            }
        });
        actorNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                actorNameTextFieldFocusGained(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Actor Name:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Actor Twitter Handle:");

        addButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addButton.setText("Add this actor");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(actorTwitterHandleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(actorNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(actorNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(actorTwitterHandleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(jLabel11))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 100));

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Movie Name: ");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        theatreTextField.setColumns(5);
        theatreTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                theatreTextFieldMouseClicked(evt);
            }
        });
        theatreTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                theatreTextFieldActionPerformed(evt);
            }
        });
        theatreTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                theatreTextFieldFocusGained(evt);
            }
        });
        jPanel2.add(theatreTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 137, 30));

        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, -1, -1));

        jSplitPane2.setLeftComponent(jPanel2);

        jTabbedPane2.setName(""); // NOI18N
        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        predictionPanel.setName("predictionPanel"); // NOI18N
        predictionPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                predictionPanelComponentShown(evt);
            }
        });

        movieNamesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieNamesComboBoxActionPerformed(evt);
            }
        });

        predictionTextArea.setColumns(20);
        predictionTextArea.setRows(5);
        jScrollPane2.setViewportView(predictionTextArea);

        jLabel2.setText("Prediction:");

        getPredictionButton.setText("Get Prediction");
        getPredictionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getPredictionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout predictionPanelLayout = new javax.swing.GroupLayout(predictionPanel);
        predictionPanel.setLayout(predictionPanelLayout);
        predictionPanelLayout.setHorizontalGroup(
            predictionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(movieNamesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(predictionPanelLayout.createSequentialGroup()
                .addGroup(predictionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(predictionPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(predictionPanelLayout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(getPredictionButton)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        predictionPanelLayout.setVerticalGroup(
            predictionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(predictionPanelLayout.createSequentialGroup()
                .addComponent(movieNamesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(getPredictionButton)
                .addGap(0, 174, Short.MAX_VALUE))
        );

        movieNamesComboBox.getAccessibleContext().setAccessibleParent(predictionPanel);

        jTabbedPane2.addTab("Prediction", predictionPanel);

        hypeAnalysisPanel.setName("hypeAnalysisPanel"); // NOI18N
        hypeAnalysisPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                hypeAnalysisPanelComponentShown(evt);
            }
        });
        hypeAnalysisPanel.setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel3.setLayout(new java.awt.BorderLayout());
        jSplitPane1.setTopComponent(jPanel3);

        jSplitPane3.setDividerLocation(300);

        sentimentPanel.setLayout(new java.awt.BorderLayout());
        jSplitPane3.setLeftComponent(sentimentPanel);

        countryPanel.setLayout(new java.awt.BorderLayout());
        jSplitPane3.setRightComponent(countryPanel);

        jSplitPane1.setRightComponent(jSplitPane3);

        hypeAnalysisPanel.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane2.addTab("Hype Analysis", hypeAnalysisPanel);

        timeLinePanel.setName("timeLinePanel"); // NOI18N
        timeLinePanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                timeLinePanelComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                timeLinePanelComponentShown(evt);
            }
        });
        timeLinePanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane2.addTab("Time Line", timeLinePanel);

        tweetsPanel.setName("tweetsPanel"); // NOI18N
        tweetsPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tweetsPanelComponentShown(evt);
            }
        });

        tweetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tweet", "Polarity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tweetTable);
        if (tweetTable.getColumnModel().getColumnCount() > 0) {
            tweetTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        }

        nextButton.setText("Next>>");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        prevButton.setText("<<Prev");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tweetsPanelLayout = new javax.swing.GroupLayout(tweetsPanel);
        tweetsPanel.setLayout(tweetsPanelLayout);
        tweetsPanelLayout.setHorizontalGroup(
            tweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tweetsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tweetsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prevButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextButton)
                .addGap(35, 35, 35))
        );
        tweetsPanelLayout.setVerticalGroup(
            tweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tweetsPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(tweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prevButton)
                    .addComponent(nextButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Tweets", tweetsPanel);

        liveTweetsAnalysisPanel.setName("liveTweetsAnalysisPanel"); // NOI18N
        liveTweetsAnalysisPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                liveTweetsAnalysisPanelComponentShown(evt);
            }
        });
        liveTweetsAnalysisPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane2.addTab("Live Tweet Analysis", liveTweetsAnalysisPanel);

        mapPanel.setName("mapPanel"); // NOI18N
        mapPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane2.addTab("Map", mapPanel);

        jSplitPane2.setRightComponent(jTabbedPane2);

        jToolBar1.setRollover(true);
        jToolBar1.setFocusable(false);

        twitterStatusLabel.setText("TWITTER STATUS: Not gathering tweets | Running as Client");
        jToolBar1.add(twitterStatusLabel);
        jToolBar1.add(jSeparator2);

        internetConnectionLabel.setText("No Internet Connection");
        jToolBar1.add(internetConnectionLabel);
        jToolBar1.add(jSeparator3);

        databaseConnectionLabel.setText("No database detected");
        jToolBar1.add(databaseConnectionLabel);

        fileMenu.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Quit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        jMenuBar1.add(fileMenu);

        jMenu1.setText("Twitter");

        startStreamingCheckBoxMenuItem.setText("Start Streaming");
        startStreamingCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStreamingCheckBoxMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(startStreamingCheckBoxMenuItem);

        jMenuBar1.add(jMenu1);

        helpMenu.setText("Help");
        helpMenu.setName("Help"); // NOI18N
        helpMenu.setPreferredSize(new java.awt.Dimension(29, 19));

        ContentsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        ContentsMenuItem.setText("Contents");
        helpMenu.add(ContentsMenuItem);

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(myTwitterObj!=null)
            myTwitterObj.commitNow();
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * Sets the Window Title for MainGUI Form
     * @param title The title to be set
     */
    static void setFormTitle(String title){
        formRef.setTitle(title);
    }
    
    /**
     * This function perform initialization activities
     * @param evt 
     */
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        
        formRef=this;
        Initializer.checkStatus();        
        
        List tmp=ScheduledMoviesList.getMovieNames();
        for (Object tmp1 : tmp) {
            movieNamesComboBox.addItem(tmp1);
        }
        
        if(!tmp.isEmpty() && JOptionPane.showConfirmDialog(null, "Resume Twitter Streaming?", "HypeAnalyzer", JOptionPane.YES_NO_OPTION)==0){
            myTwitterObj=new myTwitter(twitterStatusLabel);
            twitterThread = new Thread(myTwitterObj);
            twitterThread.setPriority(Thread.MAX_PRIORITY);
            twitterThread.start();
            startStreamingCheckBoxMenuItem.setSelected(true);
            twitterStatusLabel.setText("STREAMING TWEETS");
        }
        
        try {
            URL url = new URL("http://www.google.com");
            url.openConnection().connect();
            internetConnectionLabel.setText("Internet Status: Available");
        }catch (IOException ex) {
        }
        try {
            stmt=inConn.createStatement();
	} catch (SQLException e) {
            ExceptionManager.handleException(e, "Error Connecting to MySql");
	}
        databaseConnectionLabel.setText("Connected to database");
        liveTweetAnalysis();
        
        myRegression=new Regression();
        
        updateAllGraphs();
        /*new Thread(new Runnable(){

            @Override
            public void run() {
                try{
                    int i=0;
                    //PreparedStatement pstmt=Initializer.outConn2.prepareStatement("");
                    while(true){
                        ResultSet rs=stmt.executeQuery("select username from tweets where country='' limit "+i+", 30");
                        if(rs.first()==false)
                            break;
                        else
                            i+=30;
                        while(rs.next()){
                            String user=rs.getString(1);
                            String country=MyRestAPI.getUserCountry(user);
                        }
                    }
                    
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            
        }).start();
        */
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    createTimeLine();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }//GEN-LAST:event_formComponentShown

    void updateAllGraphs(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    while(stmt==null)
                        ;
                    createPieChart();
                    createTagCloud();
                    createCountryPieChart();
                    showAllTweets();
                    createMap();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        }).start();
    }
    
    private void scheduleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleButtonActionPerformed
        String movieName=movieNameTextField.getText(), releaseDate=releaseDateTextField.getText(),
                theatre=theatreTextField.getText();
        int t;
        boolean isSequel=isSequelYesRadioButton.isSelected();
        if(movieName.equals("") || theatre.equals("")){
            JOptionPane.showMessageDialog(null, "Fields cannot be left blank");
            return;
        }
        
        try{
            t=Integer.parseInt(theatre);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid theatre count.");
            return;
        }
        
        //TODO DATABASE CHECK
        if(movieName.length()>=30){
            JOptionPane.showMessageDialog(null, "Movie name length too large.");
            return;
        }
        if(movieName.contains("'") || movieName.contains("\"")){
            JOptionPane.showMessageDialog(null, "Movie name cannot contain special characters");
            return;
        }
        
        try{
            //java.util.Date d1=new java.util.Date();
            DateFormat format = new SimpleDateFormat("DD-MM-YYYY");
            Date date = format.parse(releaseDate);
            
            if(date.getYear()>199){
                releaseDate="";
            }
        }catch(ParseException e){
            releaseDate="";
        }

        if(releaseDate.equals("")){
            JOptionPane.showMessageDialog(null, "Invalid release date.");
            return;
        }
        
        String tmp="insert into movienames values ('"+movieName+"', STR_TO_DATE('"+releaseDate+"','%d-%m-%Y'), "+isSequel+", "+t+")";
        String tmp1="insert into category values('"+movieName+"',"+cat1CheckBox.isSelected()+","+cat2CheckBox.isSelected()+","+cat3CheckBox.isSelected()+","+cat4CheckBox.isSelected()+","+cat5CheckBox.isSelected()+")";
        try {
            stmt.execute(tmp);
            stmt.execute(tmp1);
            for(String i: actorList){
                stmt.execute("insert into movieactorassoc values('"+movieName+"', '"+i+"')");
            }
        }catch(SQLException e){
            ExceptionManager.handleException(e, "");
        }
        
        actorList.clear();
        JOptionPane.showMessageDialog(null, "Scheduled");
        movieNameTextField.setText("");
        releaseDateTextField.setText("");
        actorNameTextField.setText("");
        actorTwitterHandleTextField.setText("");
        for(Object i: jPanel1.getComponents()){
            if(i instanceof JCheckBox){
                ((JCheckBox)i).setSelected(false);
            }
        }
        

    }//GEN-LAST:event_scheduleButtonActionPerformed

    private void isSequelYesRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isSequelYesRadioButtonActionPerformed

    }//GEN-LAST:event_isSequelYesRadioButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String actorName=actorNameTextField.getText();
        String actorTwitterHandle=actorTwitterHandleTextField.getText();
        if(actorName.equals("") || actorTwitterHandle.equals("")){
            JOptionPane.showMessageDialog(null, "Actor name/Twitter Handle cannot be null");
            return;
        }
        if(actorName.length()>=30){
            JOptionPane.showMessageDialog(null, "Actor name too large to hold");
            return;
        }
        if(actorTwitterHandle.length()>15){
            JOptionPane.showMessageDialog(null, "Actor twitter handle too large");
            return;
        }
        try{
            stmt.execute("insert into actorslist values('"+actorName+"', '"+actorTwitterHandle+"', "+MyRestAPI.getFollowerCount(actorTwitterHandle)+")");
        } catch(SQLException e){
            //NO NEED TO HANDLE, IT IS JUST DUPLICATE ENTRY EXCEPTION
        } catch (TwitterException ex) {
            try {
                stmt.execute("insert into actorslist(name) values('"+actorName+"')");
            } catch (SQLException ex1) {
                ExceptionManager.handleException(ex1, "");
            }
        }
        
        actorList.add(actorName);
        actorNameTextField.setText("");
        actorTwitterHandleTextField.setText("");
    }//GEN-LAST:event_addButtonActionPerformed

    private void actorNameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_actorNameTextFieldFocusGained

    }//GEN-LAST:event_actorNameTextFieldFocusGained

    private void actorNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actorNameTextFieldActionPerformed

    }//GEN-LAST:event_actorNameTextFieldActionPerformed

    private void actorNameTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actorNameTextFieldMouseClicked

    }//GEN-LAST:event_actorNameTextFieldMouseClicked

    private void movieNameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_movieNameTextFieldFocusGained

    }//GEN-LAST:event_movieNameTextFieldFocusGained

    private void movieNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieNameTextFieldActionPerformed

    }//GEN-LAST:event_movieNameTextFieldActionPerformed

    private void movieNameTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieNameTextFieldMouseClicked

    }//GEN-LAST:event_movieNameTextFieldMouseClicked

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        JOptionPane.showMessageDialog(null, "BE Project titled \"Prediction of Box Office Success of Movies using Hype Analysis of Twitter Data\""+""
                + "\nAUTHOR: Sameer Thigale, Tushar Prasad, Ustat Kaur, Vibha Ravichandran\nUnder guidance of Prof. Reena Pagare, MIT COE and Mr. Chinatmani Gokhale, Persistent Systems Ltd\n", "About HypeAnalyzer", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(myTwitterObj!=null)
            myTwitterObj.commitNow();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    
    TreeMap getFrequentWords() throws SQLException{
        HashMap<String, Integer> i=new HashMap<>();
        ResultSet rs=stmt.executeQuery("select tweet from tweets where moviename='"+movieName+"' LIMIT 0, 10000");
        while(rs.next()){
            String[] data=rs.getString(1).toLowerCase().split("[ \t\n\'\";?!,]");
            for(String tmp: data){
                if(tmp.contains("http") ||tmp.length()<4)continue;
                Integer a=i.putIfAbsent(tmp, 1);
                if(a!=null){
                    i.put(tmp, a+1);
                }
            }
        }
        
        ValueComparator bvc =  new ValueComparator(i);
        TreeMap<String,Integer> sorted_map = new TreeMap<>(bvc);
        sorted_map.putAll(i);
        return sorted_map;
    }
    
    class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;
    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    @Override
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
    
    void createTagCloud() throws SQLException{
        TreeMap tmp=getFrequentWords();
        Cloud cld=new Cloud();
        JPanel tmpPanel =new JPanel();
        FlowLayout t1=new FlowLayout();
        tmpPanel.setPreferredSize(new Dimension(512, 512));
        tmpPanel.setLayout(t1);
        tmpPanel.setBounds(0, 0, 512, 512);
        //FlowLayout lm=(FlowLayout) tmpPanel.getLayout();
        for(int i=0; i<40 && !tmp.isEmpty(); i++){
            Map.Entry mp=tmp.pollFirstEntry();
            Tag t=new Tag((String)mp.getKey(), (int)(mp.getValue()));
            cld.addTag(t);
        }
        Random rand=new Random();
        for (Tag tag : cld.tags()) {
            final JLabel label = new JLabel(tag.getName());
            label.setOpaque(false);
            label.setFont(label.getFont().deriveFont(rand.nextFloat()*39));
            label.setForeground(new Color(rand.nextInt()));
            tmpPanel.add(label);
        }
        if(tagCloudPanel==null){
            tagCloudPanel=new JScrollPane(tmpPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }else{
            jPanel3.remove(tagCloudPanel);
            jPanel3.validate();
            tagCloudPanel=new JScrollPane(tmpPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }
        //tagCloudPanel.setLayout(new ScrollPaneLayout());
        //tagCloudPanel.setAutoscrolls(true);
        tmpPanel.validate();
        tagCloudPanel.validate();
        jPanel3.add(tagCloudPanel, BorderLayout.CENTER);
        jPanel3.validate();
        
        
    }
    
    private void movieNamesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieNamesComboBoxActionPerformed
        movieName=movieNamesComboBox.getSelectedItem().toString();
        updateAllGraphs();/*
        if(jTabbedPane2.getSelectedComponent() instanceof JPanel){
            JPanel j=(JPanel)jTabbedPane2.getSelectedComponent();
            switch(j.getName()){
                case "predictionPanel":
                    
                    break;
                case "hypeAnalysisPanel":
                    try {
                        createTagCloud();
                        createPieChart();
                        createCountryPieChart();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "timeLinePanel":
                    try{
                        createTimeLine();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                    break;
                case "tweetsPanel":
                    showAllTweets();
                    break;
                case "liveTweetsAnalysisPanel":
                    
                    break;
                case "mapPanel":
                        try{
                            createMap();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    break;
                default:
                    System.out.println("Tab name is missing");
                    break;
            }
        }*/
    }//GEN-LAST:event_movieNamesComboBoxActionPerformed

    
    void createMap() throws Exception{
        int x=512;//mapPanel.getSize().height;
        int y=512;//mapPanel.getSize().width;
        ResultSet rs=stmt.executeQuery("select country from tweets where moviename='"+movieName+"' and country <> \"\" group by country");
        String tmp="";
        if(rs==null)return;
        while(rs.next()){
            if(rs.getString(1)==null)
                continue;
            tmp+="%7C"+rs.getString(1).replaceAll(" ", "%20");
        }
        String astr="https://maps.googleapis.com/maps/api/staticmap?size="+x+"x"+y+"&markers=color:blue%7Clabel:S"+tmp;
        URLConnection con = new URL(astr).openConnection();
        InputStream is = con.getInputStream();
        BufferedImage map=ImageIO.read(is);
        
        if(mapLabel==null){
            mapLabel=new JLabel(new ImageIcon(map));
        }else{
            mapPanel.remove(mapLabel);
            mapLabel=new JLabel(new ImageIcon(map));
        }
        mapLabel.setSize(x, y);
        mapPanel.add(mapLabel);
        mapPanel.validate();
    }
    
    private void startStreamingCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStreamingCheckBoxMenuItemActionPerformed
        
        if(startStreamingCheckBoxMenuItem.isSelected()){
            
        }else{
            myTwitterObj=new myTwitter(twitterStatusLabel);
            twitterThread = new Thread(myTwitterObj);
            twitterThread.setPriority(Thread.MAX_PRIORITY);
            twitterThread.start();
            startStreamingCheckBoxMenuItem.setSelected(true);
            twitterStatusLabel.setText("STREAMING TWEETS");
        }
        
    }//GEN-LAST:event_startStreamingCheckBoxMenuItemActionPerformed

    void getTimeLineData(TimeSeriesCollection t) throws SQLException{
        Statement stmt=Initializer.inConn2.createStatement();
        ResultSet rs1=stmt.executeQuery("select max(ts) from tweets");
        rs1.first();
        Timestamp ts1=rs1.getTimestamp(1);
        for(String tmp: ScheduledMoviesList.getMovieNames()){
            TimeSeries t1=new TimeSeries(tmp, Hour.class);
            Timestamp ts=(Timestamp)ts1.clone();
            for(int i=0; i<6; i++){
                Date d1=new java.util.Date(ts.getTime());
                Date d2=new java.util.Date(ts.getTime()+3600000);
                ResultSet rs=stmt.executeQuery("select count(*) from tweets where moviename='"+tmp+"' and ts between '"+Regression.sdf.format(d1)+"' and '"+Regression.sdf.format(d2)+"'");
                rs.first();
                //if(!rs.first())
                  //  t1.addOrUpdate(new Hour(d1), 0);
                //else
                    t1.addOrUpdate(new Hour(d1), rs.getInt(1));
                ts.setTime(ts.getTime()-3600000);
            }
            t.addSeries(t1);
        }
        
    }
    
    void createTimeLine() throws SQLException{
        TimeSeriesCollection dataset =new TimeSeriesCollection();
        getTimeLineData(dataset);
        DateAxis domain = new DateAxis("Time");
        NumberAxis range = new NumberAxis("Tweet Count");
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(3f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));
        renderer.setSeriesStroke(1, new BasicStroke(3f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));
        XYPlot plot = new XYPlot(dataset, domain, range, renderer);
        domain.setAutoRange(true);
        domain.setTickUnit(new DateTickUnit(DateTickUnitType.HOUR, 2, Regression.sdf));
        domain.setLowerMargin(0.0);
        domain.setUpperMargin(0.0);
        domain.setTickLabelsVisible(true);
        range.setTickLabelsVisible(true);

        plot.setDomainGridlinesVisible(false);
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        renderer.setBaseItemLabelsVisible(true);

        JFreeChart liveTweetAnalysisChart = new JFreeChart("Rate of tweets per hour", new Font("Tahoma", Font.BOLD, 24), plot, true);
        liveTweetAnalysisChart.setBorderVisible(false);

        ChartUtilities.applyCurrentTheme(liveTweetAnalysisChart);
        
        domain.setTickLabelInsets(RectangleInsets.ZERO_INSETS);
        range.setTickMarksVisible(false);
        range.setTickLabelInsets(RectangleInsets.ZERO_INSETS);
        domain.setTickMarksVisible(false);
        liveTweetAnalysisChart.setPadding(RectangleInsets.ZERO_INSETS);

        ChartPanel liveTweetAnalysisChartPanel = new ChartPanel(liveTweetAnalysisChart, true);
        liveTweetAnalysisChartPanel.setBorder(null);
        
        if(timeLineChart==null){
            timeLineChart=liveTweetAnalysisChartPanel;
        }else{
            timeLinePanel.remove(timeLineChart);
            timeLineChart=liveTweetAnalysisChartPanel;
        }
        timeLinePanel.add(timeLineChart);
        timeLinePanel.validate();
    }
    
    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged

        ((JPanel)((JTabbedPane)evt.getSource()).getSelectedComponent()).add(movieNamesComboBox, BorderLayout.NORTH);
    }//GEN-LAST:event_jTabbedPane2StateChanged

    
    void createPieChart() throws SQLException{
        DefaultPieDataset dataset = new DefaultPieDataset();
        String tmp="select polarity, count(polarity) from tweets where moviename='"+movieName+"' group by polarity";
        ResultSet rs=stmt.executeQuery(tmp);
        while(rs.next()){
            int a=rs.getInt(1);
            switch(a){
                case 4:
                    dataset.setValue("Very Positive" , rs.getInt(2));
                    break;
                case 3:
                    dataset.setValue("Positive" , rs.getInt(2));
                    break;
                case 2:
                    dataset.setValue("Neutral" , rs.getInt(2));
                    break;
                case 1:
                    dataset.setValue("Negative" , rs.getInt(2));
                    break;
                case 0:
                    dataset.setValue("Very Negative" , rs.getInt(2));
                    break;
            }
        }
        
        JFreeChart chart = ChartFactory.createPieChart("Sentiment Analysis", dataset, true, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        chart.setBorderVisible(false);
        PiePlot p=(PiePlot)chart.getPlot();
        //p.setSectionPaint("Very Negative", Color.BLACK);
        p.setSectionPaint("Negative", Color.BLACK);
        //p.setSectionPaint("Neutral", new Color(3, 156, 248));
        //p.setSectionPaint("Positive", new Color(96, 194, 253));
        //p.setSectionPaint("Very Positive", new Color(0xffffff));
        p.setSimpleLabels(true);
        ChartUtilities.applyCurrentTheme(chart);
        if(pieChart==null){
            pieChart=new ChartPanel(chart);
            sentimentPanel.add(pieChart);
        }else{
            sentimentPanel.remove(pieChart);
            pieChart=new ChartPanel(chart);
            sentimentPanel.add(pieChart);
        }
        pieChart.setBackground(Color.WHITE);
        sentimentPanel.validate();
    }
    
    void createCountryPieChart() throws SQLException{
        DefaultPieDataset dataset = new DefaultPieDataset();
        String tmp="select country, count(country) from tweets where moviename='"+movieName+"' group by country";
        ResultSet rs=stmt.executeQuery(tmp);
        while(rs.next()){
            String tmp1=rs.getString(1);
            if(tmp1.equals(""))tmp1="Others";
            dataset.setValue(tmp1 , rs.getInt(2));
        }
        
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0.00%"));
        
        JFreeChart chart = ChartFactory.createPieChart("Tweets by Country", dataset, true, true, false);
        PiePlot p=(PiePlot)chart.getPlot();
        p.setSectionPaint("Others", Color.gray);
        //p.setSectionPaint(KEY2, Color.red);
        //p.setExplodePercent(KEY1, 0.10);
        p.setSimpleLabels(true);
        p.setLabelGenerator(gen);
        if(countryPieChart==null){
            countryPieChart=new ChartPanel(chart);
            countryPanel.add(countryPieChart);
        }else{
            countryPanel.remove(countryPieChart);
            countryPieChart=new ChartPanel(chart);
            countryPanel.add(countryPieChart);
        }
        
        countryPanel.validate();
    }
    
    private void hypeAnalysisPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_hypeAnalysisPanelComponentShown

    }//GEN-LAST:event_hypeAnalysisPanelComponentShown

    private void predictionPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_predictionPanelComponentShown

    }//GEN-LAST:event_predictionPanelComponentShown

    void showAllTweets(){
        try{
            ResultSet rs=stmt.executeQuery("select tweet, polarity from tweets where moviename='"+movieName+"' LIMIT "+tweetShownCount+", 30");
            int i=0;
            while(rs.next()){
                tweetTable.setValueAt(rs.getString(1), i, 0);
                tweetTable.setValueAt(rs.getInt(2), i, 1);
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    private void tweetsPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tweetsPanelComponentShown
        
    }//GEN-LAST:event_tweetsPanelComponentShown

    private void timeLinePanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_timeLinePanelComponentShown
        
        /*rate=new TimeSeries("Total count", Second.class);
        rate.setMaximumItemAge(15);
         //TimeSeriesCollection dataset = new TimeSeriesCollection();
        //dataset.addSeries(rate);
         DateAxis domain = new DateAxis("Time");
        NumberAxis range = new NumberAxis("Tweet Count");
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, Color.red);
        renderer.setSeriesPaint(1, Color.green);
        
        renderer.setSeriesStroke(0, new BasicStroke(3f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));
        renderer.setSeriesStroke(1, new BasicStroke(3f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));
        XYPlot plot = new XYPlot(timeLinePlot(), domain, range, renderer);
        domain.setAutoRange(true);
        domain.setLowerMargin(0.0);
        domain.setUpperMargin(0.0);
        domain.setTickLabelsVisible(true);
        range.setTickLabelsVisible(true);

        plot.setDomainGridlinesVisible(true);
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        renderer.setBaseItemLabelsVisible(true);

        JFreeChart chart = new JFreeChart("Tweets Per Second",
                new Font("SansSerif", Font.BOLD, 24), plot, true);
         chart.setBackgroundPaint(Color.blue);

        ChartUtilities.applyCurrentTheme(chart);
        chart.setBorderVisible(false);
        chart.setBorderPaint(null);
        domain.setTickLabelInsets(RectangleInsets.ZERO_INSETS);
        range.setTickMarksVisible(false);
        range.setTickLabelInsets(RectangleInsets.ZERO_INSETS);
        domain.setTickMarksVisible(false);
        chart.setPadding(RectangleInsets.ZERO_INSETS);

        ChartPanel chartPanel = new ChartPanel(chart, true);
        chartPanel.setBorder(null);
        
        timeLinePanel.add(chartPanel,BorderLayout.CENTER);
        timeLinePanel.validate();
      */
        
    }//GEN-LAST:event_timeLinePanelComponentShown

    
    private TimeSeriesCollection timeLinePlot( )
   {
      final TimeSeries relaxed = new TimeSeries("Relaxed");
      final TimeSeries happy = new TimeSeries("Happy");
      final TimeSeries unhappy = new TimeSeries("Unhappy");
      final TimeSeries upset = new TimeSeries("Upset");
      int r=0, h=0, u=0, p=0;
      ResultSet rs=null;//Regression.getOneHourTweets(15, movieName);
      try{
          while(rs.next()){
              int tmp=rs.getInt(4);
              if(tmp>0.5){
                  r++;
              }else if(tmp>=0){
                  h++;
              }else if(tmp>-0.5){
                  u++;
              }else{
                  p++;
              }
          }
      }catch(SQLException e){
          
      }
      relaxed.add(new Second(), r);
      happy.add(new Second(), h);
      unhappy.add(new Second(), -u);
      upset.add(new Second(), -p);
      
      final TimeSeriesCollection dataset = new TimeSeriesCollection( );
      dataset.addSeries(relaxed);
      dataset.addSeries(happy);
      dataset.addSeries(unhappy);
      dataset.addSeries(upset);
      return dataset;
   }
    
    void liveTweetAnalysis(){
        new DataGenerator(1000).start();
        rate=new TimeSeries("Total count", Second.class);
        rate.setMaximumItemAge(15);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(rate);
        DateAxis domain = new DateAxis("Time");
        NumberAxis range = new NumberAxis("Tweet Count");
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(3f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));
        renderer.setSeriesStroke(1, new BasicStroke(3f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));
        XYPlot plot = new XYPlot(dataset, domain, range, renderer);
        domain.setAutoRange(true);
        domain.setLowerMargin(0.0);
        domain.setUpperMargin(0.0);
        domain.setTickLabelsVisible(true);
        range.setTickLabelsVisible(true);

        plot.setDomainGridlinesVisible(false);
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        renderer.setBaseItemLabelsVisible(true);

        JFreeChart liveTweetAnalysisChart = new JFreeChart("Tweets Per Second", new Font("Tahoma", Font.BOLD, 24), plot, true);
        liveTweetAnalysisChart.setBorderVisible(false);
        liveTweetAnalysisChart.setBorderPaint(null);

        ChartUtilities.applyCurrentTheme(liveTweetAnalysisChart);
        
        domain.setTickLabelInsets(RectangleInsets.ZERO_INSETS);
        range.setTickMarksVisible(false);
        range.setTickLabelInsets(RectangleInsets.ZERO_INSETS);
        domain.setTickMarksVisible(false);
        liveTweetAnalysisChart.setPadding(RectangleInsets.ZERO_INSETS);

        ChartPanel liveTweetAnalysisChartPanel = new ChartPanel(liveTweetAnalysisChart, true);
        liveTweetAnalysisChartPanel.setBorder(null);
        
        liveTweetsAnalysisPanel.add(liveTweetAnalysisChartPanel,BorderLayout.CENTER);
        liveTweetsAnalysisPanel.validate();
    }
    
    /*ChartPanel drawHistogram(double[][] values){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(values[0][0], Integer.valueOf(1), Integer.valueOf(1));
        
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, Color.red);
        
        renderer.setSeriesStroke(0, new BasicStroke(3f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));
        renderer.setSeriesStroke(1, new BasicStroke(3f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));
        NumberAxis tmp=new NumberAxis("Frequency");
        tmp.setTickUnit(new NumberTickUnit(1.0));
        NumberAxis tmp2=new NumberAxis("Tweet Count");
        tmp2.setTickUnit(new NumberTickUnit(1.0));
        //XYPlot plot = null;//new XYPlot(dataset, tmp, tmp2, renderer);

        //plot.setDomainGridlinesVisible(false);
        JFreeChart liveTweetAnalysisChart =ChartFactory.createBarChart("Tweets Per Second", "Frequency", "Tweet Count", 
                dataset, PlotOrientation.VERTICAL, true, true, false);
        liveTweetAnalysisChart.setBorderPaint(null);
        
        liveTweetAnalysisChart.setPadding(RectangleInsets.ZERO_INSETS);
        liveTweetAnalysisChart.setBackgroundPaint(Color.WHITE);
        liveTweetAnalysisChart.setBorderVisible(false);
        ChartUtilities.applyCurrentTheme(liveTweetAnalysisChart);
        ChartPanel liveTweetAnalysisChartPanel = new ChartPanel(liveTweetAnalysisChart, true);
        liveTweetAnalysisChartPanel.setBorder(null);
        
        return liveTweetAnalysisChartPanel;
    }*/
    
    private void liveTweetsAnalysisPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_liveTweetsAnalysisPanelComponentShown

    }//GEN-LAST:event_liveTweetsAnalysisPanelComponentShown

    private void timeLinePanelComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_timeLinePanelComponentHidden

    }//GEN-LAST:event_timeLinePanelComponentHidden

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        tweetShownCount-=30;
        if(tweetShownCount<0)
            tweetShownCount=0;
    }//GEN-LAST:event_prevButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        tweetShownCount+=30;
    }//GEN-LAST:event_nextButtonActionPerformed

    
    private void getPredictionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getPredictionButtonActionPerformed
        List l=null;
        try{
            ResultSet rs=stmt.executeQuery("select releasedate from movienames where moviename='"+movieName+"'");
            rs.next();
            java.sql.Date d=rs.getDate(1),
                    d1=new java.sql.Date(new java.util.Date().getTime());
            if(d1.getTime()-d.getTime() > 86400000){
                JOptionPane.showMessageDialog(null, "Prediction can only be made at release date");
                return;
            }
            l=myRegression.getRegressionValues();
            predictionTextArea.setText("");
            predictionTextArea.append("The current regression co-efficients are as follows:\n");
            predictionTextArea.append("βa="+l.get(0)+"\n");
            //predictionTextArea.append("βp="+l.get(1)+"\n");
            //predictionTextArea.append("βd="+l.get(2)+"\n");
            //predictionTextArea.append("βc="+l.get(3)+"\n");
            //predictionTextArea.append("βe="+l.get(4)+"\n");
            //predictionTextArea.append("βs="+l.get(5)+"\n");
            predictionTextArea.append("ε="+l.get(6)+"\n");
            predictionTextArea.append("With these co-efficients we predict the *OPENING WEEKEND GROSS* of the movie `"+movieName+"` using the multiple linear regression equation:\n");
            //predictionTextArea.append("\tI=βa*A+βp*P+βd*D+βc*C+βe*E+βs*S+ε\n");
            predictionTextArea.append("\tI=βa*(A+D)+ε\n");
            predictionTextArea.append("Estimated Gross in USD "+myRegression.getPrediction(movieName));
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_getPredictionButtonActionPerformed

    private void theatreTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_theatreTextFieldMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_theatreTextFieldMouseClicked

    private void theatreTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_theatreTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_theatreTextFieldActionPerformed

    private void theatreTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_theatreTextFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_theatreTextFieldFocusGained

    
     class DataGenerator extends Timer implements ActionListener {

         int prev;
        DataGenerator(int interval) {
            super(interval, null);
            addActionListener(this);
        }
        
         @Override
        public void actionPerformed(ActionEvent event) {
            addRateObservation(myTwitter.totalProcessedTweets-prev);
            prev=myTwitter.totalProcessedTweets;
        }

    }
     
     void addRateObservation(double i){
         rate.addOrUpdate(new Second(), i);
     }
    
    
    
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
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ContentsMenuItem;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JTextField actorNameTextField;
    private javax.swing.JTextField actorTwitterHandleTextField;
    private javax.swing.JButton addButton;
    private javax.swing.JCheckBox cat1CheckBox;
    private javax.swing.JCheckBox cat2CheckBox;
    private javax.swing.JCheckBox cat3CheckBox;
    private javax.swing.JCheckBox cat4CheckBox;
    private javax.swing.JCheckBox cat5CheckBox;
    private javax.swing.ButtonGroup categoryButtonGroup;
    private javax.swing.JPanel countryPanel;
    private javax.swing.JLabel databaseConnectionLabel;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton getPredictionButton;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel hypeAnalysisPanel;
    private javax.swing.JLabel internetConnectionLabel;
    private javax.swing.ButtonGroup isSequelButtonGroup;
    private javax.swing.JRadioButton isSequelNoRadioButton;
    private javax.swing.JRadioButton isSequelYesRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel liveTweetsAnalysisPanel;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JTextField movieNameTextField;
    private javax.swing.JComboBox movieNamesComboBox;
    private javax.swing.JButton nextButton;
    private javax.swing.JPanel predictionPanel;
    private javax.swing.JTextArea predictionTextArea;
    private javax.swing.JButton prevButton;
    private javax.swing.JFormattedTextField releaseDateTextField;
    private javax.swing.JButton scheduleButton;
    private javax.swing.JPanel sentimentPanel;
    private javax.swing.JCheckBoxMenuItem startStreamingCheckBoxMenuItem;
    private javax.swing.JTextField theatreTextField;
    private javax.swing.JPanel timeLinePanel;
    private javax.swing.JTable tweetTable;
    private javax.swing.JPanel tweetsPanel;
    private javax.swing.JLabel twitterStatusLabel;
    // End of variables declaration//GEN-END:variables
}

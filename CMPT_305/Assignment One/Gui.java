/**
 * Property Assessment Viewer GUI
 * 
 * This program provides a graphical user interface (GUI) for viewing property assessments.
 * It allows users to upload a CSV file containing property assessment data, search for 
 * specific account numbers, and view statistics and charts for different assessment classes 
 * and neighborhoods.
 * 
 * Features:
 * - File selection and upload functionality
 * - Display of main assessment statistics (record count, min, max, median, mean)
 * - Dropdown menus for selecting assessment classes and neighborhoods
 * - Display of statistics and charts for selected assessment classes and neighborhoods
 * - Search functionality for specific account numbers
 * 
 * Dependencies:
 * - javax.swing for GUI components
 * - java.awt for layout and event handling
 * - org.jfree.chart for creating charts
 * 
 * Author: Alec Ortiz
 * Date: October 20th, 2024
 */


 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.ArrayList;
import java.util.List;

public class Gui implements ActionListener {
    // GUI components
    private JComboBox<String> assessmentClassComboBox;
    private JComboBox<String> neighborhoodComboBox;
    private boolean uploadSafe = false;
    private JLabel mainTitle;
    private JFrame frame;
    private JPanel panel;
    private String filePath;
    private String fileName;
    private String neighborhoodString;
    private JLabel filePathLabel;
    private JLabel assessmentClassLabel;
    private JLabel assessmentClassAMTLabel;
    private JLabel assessmentClassMinLabel;
    private JLabel assessmentClassMaxLabel;
    private JLabel assessmentClassMedianLabel;
    private JLabel assessmentClassMeanLabel;
    private JLabel neighborhoodAMTLabel;
    private JLabel neighborhoodMinLabel;
    private JLabel neighborhoodMaxLabel;
    private JLabel neighborhoodMedianLabel;
    private JLabel neighborhoodMeanLabel;
    private JLabel mainAssessmentAMTLabel;
    private JLabel mainAssessmentMinLabel;
    private JLabel mainAssessmentMaxLabel;
    private JLabel mainAssessmentMedianLabel;
    private JLabel mainAssessmentMeanLabel;
    private JLabel accountSearchAccountLabel;
    private JLabel accountSearchAssessedValLabel;
    private JLabel accountSearchNeighborhoodLabel;
    private JLabel accountSearchWardLabel;
    private JLabel accountSearchLocationLabel;
    private JTextField accountSearchInput;
    private JButton accountSearchButton;
    private JLabel fileTitle;
    private JLabel neighborhoodLabel;
    private JButton uploadButton;
    private JButton chooseFileButton;
    private JButton neighborhoodSearchButton;
    private JButton assessmentClassButton;
    private int frameWidth = 500;
    private int frameHeight = 250;
    private ChartPanel assessmentchartPanelOne;
    private ChartPanel assessmentchartPanelTwo;
    private ChartPanel neighborhoodchartPanelOne;
    private ChartPanel neighborhoodchartPanelTwo;
    private PropertyAssessments mainAssessment;
    private PropertyAssessments neighborhoodAssessment;
    private PropertyAssessments assessmentClassAssessment;

    public Gui() {
        fileName = "";
        frame = new JFrame("Property Assessment Viewer");
        frame.setSize(frameWidth, frameHeight);
        panel = new JPanel();
        mainTitle = new JLabel("Property Assessment Program");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 20));
        mainTitle.setBounds(25, 10, 300, 100);
        chooseFileButton = new JButton("Choose File");
        chooseFileButton.setBounds(50, 100, 100, 25);
        uploadButton = new JButton("Upload File");
        uploadButton.setBounds(50, 130, 100, 25);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));
        filePathLabel = new JLabel("No file Selected...");
        filePathLabel.setBounds(160, 130, 500, 25);
        frame.add(panel, BorderLayout.CENTER);
        panel.add(chooseFileButton);
        panel.add(filePathLabel);
        panel.add(uploadButton);
        panel.add(mainTitle);
        ImageIcon icon = new ImageIcon("PropertyAssessmentViewerLogo.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        chooseFileButton.addActionListener(this);
        uploadButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Choose File")) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                filePath = fileChooser.getSelectedFile().getAbsolutePath();
                fileName = getFileName(filePath);
                filePathLabel.setText("Selected File: " + fileName);
            }
        } else if (e.getActionCommand().equals("Upload File")) {
            if (filePath != null && filePath.toLowerCase().endsWith(".csv")) {
                uploadSafe = true;
                mainAssessment = new PropertyAssessments();
                mainAssessment = mainAssessment.createAssessment(filePath);
                clearScreen();
                accountSearchInput = new JTextField();
                accountSearchInput.setBounds(1210, 60, 300, 25);
                accountSearchButton = new JButton("Search Account Number");
                accountSearchButton.setBounds(1520, 60, 200, 25);
                accountSearchButton.addActionListener(this);
                mainAssessmentAMTLabel = new JLabel("There are " + mainAssessment.getRecordAmt() + " records in the main assessment.");
                mainAssessmentMinLabel = new JLabel("The minimum assessed value in the main assessment is " + mainAssessment.getMin() + ".");
                mainAssessmentMaxLabel = new JLabel("The maximum assessed value in the main assessment is " + mainAssessment.getMax() + ".");
                mainAssessmentMedianLabel = new JLabel("The median assessed value in the main assessment is " + mainAssessment.calcMedian() + ".");
                mainAssessmentMeanLabel = new JLabel("The mean assessed value in the main assessment is " + mainAssessment.calcMean() + ".");
                mainAssessmentAMTLabel.setBounds(370, 40, 500, 50);
                mainAssessmentMinLabel.setBounds(370, 60, 500, 50);
                mainAssessmentMaxLabel.setBounds(370, 80, 500, 50);
                mainAssessmentMedianLabel.setBounds(370, 100, 500, 50);
                mainAssessmentMeanLabel.setBounds(370, 120, 500, 50);
                List<String> assessmentOptionsList = new ArrayList<>();
                assessmentOptionsList = mainAssessment.getClasses();
                String[] options = new String[assessmentOptionsList.size()];
                options = assessmentOptionsList.toArray(options);
                assessmentClassComboBox = new JComboBox<String>(options);
                assessmentClassComboBox.setBounds(100, 330, 250, 25);
                panel.add(assessmentClassComboBox);
                assessmentClassComboBox.addActionListener(this);
                List<String> neighborhoodOptionsList = new ArrayList<>();
                neighborhoodOptionsList = mainAssessment.getNeighborhoods();
                String[] neighborhoodOptions = new String[neighborhoodOptionsList.size()];
                neighborhoodOptions = neighborhoodOptionsList.toArray(neighborhoodOptions);
                neighborhoodComboBox = new JComboBox<String>(neighborhoodOptions);
                neighborhoodComboBox.setBounds(100, 610, 250, 25);
                panel.add(neighborhoodComboBox);
                neighborhoodComboBox.addActionListener(this);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                assessmentClassLabel = new JLabel("Assessment Class: No Class Selected");
                assessmentClassLabel.setFont(new Font("Arial", Font.BOLD, 20));
                assessmentClassLabel.setBounds(50, 300, 500, 25);
                assessmentClassButton = new JButton("Search Assessment Class");
                assessmentClassButton.setBounds(320, 330, 200, 25);
                assessmentClassButton.addActionListener(this);
                neighborhoodLabel = new JLabel("Neighborhood: No Neighborhood Selected");
                neighborhoodLabel.setFont(new Font("Arial", Font.BOLD, 20));
                neighborhoodLabel.setBounds(50, 575, 500, 25);
                neighborhoodSearchButton = new JButton("Search Neighborhood");
                neighborhoodSearchButton.setBounds(320, 610, 200, 25);
                neighborhoodSearchButton.addActionListener(this);
                fileTitle = new JLabel("File: " + fileName);
                fileTitle.setFont(new Font("Arial", Font.BOLD, 30));
                fileTitle.setBounds(600, 10, 1000, 30);
                panel.add(fileTitle);
                filePathLabel.setText("No file Selected...");
                filePathLabel.setBounds(140, 30, 500, 25);
                chooseFileButton.setBounds(30, 5, 100, 25);
                uploadButton.setBounds(30, 30, 100, 25);
                DefaultCategoryDataset maxMinDataset = new DefaultCategoryDataset();
                DefaultCategoryDataset meanMedDataset = new DefaultCategoryDataset();
                meanMedDataset.addValue(mainAssessment.calcMean(), "Mean", "Mean");
                meanMedDataset.addValue(mainAssessment.calcMedian(), "Median", "Median");
                maxMinDataset.addValue(mainAssessment.getMin(), "Min", "Min");
                maxMinDataset.addValue(mainAssessment.getMax(), "Max", "Max");
                JFreeChart mainAssessmentChart = ChartFactory.createBarChart("", "", "Assessed Value", maxMinDataset);
                JFreeChart meanMedChart = ChartFactory.createBarChart("", "", "Assessed Value", meanMedDataset);
                ChartPanel chartPanel = new ChartPanel(mainAssessmentChart);
                ChartPanel chartPanel2 = new ChartPanel(meanMedChart);
                chartPanel2.setBounds(186, 60, 175, 175);
                chartPanel.setBounds(10, 60, 175, 175);
                panel.add(chartPanel);
                panel.add(chartPanel2);
                panel.revalidate();
                panel.repaint();
                panel.add(chooseFileButton);
                panel.add(filePathLabel);
                panel.add(uploadButton);
                panel.add(neighborhoodLabel);
                panel.add(assessmentClassLabel);
                panel.add(mainAssessmentAMTLabel);
                panel.add(mainAssessmentMinLabel);
                panel.add(mainAssessmentMaxLabel);
                panel.add(mainAssessmentMedianLabel);
                panel.add(mainAssessmentMeanLabel);
                panel.add(accountSearchInput);
                panel.add(accountSearchButton);
                filePath = null;
            } else if (filePath == null) {
                JOptionPane.showMessageDialog(frame, "File not found. Choose again!", "Error", JOptionPane.ERROR_MESSAGE);
                filePathLabel.setText("No file Selected...");
            } else {
                JOptionPane.showMessageDialog(frame, "File not CSV file. Choose again!", "Error", JOptionPane.ERROR_MESSAGE);
                filePathLabel.setText("No file Selected...");
            }
        } else if (e.getSource() == assessmentClassComboBox) {
            JComboBox cb = (JComboBox) e.getSource();
            String assessmentClassString = (String) cb.getSelectedItem();
            assessmentClassLabel.setText("Assessment Class: " + assessmentClassString.toUpperCase());
            assessmentClassAssessment = new PropertyAssessments();
            assessmentClassAssessment = mainAssessment.searchAssessmentClass(assessmentClassString);
            if (assessmentClassAMTLabel != null) {
                panel.remove(assessmentClassAMTLabel);
                panel.remove(assessmentClassMinLabel);
                panel.remove(assessmentClassMaxLabel);
                panel.remove(assessmentClassMedianLabel);
                panel.remove(assessmentClassMeanLabel);
            }
            assessmentClassAMTLabel = new JLabel("There are " + assessmentClassAssessment.getRecordAmt() + " records in the " + assessmentClassString + " class.");
            assessmentClassMinLabel = new JLabel("The minimum assessed value in the " + assessmentClassString + " class is " + assessmentClassAssessment.getMin() + ".");
            assessmentClassMaxLabel = new JLabel("The maximum assessed value in the " + assessmentClassString + " class is " + assessmentClassAssessment.getMax() + ".");
            assessmentClassMedianLabel = new JLabel("The median assessed value in the " + assessmentClassString + " class is " + assessmentClassAssessment.calcMedian() + ".");
            assessmentClassMeanLabel = new JLabel("The mean assessed value in the " + assessmentClassString + " class is " + assessmentClassAssessment.calcMean() + ".");
            assessmentClassAMTLabel.setBounds(370, 340, 500, 50);
            assessmentClassMinLabel.setBounds(370, 360, 500, 50);
            assessmentClassMaxLabel.setBounds(370, 380, 500, 50);
            assessmentClassMedianLabel.setBounds(370, 400, 500, 50);
            assessmentClassMeanLabel.setBounds(370, 420, 500, 50);
            DefaultCategoryDataset maxMinDataset = new DefaultCategoryDataset();
            DefaultCategoryDataset meanMedDataset = new DefaultCategoryDataset();
            meanMedDataset.addValue(assessmentClassAssessment.calcMean(), "Mean", "Mean");
            meanMedDataset.addValue(assessmentClassAssessment.calcMedian(), "Median", "Median");
            maxMinDataset.addValue(assessmentClassAssessment.getMin(), "Min", "Min");
            maxMinDataset.addValue(assessmentClassAssessment.getMax(), "Max", "Max");
            JFreeChart maxMinChart = ChartFactory.createBarChart("", "", "Assessed Value", maxMinDataset);
            JFreeChart meanMedChart = ChartFactory.createBarChart("", "", "Assessed Value", meanMedDataset);
            if (assessmentchartPanelOne != null) {
                panel.remove(assessmentchartPanelOne);
            }
            if (assessmentchartPanelTwo != null) {
                panel.remove(assessmentchartPanelTwo);
            }
            assessmentchartPanelOne = new ChartPanel(maxMinChart);
            assessmentchartPanelTwo = new ChartPanel(meanMedChart);
            assessmentchartPanelOne.setBounds(10, 360, 175, 175);
            assessmentchartPanelTwo.setBounds(186, 360, 175, 175);
            panel.add(assessmentchartPanelOne);
            panel.add(assessmentchartPanelTwo);
            panel.add(assessmentClassAMTLabel);
            panel.add(assessmentClassMinLabel);
            panel.add(assessmentClassMaxLabel);
            panel.add(assessmentClassMedianLabel);
            panel.add(assessmentClassMeanLabel);
            panel.revalidate();
            panel.repaint();
        } else if (e.getSource() == neighborhoodComboBox) {
            JComboBox cb = (JComboBox) e.getSource();
            String neighborhoodString = (String) cb.getSelectedItem();
            neighborhoodLabel.setText("Neighborhood: " + neighborhoodString.toUpperCase());
            neighborhoodAssessment = new PropertyAssessments();
            neighborhoodAssessment = mainAssessment.searchNeighborhood(neighborhoodString);
            if (neighborhoodAMTLabel != null) {
                panel.remove(neighborhoodAMTLabel);
                panel.remove(neighborhoodMinLabel);
                panel.remove(neighborhoodMaxLabel);
                panel.remove(neighborhoodMedianLabel);
                panel.remove(neighborhoodMeanLabel);
            }
            neighborhoodAMTLabel = new JLabel("There are " + neighborhoodAssessment.getRecordAmt() + " records in the " + neighborhoodString + " neighborhood.");
            neighborhoodMinLabel = new JLabel("The minimum assessed value in the " + neighborhoodString + " neighborhood is " + neighborhoodAssessment.getMin() + ".");
            neighborhoodMaxLabel = new JLabel("The maximum assessed value in the " + neighborhoodString + " neighborhood is " + neighborhoodAssessment.getMax() + ".");
            neighborhoodMedianLabel = new JLabel("The median assessed value in the " + neighborhoodString + " neighborhood is " + neighborhoodAssessment.calcMedian() + ".");
            neighborhoodMeanLabel = new JLabel("The mean assessed value in the " + neighborhoodString + " neighborhood is " + neighborhoodAssessment.calcMean() + ".");
            neighborhoodAMTLabel.setBounds(370, 640, 500, 50);
            neighborhoodMinLabel.setBounds(370, 660, 500, 50);
            neighborhoodMaxLabel.setBounds(370, 680, 500, 50);
            neighborhoodMedianLabel.setBounds(370, 700, 500, 50);
            neighborhoodMeanLabel.setBounds(370, 720, 500, 50);
            DefaultCategoryDataset maxMinDataset = new DefaultCategoryDataset();
            DefaultCategoryDataset meanMedDataset = new DefaultCategoryDataset();
            meanMedDataset.addValue(neighborhoodAssessment.calcMean(), "Mean", "Mean");
            meanMedDataset.addValue(neighborhoodAssessment.calcMedian(), "Median", "Median");
            maxMinDataset.addValue(neighborhoodAssessment.getMin(), "Min", "Min");
            maxMinDataset.addValue(neighborhoodAssessment.getMax(), "Max", "Max");
            JFreeChart maxMinChart = ChartFactory.createBarChart("", "", "Assessed Value", maxMinDataset);
            JFreeChart meanMedChart = ChartFactory.createBarChart("", "", "Assessed Value", meanMedDataset);
            if (neighborhoodchartPanelOne != null) {
                panel.remove(neighborhoodchartPanelOne);
            }
            if (neighborhoodchartPanelTwo != null) {
                panel.remove(neighborhoodchartPanelTwo);
            }
            neighborhoodchartPanelOne = new ChartPanel(maxMinChart);
            neighborhoodchartPanelTwo = new ChartPanel(meanMedChart);
            neighborhoodchartPanelOne.setBounds(10, 650, 175, 175);
            neighborhoodchartPanelTwo.setBounds(186, 650, 175, 175);
            panel.add(neighborhoodchartPanelOne);
            panel.add(neighborhoodchartPanelTwo);
            panel.add(neighborhoodAMTLabel);
            panel.add(neighborhoodMinLabel);
            panel.add(neighborhoodMaxLabel);
            panel.add(neighborhoodMedianLabel);
            panel.add(neighborhoodMeanLabel);
            panel.revalidate();
            panel.repaint();
        } else if (e.getSource() == accountSearchButton) {
            String accountNumber = accountSearchInput.getText();
            if (accountNumber != null) {
                try {
                    accountSearchInput.setText("");
                    PropertyAssessment assessment = mainAssessment.searchAccount(Integer.parseInt(accountNumber));
                    accountSearchAccountLabel = new JLabel("Account Number: " + assessment.getAccountNum());
                    accountSearchAssessedValLabel = new JLabel("Assessed Value: " + assessment.getAssessedValue());
                    accountSearchNeighborhoodLabel = new JLabel("Neighborhood: " + assessment.getNeighborhood());
                    accountSearchLocationLabel = new JLabel("Location: " + assessment.getLocation());
                    accountSearchWardLabel = new JLabel("Ward: " + assessment.getWardClass());

                    if (accountSearchAccountLabel != null) {
                        panel.remove(accountSearchAccountLabel);
                        panel.remove(accountSearchAssessedValLabel);
                        panel.remove(accountSearchNeighborhoodLabel);
                        panel.remove(accountSearchWardLabel);
                        panel.remove(accountSearchLocationLabel);
                    }

                    accountSearchAssessedValLabel.setBounds(1210, 100, 500, 50);
                    accountSearchNeighborhoodLabel.setBounds(1210, 120, 500, 50);
                    accountSearchWardLabel.setBounds(1210, 140, 500, 50);
                    accountSearchLocationLabel.setBounds(1210, 160, 500, 50);
                    accountSearchAccountLabel.setBounds(1210, 80, 500, 50);
                    panel.add(accountSearchAccountLabel);
                    panel.add(accountSearchAssessedValLabel);
                    panel.add(accountSearchNeighborhoodLabel);
                    panel.add(accountSearchWardLabel);
                    panel.add(accountSearchLocationLabel);
                    panel.revalidate();
                    panel.repaint();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Account Number not found. Choose again!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private String getFileName(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }

    private void clearScreen() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        new Gui();
    }
}

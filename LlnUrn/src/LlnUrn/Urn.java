package LlnUrn;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Urn implements ActionListener {
    //ATTRIBUTES
    
    //main frame
    private final JFrame frame;
    
    //panels
    private final JPanel explanationPanel;
    private final JPanel probabilitiesPanel;
    private final JPanel mainPanel;
    
    //various components
    private final JTextArea explanationTextArea;
    private final JButton closeExplanationButton;
    private final JButton openExplanationButton;
    private final JLabel theoricalProbabilityLabel;
    private final JLabel label1;
    private final JLabel label2;
    private final JLabel practicalProbabilityLabel;
    private final JTextField extractionsTextField;
    private final JLabel extractionsLabel;
    private final JLabel redBallsNumberLabel;
    private final JLabel blueBallsNumberLabel;
    private final JButton extractButton;
    private final JLabel warningLabel;
        
    //fonts
    private final Font explanationFont = new Font("Segoe", Font.BOLD, 15);
    private final Font probabilityFont = new Font("Segoe", Font.BOLD, 25);
    private final Font labelsFont = new Font ("Segoe", Font.BOLD, 11);
    
    //border
    private final Border empty = BorderFactory.createEmptyBorder();
    
    //colors
    private final Color explanationColor = new Color(81,91,255);
    private final Color probabilitiesColor = new Color(120,139,255);
    private final Color mainColor = new Color(155,177,255);
    
    //icons
    private final ImageIcon warning = new ImageIcon(getClass().getResource("warning.png"));
    
    //random
    private Random random = new Random();
    
    //back-end variables
    private long numberOfExtractions = 0;
    private int numberOfBalls = 2000;
    private int numberOfRedBalls = 200;
    private long redBallsExtracted = 0;
    private long blueBallsExtracted = 0;
    private long practicalProbability;
    
    //CONSTRUCTOR
    public Urn() {
        
        //setup of the window
        frame = new JFrame("Law of large numbers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setLayout(null);
        frame.setResizable(false);
        
        //explanationPanel
        explanationPanel = new JPanel();
        explanationPanel.setBackground(explanationColor);
        explanationPanel.setLayout(null);
        explanationPanel.setVisible(true);
        explanationPanel.setBounds(0, 0, 265, 320);
        
        //mainPanel
        mainPanel = new JPanel();
        mainPanel.setBackground(mainColor);
        mainPanel.setLayout(null);
        mainPanel.setVisible(false);
        mainPanel.setBounds(0, 0, 265, 320);
        
        //probabilitiesPanel
        probabilitiesPanel = new JPanel();
        probabilitiesPanel.setBackground(probabilitiesColor);
        probabilitiesPanel.setLayout(null);
        probabilitiesPanel.setBounds(265, 0, 135, 320);
        
        //textArea
        explanationTextArea = new JTextArea("In this urn there are 2000 balls,\n1800 are blue and 200 are red.\nThe theorical probability of picking\na red ball is 10%, but in reality only\npicking 10 balls we might have a\ndifferent result. That is called\npractical probability. According to\nthe law of large numbers, by\npicking a higher number of balls\nthe practical probability will tend to\nthe theorical probability. This\nsimple program verify that.");
        explanationTextArea.setFont(explanationFont);
        explanationTextArea.setBounds(0, 0, 265, 240);
        explanationTextArea.setEditable(false);
        explanationTextArea.setOpaque(false);       
        
        //closeExplanationButton
        closeExplanationButton = new JButton("Close");
        closeExplanationButton.setBorder(empty);
        closeExplanationButton.setBounds(187, 247, 70, 20);
        closeExplanationButton.addActionListener(this);
        
        //openExplanationButton
        openExplanationButton = new JButton("Explanation");
        openExplanationButton.setBorder(empty);
        openExplanationButton.setBounds(5,5,110,20);
        openExplanationButton.addActionListener(this);
        
        //label1
        label1 = new JLabel("Theorical Probability: ");
        label1.setFont(labelsFont);
        label1.setBounds(6, 30, 150, 20);
        
        //theoricalProbabilityLabel
        theoricalProbabilityLabel = new JLabel("10%");
        theoricalProbabilityLabel.setFont(probabilityFont);
        theoricalProbabilityLabel.setBounds(39,50, 50, 20);
        
        //label2
        label2 = new JLabel("Practical Probability: ");
        label2.setFont(labelsFont);
        label2.setBounds(8, 120, 150, 20);
        
        //practicalProbabilityLabel
        practicalProbabilityLabel = new JLabel();
        practicalProbabilityLabel.setFont(probabilityFont);
        practicalProbabilityLabel.setBounds(39,140,150,20);
        
        //extractionsTextField
        extractionsTextField = new JTextField();
        extractionsTextField.setBorder(empty);
        extractionsTextField.setBounds(135, 50, 70, 20);
        
        //extractionsLabel
        extractionsLabel = new JLabel("Number of extractions:");
        extractionsLabel.setFont(labelsFont);
        extractionsLabel.setBounds(7, 47, 200, 20);
        
        //warningLabel
        warningLabel = new JLabel("The number of extractions is too low");
        warningLabel.setIcon(warning);
        warningLabel.setFont(labelsFont);
        warningLabel.setVisible(false);
        warningLabel.setBounds(7, 80, 300, 40);
                
        //redBallsLabel
        redBallsNumberLabel = new JLabel("Red balls extracted: " + redBallsExtracted);
        redBallsNumberLabel.setFont(labelsFont);
        redBallsNumberLabel.setBounds(7, 122, 200, 20);
        
        //blueBallsLabel
        blueBallsNumberLabel = new JLabel("Blue balls extracted: " + blueBallsExtracted);
        blueBallsNumberLabel.setFont(labelsFont);
        blueBallsNumberLabel.setBounds(7, 152, 200, 20);
        
        //extractButton
        extractButton = new JButton("Extract");
        extractButton.setBorder(empty);
        extractButton.setBounds(86, 200, 120, 20);
        extractButton.addActionListener(this);
        
        //adding components
        explanationPanel.add(explanationTextArea);
        explanationPanel.add(closeExplanationButton);
        
        mainPanel.add(openExplanationButton);
        mainPanel.add(extractionsTextField);
        mainPanel.add(extractionsLabel);
        mainPanel.add(redBallsNumberLabel);
        mainPanel.add(blueBallsNumberLabel);
        mainPanel.add(extractButton);
        mainPanel.add(warningLabel);
        
        probabilitiesPanel.add(label1);
        probabilitiesPanel.add(theoricalProbabilityLabel);
        probabilitiesPanel.add(label2);
        probabilitiesPanel.add(practicalProbabilityLabel);
        
        frame.add(mainPanel);
        frame.add(explanationPanel);
        frame.add(probabilitiesPanel);
        frame.setVisible(true);
        
    }
    
    public static void main(String[] args) {
        Urn urn = new Urn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == closeExplanationButton){
            
            explanationPanel.setVisible(false);
            mainPanel.setVisible(true);
            
        }
        
        if(e.getSource() == openExplanationButton) {
            
            explanationPanel.setVisible(true);
            mainPanel.setVisible(false);
            
        }
        
        if(e.getSource() == extractButton) {
            
            redBallsExtracted = 0;
            blueBallsExtracted = 0;
            practicalProbabilityLabel.setText("");
            warningLabel.setVisible(false);
            
            
            try{
                
                numberOfExtractions = Long.parseLong(extractionsTextField.getText());
                
                //to verify the law of large number we need a large number of extractions, the higher the better.
                if(numberOfExtractions < 500){
                    warningLabel.setVisible(true);
                }
                
                /*a number that goes from 0 to the number of balls in the urn -1 is extracted,
                if this number is less or equal to the number of red balls -1 the number of red balls extracted is incremented,
                in case of the number being higher the number of blue balls extracted is incremented.*/      
                for(int i = 0; i < numberOfExtractions; i++){
                    if(random.nextInt(numberOfBalls-1) <= (numberOfRedBalls-1))
                        redBallsExtracted++;
                    else
                        blueBallsExtracted++;
                }
                    
                // x:100 = redBallsExtracted:numberOfExtractions
                practicalProbability = redBallsExtracted*100 / numberOfExtractions;
            
            }catch(Exception exc){
                extractionsTextField.setText("Error!");
            }
            
            redBallsNumberLabel.setText("Red balls extracted: " + redBallsExtracted);
            blueBallsNumberLabel.setText("Blue balls extracted: " + blueBallsExtracted);
                       
            practicalProbabilityLabel.setText(practicalProbability + "%");
            
        }
        
    }
    
}

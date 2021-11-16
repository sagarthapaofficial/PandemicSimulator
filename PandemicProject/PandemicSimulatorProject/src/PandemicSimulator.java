import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

/**
 * 
 * Program Name: About.java
 * Purpose: Creates the UI to manage user input, and simulation display. Reports data in real time and controls
 * 	the simulation state.
 * Coded by: 
 * Alfredo Alexander Quele Mendez - Section A
 * Sagar Thapa - Section C
 * James Hill - Section B
 * Lee Hutson - Section A
 *  Date: August 7, 2021
 * 
 */

public class PandemicSimulator extends JFrame
{
	// Declaring varibles
	private static final long serialVersionUID = 1L;
	private static Border blackline = BorderFactory.createLineBorder(Color.black);
	private Container c;
	private JLabel population;
  private JLabel unvaccinated;
  private JLabel oneShot;
  private JLabel twoShots;
  private JLabel naturalImmunity;
  private JLabel realTimeDataTitle;
  private JLabel about;    
  private JPanel realTimeData;
  private JPanel realTimeDataTitleContainer;    
  private JButton startBtn;
  private JButton resumeBtn;
  private JButton pauseBtn;    
  private JSpinner populationQty;
  private JSpinner unvaccinatedQty;
  private JSpinner oneShotQty;
  private JSpinner twoShotsQty;
  private JSpinner naturalImmunityQty;
  private JPanel simulationArea;
  private int PopulationCount_;
	private double unVaccinatedCount_;
	private double TwoShotCount_;
	private double oneshotCount_;
	private double NaturalImmunity_;
  private static JTextArea ShowData;
	private SpinnerListener Spinnnerlistener;
	private int totalPercent=100;
	int populationCount;
	int unVaccinatedCount;
	int oneshotCount;
	int TwoShotCount ;
	int NaturalImmunity; 

    private PersonMovement pm;
    
    
    
    //Constructor
    public PandemicSimulator()
    {
				super("Pandemic Simulator - Tech Raid");
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setSize(1200, 745);
				this.setLocationRelativeTo(null);
		    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		    this.setResizable(false);
		    
		    c = getContentPane();
		    c.setLayout(null);
		    
		    about = new JLabel("About",SwingConstants.CENTER);
		    about.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		    about.setBounds(1092, 2, 68, 25);
		    about.setBorder(blackline);
		    about.setToolTipText("About");
		    c.add(about);
				
				//area to put people/dots
				simulationArea = new JPanel(new FlowLayout());
				simulationArea.setBounds(25,30,740,650);
				simulationArea.setBorder(blackline);
				
				c.add(simulationArea);
				
				//user input area/control area
				JPanel controlArea = new JPanel();
				controlArea.setLayout(null);
				controlArea.setBounds(780,30, 380, 650);
				controlArea.setBorder(blackline);
				
				population = new JLabel("Population: ");
				population.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				population.setBounds(25, 25, 300, 25);
        controlArea.add(population);
        
        unvaccinated = new JLabel("Unvaccinated population: ");
        unvaccinated.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        unvaccinated.setBounds(25, 75, 300, 25);
        controlArea.add(unvaccinated);
        
        oneShot = new JLabel("One-shot population: ");
        oneShot.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        oneShot.setBounds(25, 125, 300, 25);
        controlArea.add(oneShot);
        
        twoShots = new JLabel("Two-shot population: ");
        twoShots.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        twoShots.setBounds(25, 175, 300, 25);
        controlArea.add(twoShots);
        
        naturalImmunity = new JLabel("Natural immunity population: ");
        naturalImmunity.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        naturalImmunity.setBounds(25, 225, 300, 25);
        controlArea.add(naturalImmunity);
		
        realTimeData = new JPanel();
        realTimeData.setLayout(null);
        realTimeData.setBounds(25, 275, 330, 300);
        realTimeData.setBorder(blackline);
        controlArea.add(realTimeData);
        
        realTimeDataTitleContainer = new JPanel();
        realTimeDataTitleContainer.setLayout(null);
        realTimeDataTitleContainer.setBounds(0, 0, 330, 40);
        realTimeDataTitleContainer.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        realTimeData.add(realTimeDataTitleContainer);
        
        realTimeDataTitle = new JLabel("Real Time Data");
        realTimeDataTitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        realTimeDataTitle.setBounds(110, 8, 330, 25);
        realTimeDataTitleContainer.add(realTimeDataTitle);
        
        ShowData = new JTextArea();
        ShowData.setBounds(10, 58, 310, 232);
        ShowData.setEditable(false);
        realTimeData.add(ShowData);
        
        startBtn = new JButton("START");
        startBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        startBtn.setBounds(25, 595, 100, 40);
        controlArea.add(startBtn);
        
        pauseBtn = new JButton("PAUSE");
        pauseBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pauseBtn.setBounds(145, 595, 100, 40);
        controlArea.add(pauseBtn);
        
        resumeBtn = new JButton("RESUME");
        resumeBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        resumeBtn.setBounds(260, 595, 100, 40);
        controlArea.add(resumeBtn);
        
        populationQty = new JSpinner();
        populationQty.setBounds(315, 25, 50, 25);
        controlArea.add(populationQty);
        
        unvaccinatedQty = new JSpinner();
        unvaccinatedQty.setBounds(315, 75, 50, 25);
        controlArea.add(unvaccinatedQty);
        
        oneShotQty = new JSpinner();
        oneShotQty.setBounds(315, 125, 50, 25);
        controlArea.add(oneShotQty);
        
        twoShotsQty = new JSpinner();
        twoShotsQty.setBounds(315, 175, 50, 25);
        controlArea.add(twoShotsQty);
        
        naturalImmunityQty = new JSpinner();
        naturalImmunityQty.setBounds(315, 225, 50, 25);
        controlArea.add(naturalImmunityQty);
       
        
        
		c.add(controlArea);
		
		//listener to invoke About modal
		about.addMouseListener(new MouseAdapter()  
		{
			public void mouseClicked(MouseEvent e)  
		    {
		    	About newAboutPanel = new About();
		    	newAboutPanel.setVisible(true);
		    	newAboutPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    }  
		});
		
		// Btn listener
		Listener listener = new Listener();
		startBtn.addActionListener(listener);
		pauseBtn.addActionListener(listener);
		resumeBtn.addActionListener(listener);


		
		Spinnnerlistener= new SpinnerListener();
		// Adding spinner listeners
		populationQty.addChangeListener(Spinnnerlistener);
		unvaccinatedQty.addChangeListener(Spinnnerlistener);
		oneShotQty.addChangeListener(Spinnnerlistener);
		twoShotsQty.addChangeListener(Spinnnerlistener);
		naturalImmunityQty.addChangeListener(Spinnnerlistener);
		pauseBtn.setEnabled(false);
		resumeBtn.setEnabled(false);	
		this.setVisible(true);
	}
    
    
    /*Method Name: RealTime Data
    *Purpose: TO set real time data
    *Accepts: String value
    *Returns: void
    */
    public static void RealTimeData(String value)
    {
    	ShowData.setText("");
    	ShowData.setText(value);
    
    }
    // SpinnerListener inner class
    public class SpinnerListener implements ChangeListener
  	{

  		@Override
  		public void stateChanged(ChangeEvent e)
  		{
  	
  			if(e.getSource().equals(populationQty))
  			{
  				populationCount=(int) populationQty.getValue();
  			
  			
  			}else if(e.getSource().equals(unvaccinatedQty))
  			{
  				
  				unVaccinatedCount = (int) unvaccinatedQty.getValue();
  				
  				// checks if the inputed number is greater than 100%
  				if(totalPercent>=unVaccinatedCount)
  				{
  					totalPercent-=unVaccinatedCount;
  					
  				}else
  					
  				{
  					 JOptionPane.showMessageDialog(null, "Try number less than or equal to "+ totalPercent,
  					      "Hey!", JOptionPane.ERROR_MESSAGE);
  					 unvaccinatedQty.requestFocus();
  					 unvaccinatedQty.setValue(0);
  					 return;
  					 
  				}
  				
  			}else if(e.getSource().equals(oneShotQty))
  			{
  				oneshotCount = (int) oneShotQty.getValue();
  				
  				// checks if the inputed number is greater than 100%
  				if(totalPercent>=oneshotCount)
  				{
  					totalPercent-=oneshotCount;
  					
  				}else
  					
  				{
  					 JOptionPane.showMessageDialog(null, "Try number less than or equal to "+ totalPercent,
  					      "Hey!", JOptionPane.ERROR_MESSAGE);
  					 oneShotQty.requestFocus();
  					 oneShotQty.setValue(0);
  					 return;
  					 
  				}
  			}
  			else if(e.getSource().equals(twoShotsQty))
  			{
  				TwoShotCount = (int) twoShotsQty.getValue();
  				
  				// checks if the inputed number is greater than 100%
  				if(totalPercent>=TwoShotCount)
  				{
  					totalPercent-=TwoShotCount;
  					
  				}else
  					
  				{
  					 JOptionPane.showMessageDialog(null, "Try number less than or equal to "+ totalPercent,
  					      "Hey!", JOptionPane.ERROR_MESSAGE);
  					 twoShotsQty.requestFocus();
  					 twoShotsQty.setValue(0);
  					 return;
  					 
  				}
  			}	else if(e.getSource().equals(naturalImmunityQty))
  			{
  				NaturalImmunity = (int) naturalImmunityQty.getValue();
  				
  				if(totalPercent>=NaturalImmunity)
  				{
  					totalPercent-=NaturalImmunity;
  					
  				}else
  					
  				{
  					 JOptionPane.showMessageDialog(null, "Try number less than or equal to "+ totalPercent,
  					      "Hey!", JOptionPane.ERROR_MESSAGE);
  					 naturalImmunityQty.requestFocus();
  					 naturalImmunityQty.setValue(0);
  					 return;
  					 
  				}
  			}
  			
  			PopulationCount_= populationCount;
  			unVaccinatedCount_=(double)unVaccinatedCount/100*populationCount ;
  			TwoShotCount_=(double)TwoShotCount/100*populationCount;
  			oneshotCount_=(double)oneshotCount/100*populationCount;
  			NaturalImmunity_=(double)NaturalImmunity/100* populationCount;
  		
  		}

  	}
    
   public class Listener implements ActionListener
    {
    	@Override
		public void actionPerformed(ActionEvent e)
		{
			//Container content = getContentPane();
			if(e.getActionCommand().equals("START"))
			{
			
				// for displaying the data on the Real TIme Data section
				String s = "";
				s = "Total population: " + PopulationCount_ + "\n\n" + "Total Unvaccinated population: "
						+ unVaccinatedCount_+"\n"+"\n" + "Total one-Shot Vaccinated Population: "
						+ TwoShotCount_ + "\n\n"+"\n" + "Total tow-shot Vaccinated Population: "
						+ oneshotCount_+ "\n\n"+"\n" + "Natural Immunity Population: "
						+ NaturalImmunity_ ;

				ShowData.setText("");
				ShowData.setFont(new Font("Segoe UI", Font.PLAIN, 13));
				ShowData.setText(s);
				ShowData.setEditable(false);
			
				
				pm = new PersonMovement(populationCount, (int)unVaccinatedCount_, (int)oneshotCount_, (int)TwoShotCount_, (int)NaturalImmunity_);
				simulationArea.add(pm);
				pm.getTimer().start();
				revalidate();
				repaint();
				startBtn.setEnabled(false);
				pauseBtn.setEnabled(true);
				resumeBtn.setEnabled(true);
				//System.out.println("Started! - " + System.currentTimeMillis() );
			}
			else if(e.getActionCommand().equals("PAUSE"))
			{
				pm.getTimer().stop();
				//System.out.println("Paused!");
			}else if(e.getActionCommand().equals("RESUME"))
			{
				pm.getTimer().start();
			}			
		}
    }
    
    //Main Method
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new PandemicSimulator();
            }
        });
	}//End Main()
}//EOC
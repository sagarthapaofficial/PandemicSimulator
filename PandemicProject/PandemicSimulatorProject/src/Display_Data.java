import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * 
 * Program Name: Display_Data.java
 * Purpose: Creates a modal window to show result of the simulation
 * Coded by: 
 * Alfredo Alexander Quele Mendez - Section A
 * Sagar Thapa - Section C
 * James Hill - Section B
 * Lee Hutson - Section A
 * Date: August 7, 2021
 * 
 */

public class Display_Data extends JDialog
{
	// Declaring variables
		private static final long serialVersionUID = 1L;
		private static Border blackline = BorderFactory.createLineBorder(Color.black);
		private JLabel Title;
    private JLabel totalInfectedL;
    private JLabel totalunvaccinatedL;
    private JLabel partially_vaccinatedL;    
    private JLabel fullyVaccinatedL;
    private JLabel recoveredPL;
    private JLabel deathPl;
    private JPanel companyContainer;
  	public double totalPopulationP;
  	public double totalInfectedPopulation;
  	public double total_unVaccinatedP;
  	public double partially_vaccinatedP;
  	public double fully_vaccinatedP;
  	public double recoveredP;
  	public double total_DeathP;
	
    //Constructor
	public Display_Data(int totalPopulation,int totalInfectedPopulation, int total_unvaccinated, int partially_vaccinated_, int fully_vaccinated, int recovered_, int death_)
	{
				this.totalPopulationP= totalPopulation;
				this.totalInfectedPopulation= ((double)totalInfectedPopulation/totalPopulation)*100;
				this.total_unVaccinatedP=((double)total_unvaccinated/this.totalPopulationP)*100;
				this.partially_vaccinatedP= ((double)partially_vaccinated_/this.totalPopulationP)*100;
				this.fully_vaccinatedP= ((double)fully_vaccinated/this.totalPopulationP)*100;
				this.recoveredP= ((double)recovered_/this.totalPopulationP)*100;
				this.total_DeathP=((double)death_/this.totalPopulationP)*100;
				this.setModal(true);
				this.setSize(700, 475);
				this.setLocationRelativeTo(null);
        this.setResizable(false);
        getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(210,210,210));
        
        //container to include borders to company name
        companyContainer = new JPanel();
        companyContainer.setBounds(238,31,246,45);
        companyContainer.setBorder(blackline);
        companyContainer.setBackground(new Color(210,210,210));
        getContentPane().add(companyContainer);
        
        
        Title = new JLabel("Pandemic Simulation  Result  ");
        companyContainer.add(Title);
        Title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
       
        totalInfectedL = new JLabel("Percentage of totalPopulation Contracted disease: "+ String.format("%.2f",this.totalInfectedPopulation)+ " %");
        totalInfectedL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        totalInfectedL.setBounds(48, 126, 455, 25);
        getContentPane().add(totalInfectedL);
        

        totalunvaccinatedL = new JLabel("Percentage of unvaccinated person who contracted disease: "+  String.format("%.2f",total_unVaccinatedP)+ " %");
        totalunvaccinatedL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        totalunvaccinatedL.setBounds(48, 263, 490, 39);
        getContentPane().add(totalunvaccinatedL);
        
   
        partially_vaccinatedL = new JLabel("Percentage of partially-vaccinated people who contracted disease: "+ String.format("%.2f",partially_vaccinatedP)+ " %");
        partially_vaccinatedL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        partially_vaccinatedL.setBounds(48, 301, 533, 30);
        getContentPane().add(partially_vaccinatedL);
        
      
        fullyVaccinatedL = new JLabel("Percentage of full-vaccinated person who contracted the disease: "+ String.format("%.2f",this.fully_vaccinatedP)+ " %");
        fullyVaccinatedL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        fullyVaccinatedL.setBounds(48, 236, 568, 25);
        getContentPane().add(fullyVaccinatedL);
        
        recoveredPL = new JLabel("Percentage of population recovered: "+ String.format("%.2f",this.recoveredP)+ " %");
        recoveredPL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        recoveredPL.setBounds(50, 161, 402, 30);
        getContentPane().add(recoveredPL);
        
        deathPl = new JLabel("Percentage of population died: "+ String.format("%.2f",this.total_DeathP)+ " %");
        deathPl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        deathPl.setBounds(50, 201, 352, 25);
        getContentPane().add(deathPl);
        
        
        
        
	}
}//EOC
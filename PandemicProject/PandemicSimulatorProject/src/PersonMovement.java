import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Program Name: PersonMovement.java
 * Purpose: Defines a PersonMovement class draws the balls on the panel and does the ball position calculations
 * Coded by: 
 * Alfredo Alexander Quele Mendez - Section A
 * Sagar Thapa - Section C
 * James Hill - Section B
 * Lee Hutson - Section A
 * Date: August, 2021
 * 
 */
public class PersonMovement extends JPanel
{
	private static final long serialVersionUID = 1L;
	// private final int WIDTH = 740, HEIGHT = 650;
	private final int WIDTH = 720, HEIGHT = 635;
	private final int LAG_TIME = 100;
	private Timer timer;
	private final int DIAMETER = 10;
	// private int populationQty;
	private Person[] personArray;
	private int Times;
	private int totalInfected;
	private int non_Vaccinated = 0;
	private int one_ShotVaccinated = 0;
	private int twoShotVaccinated = 0;
	private int recovered = 0;
	private int diedPopulation = 0;
	public String data="";


	public Timer getTimer()
	{
		return this.timer;
	}

	public PersonMovement(int populationQty, int unvaccinatedQty, int oneShotQty, int twoShotsQty, int naturalImmunityQty)
	{

		personArray = new Person[populationQty];

		timer = new Timer(LAG_TIME, new MovementListener());
		// this.timer = time;

		// Populate the array
		for (int i = 0; i < personArray.length; i++)
		{
			personArray[i] = new Person(unvaccinatedQty, oneShotQty, twoShotsQty, naturalImmunityQty);
		}

		// Set first person to infected
		personArray[0].setIsInfected(true);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// this.time.start();
	}

	/*Method Name: PainComponent
	*Purpose: To draw the balls on different position
	*Accepts: Graphics g
	*Returns: void
	*/
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.PINK);
		for (int i = 0; i < personArray.length; i++)
		{
			g.setColor(personArray[i].getColour());
			g.fillOval(personArray[i].getxCoordinate(), personArray[i].getyCoordinate(), DIAMETER, DIAMETER);
		}
	
		data="Number of infected persons: "+ totalInfected +"\n"
				+ "Number of non-vaccinated person infected: " +non_Vaccinated+ "\n"
				+ "Number of partially-Vaccinated people infected: "+one_ShotVaccinated +"\n"
				+ "Number of fully-vaccinated person infected: " +twoShotVaccinated+ "\n"
				+ "Number of recovered people: "+recovered+ "\n"
				+ "Number of people died: "+diedPopulation;
		PandemicSimulator.RealTimeData(data);
	}

	// Inner class MovementListener
	public class MovementListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			non_Vaccinated = 0;
			one_ShotVaccinated = 0;
			twoShotVaccinated = 0;
			recovered = 0;
			diedPopulation = 0;

			totalInfected =0;

			for (int i = 0; i < personArray.length; i++)
			{
				// Movement of person
				personArray[i].Move();

				// get values
				if (personArray[i].getImmunityStatus() == 1 && personArray[i].getIsInfected()==true)
				{
					non_Vaccinated++;

				} else if (personArray[i].getImmunityStatus() == 2 && personArray[i].getIsInfected()==true)
				{
					one_ShotVaccinated++;
				} else if (personArray[i].getImmunityStatus() == 3 && personArray[i].getIsInfected()==true)
				{
					twoShotVaccinated++;
				} else if (personArray[i].getImmunityStatus() == 4)
				{
					recovered++;
				}

				if (personArray[i].getisAlive() == false)
				{

					diedPopulation++;
				}
				if (personArray[i].getIsInfected() == true)
				{
					totalInfected++;
				}

			}
			

			// Check the collision of the people
			for (int i = 0; i < personArray.length; i++)
			{
				for (int j = i + 1; j < personArray.length; j++)
				{
					personArray[0].CollisionCheck(personArray[i], personArray[j]);
				}
			}
		
			
			repaint();
			Times++;
			if (Times == 450)
			{
				// opens the final presentation modal
				 Display_Data view = new Display_Data(personArray.length,totalInfected, non_Vaccinated, one_ShotVaccinated, twoShotVaccinated,recovered,diedPopulation);
				 view.setVisible(true);
				timer.stop();
			}

			
		}

	}
}// EOC
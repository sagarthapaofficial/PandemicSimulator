/**
 * Program Name: Person.java
 * Purpose: Defines a person class, each person is represented by a ball in the GUI as the pandemic 
 * simulator runs. Contains a number of attributes and methods used in determining the spread of COVID-19
 * Coded by: 
 * Alfredo Alexander Quele Mendez - Section A
 * Sagar Thapa - Section C
 * James Hill - Section B
 * Lee Hutson - Section A
 * Date: August, 2021
 * 
 */

import java.awt.Color;

public class Person 
{
	private boolean isInfected;
	private boolean isAlive = true;
	private boolean contractedDisease;
	private int initialStatus;
	private int immunityStatus;
	private Color colour;
	private int xCoordinate;
	private int yCoordinate;
	private int xIncrementValue;
	private int yIncrementValue;
	private int cycleCounter;
	private final int DIAMETER = 10;
	private final int WIDTH = 720;
	private final int HEIGHT = 635;
	private final double CATCH_NO_IMMUNITY = 80.0;
	private final double CATCH_ONE_SHOT = 40.0;
	private final double CATCH_BOTH_SHOTS = 10.0;
	private final double CATCH_RECOVERED = 10.0;
	private final double DEATH_NO_IMMUNITY = 10.0;
	private final double DEATH_ONE_SHOT = 5.0;
	private final double DEATH_TWO_SHOTS = 1.0;
	private final double DEATH_RECOVERED = 0.3;
	
	//getters
	public int getxCoordinate()
	{
		return this.xCoordinate;
	}
	public int getyCoordinate()
	{
		return this.yCoordinate;
	}
	public Color getColour()
	{
		return this.colour;
	}
	public boolean getContractedDisease()
	{
		return this.contractedDisease;
	}
	public int getInitialStatus()
	{
		return this.initialStatus;
	}
	public void setIsInfected(boolean val)
	{
		this.isInfected = val;
		if(this.isInfected)
			colour = Color.red;
	}
	public int getImmunityStatus()
	{
		return this.immunityStatus;
	}
	
	public boolean getisAlive()
	{
		return this.isAlive;
	}
	
	public boolean getIsInfected()
	{
		return this.isInfected;
	}

	//constructor
	public Person(double noImmunity, double oneShot, double twoShots, double recovered)
	{
		//sets initial immunity level based on user input %chance
		int random = (int)Math.floor(Math.random() * 100);
		
		if(random <= noImmunity) //has no immunity
		{
			initialStatus = 1;
			immunityStatus = 1;
			colour = Color.blue;
		}
		else if(random <= noImmunity + oneShot) //has one shot
		{
			initialStatus = 2;
			immunityStatus = 2;
			colour = Color.cyan;
		}
		else if(random <= noImmunity + oneShot + twoShots) //has two shots
		{
			initialStatus = 3;
			immunityStatus = 3;
			colour = Color.yellow;
		}
		else // has recovered from COVID
		{
			initialStatus = 4;
			immunityStatus = 4;
			colour = Color.green;
		}
		this.InitializeCoordinates();
		this.InitializeIncrement();
	}//end constructor
	
	/*Method Name: Move()
	*Purpose: Calculates the movement of a person object on the screen for the next tick.
	*Accepts: Nothing.
	*Returns: Nothing.
	*/
	public void Move()
	{
		if(this.isAlive)
		{
			if(this.xCoordinate >= WIDTH - this.DIAMETER )
			{
				//we are at right side, so change xIncrement to a negative
				this.xIncrementValue *= -1;
			}
			if(this.xCoordinate <= 0)
			{
				//if true, we're at left edge, flip the flag
				this.xIncrementValue *= -1;
			}
			if(this.yCoordinate >= HEIGHT - this.DIAMETER )
			{
				this.yIncrementValue *= -1;
			}
			if(this.yCoordinate <= 0)
			{
				//if true, we're at left edge, flip the flag
				this.yIncrementValue *= -1;
			}
			this.xCoordinate += this.xIncrementValue;
			this.yCoordinate += this.yIncrementValue;
			if(this.isInfected)
			{
				if(this.cycleCounter != 150)
				{
					this.cycleCounter++;
				}
				else
				{
					this.ConcludeDisease();
				}
			}
		}
	}
	
	/*Method Name: CollisionCheck()
	*Purpose: Determines if two person objects have touched each other. Determines if an infection might happen. Reassigns travel directions if collision.
	*Accepts: Two person objects.
	*Returns: Nothing.
	*/
	public void CollisionCheck(Person p1, Person p2)
	{
		int deltaX = p1.xCoordinate - p2.xCoordinate;
		int deltaY = p1.yCoordinate - p2.yCoordinate;
		if(Math.sqrt(deltaX * deltaX + deltaY * deltaY) <= DIAMETER) //the two people touched
		{
			if(p1.isInfected ^ p2.isInfected) //XOR operator; evaluates true if an infected meets a noninfected
			{
				if(p1.isInfected)
				{
					p2.DiseaseExposure();
				}
				else
				{
					p1.DiseaseExposure();
				}
			}
			p1.InitializeIncrement();
			p2.InitializeIncrement();
		}
	}
	
	/*Method Name: InitializeCoordinates
	*Purpose: Generates random coordinates which are validated to be inside of ball pit.
	*Accepts: N/A
	*Returns: Void
	*/
	public void InitializeCoordinates()
	{
			int x = 0, y = 0;
			boolean inRange = false;
			
			
		  while(inRange == false) 
		  {
			x = (int)(Math.random() * WIDTH);
			y = (int)(Math.random() * HEIGHT);
			
			//Give a number for x between 0 and 700 & a number for y between 0 and 500
			  if(x >= 0 && x <= (WIDTH - DIAMETER) && y >= 0 && y <= (HEIGHT - DIAMETER)) 
			  {
				xCoordinate = x;
				yCoordinate = y;
				inRange = true;
			  }
		  }
	}//end InitializeCoordinates
	
	
	/*Method Name: InitializeIncrement
	*Purpose: Generates random values range -5 to +5.
	*Accepts: N/A
	*Returns: Double
	*/
	public void InitializeIncrement()
	{
		//Generate random value between -5 and +5
		boolean inRangeX = false;
		boolean inRangeY = false;
		double random = 0.0;
		
		//X increment
		while(inRangeX == false)
		{
			random = -5 + (Math.random() * ((5 - (-5)) + 1));
			if(random > -5 && random < 5)
			{
				inRangeX = true;
			}
		}
		this.xIncrementValue = (int)random;
		
		//Y Increment
		while(inRangeY == false)
		{
			random = -5 + (Math.random() * ((5 - (-5)) + 1));
			if(random > -5 && random < 5)
			{
				inRangeY = true;
			}
		}
		this.yIncrementValue = (int)random;
		
		//If both are 0 call the method again
		if(this.xIncrementValue ==0 && this.yIncrementValue ==0)
		{
			InitializeIncrement();
		}
	}//end InitializeIncrement
	
	/*Method Name: DiseaseExposure
	*Purpose: Determines if a non infected person who collides with an infected person will
	*	  become infected based on the probability of their immunity status.
	*Accepts: N/A
	*Returns: Void
	*/
	public void DiseaseExposure()
	{
		if(!this.isAlive)
		{
			return;
		}
		double random = 0; 
		
		//Generate random number between 0 and 100
		random = Math.random() * 100;
		if(immunityStatus == 1)
		{
			if(random <= CATCH_NO_IMMUNITY)
			{
				this.isInfected = true;
				this.colour = Color.red;
			}
		}
		else if(immunityStatus == 2) 
		{
			if(random <= CATCH_ONE_SHOT)
			{
				this.isInfected = true;
				this.colour = Color.red;
			}
		}
		else if(immunityStatus == 3) 
		{
			if(random <= CATCH_BOTH_SHOTS)
			{
				this.isInfected = true;
				this.colour = Color.red;
			}
		}
		else if(immunityStatus == 4) 
		{
			if(random <= CATCH_RECOVERED)
			{
				this.isInfected = true;
				this.colour = Color.red;
			}
		}
		else
		{
			this.isInfected = false;
		}
	}//end DiseaseExposure()
	
	/*Method Name: ConcludeDisease
	*Purpose: Determines if an infected person will live or die after having 
	*         become infected based on the probability of death based on their immunity status.
	*Accepts: N/A
	*Returns: Void
	*/
	public void ConcludeDisease()
	{
		double random = 0; 
		
		//Generate random number between 0 and 100
		random = Math.random() * 100;
		if(immunityStatus == 1)
		{
			if(random <= DEATH_NO_IMMUNITY)
			{
				this.isAlive = false;
				this.colour = Color.black;
			}
			else
			{
				this.isAlive = true;
				this.colour = Color.green;
				this.isInfected = false;
				this.immunityStatus = 4;
			}
		}
		else if(immunityStatus == 2) 
		{
			if(random <= DEATH_ONE_SHOT)
			{
				this.isAlive = false;
				this.colour = Color.black;
			}
			else
			{
				this.isAlive = true;
				this.colour = Color.green;
				this.isInfected = false;
				this.immunityStatus = 4;
			}
		}
		else if(immunityStatus == 3) 
		{
			if(random <= DEATH_TWO_SHOTS)
			{
				this.isAlive = false;
				this.colour = Color.black;
			}
			else
			{
				this.isAlive = true;
				this.colour = Color.green;
				this.isInfected = false;
				this.immunityStatus = 4;
			}
		}
		else if(immunityStatus == 4) 
		{
			if(random <= DEATH_RECOVERED)
			{
				this.isAlive = false;
				this.colour = Color.black;
			}
			else
			{
				this.isAlive = true;
				this.colour = Color.green;
				this.isInfected = false;
				this.immunityStatus = 4;
			}
		}
		this.isInfected = false;
		
	}//end ConcludeDisease()
}

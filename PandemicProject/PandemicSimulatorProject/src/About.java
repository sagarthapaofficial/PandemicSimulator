import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * 
 * Program Name: About.java
 * Purpose: Creates a modal window to show group members details. It includes company
 * 			name as well as all names and sections of the members
 * Coded by: 
 * Alfredo Alexander Quele Mendez - Section A
 * Sagar Thapa - Section C
 * James Hill - Section B
 * Lee Hutson - Section A
 *  Date: August 7, 2021
 * 
 */

public class About extends JDialog
{
	private static final long serialVersionUID = 1L;
	private static Border blackline = BorderFactory.createLineBorder(Color.black);
	private JLabel companyName;
    private JLabel member1;
    private JLabel member2;
    private JLabel member3;    
    private JLabel member4;
    private JLabel member1Section;
    private JLabel member2Section;
    private JLabel member3Section;    
    private JLabel member4Section;
    private JLabel member;
    private JLabel section;
    private JPanel companyContainer;
	
    
    //Constructor
	public About()
	{
		this.setModal(true);
		this.setSize(525, 375);
		this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(210,210,210));
        
        //container to include borders to company name
        companyContainer = new JPanel();
        companyContainer.setBounds(170,20,175,45);
        companyContainer.setBorder(blackline);
        companyContainer.setBackground(new Color(210,210,210));
        this.add(companyContainer);
        
        
        companyName = new JLabel("Tech Raid");
        companyName.setFont(new Font("Segoe UI", Font.BOLD, 16));
        companyName.setBounds(0, 50, 125, 20);
        companyContainer.add(companyName);
        
        member = new JLabel("Group Member");
        member.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        member.setBounds(50, 85, 300, 25);
        this.add(member);
        
        section = new JLabel("Section");
        section.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        section.setBounds(400, 85, 300, 25);
        this.add(section);
        
        //Group Member 1 - Lee Hutson
        member1 = new JLabel("Lee Hutson");
        member1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        member1.setBounds(50, 135, 300, 25);
        this.add(member1);
        
        member1Section = new JLabel("A");
        member1Section.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        member1Section.setBounds(425, 135, 300, 25);
        this.add(member1Section);
        
        //Group Member 2 - James Hill
        member2 = new JLabel("James Hill");
        member2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        member2.setBounds(50, 235, 300, 25);
        this.add(member2);
        
        member2Section = new JLabel("B");
        member2Section.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        member2Section.setBounds(425, 235, 300, 25);
        this.add(member2Section);
        
        //Group Member 3 - Sagar Thapa
        member3 = new JLabel("Sagar Thapa");
        member3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        member3.setBounds(50, 285, 300, 25);
        this.add(member3);
        
        member3Section = new JLabel("C");
        member3Section.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        member3Section.setBounds(425, 285, 300, 25);
        this.add(member3Section);
        
        //Group Member 4 - Alfredo Alexander Mendez
        member4 = new JLabel("Alfredo Alexander Mendez");
        member4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        member4.setBounds(50, 185, 300, 25);
        this.add(member4);
        
        member4Section = new JLabel("A");
        member4Section.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        member4Section.setBounds(425, 185, 300, 25);
        this.add(member4Section);
	}
}//EOC
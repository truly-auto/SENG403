package FoodLink.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SendCommentWindow extends JDialog implements ActionListener{

	private static final long serialVersionUID = -605941051526158691L;
	
	
	private Boolean choice = false;
	private String comment;
	
	private JLabel jlName;
	
	private JTextField jtfName;
	private JTextArea jtaComments;
	private JButton jbSend;
	private JButton jbCancel;
	
	public SendCommentWindow() {
		setTitle("Comment");
		setSize(430,330);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initializeComponents();
		
		JPanel jpNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jpNorth.add(jlName);
		jpNorth.add(jtfName);
		
		JPanel jpSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpSouth.add(jbSend);
		jpSouth.add(jbCancel);
		
		getContentPane().add(jpNorth, BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(jtaComments), BorderLayout.CENTER);
		getContentPane().add(jpSouth,BorderLayout.SOUTH);
	}
	
	private void initializeComponents(){
		
		choice = false;
		comment = null;
		
		jlName = new JLabel("Your name: ");
		jtfName = new JTextField(20);
		
		jtaComments = new JTextArea();
		
		jbSend = new JButton("Send");
		jbSend.addActionListener(this);
		
		jbCancel = new JButton("Cancel");
		jbCancel.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("send")){
			if(!jtfName.getText().trim().isEmpty() && !jtaComments.getText().trim().isEmpty()){		
				
				comment = jtfName.getText() + "\n\n" + jtaComments.getText();
				
				JOptionPane.showMessageDialog(this, "Comment sent");
				choice = true;
				this.dispose();
				
			}
			else{
				JOptionPane.showMessageDialog(this, "Please do not leave blanks in any field");
				
			}
			
		}
		else if(e.getActionCommand().equalsIgnoreCase("cancel")){
			this.dispose();
		}
			
		
	}
	
	public Boolean valid(){
		if(choice && comment != null){
			return true;
		}
		return false;
	}
	
	public String getComment(){
		return comment;
	}

}

package randEpisode;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Window implements ActionListener{
	private Frame frame;
	private TextField req,res,src;
	private Button btn,opnF,opnD;
	private File file;
	private randEp rnd;
	
	Window(String str){
		rnd = new randEp(str,"");
		frame = new Frame("Random Episode");
		btn = new Button("Get Episode");
		opnF = new Button("Open File");
		opnD = new Button("Open Dir");
		src = new TextField(rnd.getAdd());
		req = new TextField("");
		res = new TextField("");
		frame.setSize(380,160);
		frame.setLocation(50, 50);;
		req.setBounds(20, 60, 340, 20);
		src.setBounds(req.getX(),req.getY()-req.getHeight()-10,req.getWidth(),req.getHeight());
		res.setBounds(req.getX(),req.getY()+req.getHeight()+10,req.getWidth(),req.getHeight());
		btn.setBounds(res.getX(), res.getY()+res.getHeight()+10, 100, 20);
		opnF.setBounds(btn.getX()+btn.getWidth()+20,btn.getY(),100,20);
		opnD.setBounds(opnF.getX()+btn.getWidth()+20,btn.getY(),100,20);
		res.setEditable(false);
		frame.add(req); frame.add(btn); frame.add(res); frame.add(opnF); frame.add(opnD); frame.add(src);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		btn.addActionListener(this);
		opnF.addActionListener(this);
		opnD.addActionListener(this);
		opnF.setVisible(false);
		opnD.setVisible(false);
		frame.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	            System.exit(0);
	        }
	    });
		req.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
					btnAction();
				
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		req.requestFocus();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btn)
			btnAction();
		else if (e.getSource()==opnF)
			opnFAction();
		else if (e.getSource()==opnD)
			opnDAction();
	}
	
	private void btnAction() {
		rnd.setName(req.getText());
		rnd.setAdd(src.getText());
		file = rnd.getRand();
		if (file==null) {
			res.setText("Invalid Name");
			opnF.setVisible(false);
			opnD.setVisible(false);
			return;
		}
		res.setText(file.getName());
		if (!req.getText().isEmpty())
			req.setText(rnd.getFile(req.getText(), new File(src.getText())).getName());
		opnF.setVisible(true);
		opnD.setVisible(true);
		opnF.requestFocus();
	}
	
	private void opnFAction() {
		Desktop d = Desktop.getDesktop();
		if (file.exists())
			try {
				d.open(file);
			} catch (IOException e) {
				res.setText("!Can't open! "+res.getText());
			}
	}
	
	private void opnDAction() {
		Desktop d = Desktop.getDesktop();
		if (file.exists())
			try {
				d.open(file.getParentFile());
			} catch (IOException e) {
				res.setText("!Can't open! "+res.getText());
			}
	}
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CaesarFrame extends JFrame
{
	private Object[] _abc = new Object[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	// 3
	private JTextField _inputText = new JTextField(20);
	private JTextField _outputText = new JTextField(20);
	private JButton _codeButton = new JButton("Code!");
	private JComboBox<?> _offsetCombo = new JComboBox<>(_abc);
	
	private boolean _encode = true;
	
	class OkButtonActionListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent ae)
		{
			if (ae.getActionCommand().contentEquals("encode"))
			{
				if (_encode)
					_outputText.setText(
							(new Caesar()).getCaesarCode(
							_inputText.getText(), 
							(char) _offsetCombo.getSelectedItem()
							));
				else
					_inputText.setText(
							(new Caesar()).getCaesarDecode(
							_outputText.getText(), 
							(char) _offsetCombo.getSelectedItem()
							));
			}
		}
	}
	
	// Automatizált enkódolás
	class InputFieldKeyListener extends KeyAdapter implements DocumentListener
	{
		void caesar() 
		{
			_outputText.setText(
					(new Caesar()).getCaesarCode(
					_inputText.getText(), 
					(char) _offsetCombo.getSelectedItem()
					));
		}
		
		// 5
		/*
		@Override
		public void keyPressed(KeyEvent e) { caesar(); }
		*/

		// 6
		@Override
		public void changedUpdate(DocumentEvent arg0) { caesar(); }
		@Override
		public void insertUpdate(DocumentEvent arg0) { caesar(); }
		@Override
		public void removeUpdate(DocumentEvent arg0) { caesar(); }
	}
	
	class DirectionListener implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0) 
		{
			if (arg0.getComponent() == _inputText)
				_encode = true;
			else if (arg0.getComponent() == _outputText)
				_encode = false;
		}
		@Override
		public void focusLost(FocusEvent arg0) {}
	}
	
	public CaesarFrame() 
	{
		super("SwingLab");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setName("SwingLab");
		this.setSize(400, 110);
		this.setResizable(true);
		
		// 3
		// layout
		BoxLayout layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(layout);

		// panelek és feltöltésük
		JPanel upper = new JPanel();
		JPanel lower = new JPanel();
		
		upper.add(_offsetCombo);
		upper.add(_inputText);
		upper.add(_codeButton);
		lower.add(new JLabel("Output:"));
		lower.add(_outputText);

		// panelek jozzáadása a frame-hez
		this.add(upper);
		this.add(lower);
		
		//outputText.setEditable(false); // 7-es miatt kikommentelve
		
		// 4
		_codeButton.setActionCommand("encode");
		_codeButton.addActionListener(new OkButtonActionListener());
		
		// 5
		/*
		inputText.setActionCommand("textChange");
		inputText.addKeyListener(new InputFieldKeyListener());
		// megfigyelés: a feldolgozott szöveg 1 char-nyi "lemaradásban" van
		*/
		
		// 6
		_inputText.getDocument().addDocumentListener(new InputFieldKeyListener());
		
		// 7
		_inputText.addFocusListener(new DirectionListener());
		_outputText.addFocusListener(new DirectionListener());
		/*Mivel az automatikus kódolás nincs "kikapcsolva",
		 * az outputba írt (enkódolt) szöveg dekódolás után 
		 * visszakerül az outputba (formázva NAGYBETŰSRE).
		 * 
		 * Ha ezt ki akarnánk kapcsolni, elég lenne kikommentelni a
		 * 130-as sort.*/
	}

}

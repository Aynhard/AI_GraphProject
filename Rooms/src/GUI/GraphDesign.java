package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import javax.swing.JTextArea;
import java.awt.Color;

import javax.swing.border.LineBorder;


import graph.Controller;
import graph.LinkType;
import serach.algurithms.EscapeByType;
import serach.algurithms.SearchByLift;
import serach.algurithms.ShortestPath;


import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ButtonGroup;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.io.File;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;


public class GraphDesign extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	Controller controller ;
	
	public GraphDesign() {
		controller = Controller.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					controller.readingFile(file);
				}
			}
		});
		mnNewMenu.add(mntmOpen);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u0422\u042A\u0420\u0421\u0415\u041D\u0415 \u041D\u0410 \u0421\u0422\u0410\u042F \u0412 \u0421\u0413\u0420\u0410\u0414\u0410");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setBackground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 23));
		lblNewLabel.setBounds(112, 30, 412, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblFrom = new JLabel("\u041D\u0430\u0447\u0430\u043B\u043D\u0430 \u0441\u0442\u0430\u044F :");
		lblFrom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblFrom.setBounds(63, 162, 157, 27);
		contentPane.add(lblFrom);
		
		JLabel lblTo = new JLabel("\u041A\u0440\u0430\u0439\u043D\u0430 \u0441\u0442\u0430\u044F :");
		lblTo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblTo.setBounds(63, 208, 157, 27);
		contentPane.add(lblTo);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		textField.setBounds(259, 165, 153, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		textField_1.setBounds(259, 211, 153, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 250, 240));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setBounds(12, 317, 558, 212);
		JScrollPane textAreaContentScrollPane = new JScrollPane(textArea);
		textAreaContentScrollPane.setSize(558, 212);
		textAreaContentScrollPane.setLocation(12, 317);
		add(textAreaContentScrollPane);
		contentPane.add(textAreaContentScrollPane);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setToolTipText("");
		comboBox.setBounds(44, 86, 502, 27);
		comboBox.addItem("1. Търсене на път до стая чрез избягване на определен тип\r\n");
		comboBox.addItem("2. Търсене на стая с минимален брой стъпки\r\n");
		comboBox.addItem("3. Търсене на път до стая с асансьор или по-скъпо със стълби");
		contentPane.add(comboBox);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		JRadioButton radioButtonClimb = new JRadioButton("\u0421\u0442\u044A\u043B\u0431\u0438");
		radioButtonClimb.setSelected(true);
		radioButtonClimb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioButtonClimb.setBounds(224, 266, 79, 25);
		buttonGroup.add(radioButtonClimb);
		contentPane.add(radioButtonClimb);
		
		JRadioButton radioButtonLift = new JRadioButton("\u0410\u0441\u0430\u043D\u0441\u044C\u043E\u0440");
		radioButtonLift.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioButtonLift.setBounds(307, 266, 91, 25);
		buttonGroup.add(radioButtonLift);
		contentPane.add(radioButtonLift);
		
		JLabel label = new JLabel("\u0418\u0437\u0431\u044F\u0433\u0432\u0430\u043D\u0435 \u043D\u0430 :");
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		label.setBounds(90, 271, 151, 16);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("\u0422\u042A\u0420\u0421\u0418");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean path = false;
				switch ((String)comboBox.getSelectedItem()) {
				case "1. Търсене на път до стая чрез избягване на определен тип\r\n":
					path = controller.search(textField.getText(), 
							textField_1.getText(), 
							new EscapeByType(controller.getGraph()),
							radioButtonClimb.isSelected() ? LinkType.CLIMB : LinkType.LIFT);
					break;
				case "2. Търсене на стая с минимален брой стъпки\r\n":
					path = controller.search(textField.getText(), 
							textField_1.getText(), 
							new ShortestPath(controller.getGraph()));
					break;
				case "3. Търсене на път до стая с асансьор или по-скъпо със стълби":
					path = controller.search(textField.getText(), 
							textField_1.getText(), 
							new SearchByLift(controller.getGraph()));
					break;

				default:
					break;
				}
				if(path) {
					textArea.setText("");
					textArea.append("Have a path : ");
					textArea.append(controller.getGraph().getPathInforamtion() + "\n");
					textArea.append("Path is : ");
					for (int i = controller.getPathNodes().size() - 1; i >= 0; i--) {
						textArea.append(controller.getPathNodes().get(i).getRoomName() + ",");
					}
					textArea.append("\n");
					textArea.append(controller.getGraph().getFullInformation());
				}else {
					textArea.setText("Don't have a path");
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(135, 206, 250));
		btnNewButton.setBounds(443, 181, 97, 61);
		contentPane.add(btnNewButton);
	}
}

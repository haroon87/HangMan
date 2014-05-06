package com.gamemaker.views;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.gamemaker.action.Action;
import com.gamemaker.action.UnsupportedAction;
import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.controllers.MakerController;
import com.gamemaker.event.Event;
import com.gamemaker.event.UnsupportedEvent;
import com.gamemaker.helper.ActionDeserializer;
import com.gamemaker.helper.Dimensions;
import com.gamemaker.helper.EventeDeserializer;
import com.gamemaker.models.Sprite;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.Icon;
import javax.swing.ScrollPaneConstants;

/**
 * GameMakerView will house the form to add sprites and also
 * the actual grid to place objects onto it to build the game map.
 * Game Maker view is only accessible if the user is a GameMaker and 
 * not accessible by Game Players
 */

public class GameMakerView extends JFrame {

	private final Logger logger;

	private final JLabel lblGameName;
	private final JLabel spriteName;
	private final JLabel fixName;
	private final JTextField spriteNameTextField;
	private final JLabel previousSavedSprites;
	private final JComboBox previousSavedSpritesComboBox;
	private final JScrollPane imageScrollPane;
	private final JLabel eventTypeLabel;
	private final JComboBox eventTypeComboBox;
	private final JList actionTypeList;
	private final JList GameConditionList;
	private final JButton associateBtn;
	private final JButton deassociateBtn;
	private final JLabel summaryLabel;
	private final JList summaryTextArea;
	private String selectedIcon;
	private JButton imageBtn;
	private final JPanel imagePanel;
	private ImageIcon draggedImageIcon;
	private JLabel imageLabelRightPnl;
	private final JButton savedSpriteBtn;
	private JButton JSprite;
	private final JLabel spritesToAssociateWithLabel;
	private final JList associateWithSpriteList;
	private int count;
	private JLabel bkgImageLabel;
	private int firstTime = 0;

	private final List<Sprite> selectedSprites = new ArrayList<Sprite>();;
	private final HashMap<String, ArrayList<String>> eventActionListTemp;

	private JLabel nameLabel;

	private JTextField nameVal;

	private JPanel namePanel;

	public JPanel contentPane;

	private final JPanel leftPanel;
	private final JPanel rightPanel;
	private final JPanel leftTopPanel;
	private final JPanel leftImagePanel;
	private final JPanel leftSubPanel;
	private final JPanel leftMasterPanel;
	private final JPanel leftSubPanel1;
	private JPanel dropPanel;
	private DropTarget dropTarget;

	private DragNDropHelper dragNDropHelper;
	private final JPanel btnPanel1;
	private final JPanel leftBottomSubPanel;
	private final JPanel btnPanel2;

	private final JButton btnSave;
	private final JButton btnTest;
	private final JButton btnReset;
	private DataFlavor dataFlavor;

	private final MakerController makerController;
	private JTabbedPane leftTabPanel;
	private JPanel objectEventActionsTabPanel;

	public GameMakerView(MakerController makerController) {

		this.makerController = makerController;
		this.setPreferredSize(getSize());
		
		this.addComponentListener(new ComponentListener() {
			
			public void componentResized(ComponentEvent e) {
				double a = e.getComponent().getSize().width;
				double b = e.getComponent().getSize().height;
				Dimensions.xScale = ((a - Dimensions.GAME_MAKER_WIDTH) / Dimensions.GAME_MAKER_WIDTH) + 1;
				Dimensions.yScale = ((b - Dimensions.GAME_MAKER_HEIGHT) / Dimensions.GAME_MAKER_HEIGHT) + 1;
				if (rightPanel.getComponentCount() > 0) {
					resizeContents(a,b);
				}
			}
			
			public void componentHidden(ComponentEvent arg0) {

			}
			
			public void componentMoved(ComponentEvent arg0) {

			}
			
			public void componentShown(ComponentEvent arg0) {

			}
		});

		logger = Logger.getLogger(this.getClass());
		BasicConfigurator.configure();

		eventActionListTemp = makerController.getEventActionPairs();
		count = 0;
		getContentPane().setLayout(new GridLayout(1,2));
		getContentPane().setBackground(Color.lightGray);
		
		leftTabPanel = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(leftTabPanel);
		leftPanel = new JPanel();
		leftTabPanel.addTab("Object Details", null, leftPanel, null);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
																							GridBagLayout gbl_leftPanel = new GridBagLayout();
																							gbl_leftPanel.columnWidths = new int[]{567, 0};
																							gbl_leftPanel.rowHeights = new int[] {100, 514, 0};
																							gbl_leftPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
																							gbl_leftPanel.rowWeights = new double[]{0.0, 0.0};
																							leftPanel.setLayout(gbl_leftPanel);
																							leftTopPanel = new JPanel();
																							leftTopPanel.setLayout(new GridLayout(3,2));
																							
																								// adding jlabel and jtextfield for sprite name
																								spriteName = new JLabel("Object Name:");
																								spriteName.setFont(new Font ("serif", Font.ITALIC, 20));
																								leftTopPanel.add(spriteName);
																								
																										spriteNameTextField = new JTextField();
																										leftTopPanel.add(spriteNameTextField);
																										
																												// adding jlabel and jcombobox for selecting previously saved sprites to the game
																												
																												previousSavedSprites = new JLabel("Edit Object:");
																												previousSavedSprites.setFont(new Font ("serif", Font.ITALIC, 20));
																												leftTopPanel.add(previousSavedSprites);
																												
																														previousSavedSpritesComboBox = new JComboBox(spriteListModel().toArray());
																														previousSavedSpritesComboBox.setSelectedIndex(-1);
																														leftTopPanel.add(previousSavedSpritesComboBox);
																														
																																fixName = new JLabel(" ");
																																leftTopPanel.add(fixName);
																																
																																		// button to load the selected sprite
																																		savedSpriteBtn = new JButton("Load Object");
																																		savedSpriteBtn.setFont(new Font ("serif", Font.ITALIC, 20));
																																		savedSpriteBtn.addActionListener(new LoadPreviousSprite());
																																		leftTopPanel.add(savedSpriteBtn);
																																		GridBagConstraints gbc_leftTopPanel = new GridBagConstraints();
																																		gbc_leftTopPanel.fill = GridBagConstraints.BOTH;
																																		gbc_leftTopPanel.insets = new Insets(0, 0, 5, 0);
																																		gbc_leftTopPanel.gridx = 0;
																																		gbc_leftTopPanel.gridy = 0;
																																		leftPanel.add(leftTopPanel, gbc_leftTopPanel);
																							
																							// adding image jlabel and image display jscrollpane
																							leftImagePanel= new JPanel();
																							leftImagePanel.setLayout(new GridLayout(1,1,5,5));
																							leftImagePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Drag and Drop Images", TitledBorder.LEADING, TitledBorder.TOP, new Font("Serif",Font.ITALIC,16), new Color(0, 0, 0)));
																							
																									imageScrollPane = new JScrollPane();
																									imagePanel = new JPanel();
																									imageScrollPane.setViewportView(imagePanel);
																									imagePanel.setLayout(new GridLayout(4, 6, 3, 3));
																									leftImagePanel.add(imageScrollPane);
																									GridBagConstraints gbc_leftImagePanel = new GridBagConstraints();
																									gbc_leftImagePanel.fill = GridBagConstraints.BOTH;
																									gbc_leftImagePanel.gridx = 0;
																									gbc_leftImagePanel.gridy = 1;
																									leftPanel.add(leftImagePanel, gbc_leftImagePanel);
													
																
																// adding the associate and disassociate button
																btnPanel1 = new JPanel();
																FlowLayout fl_btnPanel1 = new FlowLayout();
																fl_btnPanel1.setVgap(50);
																btnPanel1.setLayout(fl_btnPanel1);
																JPanel btnSubPanel = new JPanel();
																btnSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
																
																associateBtn = new JButton("Link");
																associateBtn.addActionListener(new AssociateBtnActionListener());
																associateBtn.setFont(new Font ("serif", Font.ITALIC, 20));
																btnSubPanel.add(associateBtn);
																deassociateBtn = new JButton("Unlink");
																deassociateBtn.setFont(new Font ("serif", Font.ITALIC, 20));
																deassociateBtn.addActionListener(new DeassociateBtnActionListener());
																btnSubPanel.add(deassociateBtn);
																
																btnPanel2 = new JPanel();
																btnPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
																
																		btnSave = new JButton("Save");
																		btnSave.addActionListener(new SaveBtnActionListener());
																		btnSave.setFont(new Font ("serif", Font.ITALIC, 20));
																		btnPanel2.add(btnSave);
																		
																				btnReset = new JButton("Reset");
																				btnReset.setFont(new Font ("serif", Font.ITALIC, 20));
																				
																						btnReset.addActionListener(new ResetBtnActionListener());
																						btnPanel2.add(btnReset);
																						
																								btnTest = new JButton("Test");
																								btnTest.addActionListener(new TestBtnActionListener());
																								btnTest.setFont(new Font ("serif", Font.ITALIC, 20));
																								btnPanel2.add(btnTest);
																								
																										btnPanel1.add(btnSubPanel);
																										btnPanel1.add(btnPanel2);
																										
																														
		objectEventActionsTabPanel = new JPanel();
		leftTabPanel.addTab("Object Event Actions", null, objectEventActionsTabPanel, null);
		objectEventActionsTabPanel.setLayout(null);
		
		JPanel objectEventActionsPanel = new JPanel();
		objectEventActionsPanel.setBounds(0, 0, 587, 634);
		objectEventActionsTabPanel.add(objectEventActionsPanel);
		
		/*
		 * master panel, contains the action list, game conditions
		 * and the sprite list
		 */
		
		leftSubPanel = new JPanel();
		leftSubPanel.setLayout(new GridLayout(2,1,5, 5));
		

		leftSubPanel1 = new JPanel();
		leftSubPanel1.setLayout(new GridLayout(1,2));
		leftSubPanel1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Event Action", TitledBorder.LEADING, TitledBorder.TOP, new Font("Serif",Font.ITALIC,16), new Color(0, 0, 0)));
		
		// adding event type jlabel and jcombobox
		eventTypeLabel = new JLabel("Event Type:");
		eventTypeLabel.setFont(new Font ("serif", Font.ITALIC, 20));
		leftSubPanel1.add(eventTypeLabel);
		
		eventTypeComboBox = new JComboBox(eventTypeComboList());
		eventTypeComboBox.addActionListener(new EventListOnSelectListener());
		objectEventActionsPanel.setLayout(new GridLayout(4, 1, 0, 5));
		leftSubPanel1.add(eventTypeComboBox);
		leftSubPanel.add(leftSubPanel1);
		JPanel leftMasterPanel1 =  new JPanel();
		leftMasterPanel1.setLayout(new GridLayout(1,3,0,0));
		leftSubPanel.add(leftMasterPanel1);
		
		// adding the action jlabel and game condition jlabel
		JLabel actionLabel = new JLabel("Action Type:");
		leftMasterPanel1.add(actionLabel);
		actionLabel.setFont(new Font ("serif", Font.ITALIC, 20));
		
		JLabel gameCondLabel = new JLabel("Game Condition:");
		leftMasterPanel1.add(gameCondLabel);
		gameCondLabel.setFont(new Font ("serif", Font.ITALIC, 20));
		
		
		spritesToAssociateWithLabel = new JLabel("Link with Object:");
		leftMasterPanel1.add(spritesToAssociateWithLabel);
		spritesToAssociateWithLabel.setFont(new Font ("serif", Font.ITALIC, 20));
		objectEventActionsPanel.add(leftSubPanel);
		leftMasterPanel = new JPanel();
		
		actionTypeList = new JList();
		actionTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		actionTypeList.setBackground(Color.WHITE);
		actionTypeList.setEnabled(false);
		leftMasterPanel.setLayout(new GridLayout(1, 3, 5, 5));
		JScrollPane scrollPane = new JScrollPane(actionTypeList);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		leftMasterPanel.add(scrollPane);
		
		objectEventActionsPanel.add(leftMasterPanel);
		
		// adding summary to display event-action-game condition pairs selected
		// for each sprite
		
		leftBottomSubPanel = new JPanel();
		leftBottomSubPanel.setLayout(new GridLayout(1,1,5,5));
		
		summaryTextArea = new JList();
		summaryTextArea.setForeground(Color.BLACK);
		summaryTextArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		summaryTextArea.setBackground(Color.WHITE);
		leftBottomSubPanel.add(new JScrollPane(summaryTextArea));
		leftBottomSubPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Recent Activities", TitledBorder.LEADING, TitledBorder.TOP, new Font("Serif",Font.ITALIC,15), new Color(0, 0, 0)));
		
		
		leftPanel.setBackground(Color.WHITE);
		objectEventActionsPanel.add(leftBottomSubPanel);
		objectEventActionsPanel.add(btnPanel1);
		
		
		
		GameConditionList = new JList(makerController.gameStateStr());
		
		GameConditionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		GameConditionList.setBackground(Color.WHITE);
		GameConditionList.setEnabled(false);
		JScrollPane scrollPane_1 = new JScrollPane(GameConditionList);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		leftMasterPanel.add(scrollPane_1);
		
		associateWithSpriteList = new JList();
		associateWithSpriteList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		associateWithSpriteList.setBackground(Color.WHITE);
		associateWithSpriteList.setEnabled(false);
		associateWithSpriteList.setModel(spriteListModel());
		JScrollPane scrollPane_2 = new JScrollPane(associateWithSpriteList);
		scrollPane_2.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		leftMasterPanel.add(scrollPane_2);
		
		
		lblGameName  =  new JLabel("Game Name");
		JPanel jp = new JPanel();
		final List<String> fileNamesImgs = new ArrayList<String>();
		if (true) {
			URL urlString = this.getClass().getClassLoader().getResource("sprites.jar");
			ZipInputStream zip;
			try {
				zip = new ZipInputStream(urlString.openStream());
				ZipEntry ze = null;

				while ((ze = zip.getNextEntry()) != null) {
					String entryName = ze.getName();
					if (entryName.endsWith(".jpg") || (entryName.endsWith(".png"))) {
						fileNamesImgs.add("/" + entryName);
					}
				}
			} catch (IOException e1) {
		 }
		}
	
		String[] fileListString = new String[fileNamesImgs.size()];
		fileListString = fileNamesImgs.toArray(fileListString);
		
		for (int j = 0; j < fileListString.length; j++) {
			URL imageUrl = this.getClass().getResource(fileListString[j]);
			ImageIcon icon12 = new ImageIcon(imageUrl);
			if ((icon12.getIconWidth() > 45) || (icon12.getIconHeight() > 45)) {
				Image resizedImage = icon12.getImage();
				if (icon12.getIconWidth() > icon12.getIconHeight()) {
					double imgWidth = icon12.getIconWidth();
					double x = (45 / imgWidth);
					double y = icon12.getIconHeight() * x;
					resizedImage = resizedImage.getScaledInstance(45, (int) y, Image.SCALE_SMOOTH);
				} else {
					double imgHeight = icon12.getIconHeight();
					double x = (45 / imgHeight);
					double y = icon12.getIconWidth() * x;
					resizedImage = resizedImage.getScaledInstance((int) y, 45, Image.SCALE_SMOOTH);
				}
				icon12 = new ImageIcon(resizedImage);
			}
			icon12.setDescription(fileListString[j]);
			imageBtn = new JButton(icon12);
			imagePanel.add(imageBtn);
			imagePanel.repaint();
		}

		JLabel eventActionGameCondLabel = new JLabel("Event-Action-GameConditions");
		eventActionGameCondLabel.setFont(new Font ("serif", Font.ITALIC, 20));

		eventActionGameCondLabel.setHorizontalAlignment(SwingConstants.CENTER);

		summaryLabel = new JLabel("Summary:");
		summaryLabel.setFont(new Font ("serif", Font.ITALIC, 20));
		
		this.setBounds(10, 10, 1200, 700);
		this.setVisible(true);
		this.setTitle("Game Maker");
		
		rightPanel = new JPanel();
		// setting right panel dimensions/bounds
		rightPanel.setBounds(600, 0, 600, 700);

		getContentPane().add(rightPanel);
		rightPanel.setLayout(null);

		//modified to get background using getresource method.
		ImageIcon bgImageIcon = new ImageIcon(this.getClass().getResource(makerController.getGameModel().getBkgURL()));
		Image reImage = bgImageIcon.getImage();
		reImage = reImage.getScaledInstance(Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH,
				Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT, Image.SCALE_SMOOTH);
		bgImageIcon = new ImageIcon(reImage);
		bkgImageLabel = new JLabel(bgImageIcon);
		bkgImageLabel.setBounds(0, Dimensions.GAME_MAKER_RIGHT_PANEL_Y, Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH,
				Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT);

		rightPanel.add(bkgImageLabel);

		dragNDropHelper = new DragNDropHelper();
		rightPanel.addMouseListener(dragNDropHelper);
		rightPanel.addMouseMotionListener(dragNDropHelper);
		
		//Initialize drag and drop
		dataFlavor = new DataFlavor(Sprite.class, Sprite.class.getSimpleName());
		DragSource ds = new DragSource();
		for (Component JlblSprite : ((JPanel)(this.imageScrollPane.getViewport()).getComponent(0)).getComponents()) {
			ds.createDefaultDragGestureRecognizer((JButton)JlblSprite, DnDConstants.ACTION_COPY, new SelectAdapter());
		}
		new MyDropTargetListImp(this.rightPanel);
		
		}

	public JTextField getNameVal() {
		return nameVal;
	}

	public void setNameVal(JTextField nameVal) {
		this.nameVal = nameVal;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}

	public static String[] fileList(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		String[] lis = new String[listOfFiles.length];
		for (int i = 0; i < listOfFiles.length; i++) {
			lis[i] = listOfFiles[i].toString();
		}
		return lis;
	}

	public class TestBtnActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			makerController.gamePlayWindowIsRequested(lblGameName.getText());
		}

	}

	// set the event type combobox items
	public String[] eventTypeComboList() {
		String[] eventTempList = new String[eventActionListTemp.size() + 1];
		int i = 0;
		eventTempList[i++] = "None";
		for (String key : eventActionListTemp.keySet()) {
			eventTempList[i++] = key;
		}
		return eventTempList;
	}

	// event list select handler
	public class EventListOnSelectListener implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			if (!eventTypeComboBox.getSelectedItem().equals("None")) {
				for (String key : eventActionListTemp.keySet()) {
					if (eventTypeComboBox.getSelectedItem().equals(key)) {
						actionTypeList.setEnabled(true);
						GameConditionList.setEnabled(true);
						associateWithSpriteList.setEnabled(true);
						actionTypeList.setListData(eventActionListTemp.get(key).toArray());
					}
				}
			} else {
				actionTypeList.setEnabled(false);
				GameConditionList.setEnabled(false);
				associateWithSpriteList.setEnabled(false);
			}
		}

	}

	// resetFormHelper, this method is called by the reset button and save
	// button
	// listeners, it clears all the form fields
	private void resetFormHelper() {
		spriteNameTextField.setText("");
		DefaultListModel model = new DefaultListModel();
		previousSavedSpritesComboBox.removeAllItems();
		setPreviouslySavedSpritesComboBoxValues();
		previousSavedSpritesComboBox.setSelectedIndex(-1);
		clearEventActionLists();
		associateWithSpriteList.setModel(spriteListModel());
		summaryTextArea.setModel(model);
		count = 0;
	}

	private void clearEventActionLists() {
		DefaultListModel model = new DefaultListModel();
		eventTypeComboBox.setSelectedIndex(0);
		actionTypeList.setEnabled(false);
		GameConditionList.setEnabled(false);
		actionTypeList.setModel(model);
		associateWithSpriteList.setModel(spriteListModel());
	}

	// add's the sprite's into the edit previous sprite combobox at runtime
	private void setPreviouslySavedSpritesComboBoxValues() {
		DefaultListModel tempListModel = new DefaultListModel();
		tempListModel = spriteListModel();
		String[] tempStr = new String[tempListModel.size()];
		for (int i = 0; i < tempListModel.size(); i++) {
			tempStr[i] = (String) tempListModel.elementAt(i);
			previousSavedSpritesComboBox.addItem(tempStr[i].toString());
		}

	}

	// action listener for save button
	public class SaveBtnActionListener implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			try {
				List<Sprite> sprites = getGeneratedSpriteDetails();
				makerController.addAll(new ArrayList<Sprite>(sprites), lblGameName.getText());
				sprites.clear();
			} catch (UnsupportedAction e1) {
				e1.printStackTrace();
			} catch (UnsupportedEvent e1) {
				e1.printStackTrace();
			}
			resetFormHelper();
		}

	}

	// action listener for reset button
	public class ResetBtnActionListener implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			resetFormHelper();
			spriteListModel();
		}

	}

	// action listener for associate button
	public class AssociateBtnActionListener implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			String gameState = null;
			if (GameConditionList.getSelectedValue() == null) {
				gameState = GameState.GAME_CONTINUE.toString();
			} else {
				gameState = GameConditionList.getSelectedValue().toString();
			}
			String tempEventActionGameCond = eventTypeComboBox.getSelectedItem() + "-"
					+ actionTypeList.getSelectedValue() + "-" + gameState + "-";
			if (associateWithSpriteList.getSelectedValues().length > 0) {
				for (int j = 0; j < associateWithSpriteList.getSelectedValues().length; j++) {
					tempEventActionGameCond = tempEventActionGameCond + associateWithSpriteList.getSelectedValues()[j]
							+ ":";
				}
			} else {
				tempEventActionGameCond += "";
			}

			DefaultListModel tempModel = new DefaultListModel();
			for (int i = 0; i < summaryTextArea.getModel().getSize(); i++) {
				String tempStr = (String) summaryTextArea.getModel().getElementAt(i);
				tempModel.addElement(tempStr);
			}
			tempModel.addElement(tempEventActionGameCond);
			summaryTextArea.setModel(tempModel);
			clearEventActionLists();
		}
	}

	// action listener for deassociate button
	public class DeassociateBtnActionListener implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			DefaultListModel model = (DefaultListModel) summaryTextArea.getModel();
			int selectedIndex = summaryTextArea.getSelectedIndex();
			if (selectedIndex != -1) {
				model.remove(selectedIndex);
			}
			summaryTextArea.setModel(model);
		}

	}

	// selection helper for fetching image url from the button containing
	// the image in the jscrollpane
	public class SelectAdapter implements DragGestureListener {

		@Override
		public void dragGestureRecognized(DragGestureEvent event) {
			Cursor cursor = null;
			JButton lblSprite = (JButton) event.getComponent();

			if (event.getDragAction() == DnDConstants.ACTION_COPY) {
				cursor = DragSource.DefaultCopyDrop;
			}
			
			event.startDrag(cursor, new TransferableSprite(lblSprite));
		}

	}
	
	class MyDropTargetListImp extends DropTargetAdapter implements
	DropTargetListener {

			int dragX;
			int dragY;
			ImageIcon icon;
	
	public MyDropTargetListImp(JPanel panel) {
		GameMakerView.this.dropPanel = panel;
	
		dropTarget = new DropTarget(dropPanel, DnDConstants.ACTION_COPY, this,
				true, null);
	
	}

public void drop(DropTargetDropEvent event) {
	try {
		Transferable tr = event.getTransferable();
		JSprite = (JButton) tr.getTransferData(dataFlavor);
		icon = (ImageIcon) JSprite.getIcon();
		JSprite = new JButton(icon);
		String desc = ((ImageIcon) JSprite.getIcon()).getDescription();
		selectedIcon = desc;
		Insets insetRight = GameMakerView.this.dropPanel.getInsets();
		Dimension newsize = JSprite.getPreferredSize();

		setSprite((int) event.getLocation().getX(), (int) event.getLocation().getY());
		
		if (event.isDataFlavorSupported(dataFlavor)) {
			event.acceptDrop(DnDConstants.ACTION_COPY);
			GameMakerView.this.dropPanel.setLayout(null);
			GameMakerView.this.dropPanel.add(JSprite);
			event.dropComplete(true);

			GameMakerView.this.dropPanel.validate();
			return;
		}
		event.rejectDrop();
	} catch (Exception e) {
		e.printStackTrace();
		event.rejectDrop();
	}
}
}

	class TransferableSprite implements Transferable {

		private JButton sprite;

		public TransferableSprite(JButton sprite) {
			this.sprite = sprite;
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { dataFlavor };
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return flavor.equals(dataFlavor);
		}

		@Override
		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {

			if (flavor.equals(dataFlavor))
				return sprite;
			else
				throw new UnsupportedFlavorException(flavor);
		}
	}
	private List<Sprite> getGeneratedSpriteDetails() throws UnsupportedAction, UnsupportedEvent {
		HashMap<Event, ArrayList<Action>> newEventActionPairs = new HashMap<Event, ArrayList<Action>>();
		HashMap<String, ArrayList<Action>> specialActions = new HashMap<String, ArrayList<Action>>();
		for (int i = 0; i < summaryTextArea.getModel().getSize(); i++) {
			String tempStr = (String) summaryTextArea.getModel().getElementAt(i);
			String[] strList = tempStr.split("-");
			Event event = EventeDeserializer.getEventObjectFromString(strList[0]);
			Action action = ActionDeserializer.getActionObjectFromString(strList[1], strList[2]);
			if (strList.length >= 4 && strList[3].equals("") == false) {
				String[] associatedSpritesStr = strList[3].split(":");
				for (String str : associatedSpritesStr) {
					if (specialActions.containsKey(str) == false) {
						ArrayList<Action> actions = new ArrayList<Action>();
						specialActions.put(str, actions);
					}
					ArrayList<Action> actions = specialActions.get(str);
					actions.add(action);
					specialActions.put(str, actions);
				}
			}

			if (newEventActionPairs.containsKey(event) == false) {
				ArrayList<Action> actions = new ArrayList<Action>();
				newEventActionPairs.put(event, actions);
			}
			ArrayList<Action> actions = newEventActionPairs.get(event);
			actions.add(action);
			newEventActionPairs.put(event, actions);
		}

		for (Sprite sprite : selectedSprites) {
			sprite.setNewEventActionPairs(newEventActionPairs);
			sprite.setSpecialActions(specialActions);
		}

		return selectedSprites;
	}

	// mouselistener for drag and drop on right panel
	public class DragNDropHelper implements MouseListener, MouseMotionListener {

		
		int dragX = 0;
		int dragY = 0;
		boolean spriteFound = false;
		Sprite draggedSprite = new Sprite();
		@Override
		public void mouseDragged(MouseEvent e) {
			draggedSprite.setX(e.getX());
			draggedSprite.setY(e.getY());
			for(Sprite sprite : selectedSprites)
			{
				if(e.getX() >= sprite.getX() && e.getX() <= sprite.getX()+sprite.getWidth() && e.getY() >= sprite.getY() && e.getY() <= sprite.getY()+sprite.getHeight()  )
				{
					this.draggedSprite = sprite;
					spriteFound = true;
					sprite.setX(e.getX());
					sprite.setY(e.getY());
				}
			}
			
			if(!spriteFound)
			{
				draggedSprite = new Sprite();
				draggedSprite.setWidth(0);
				draggedSprite.setHeight(0);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
			
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			rightPanel.removeAll();
			paintSprites(selectedSprites, true);	
			spriteFound = false;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		
	}
	
	public void setSprite(int xPos, int yPos)
	{		
		URL imageUrl = this.getClass().getResource(selectedIcon);
		ImageIcon draggedImageIcon = paintSprite(selectedIcon, xPos, yPos);

		Sprite sprite = new Sprite();
		String spriteTempName = new String(spriteNameTextField.getText());
		spriteTempName = spriteTempName + "_" + count;
		sprite.setName(spriteTempName);
		sprite.setX(xPos);
		sprite.setY(yPos);
		sprite.setInitX(xPos);
		sprite.setInitY(yPos);
		sprite.setHeight(draggedImageIcon.getIconHeight());
		sprite.setWidth(draggedImageIcon.getIconWidth());
		
		sprite.setImageName(selectedIcon);
		selectedSprites.add(sprite);
		count++;
	}

	public void setLblGameName(String gameName) {
		lblGameName.setText(gameName);
	}

	private ImageIcon paintSprite(String selectedIcon, int x, int y) {
		URL imageUrl = this.getClass().getResource(selectedIcon);
		ImageIcon draggedImageIcon = new ImageIcon(imageUrl);
		JLabel imageLabelRightPnl = new JLabel(draggedImageIcon);
		
		imageLabelRightPnl.setLocation(x, y);
		imageLabelRightPnl.setBounds(x, y, draggedImageIcon.getIconWidth(), draggedImageIcon.getIconHeight());

		rightPanel.add(imageLabelRightPnl);
		rightPanel.add(bkgImageLabel);
		rightPanel.repaint();
		rightPanel.revalidate();

		return draggedImageIcon;
	}

	public void paintSprites(List<Sprite> sprites, boolean clearBeforePaint) {
		if (clearBeforePaint) {
			rightPanel.repaint();
			rightPanel.revalidate();
		}

		for (Sprite sprite : sprites) {
			paintSprite(sprite.getImageName(), sprite.getX(), sprite.getY());
		}
	}

	// sprite list model builder helper
	public DefaultListModel spriteListModel() {
		List<String> temp = new ArrayList<String>();
		temp = makerController.getAllSprites();
		DefaultListModel associatedSpritesModel = new DefaultListModel();
		for (String spriteName : temp) {
			associatedSpritesModel.addElement(spriteName);
		}
		return associatedSpritesModel;
	}

	// previouslySelectedSpriteComboBox select listener
	public class LoadPreviousSprite implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			if (previousSavedSpritesComboBox.getSelectedItem() != null) {
				selectedSprites.clear();
				String selectedSpriteName = previousSavedSpritesComboBox.getSelectedItem().toString();
				Sprite sprite = makerController.loadSavedSprites(selectedSpriteName);
				selectedSprites.add(sprite);
			}
		}
	}

	// set the sprite name
	public void setSpriteName(String str) {
		spriteNameTextField.setText(str);
	}

	public void setSummaryTextArea(String summaryText) {
		DefaultListModel model = new DefaultListModel();
		for(int i=0; i < summaryTextArea.getModel().getSize(); i++){
			String previousRecord = (String) summaryTextArea.getModel().getElementAt(i);
			model.addElement(previousRecord);
		}
		model.addElement(summaryText);
		summaryTextArea.setModel(model);
	}

	public void resizeContents(double a, double b) {
		if (firstTime > 2) {
			rightPanel.remove(bkgImageLabel);
			Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH = (int) (a/2);
			Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT = (int) (b-80);
			
			rightPanel.setBounds((int) (a/2),0,Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH,Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT);
			ImageIcon bgImageIcon = new ImageIcon(this.getClass().getResource(makerController.getGameModel().getBkgURL()));
			Image reImage = bgImageIcon.getImage();
			reImage = reImage.getScaledInstance(Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH,
					Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT, Image.SCALE_SMOOTH);
			bgImageIcon = new ImageIcon(reImage);
			
			bkgImageLabel = new JLabel(bgImageIcon);
			bkgImageLabel.setBounds(0, Dimensions.GAME_MAKER_RIGHT_PANEL_Y, Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH,
					Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT);

			rightPanel.add(bkgImageLabel);

			/*
			 * bkgImageLabel.setSize(Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH,
			 * Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT);
			 * rightPanel.add(bkgImageLabel);
			 */
		}
		firstTime++;
	}
}

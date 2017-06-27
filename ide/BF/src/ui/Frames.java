package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;

import rmi.RemoteHelper;
public class Frames extends JFrame {
	private JTextArea textArea;//代码区
	private JTextArea inputArea;//输入区
	private JTextArea outputArea;//输出区
	private JTextField user;//用户名区
	private JTextField in;//文件名
	private JButton log;//登陆按钮
	private JFrame file;//文件ui
	private JMenu openMenuItem;//打开菜单
	private JMenu deleteFMenuItem;//删除菜单
	private JLabel flag;//标记登陆成功与否
	private JMenu deleteMenuItem;//删除菜单
	private JMenuItem redoMenuItem;//redo
	private JMenuItem undoMenuItem;//undo
	private JPasswordField password;//密码区
	private JFrame logFrame;//登陆ui
	private JFrame error;//错误ui
	private JFrame errorx;//另一种错误ui
	private static String userNow;//当前用户
	private String userStr = "";//用户
	private String passwordStr = "";//密码
	private DocumentListener documentListener = new DocumentListener();//redo监听器
	private String fileName = "";//文件名
	private static int mode = 1;//bf或者ook模式选择
	private static int logmode = 0;//登陆或者新建用户模式选择

	public Frames() {
		//清除远端记录
		try {
			RemoteHelper.getInstance().getIOService().clear();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// 创建总窗体
		JFrame frame = new JFrame("BF Client");
		frame.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		panel.setLayout(layout);
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		openMenuItem = new JMenu("Open");
		fileMenu.add(openMenuItem);
		deleteFMenuItem = new JMenu("Delete");
		fileMenu.add(deleteFMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem runMenuItem = new JMenuItem("Run");
		fileMenu.add(runMenuItem);
		JMenu modeMenu = new JMenu("Mode");
		menuBar.add(modeMenu);
		JMenuItem BFMenuItem = new JMenuItem("Bf");
		modeMenu.add(BFMenuItem);
		JMenuItem OOKMenuItem = new JMenuItem("Ook");
		modeMenu.add(OOKMenuItem);
		JMenu opMenu = new JMenu("Operation");
		redoMenuItem = new JMenuItem("redo");
		redoMenuItem.setEnabled(false);
		opMenu.add(redoMenuItem);
		undoMenuItem = new JMenuItem("undo");
		undoMenuItem.setEnabled(false);
		opMenu.add(undoMenuItem);
		menuBar.add(opMenu);
		JMenu versionMenu = new JMenu("Version");
		menuBar.add(versionMenu);
		JMenuItem xMenuItem = new JMenuItem("0");
		versionMenu.add(xMenuItem);
		JMenuItem aMenuItem = new JMenuItem("1");
		versionMenu.add(aMenuItem);
		JMenuItem bMenuItem = new JMenuItem("2");
		versionMenu.add(bMenuItem);
		JMenuItem cMenuItem = new JMenuItem("3");
		versionMenu.add(cMenuItem);
		JMenuItem dMenuItem = new JMenuItem("4");
		versionMenu.add(dMenuItem);
		JMenuItem eMenuItem = new JMenuItem("5");
		versionMenu.add(eMenuItem);
		JMenu userMenu = new JMenu("user");
		menuBar.add(userMenu);
		JMenuItem newuserMenuItem = new JMenuItem("Newuser");
		userMenu.add(newuserMenuItem);
		JMenuItem loginMenuItem = new JMenuItem("Login");
		userMenu.add(loginMenuItem);
		JMenuItem logoutMenuItem = new JMenuItem("Logout");
		userMenu.add(logoutMenuItem);
		deleteMenuItem = new JMenu("Delete");
		userMenu.add(deleteMenuItem);
		deleteMenuItem.setEnabled(false);
		frame.setJMenuBar(menuBar);
		textArea = new JTextArea();
		textArea.setMargin(new Insets(10, 10, 10, 10));
		inputArea = new JTextArea(2,17);
		inputArea.setMargin(new Insets(10, 10, 10, 10));
		outputArea = new JTextArea(2,17);
		outputArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setEditable(false);
		inputArea.setEditable(false);
		outputArea.setEditable(false);
		textArea.setBackground(Color.LIGHT_GRAY);
		inputArea.setBackground(Color.LIGHT_GRAY);
		outputArea.setBackground(Color.LIGHT_GRAY);
		textArea.setLineWrap(true);
		inputArea.setLineWrap(true);
		outputArea.setLineWrap(true);
		JScrollPane scroller1 = new JScrollPane(textArea);
		JScrollPane scroller2 = new JScrollPane(inputArea);
		JScrollPane scroller3 = new JScrollPane(outputArea);
		scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scroller2);
		panel.add(scroller3);
		frame.getContentPane().add(BorderLayout.CENTER,scroller1);
		frame.getContentPane().add(BorderLayout.SOUTH,panel);
		frame.pack();
		frame.setSize(500, 400);
		frame.setLocation(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//创建登陆界面
		logFrame = new JFrame("Login");
		JLabel userLabel = new JLabel("User");
		JLabel passwordLabel = new JLabel("Password");
		user = new JTextField(19);
		password = new JPasswordField(16);
		password.setEchoChar('*');
		flag = new JLabel();
		log = new JButton("log");
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel2.setLayout(layout);
		panel3.setLayout(layout);
		panel2.add(userLabel);
		panel2.add(user);
		panel3.add(passwordLabel);
		panel3.add(password);
		logFrame.setLayout(layout);
		logFrame.add(panel2);
		logFrame.add(panel3);
		logFrame.add(flag);
		logFrame.add(log);
		logFrame.pack();
		logFrame.setSize(300, 200);
		logFrame.setLocation(500, 300);
		logFrame.setVisible(false);
		//保存新建文件
		file = new JFrame("FileAdmin");
		JLabel path = new JLabel("Name");
		JButton ok = new JButton("ok");
		in = new JTextField(17);
		file.setLayout(layout);
		file.add(path);
		file.add(in);
		file.add(ok);
		file.setSize(500, 100);
		file.setLocation(400, 300);
		file.setVisible(false);
		//未登录提示
		error = new JFrame("Error");
		JButton okx = new JButton("ok");
		JLabel message = new JLabel("you need a user");
		error.setLayout(layout);
		error.add(message);
		error.add(okx);
		error.setSize(300, 100);
		error.setLocation(500, 300);
		error.setVisible(false);
		//已登录提示
		errorx = new JFrame("Error");
		JButton okex = new JButton("ok");
		JLabel messagex = new JLabel("you need to logout before login");
		errorx.setLayout(layout);
		errorx.add(messagex);
		errorx.add(okex);
		errorx.setSize(300, 100);
		errorx.setLocation(500, 300);
		errorx.setVisible(false);
		newuserMenuItem.addActionListener(new userMenuItemListener());
		loginMenuItem.addActionListener(new userMenuItemListener());
		logoutMenuItem.addActionListener(new userMenuItemListener());
		deleteMenuItem.addActionListener(new userMenuItemListener());
        aMenuItem.addActionListener(new MenuItemActionListener());
        xMenuItem.addActionListener(new MenuItemActionListener());
        bMenuItem.addActionListener(new MenuItemActionListener());
        cMenuItem.addActionListener(new MenuItemActionListener());
        dMenuItem.addActionListener(new MenuItemActionListener());
        eMenuItem.addActionListener(new MenuItemActionListener());
        BFMenuItem.addActionListener(new MenuItemActionListener());
		OOKMenuItem.addActionListener(new MenuItemActionListener());
		newMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		redoMenuItem.addActionListener(new MenuItemActionListener());
		undoMenuItem.addActionListener(new MenuItemActionListener());
		textArea.getDocument().addDocumentListener(documentListener);
		log.addActionListener(new buttonActionListener_log());
		ok.addActionListener(new buttonActionListener_ok());
		okx.addActionListener(new buttonActionListener_okx());
		okex.addActionListener(new buttonActionListener_okex());
		}

	 class MenuItemActionListener implements ActionListener{//新建运行删除文件选择版本选择模式redoundo
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(userNow == null){
				error.setVisible(true);
			}
			else{
				if(cmd.equals("New")){
				in.setText(null);
				textArea.setText(null);
				inputArea.setText(null);
				outputArea.setText(null);
				file.setVisible(true);
				textArea.setEditable(true);
				inputArea.setEditable(true);
			}
			//run的设计思路：将text区存到文件里再取出来执行
			else if (cmd.equals("Run")) {
				String code = textArea.getText();
				String input = inputArea.getText();
				try{
					String finalCode = null;
					if(mode == 1){
						userNow = RemoteHelper.getInstance().getUserService().getName();
						RemoteHelper.getInstance().getIOService().writeFile(code, userNow, fileName + ".bf");
						finalCode = RemoteHelper.getInstance().getDealService().dealing(userNow + "_" + fileName + ".bf");
					}
						else{
						userNow = RemoteHelper.getInstance().getUserService().getName();
						RemoteHelper.getInstance().getIOService().writeFile(code, userNow, fileName + ".ook");
						finalCode = RemoteHelper.getInstance().getDealService().dealing(userNow + "_" + fileName + ".ook");
						finalCode = RemoteHelper.getInstance().getExcuteServise().turn(finalCode);
					}
					outputArea.setText(RemoteHelper.getInstance().getExcuteServise().execute(finalCode, input + '\0'));
					inputArea.setText(null);
			}catch(Exception o){
				o.printStackTrace();
			}
			}
			else if(cmd.equals("Bf")){
				mode = 1;
			}
			else if(cmd.equals("Ook")){
				mode = 2;
			}
			else if(cmd.equals("0")){
				try{
				textArea.setText(RemoteHelper.getInstance().getIOService().getCode(1));
				}catch(Exception e1){
					e1.printStackTrace();
				}
				}
			else if(cmd.equals("1")){
				try{
				textArea.setText(RemoteHelper.getInstance().getIOService().getCode(2));
				}catch(Exception e1){
					e1.printStackTrace();
				}
				}
			else if(cmd.equals("2")){
				try{
				textArea.setText(RemoteHelper.getInstance().getIOService().getCode(3));
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			else if(cmd.equals("3")){
				try{
				textArea.setText(RemoteHelper.getInstance().getIOService().getCode(4));
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			else if(cmd.equals("4")){
				try{
				textArea.setText(RemoteHelper.getInstance().getIOService().getCode(5));
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			else if(cmd.equals("5")){
				try{
				textArea.setText(RemoteHelper.getInstance().getIOService().getCode(6));
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			else if(cmd.equals("redo")){
				try{
					textArea.getDocument().removeDocumentListener(documentListener);
					RemoteHelper.getInstance().getIOService().addUn(textArea.getText());
				textArea.setText(RemoteHelper.getInstance().getIOService().redo());
				undoMenuItem.setEnabled(true);
				if(!RemoteHelper.getInstance().getIOService().textredo())
					redoMenuItem.setEnabled(false);
				textArea.getDocument().addDocumentListener(documentListener);
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			else if(cmd.equals("undo")){
				try{
					textArea.getDocument().removeDocumentListener(documentListener);
					textArea.setText(RemoteHelper.getInstance().getIOService().undo());
					RemoteHelper.getInstance().getIOService().addRe(textArea.getText());
				redoMenuItem.setEnabled(true);
				if(!RemoteHelper.getInstance().getIOService().textundo())
					undoMenuItem.setEnabled(false);
				textArea.getDocument().addDocumentListener(documentListener);
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			}
		}
	}

	class SaveActionListener implements ActionListener {//保存

		@Override
		public void actionPerformed(ActionEvent e) {
			if(userNow == null){
				error.setVisible(true);
			}
			else{
			String code = textArea.getText();
			try {
				userNow = RemoteHelper.getInstance().getUserService().getName();
				RemoteHelper.getInstance().getIOService().save(code);
				if(mode == 1)
					RemoteHelper.getInstance().getIOService().writeFile(code, userNow, fileName + ".bf");
				else{
					RemoteHelper.getInstance().getIOService().writeFile(code, userNow, fileName + ".ook");
				}
				} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		}
	}


	class buttonActionListener_log implements ActionListener{//登陆或者新建用户的响应
		@Override
		public void actionPerformed(ActionEvent e){
			userStr = user.getText();
			passwordStr = password.getText();
			if(logmode == 0){//登陆
				try{
			flag.setText(RemoteHelper.getInstance().getUserService().login(userStr, passwordStr));
				}catch(Exception m){
					m.printStackTrace();
				}
			if(flag.getText().equals("success")){
				textArea.setText(null);
				inputArea.setText(null);
				outputArea.setText(null);
				textArea.setEditable(false);
				inputArea.setEditable(false);
				try{
					openMenuItem.removeAll();
					deleteFMenuItem.removeAll();
					userNow = RemoteHelper.getInstance().getUserService().getName();
					if(userNow.equals("admin")){
						deleteMenuItem.removeAll();
						deleteMenuItem.setEnabled(true);
						String temp[] = RemoteHelper.getInstance().getUserService().getUserList().split(" ");
						for(String x : temp){
							if(x == null || x.length() == 0)
								break;
							JMenuItem t = new JMenuItem(x);
							t.addActionListener(new MenuItemActionListener_deleteUser());
							deleteMenuItem.add(t);
						}
					}
					else
						deleteMenuItem.setEnabled(false);
					if(RemoteHelper.getInstance().getIOService().readFileList(userNow) != null){
					String temp[] = RemoteHelper.getInstance().getIOService().readFileList(userNow).split(" ");
					for(int i = 0;i < temp.length;i++){
						JMenuItem x = new JMenuItem(temp[i]);
						x.addActionListener(new MenuItemActionListener_open());
						JMenuItem y = new JMenuItem(temp[i]);
						y.addActionListener(new MenuItemActionListener_delete());
						openMenuItem.add(x);
						deleteFMenuItem.add(y);
					}
					}
				}catch(Exception a){
					a.printStackTrace();
				}
				user.setText("");
				password.setText("");
				flag.setText("");
				logFrame.setVisible(false);
				}
			else{
				user.setText("");
				password.setText("");
			}
			}
			else{//新建
				try{
				if(RemoteHelper.getInstance().getUserService().newuser(userStr, passwordStr)){
					user.setText("");
					password.setText("");
					flag.setText("");
					logFrame.setVisible(false);
					if(userNow.equals("admin")){
						deleteMenuItem.removeAll();
						deleteMenuItem.setEnabled(true);
						String temp[] = RemoteHelper.getInstance().getUserService().getUserList().split(" ");
						for(String x : temp){
							if(x == null || x.length() == 0)
								break;
							JMenuItem t = new JMenuItem(x);
							t.addActionListener(new MenuItemActionListener_deleteUser());
							deleteMenuItem.add(t);
						}
					}
					else
						deleteMenuItem.setEnabled(false);
					try{

					}catch(Exception e1){
						e1.printStackTrace();
					}
				}
				else{
					user.setText("");
					password.setText("");
					flag.setText("error");
				}
			}catch(Exception y){
				y.printStackTrace();
			}
			}
		}
	}

	class buttonActionListener_ok implements ActionListener{//响应新建文件
		@Override
		public void actionPerformed(ActionEvent e){
			int flag = 0;
			try{
				if(RemoteHelper.getInstance().getIOService().readFileList(userNow) != null){
			String temp[] = RemoteHelper.getInstance().getIOService().readFileList(userNow).split(" ");
			for(int i = 0;i < temp.length;i++){
				if(temp[i].equals(in.getText())){
					in.setText("existed same file");
					flag = 1;
				}
			}
				}
			if(flag == 0){
					if(in.getText().length() != 0){
					fileName = in.getText();
					file.setVisible(false);
					JMenuItem x = new JMenuItem(fileName);
					x.addActionListener(new MenuItemActionListener_open());
					JMenuItem y = new JMenuItem(fileName);
					y.addActionListener(new MenuItemActionListener_delete());
					openMenuItem.add(x);
					deleteFMenuItem.add(y);
						userNow = RemoteHelper.getInstance().getUserService().getName();
						RemoteHelper.getInstance().getIOService().addFile(fileName, userNow);

					if(mode == 1)
						RemoteHelper.getInstance().getIOService().writeFile("", userNow, fileName + ".bf");
					else
						RemoteHelper.getInstance().getIOService().writeFile("", userNow, fileName + ".ook");
			}
			}
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
	}

	class buttonActionListener_okx implements ActionListener{//响应无用户界面
		@Override
		public void actionPerformed(ActionEvent e){
			error.setVisible(false);
		}
	}

	class buttonActionListener_okex implements ActionListener{//响应有用户界面
		@Override
		public void actionPerformed(ActionEvent e){
			errorx.setVisible(false);
		}
	}

	class MenuItemActionListener_open implements ActionListener{//打开文件
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			try{
				String temp[] = RemoteHelper.getInstance().getIOService().readFileList(userNow).split(" ");
				for(int i = 0;i < temp.length;i++){
					if(cmd.equals(temp[i])){
						fileName = temp[i];
						if(mode == 1)
							textArea.setText(RemoteHelper.getInstance().getIOService().readFile(userNow, fileName + ".bf"));
						else
							textArea.setText(RemoteHelper.getInstance().getIOService().readFile(userNow, fileName + ".ook"));
						textArea.setEditable(true);
						inputArea.setEditable(true);
					}
				}
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
	}

	class MenuItemActionListener_deleteUser implements ActionListener{//响应删除用户



		@Override
		public void actionPerformed(ActionEvent e){
			String cmd = e.getActionCommand();
			try{
			if(mode == 1){
				RemoteHelper.getInstance().getUserService().deleteuser(cmd, "bf");
			}
			else
				RemoteHelper.getInstance().getUserService().deleteuser(cmd, "ook");
			deleteMenuItem.removeAll();
			String temp[] = RemoteHelper.getInstance().getUserService().getUserList().split(" ");
			for(String x : temp){
				if(x == null || x.length() == 0)
					break;
				JMenuItem t = new JMenuItem(x);
				t.addActionListener(new MenuItemActionListener_deleteUser());
				deleteMenuItem.add(t);
			}
			}catch(RemoteException e1){
				e1.printStackTrace();
			}
		}

	}

	class userMenuItemListener implements ActionListener{//登陆登出新建用户删除用户

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("Newuser")){
				logFrame.setTitle("New");
				logmode = 1;
				flag.setText(null);
				logFrame.setVisible(true);
				log.setText("new");
			}
			else if(cmd.equals("Login")){
				logFrame.setTitle("Login");
				if(userNow != null){
					errorx.setVisible(true);
				}
				else{
				logmode = 0;
				flag.setText(null);
				log.setText("log");
				logFrame.setVisible(true);
				}
			}
			else if(cmd.equals("Logout")){
				userNow = null;
				deleteMenuItem.setEnabled(false);
				textArea.setText(null);
				inputArea.setText(null);
				outputArea.setText(null);
				textArea.setEditable(false);
				inputArea.setEditable(false);
				openMenuItem.removeAll();
				deleteFMenuItem.removeAll();
			}

		}//用户菜单

	}

	class MenuItemActionListener_delete implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			try{
			RemoteHelper.getInstance().getIOService().delete(userNow,cmd,mode);
			openMenuItem.removeAll();
			deleteFMenuItem.removeAll();
			if(RemoteHelper.getInstance().getIOService().readFileList(userNow) != null){
			String temp[] = RemoteHelper.getInstance().getIOService().readFileList(userNow).split(" ");
			for(int i = 0;i < temp.length;i++){
				JMenuItem x = new JMenuItem(temp[i]);
				x.addActionListener(new MenuItemActionListener_open());
				JMenuItem y = new JMenuItem(temp[i]);
				y.addActionListener(new MenuItemActionListener_delete());
				openMenuItem.add(x);
				deleteFMenuItem.add(y);
			}
			}
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}

	}

	class DocumentListener implements javax.swing.event.DocumentListener{

	@Override
	public void changedUpdate(DocumentEvent e) {
		try{
			RemoteHelper.getInstance().getIOService().addRe(textArea.getText());
		}catch(Exception e1){
			e1.printStackTrace();
		}
		redoMenuItem.setEnabled(true);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		try{
			RemoteHelper.getInstance().getIOService().addRe(textArea.getText());
		}catch(Exception e1){
			e1.printStackTrace();
		}
		redoMenuItem.setEnabled(true);

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		try{
			RemoteHelper.getInstance().getIOService().addRe(textArea.getText());
		}catch(Exception e1){
			e1.printStackTrace();
		}
		redoMenuItem.setEnabled(true);
	}
	}
}


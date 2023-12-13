package termProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import static javax.swing.JOptionPane.*;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JTextArea;

public class Parkinglot extends JFrame {

	private DefaultTableModel dtm;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private DBController DBC;
	Object[][] data = new Object[20][4];
	String header[] = { "차량번호", "차량타입", "입차시간" };

	ButtonGroup group = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Parkinglot frame = new Parkinglot();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** data배열중 값이 들어가있는 크기를 반환하는 메소드 */
	public int getDataSize() {
		int size = 0;
		for (int i = 0; i < data.length; i++) {
			// 해당 행의 첫 번째 열이 null이 아니라면 크기 증가
			if (data[i][0] != null) {
				size++;
			}
		}
		return size;
	}

	/** 데이터베이스에서 텍스트필드에 있는 차량번호와 같은 번호가 있는지 확인하는 메소드 */
	public Object isCarNum(String TextValue) {
		Object returnValue = null;
		for (int i = 0; i < getDataSize(); i++) {
			if (data[i][0].equals(TextValue)) {
				returnValue = data[i][0];
				break;
			}
		}
		return returnValue;
	}

	/** 라디오 버튼의 값 반환해주는 메소드 */
	public String getRadio() {
		Enumeration<AbstractButton> enums = group.getElements();
		String radio = "";

		while (enums.hasMoreElements()) {
			AbstractButton ab = enums.nextElement();
			JRadioButton jb = (JRadioButton) ab;

			if (jb.isSelected())
				radio = jb.getText();
		}
		return radio;
	}

	/** 현재 날짜/시간을 반환해주는 메소드 */
	public String getTime() {
		// 현재 날짜/시간
		LocalDateTime now = LocalDateTime.now();
		// 포맷팅
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

		return formatedNow;
	}

	/** 2차원 배열에서 값 검색 후 인덱스 반환하는 함수 */
	public int getdtmIndex(String textData) {
		int i = 0;
		for (i = 0; i < dtm.getRowCount(); i++) {
			if (textField.getText().equals(dtm.getValueAt(i, 0))) {
				return i;
			}
		}
		return -1;
	}

	/** 20개의 주차공간중, SUV타입인 차량의 갯수를 반환하는 메소드 */
	public int getSUVSum() {
		String checkVal = "SUV";
		int isSUVSum = 0;
		for (int i = 0; i < dtm.getRowCount(); i++) {
			Object value = dtm.getValueAt(i, 1);
			if (value != null && value.equals(checkVal)) {
				isSUVSum += 1;
			}
		}
		return isSUVSum;
	}

	/** 20칸의 주차공간중, null값을 찾아 반환하는 메소드 */
	public int getNullSum() {
		int NullSum = 0;
		for (int i = 0; i < dtm.getRowCount(); i++) {
			if (dtm.getValueAt(i, 0) == null) {
				NullSum += 1;
			}
		}
		return NullSum;
	}

	/** 차가 들어간 시점을 반환하는 메소드 */
	public Object getCarInTimeSave(String TextValue) {
		Object CarInTimeSave = null;
		
		for (int i = 0; i < getDataSize(); i++) {
			if (data[i][0].equals(TextValue)) {
				CarInTimeSave = data[i][2];
				break;
			}
		}
		return CarInTimeSave;
	}

	private String CarNum = null;
	private String CarType = null;
	private String CarInTime = null;

	Parkinglot(String CarNum, String CarType, String CarInTime) {
		this.CarNum = CarNum;
		this.CarType = CarType;
		this.CarInTime = CarInTime;
	}

	public String getCarNum() {
		return CarNum;
	}

	public void setCarNum(String CarNum) {
		this.CarNum = CarNum;
	}

	public String getCarType() {
		return CarType;
	}

	public void setCarType(String CarType) {
		this.CarType = CarType;
	}

	public String getCarInTime() {
		return CarInTime;
	}

	public void setCarInTime(String CarInTime) {
		this.CarInTime = CarInTime;
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */

	public Object DBReload() {

		// DB 연결부
		DBC = new DBController();
		DBC.startConnection();

		// DB에서 값을 가져오기 위한 준비
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = DBC.con;
		StringBuffer sql = new StringBuffer();
		sql.append("select carnum, cartype, carintime from parkinglot");
		try {
			pstmt = con.prepareStatement(sql.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		try {
			while (rs.next()) {
				String CarNum = rs.getString("CARNUM");
				String CarType = rs.getString("CARTYPE");
				String CarIntime = rs.getString("CARINTIME");

				data[i][0] = CarNum;
				data[i][1] = CarType;
				data[i][2] = CarIntime;

				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public Parkinglot() throws SQLException {

		DBReload();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 테이블 객체에 만들어진 걸 부착한다

		JPanel Main_screen = new JPanel();
		Main_screen.setBounds(0, 0, 447, 335);
		contentPane.add(Main_screen);
		Main_screen.setLayout(null);
		Main_screen.setVisible(true);

		JPanel State_screen = new JPanel();
		State_screen.setBounds(0, 0, 447, 335);
		contentPane.add(State_screen);
		State_screen.setVisible(false);
		State_screen.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 446, 292);
		State_screen.add(scrollPane);
		dtm = new DefaultTableModel(data, header);
		JTable table = new JTable(dtm);
		table.setSize(4, 20);
		scrollPane.setViewportView(table);
		table.getColumn("차량번호").setPreferredWidth(70);
		table.getColumn("차량타입").setPreferredWidth(90);
		table.getColumn("입차시간").setPreferredWidth(300);

		JPanel panel = new JPanel();
		panel.setBounds(0, 167, 446, 167);
		State_screen.add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("차량번호 입력");
		textField.setToolTipText("");
		textField.setBounds(111, 22, 207, 30);
		Main_screen.add(textField);
		textField.setColumns(10);

		JRadioButton rdbtn_sedan = new JRadioButton("SEDAN");
		rdbtn_sedan.setBounds(332, 74, 71, 23);
		Main_screen.add(rdbtn_sedan);
		rdbtn_sedan.setSelected(true);

		JRadioButton rdbtn_suv = new JRadioButton("SUV");
		rdbtn_suv.setBounds(332, 99, 61, 23);
		Main_screen.add(rdbtn_suv);

		group.add(rdbtn_sedan);
		group.add(rdbtn_suv);

		JTextArea tA_ParkState = new JTextArea();
		tA_ParkState.setBounds(329, 12, 90, 52);
		Main_screen.add(tA_ParkState);
		tA_ParkState.setEditable(false);
		tA_ParkState.append("총 자리 / 빈자리\nSEDAN : 20 / " + getNullSum() + "\nSUV : 5 / " + (5 - getSUVSum()));
		tA_ParkState.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		tA_ParkState.setAlignmentY(JTextArea.CENTER_ALIGNMENT);

		JButton btn_0 = new JButton("0");
		btn_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 6) {
					textField.setText(null);
					textField.setText("0");
				} else if (textField.getText().length() > 3) {
					showMessageDialog(null, "차량 번호 초과");
				} else {
					textField.setText(textField.getText() + "0");
				}
			}
		});
		btn_0.setFont(new Font("굴림", Font.PLAIN, 18));
		btn_0.setBounds(184, 264, 61, 60);
		Main_screen.add(btn_0);

		JButton btn_1 = new JButton("1");
		btn_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 6) {
					textField.setText(null);
					textField.setText("1");
				} else if (textField.getText().length() > 3) {
					showMessageDialog(null, "차량 번호 초과");
				} else {
					textField.setText(textField.getText() + "1");
				}
			}
		});
		btn_1.setFont(new Font("굴림", Font.PLAIN, 18));
		btn_1.setBounds(111, 62, 61, 60);
		Main_screen.add(btn_1);

		JButton btn_2 = new JButton("2");
		btn_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 6) {
					textField.setText(null);
					textField.setText("2");
				} else if (textField.getText().length() > 3) {
					showMessageDialog(null, "차량 번호 초과");
				} else {
					textField.setText(textField.getText() + "2");
				}
			}
		});
		btn_2.setFont(new Font("굴림", Font.PLAIN, 18));
		btn_2.setBounds(184, 62, 61, 60);
		Main_screen.add(btn_2);

		JButton btn_3 = new JButton("3");
		btn_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 6) {
					textField.setText(null);
					textField.setText("3");
				} else if (textField.getText().length() > 3) {
					showMessageDialog(null, "차량 번호 초과");
				} else {
					textField.setText(textField.getText() + "3");
				}
			}
		});
		btn_3.setFont(new Font("굴림", Font.PLAIN, 18));
		btn_3.setBounds(257, 62, 61, 60);
		Main_screen.add(btn_3);

		JButton btn_4 = new JButton("4");
		btn_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 6) {
					textField.setText(null);
					textField.setText("4");
				} else if (textField.getText().length() > 3) {
					showMessageDialog(null, "차량 번호 초과");
				} else {
					textField.setText(textField.getText() + "4");
				}
			}
		});
		btn_4.setFont(new Font("굴림", Font.PLAIN, 18));
		btn_4.setBounds(111, 128, 61, 60);
		Main_screen.add(btn_4);

		JButton btn_5 = new JButton("5");
		btn_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 6) {
					textField.setText(null);
					textField.setText("5");
				} else if (textField.getText().length() > 3) {
					showMessageDialog(null, "차량 번호 초과");
				} else {
					textField.setText(textField.getText() + "5");
				}
			}
		});
		btn_5.setFont(new Font("굴림", Font.PLAIN, 18));
		btn_5.setBounds(184, 128, 61, 60);
		Main_screen.add(btn_5);

		JButton btn_6 = new JButton("6");
		btn_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 6) {
					textField.setText(null);
					textField.setText("6");
				} else if (textField.getText().length() > 3) {
					showMessageDialog(null, "차량 번호 초과");
				} else {
					textField.setText(textField.getText() + "6");
				}
			}
		});
		btn_6.setFont(new Font("굴림", Font.PLAIN, 18));
		btn_6.setBounds(257, 128, 61, 60);
		Main_screen.add(btn_6);

		JButton btn_7 = new JButton("7");
		btn_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 6) {
					textField.setText(null);
					textField.setText("7");
				} else if (textField.getText().length() > 3) {
					showMessageDialog(null, "차량 번호 초과");
				} else {
					textField.setText(textField.getText() + "7");
				}
			}
		});
		btn_7.setFont(new Font("굴림", Font.PLAIN, 18));
		btn_7.setBounds(111, 194, 61, 60);
		Main_screen.add(btn_7);

		JButton btn_8 = new JButton("8");
		btn_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 6) {
					textField.setText(null);
					textField.setText("8");
				} else if (textField.getText().length() > 3) {
					showMessageDialog(null, "차량 번호 초과");
				} else {
					textField.setText(textField.getText() + "8");
				}
			}
		});
		btn_8.setFont(new Font("굴림", Font.PLAIN, 18));
		btn_8.setBounds(184, 194, 61, 60);
		Main_screen.add(btn_8);

		JButton btn_9 = new JButton("9");
		btn_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 6) {
					textField.setText(null);
					textField.setText("9");
				} else if (textField.getText().length() > 3) {
					showMessageDialog(null, "차량 번호 초과");
				} else {
					textField.setText(textField.getText() + "9");
				}
			}
		});
		btn_9.setFont(new Font("굴림", Font.PLAIN, 18));
		btn_9.setBounds(257, 194, 61, 60);
		Main_screen.add(btn_9);

		JButton btn_enter = new JButton("입차");
		btn_enter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 3) {
					if (!textField.getText().equals(isCarNum(textField.getText()))) {
						if (getNullSum() > 0) {
							if (getSUVSum() < 5 || getRadio().equals("SEDAN")) {
								String input = "insert into parkinglot values('" + textField.getText() + "', '"
										+ getRadio() + "','" + getTime() + "')";
								
								int rowIndex = -1;
								for (int i = 0; i < dtm.getRowCount(); i++) {
									if (dtm.getValueAt(i, 0) == null) {
										rowIndex = i;
										break;
									}
								}

								Object newInput[] = new Object[4];
								newInput[0] = textField.getText();
								newInput[1] = getRadio();
								newInput[2] = getTime();

								for (int columnIndex = 0; columnIndex < dtm.getColumnCount(); columnIndex++) {
									dtm.setValueAt(newInput[columnIndex], rowIndex, columnIndex);
								}
								tA_ParkState.setText("총 자리 / 빈자리\nSEDAN : 20 / " + getNullSum() + "\nSUV : 5 / "
										+ (5 - getSUVSum()));

								System.out.println(input);
								DBC.ExeQry(input);
								DBReload();

								showMessageDialog(null, "[" + getCarInTimeSave(textField.getText()) + "]에 [" + textField.getText()
								+ "]번호의 [" + getRadio() + "]차량이 입차되었습니다.");
								
							} else {
								showMessageDialog(null, "SUV주차공간 5칸이 가득 찼습니다.");
							}
						} else {
							showMessageDialog(null, "비어있는 주차공간이 없습니다.");
						}
					} else {
						showMessageDialog(null, "해당하는 번호의 차량이 이미 입차되어 있습니다.");
					}
				} else {
					showMessageDialog(null, "차량번호는 4자리입니다.");
				}
			}
		});
		btn_enter.setFont(new Font("굴림", Font.PLAIN, 12));
		btn_enter.setBounds(329, 195, 90, 60);
		Main_screen.add(btn_enter);

		JButton btn_out = new JButton("출차");
		btn_out.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 3) {
					if (textField.getText().equals(isCarNum(textField.getText()))
							&& getdtmIndex(textField.getText()) != -1 && (textField.getText()
									.equals(dtm.getValueAt(getdtmIndex(textField.getText()), 0)) == true)) {
						
						showMessageDialog(null, "[" + getCarInTimeSave(textField.getText()) + "]에 입차되었던 [" + textField.getText() + "]번의 ["
								+ getRadio() + "]차량이 [" + getTime() + "]에 출차되었습니다.");
						
						String output = "delete from parkinglot where carnum = '" + textField.getText() + "'";


						int dtmIndex = getdtmIndex(textField.getText());
						for (int columnIndex = 0; columnIndex < dtm.getColumnCount(); columnIndex++) {
							dtm.setValueAt(null, dtmIndex, columnIndex);
						}

						tA_ParkState.setText(
								"총 자리 / 빈자리\nSEDAN : 20 / " + getNullSum() + "\nSUV : 5 / " + (5 - getSUVSum()));

						DBC.ExeQry(output);
						DBReload();

					} else {
						showMessageDialog(null, "해당하는 번호의 차량이 입차되어있지 않습니다.");
					}
				} else {
					showMessageDialog(null, "차량번호는 4자리입니다.");
				}
			}
		});
		btn_out.setFont(new Font("굴림", Font.PLAIN, 12));
		btn_out.setBounds(330, 264, 89, 60);
		Main_screen.add(btn_out);

		JButton btn_state = new JButton("현황 조회");
		btn_state.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Main_screen.setVisible(false);
				State_screen.setVisible(true);
			}
		});
		btn_state.setBounds(330, 129, 89, 60);
		Main_screen.add(btn_state);

		JButton btn_delete = new JButton("Del");
		btn_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 0) {
					String tmp = textField.getText();
					tmp = tmp.substring(0, tmp.length() - 1);
					textField.setText(tmp);
				} else {
					showMessageDialog(null, "입력된 값이 없습니다.");
				}
			}
		});
		btn_delete.setFont(new Font("굴림", Font.PLAIN, 12));
		btn_delete.setBounds(111, 265, 61, 60);
		Main_screen.add(btn_delete);

		JButton btn_delete_all = new JButton("DelA");
		btn_delete_all.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 0) {
					textField.setText("차량번호 입력");
				} else {
					showMessageDialog(null, "입력된 값이 없습니다.");
				}
			}
		});
		btn_delete_all.setFont(new Font("굴림", Font.PLAIN, 12));
		btn_delete_all.setBounds(257, 265, 61, 60);
		Main_screen.add(btn_delete_all);

		JButton return_btn = new JButton("돌아가기");
		return_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main_screen.setVisible(true);
				State_screen.setVisible(false);
			}
		});
		return_btn.setBounds(332, 126, 114, 41);
		panel.add(return_btn);
	}
}

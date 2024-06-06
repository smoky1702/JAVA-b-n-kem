package com.poly.app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.poly.entity.Account;
import com.poly.service.AccountService;

@SuppressWarnings("serial")
public class LoginJFrame extends JFrame {
	@Autowired
	AccountService aservice;
	Gson gson = new Gson();
	@Autowired
	Account account;

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	private static final Logger logger = LogManager.getLogger(LoginJFrame.class.getName());

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				logger.error("oke");
				try {
					LoginJFrame frame = new LoginJFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 static  String getMd5(String input) 
	    { 
	        try { 
	            // Static getInstance method is called with hashing MD5 
	            MessageDigest md = MessageDigest.getInstance("MD5"); 
	  
	            // digest() method is called to calculate message digest 
	            //  of an input digest() return array of byte 
	            byte[] messageDigest = md.digest(input.getBytes()); 
	  
	            // Convert byte array into signum representation 
	            BigInteger no = new BigInteger(1, messageDigest); 
	  
	            // Convert message digest into hex value 
	            String hashtext = no.toString(16); 
	            while (hashtext.length() < 32) { 
	                hashtext = "0" + hashtext; 
	            } 
	            return hashtext; 
	        }  
	        // For specifying wrong message digest algorithms 
	        catch (NoSuchAlgorithmException e) { 
	            throw new RuntimeException(e); 
	        } 
	    }
	Account getModel() {
		String username=txtUser.getText();
		String password=txtPass.getText();
		Account model = new Account();
		model.setUsername(username);
		model.setPassword(getMd5(password));
		return model;
	}

	void Resister() {
		try {
			// Tạo một HttpClient và gửi yêu cầu POST
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String apiUrl = "http://localhost:8080/rest/accounts"; // Thay thế bằng URL API của bạn
			HttpPost httpPost = new HttpPost(apiUrl);

			// Định nghĩa dữ liệu JSON để gửi trong phần thân của yêu cầu
			account = getModel();
			String json = gson.toJson(account);
			StringEntity entity = new StringEntity(json);
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-Type", "application/json");

			// Thực hiện yêu cầu POST
			CloseableHttpResponse response = httpClient.execute(httpPost);

			// Xử lý phản hồi theo cách cần thiết
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("Mã trạng thái phản hồi: " + statusCode);

			// Đóng HttpClient
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// test
	public void Login() {
		try {
			// Tạo một HttpClient và gửi yêu cầu POST
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String apiUrl = "http://localhost:9000/api/login"; // Thay thế bằng URL API của bạn
			HttpPost httpPost = new HttpPost(apiUrl);
			// Định nghĩa dữ liệu JSON để gửi trong phần thân của yêu cầu
			account = getModel();
			String json = gson.toJson(account);
			StringEntity entity = new StringEntity(json);
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-Type", "application/json");
			// Thực hiện yêu cầu POST
			CloseableHttpResponse response = httpClient.execute(httpPost);
			// Xử lý phản hồi theo cách cần thiết
			int statusCode = response.getStatusLine().getStatusCode();
//			System.out.println("Mã trạng thái phản hồi: " + statusCode);
			if (statusCode == 200) {
				System.out.println("Login success");
			} else
				System.out.println("Login fail");

			// Đóng HttpClient
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * Create the frame.
	 */
	public LoginJFrame() {

//		setIconImage(XImages.getAppIcons());
		setTitle("BTM Login Form");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 510);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("User Name:");
		lblNewLabel_1.setForeground(new Color(12, 192, 223));
		lblNewLabel_1.setBounds(349, 186, 105, 19);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel_1);

		txtUser = new JTextField();
		txtUser.setBackground(new Color(255, 255, 255));
		txtUser.setBounds(464, 176, 239, 40);
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUser.setColumns(15);
		contentPane.add(txtUser);

		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setForeground(new Color(12, 192, 223));
		lblNewLabel.setBounds(349, 235, 85, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel);

		txtPass = new JPasswordField();
		txtPass.setBounds(464, 225, 239, 40);
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPass.setColumns(15);
		contentPane.add(txtPass);

		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(12, 192, 223));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(464, 308, 239, 40);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login();
			}
		});
		contentPane.add(btnNewButton);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBorder(new TitledBorder(new LineBorder(new Color(12, 192, 223)), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		btnCancel.setForeground(new Color(12, 192, 223));
		btnCancel.setBackground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resister();
			}
		});
		btnCancel.setBounds(464, 356, 239, 40);
		contentPane.add(btnCancel);

		JLabel lblNewLabel_2 = new JLabel("SIGN IN");
		lblNewLabel_2.setForeground(new Color(12, 192, 223));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 37));
		lblNewLabel_2.setBounds(464, 74, 239, 62);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(49, 118, 270, 255);
		contentPane.add(lblNewLabel_3);
	}
}

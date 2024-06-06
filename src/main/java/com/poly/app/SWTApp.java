package com.poly.app;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;


import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;

public class SWTApp {
    public static void main(String[] args) {
    	  Display display = new Display();
          Shell shell = new Shell(display);
          shell.setLayout(new GridLayout());

          Button button = new Button(shell, SWT.PUSH);
          button.setText("Submit Button");
        button.addListener(SWT.Selection, event -> {
            try {
                // Tạo một HttpClient và gửi yêu cầu POST
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                String apiUrl = "http://localhost:8080/rest/accounts"; // Thay thế bằng URL API của bạn
                HttpPost httpPost = new HttpPost(apiUrl);

                // Định nghĩa dữ liệu JSON để gửi trong phần thân của yêu cầu
                String json = "  {\r\n"
                		+ "        \"username\": \"smoky\",\r\n"
                		+ "        \"password\": \"444\",\r\n"
                		+ "        \"fullname\": \"Phạm Minh Hiếu\",\r\n"
                		+ "        \"email\": \"minhhieupham7@gmail.com\",\r\n"
                		+ "        \"photo\": \"user.png\",\r\n"
                		+ "        \"token\": \"token\"\r\n"
                		+ "    }";
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
        });

        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}


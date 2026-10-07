package org.example.Controller;

import org.example.AI.CozeApiExample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainAppController {

    @FXML
    private TextArea chatDisplay;

    @FXML
    private TextField inputField;

    @FXML
    private Button sendButton;

    @FXML
    public void initialize() {
        // 注册发送按钮的事件处理器
        sendButton.setOnAction(event -> sendMessage());

        // 可以选择添加输入框的键盘事件处理器，支持回车发送消息
        inputField.setOnAction(event -> sendMessage());
    }

    // 发送消息的方法
    private void sendMessage() {
        String message = inputField.getText().trim();

        if (!message.isEmpty()) {
            // 将消息添加到显示区域
            chatDisplay.appendText("You: " + message + "\n");

            // 在控制台打印调试信息
            System.out.println("Message sent: " + message);

            // 调用botResponse方法获取bot的回复
            String botResponse = botResponse(message);
            System.out.println("Bot response: " + botResponse);

            // 显示bot的回复到聊天区域
            if (chatDisplay != null) {
                chatDisplay.appendText("Camelia: " + botResponse + "\n");
            }

            // 清空输入框
            inputField.clear();
        }
    }

    private String botResponse(String input) {
        System.out.println("botResponse method called with input: " + input);
        String botReply = "";

        try {
            botReply = CozeApiExample.getBotResponse(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return botReply;
    }
}
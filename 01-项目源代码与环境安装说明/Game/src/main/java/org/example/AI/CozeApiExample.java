package org.example.AI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CozeApiExample {

    public static String getBotResponse(String input) throws Exception {
        StringBuilder completedContent = new StringBuilder();

        // 1. 创建URL对象
        URL url = new URL("https://api.coze.com/v3/chat");

        // 2. 打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 3. 设置请求方式为 POST
        connection.setRequestMethod("POST");

        // 4. 设置请求头
        // 注意：出于安全考虑，原始代码中硬编码的 Coze API 访问令牌（pat_...）已移除。
        // 运行前请替换为你自己在 https://www.coze.cn 申请的个人访问令牌。
        connection.setRequestProperty("Authorization", "Bearer <your-coze-api-token>");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");

        // 5. 启用输入输出流
        connection.setDoOutput(true);

        // 6. 准备请求体内容
        String botId = "7452756665369755654";
        String userId = "7331048926987207680";
        JsonObject jsonInput = new JsonObject();
        jsonInput.addProperty("bot_id", botId);
        jsonInput.addProperty("user_id", userId);
        jsonInput.addProperty("stream", true); // 启用流模式
        jsonInput.addProperty("auto_save_history", true); // 自动保存历史记录

        // 创建additional_messages内容
        JsonArray additionalMessages = new JsonArray();
        JsonObject message = new JsonObject();
        message.addProperty("role", "user");
        message.addProperty("content", input);
        message.addProperty("content_type", "text");
        additionalMessages.add(message);

        jsonInput.add("additional_messages", additionalMessages);

        // 7. 写入请求体数据
        try (OutputStream os = connection.getOutputStream()) {
            byte[] inputBytes = jsonInput.toString().getBytes(StandardCharsets.UTF_8);
            os.write(inputBytes, 0, inputBytes.length);
        }

        // 8. 获取响应
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;

                while ((responseLine = br.readLine()) != null) {
                    // 输出完整的响应内容以便查看事件数据
                    System.out.println("response: " + responseLine);

                    // 解析事件类型和事件数据
                    if (responseLine.startsWith("event:")) {
                        String eventType = responseLine.substring(6).trim();
                        if (eventType.equals("conversation.message.completed")) {
                            String jsonResponseLine = br.readLine();
                            if (jsonResponseLine != null && jsonResponseLine.startsWith("data:")) {
                                String eventData = jsonResponseLine.substring(5).trim();
                                JsonObject jsonResponse = JsonParser.parseString(eventData).getAsJsonObject();

                                // 提取answer字段的内容
                                if (jsonResponse.has("content") && jsonResponse.get("type").getAsString().equals("answer")) {
                                    completedContent.append(jsonResponse.get("content").getAsString());
                                }
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Request failed. Response Code: " + responseCode);
        }

        // 10. 关闭连接
        connection.disconnect();

        return completedContent.toString();
    }
}
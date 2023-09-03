package com.merqury.chatgpt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merqury.chatgpt.DTO.ApiResponse;
import com.merqury.chatgpt.DTO.Main;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
@Log4j2
public class MainService {
    ObjectMapper mapper;

    public MainService(){
        mapper = new ObjectMapper();
    }
    public String getResponse(String request) throws Exception{
        URL url = new URL("https://chat.gpt.bz/api/openai/v1/chat/completions?conversation_id=9ifzNLk_oqwj4FCfe45DT");

        HttpURLConnection connection = getHttpURLConnection(url);
        String json = mapper.writeValueAsString(new Main(request));

        log.info(json);

        connection.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
        connection.getOutputStream().flush();

        Scanner scanner = new Scanner(connection.getInputStream());

        StringBuilder builder = new StringBuilder();

        while (true){
            if(scanner.hasNext())
                break;
        }

        while (scanner.hasNext())
            builder.append(scanner.next()).append(" ");

        log.info("res json: {}", builder.toString());

        ApiResponse response = mapper.readValue(builder.toString(), ApiResponse.class);

        log.info("res: {}", response);

        return response.choices[0].message.content;
    }

    private static HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Настройка хэдеров
        connection.setRequestProperty("Cookie", "_ga=GA1.1.1356486241.1693692925; _ga_HLB4V7PYB0=GS1.1.1693692924.1.0.1693692925.0.0.0");
        connection.setRequestProperty("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNjRmM2I0NTllZTU3Y2IwMDAxZWM2NGE0Iiwic291cmNlIjoiZ3B0LWRlbW8iLCJleHAiOjE2OTYyODUwMTd9.6tX7I8ktkAcM0ozk3egGZZ7DXrgyOJkBmwNYXq1YsJ8");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setConnectTimeout(150_000);
        connection.setReadTimeout(150_000);
        return connection;
    }
}

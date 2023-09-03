package com.merqury.chatgpt.DTO;

public class Main {
    public Content[] messages;
    public boolean stream;
    public String model;

    public Main(String prompt){
        messages = new Content[]{
                Content.builder()
                        .role("system")
                        .content("\\nYou are ChatGPT, a large language model trained by OpenAI.\\nKnowledge cutoff: 2021-09\\nCurrent model: gpt-4\\nCurrent time: 03.09.2023, 01:51:38\\n")
                        .build(),
                Content.builder()
                        .role("user")
                        .content(prompt)
                        .build()
        };
        stream = false;
        model = "gpt-4";
    }
}

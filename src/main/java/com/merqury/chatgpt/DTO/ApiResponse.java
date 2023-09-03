package com.merqury.chatgpt.DTO;

public class ApiResponse {
    public String id, object, model;
    public int created;
    public Choise[] choices;
    public Usage usage;
}

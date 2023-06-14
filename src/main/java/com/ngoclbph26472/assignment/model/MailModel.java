package com.ngoclbph26472.assignment.model;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class MailModel {

    @Email(message = "Vui lòng nhập email")
    private String to;

    private String subJect;

    private Object body;

    List<String> cc = new ArrayList<>();

    List<String> bcc = new ArrayList<>();

    List<File> file = new ArrayList<>();

    public MailModel(String to, String subJect, Object body) {
        super();
        this.to = to;
        this.subJect = subJect;
        this.body = body;
    }
}

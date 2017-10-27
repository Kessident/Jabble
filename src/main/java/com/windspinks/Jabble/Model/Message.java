package com.windspinks.Jabble.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Message {

    @Id @GeneratedValue
    private int messageID;

    private String messageBody;
    private LocalDateTime createdAt;

    public Message(String messageBody, LocalDateTime createdAt) {
        this.messageBody = messageBody;
        this.createdAt = createdAt;
    }

    public String formatCreatedAt(){
        return createdAt.format(DateTimeFormatter.ofPattern("MMMM dd',' uuuu HH':'mm"));
    }
}
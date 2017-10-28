package com.windspinks.Jabble.Model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity @Table(name = "messages")
public class Message {

    @Id @GeneratedValue
    private int messageID;

    private String messageBody;
    private LocalDateTime createdAt;
    private String formattedCreatedAt;
    @OneToOne private User createdBy;
    @OneToMany private List<User> likedBy;

    public Message() {
        createdAt = LocalDateTime.now();
        formatCreatedAt();
    }

    public Message(String messageBody, User createdBy) {
        this.createdBy = createdBy;
        this.messageBody = messageBody;
        createdAt = LocalDateTime.now();
        formatCreatedAt();
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getFormattedCreatedAt() {
        return formattedCreatedAt;
    }

    public void setFormattedCreatedAt(String formattedCreatedAt) {
        this.formattedCreatedAt = formattedCreatedAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<User> likedBy) {
        this.likedBy = likedBy;
    }

    private void formatCreatedAt(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd',' uuuu HH':'mm");
        formattedCreatedAt = createdAt.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return messageID == message.messageID;
    }

    @Override
    public int hashCode() {
        return messageID;
    }

    @Override
    public String toString() {
        return "Message{" +
            "messageID=" + messageID +
            ", messageBody='" + messageBody + '\'' +
            ", createdAt=" + createdAt +
            ", formattedCreatedAt='" + formattedCreatedAt + '\'' +
            ", createdBy=" + createdBy +
            ", likedBy=" + likedBy +
            '}';
    }
}
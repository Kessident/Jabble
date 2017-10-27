package com.windspinks.Jabble.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class User {

    @Id @GeneratedValue
    private int userID;

    private String username;
    private String password;
    @OneToMany private List<Message> messagesLiked;
    @OneToMany private List<Message> messagesCreated;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, List<Message> messagesLiked, List<Message> messagesCreated) {
        this.username = username;
        this.password = password;
        this.messagesLiked = messagesLiked;
        this.messagesCreated = messagesCreated;
    }

    public void addMessage(Message newMessage){
        messagesCreated.add(newMessage);
    }

    public void removeMessage(Message toBeRemoved){
        messagesCreated.remove(toBeRemoved);
    }

    public void toggleLikedStatus(Message message){
        if (messagesLiked.contains(message)){
            messagesLiked.remove(message);
        } else {
            messagesLiked.add(message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userID == user.userID;
    }

    @Override
    public int hashCode() {
        return userID;
    }

    @Override
    public String toString() {
        return "User{" +
            "userID=" + userID +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", messagesLiked=" + messagesLiked +
            ", messagesCreated=" + messagesCreated +
            '}';
    }
}

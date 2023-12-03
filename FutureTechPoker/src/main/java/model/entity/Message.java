package model.entity;

import javax.persistence.*;
import java.util.Date;
/*
CREATE TABLE Message (
        id INT PRIMARY KEY AUTO_INCREMENT,
        sender INT,
        receiver INT,
        message varchar(255),
        FOREIGN KEY (sender) REFERENCES user(id_user),
        FOREIGN KEY (receiver) REFERENCES user(id_user)
        );
 */
@Entity
@Table(name = "Message")
public class Message extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer sender;
    private Integer receiver;

    private String message;

    public Message(Integer id, int sender, int receiver, String message){
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public Message(int sender, int receiver, String message){
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }
    public Message(){
    }
    public Integer getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

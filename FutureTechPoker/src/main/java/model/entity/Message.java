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
    private int id;
    private int sender;
    private int receiver;

    private String message;

   // private Date date;

//    public Message(int id, int sender, int receiver, String message, Date date){
//        this.id = id;
//        this.sender = sender;
//        this.receiver = receiver;
//        this.message = message;
//        this.date = date;
//    }
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

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
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

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
}

package com.project.LetsTalk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity(name="c_message")
@SequenceGenerator(name = "messageidseq", sequenceName = "message_id_seq", allocationSize = 1)
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messageidseq")
	private int Id;
	private String message;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

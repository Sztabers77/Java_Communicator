package project.model;


import lombok.Data;


@Data(staticConstructor = "of")
public class Message {


	private final Long id;
	private final String sender;
	private final Long created_at;
	private final String content;
	private MessageType type;

	public enum MessageType {
		CHAT, LEAVE, JOIN
	}


}

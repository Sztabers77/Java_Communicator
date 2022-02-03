package project.model;


import lombok.Data;
import lombok.With;




@Data(staticConstructor = "of")
public class Message {


	private final Long id;
	private final Long senderId;
	private final Long receiverId;
	private final Long created_at;
	private final String content;






}

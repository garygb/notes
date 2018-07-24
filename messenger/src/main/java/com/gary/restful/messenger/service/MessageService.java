package com.gary.restful.messenger.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.gary.restful.messenger.database.DatabaseClass;
import com.gary.restful.messenger.model.Message;

// create a service that returns a simple hard-coded list of messages
public class MessageService {
	//由于在DatabaseClass中messages是一个静态变量，因此只有一份，这就保证了多个操作是对同一个对象进行操作
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	static MessageService instance = null;
	
	private MessageService() {
		Message m1 = new Message(1, "Hello World!", "Gary");
		Message m2 = new Message(2, "Hello Jersey", "Gary");
		
		messages.put(1L, m1);
		messages.put(2L, m2);
	}
	
	public static MessageService getInstance() {
		if (instance == null) {
			synchronized (MessageService.class) {
				if (instance == null) {
					instance = new MessageService();
				}
			}
		}
		return instance;
	}
	
	//真实的做法是从数据库里取数据，这里只是硬编码了这个过程，是一个stub
	public List<Message> getAllMessages() {
//		Message m1 = new Message(1, "Hello World!", "Gary");
//		Message m2 = new Message(2, "Hello Jersey", "Gary");
//		List<Message> list = new ArrayList<>();
//		list.add(m1);
//		list.add(m2);
//		
//		return list;
		
		return new ArrayList<Message>(messages.values());
	}
	
	//filter
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList<>();
		// 通过Calendar这个类将Date日期中的Year部分取出来用以比较
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<>(messages.values());
		if (start + size > list.size()) {
			// return the empty list
			return new ArrayList<Message>();
		}
		return list.subList(start, start + size);
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}

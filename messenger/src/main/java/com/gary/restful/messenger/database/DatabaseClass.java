package com.gary.restful.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.gary.restful.messenger.model.Message;
import com.gary.restful.messenger.model.Profile;

public class DatabaseClass {
	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<Long, Profile> profiles = new HashMap<>();
	
	// eager singleton
	public static Map<Long, Message> getMessages() {
		return messages;
	}

	public static Map<Long, Profile> getProfiles() {
		return profiles;
	}
	
}

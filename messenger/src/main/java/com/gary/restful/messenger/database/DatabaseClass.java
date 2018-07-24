package com.gary.restful.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.gary.restful.messenger.model.Message;
import com.gary.restful.messenger.model.Profile;

public class DatabaseClass {
	
	// set id as key
	private static Map<Long, Message> messages = new HashMap<>();
	// set profile name as key
	private static Map<String, Profile> profiles = new HashMap<>();
	
	// eager singleton
	public static Map<Long, Message> getMessages() {
		return messages;
	}

	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	
}

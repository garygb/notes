package com.gary.restful.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gary.restful.messenger.database.DatabaseClass;
import com.gary.restful.messenger.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	private static ProfileService profileService = null;
	
	private ProfileService() {
		Profile p1 = new Profile(1l, "gary", "Huang", "Gary");
		
		profiles.put("gary", p1);
	}
	
	public static ProfileService getInstance() {
		if (profileService == null) {
			synchronized (ProfileService.class) {
				if (profileService == null) {
					profileService = new ProfileService();
				}
			}
		}
		return profileService;
	}
	
	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		// 在真正的数据库中，我们不需设置id，数据库会自动帮我们设置id
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		// 检查主码是否存在
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}

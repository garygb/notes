package com.gary.restful.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gary.restful.messenger.model.Profile;
import com.gary.restful.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	private ProfileService profileService = ProfileService.getInstance();
	
	@GET
	public List<Profile> getProfiles() {
		return profileService.getAllProfiles();  
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName")String profileName) {
		return profileService.getProfile(profileName);
	}
	
	@POST
	public Profile addProfile(Profile profile) {
		return profileService.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	// 使用从url中解析的messageId来设置message的id，而不是直接写在JSON的request body里面
	public Profile updateProfile(@PathParam("profileName")String profileName, Profile profile) {
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
//	@Produces(MediaType.APPLICATION_JSON)
	public void deleteProfile(@PathParam("profileName")String profileName) {
		profileService.removeProfile(profileName);
	}
	
}

package fpt.dir.sampleweb.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import fpt.dir.sampleweb.model.AppUser;
import fpt.dir.sampleweb.service.UserService;

public class ExportAppUser implements ItemReader<AppUser> {

	@Autowired
	private UserService userService;

	private List<AppUser> users;
	private Integer index = 0;
	
	@Override
	public AppUser read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return getNextAppUser();
	}
	
	private AppUser getNextAppUser(){
		AppUser user = null;
		if (index ==0) {
			users = userService.findAll();
		}
		if (index < users.size()) {
			user = users.get(index);
			index ++ ;
		}
		
		return user;
	}
	


}

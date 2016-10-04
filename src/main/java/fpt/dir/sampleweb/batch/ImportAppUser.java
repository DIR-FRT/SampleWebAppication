package fpt.dir.sampleweb.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import fpt.dir.sampleweb.main.ReportFieldSetMapper;
import fpt.dir.sampleweb.model.AppUser;
import fpt.dir.sampleweb.service.UserService;

public class ImportAppUser implements ItemWriter<AppUser> {

	@Autowired
	private UserService userService;

	@Override
	public void write(List<? extends AppUser> appUsers) throws Exception {
		// TODO Auto-generated method stub
		for (AppUser appUser : appUsers) {
			System.out.println(appUser);
			userService.findByUsername(appUser.getUsername());
			
		}
	}

}

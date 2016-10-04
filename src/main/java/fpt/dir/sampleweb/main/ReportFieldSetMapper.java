package fpt.dir.sampleweb.main;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import fpt.dir.sampleweb.model.AppUser;

public class ReportFieldSetMapper implements FieldSetMapper<AppUser> {

	@Override
	public AppUser mapFieldSet(FieldSet fieldSet) throws BindException {
		
		AppUser appUser = new AppUser();
		appUser.setUsername(fieldSet.readString(0));
		appUser.setPassword(fieldSet.readString(1));
		appUser.setEmail(fieldSet.readString(2));
	
		return appUser;
		
	}

}
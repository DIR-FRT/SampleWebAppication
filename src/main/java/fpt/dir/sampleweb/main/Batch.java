package fpt.dir.sampleweb.main;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Batch {


	public static void main(String[] args) {
		Batch b = new Batch();
		//String batchXml = Batch.class.getResource("../../../../").getPath() + "/ExportAppUserBatch.xml";
		//b.runJob(batchXml);
		b.runJob();
		//System.out.println();

	}
	
	public void runJob(){
		String[] springConfig = { "ExportAppUserBatch.xml" };
		

	//	System.out.println(jobFile);
		
	//	FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(jobFile);
		ApplicationContext context =  new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("autoRegister");

		try {

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");
		
	}
}

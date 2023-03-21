package ieco.internal.middleware.util;

import ieco.internal.middleware.model.WaitingListDetailsEntity;
import ieco.internal.middleware.repository.WaitingListRepository;
import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



@Component
public class UpdateWaitingList {
	
	private Logger log = LoggerFactory.getLogger(UpdateWaitingList.class);

	/** The JSch to connect with remote sftp server */
	@Autowired
	private JSch jsch;

	/**
	 * The sftp server host.
	 */
	@Value("${sftpserver.host}")
	private String sftphost;

	/**
	 * The sftp server port.
	 */
	@Value("${sftpserver.port}")
	private Integer sftpport;

	/**
	 * The sftp server user.
	 */
	@Value("${sftpserver.user}")
	private String sftpuser;

	/**
	 * The sftp server password.
	 */
	@Value("${sftpserver.password}")
	private String sftppassword;

	/**
	 * The sftp server basepath.
	 */
	@Value("${sftpserver.basepath}")
	private String basepath;

	/**
	 * The sftp server folderpath.
	 */
	@Value("${sftpserver.folderpath}")
	private String folderpath;

	/**
	 * The Welcome letter header pipe separator.
	 */
	@Value("${header.pipe}")
	private String pipe;

	/**
	 * The Welcome letter header fileprefix.
	 */
	@Value("${header.fileprefix}")
	private String fileprefix;

	
	
	@Autowired
	private WaitingListRepository waitingListRepository;

	@Scheduled(cron = "${cronexpressionForUpdateWaitlistNumber:-}")
	//@SchedulerLock(name = "UpdateWaitlistNumber", lockAtMostFor = "1m", lockAtLeastFor = "1m")
	public void updateWaitingListNumber() {
		String ip = InetAddress.getLoopbackAddress().getHostAddress();
		
		if(!ip.equalsIgnoreCase("10.51.241.173")) {
		log.info("IP fetched {}",ip);
		updateWaitListNumber();
		}else {
			log.info("In else IP fetched {}",ip);
		}
	}

	public void createFileForDebounceCheck() {
		FileWriter writer = null;
		try {
			List<WaitingListDetailsEntity> detailEntity = waitingListRepository.findAll();

			writer = new FileWriter("debounce_check.txt");
			for (WaitingListDetailsEntity userEntity : detailEntity) {
				writer.write(userEntity.getEmailId() + System.lineSeparator());
			}

		} catch (Exception e) {

		} finally {
			try {
				/**
				* Fortify issue started
				 */
				if(writer != null) {
					writer.close();
				}
				/**
				 * Fortify issue ended
				 */
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	@Scheduled(cron = "${cronexpressionUploadWAitingListFileToAcousticSFTP:-}")
	//@SchedulerLock(name = "UploadWAitingListFileToAcousticSFTP", lockAtMostFor = "1m", lockAtLeastFor = "1m")
	public String  initiateFileuploadToSFTP() throws IOException {
		
String ip = InetAddress.getLoopbackAddress().getHostAddress();
		
		if(!ip.equalsIgnoreCase("10.51.241.173")) {
		log.info("IP fetched for acoustic sftp {}",ip);
		processWaitingListDetails(waitingListRepository.findAll());
		}else {
			log.info("In else IP fetched for acoustic sftp {}",ip);
		}
		
		return "SUCCESS";
	}

	private void processWaitingListDetails(List<WaitingListDetailsEntity> WaitingListDetailsEntityList) throws IOException {

		StringBuilder data = new StringBuilder();
		
		data.append("Email").append(pipe).append("WaitiListNumber").append(pipe).append("Mobile Number").append(pipe)
				.append("Full Name").append(pipe).append("Referal Code").append(pipe).append("Linkedin URL")
				.append(pipe).append("OffSet").append(pipe).append("Refered Count").append(pipe)
				.append("Refered By Code").append(pipe).append("Refered By Email").append(pipe).append("Source")
				.append(pipe).append("Medium").append(pipe).append("Extras").append(pipe).append("Created On")
				.append(pipe).append("Access Allowed").append(pipe).append("Access Allowed On").append(pipe)
				.append("Access Allowed By").append(pipe).append("Check1").append(pipe).append("Check2").append(pipe)
				.append("Invitation URL").append(pipe).append('\n');
		for (WaitingListDetailsEntity user : WaitingListDetailsEntityList) {

			data.append(user.getEmailId()).append(pipe).append(user.getWaitingListNumber()).append(pipe).append(user.getMobileNumber() != null ?user.getMobileNumber():"").append(pipe)
					.append(user.getFullName() != null ? user.getFullName():"" ).append(pipe).append(user.getReferenceCode()).append(pipe).append(user.getLinkedinURL() != null ? user.getLinkedinURL():"").append(pipe)
					.append(user.getOffset()).append(pipe).append(user.getReferedCount() != null ? user.getReferedCount():"").append(pipe).append(user.getReferedByCode() != null ? user.getReferedByCode():"" ).append(pipe)
					.append(user.getReferedByEmail() !=null ? user.getReferedByEmail():"").append(pipe).append(user.getSource() != null ?user.getSource():"").append(pipe).append(user.getMedium() != null ?user.getMedium():"").append(pipe)
					.append(user.getExtras() !=null?user.getExtras():"").append(pipe).append(new SimpleDateFormat("dd/MM/yyyy").format(user.getCreatedTime())).append(pipe)
					.append(user.getIsAccessProvided()).append(pipe)
					.append(user.getAccessAllowedOn() != null
							? new SimpleDateFormat("dd/MM/yyyy").format(user.getAccessAllowedOn())
							: "").append(pipe)
					.append(user.getAccessAllowedBy() !=null ? user.getAccessAllowedBy():"" ).append(pipe).append(user.getCheck1() !=null ? user.getCheck1():"").append(pipe).append(user.getCheck2() != null ? user.getCheck2():"").append(pipe)
					.append(user.getInvitationURL() != null ? user.getInvitationURL():"").append('\n');

		}

		log.info("The value present in file is [data]: {}", data);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		LocalDateTime now = LocalDateTime.now();

		IecoCustomMultipartFile customMultipartFile = new IecoCustomMultipartFile(
				data.toString().getBytes(StandardCharsets.UTF_8), fileprefix + formatter.format(now),
				String.format("%s.%s", fileprefix + formatter.format(now), "csv"), "text/csv");

		log.info("The multipart file is {} and name is {} [file] [name]: ", customMultipartFile,
				customMultipartFile.getOriginalFilename());

		pushSftpfiletoServer(customMultipartFile);

	}

	private void pushSftpfiletoServer(IecoCustomMultipartFile customMultipartFile) throws IOException {

		log.info("Upload waitinglist file to SFTP server [START]");
		Session session;

		try {
			log.debug("Folder:{},FileName:{}", folderpath, customMultipartFile.getOriginalFilename());
			/* Starting session with server details */
			session = jsch.getSession(sftpuser, sftphost, sftpport);
			// disable verification of public key of the SSH/SFTP server
			session.setConfig("StrictHostKeyChecking", "no");
			/* password to validate authentication */
			session.setPassword(sftppassword);
			/* connect to the server */
			session.connect();
			log.info("Connected to remote [SERVER]:{}", sftphost);
			/* create a sftp channel for file transfer */
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;

			// folderpath = basepath + File.separator + folderpath ; //commented for file
			// path multiple times add error
			folderpath = folderpath.replace("\\", "/");
			log.debug("folderPath:{}", folderpath);

			String fileToWrite = folderpath + File.separator
					+ new NullCheck<>(customMultipartFile.getOriginalFilename()).orElse(customMultipartFile.getName());
			fileToWrite = fileToWrite.replace("\\", "/");
			log.debug("File  [PATH]:{}", fileToWrite);
			sftpChannel.put(customMultipartFile.getInputStream(), fileToWrite, ChannelSftp.OVERWRITE);
			log.info("Successfully uploaded [FILE]:{} uploaded File is:{}", customMultipartFile.getOriginalFilename(),
					fileToWrite);

			/*
			 * if(!customerCheckList.isEmpty()) { try {
			 * log.info("Start processing welcome letter check update for customers");
			 * CompletableFuture.runAsync(() ->
			 * processCustomerChecksforWelcomeLetter(customerCheckList) );
			 * log.info("End processing welcome letter check update for customers"); } catch
			 * (Exception ex) { log.error(
			 * "Error occurred while processing customercheck list for welcome letter for user [ERROR] {}"
			 * , ex); } }
			 */

		} catch (JSchException | SftpException e) {
			log.error("Error in connecting to server {}", e);
		} catch (IOException e) {
			log.error("Error in uploading file {}", e);
		} catch (Exception e) {
			log.error("Error in uploading sa welcome letter file to SFTP Server [ERROR] {}", e);
		}
		/**
		* Fortify issue started added finally block
		*/
		finally {
			if(customMultipartFile.getInputStream() != null) {
				customMultipartFile.getInputStream().close();
			}
		}
		/**
		 * Fotify issue ended
		 */
	}
	void updateWaitListNumber() {
		try {
			
			List<WaitingListDetailsEntity> referedUsers = waitingListRepository
					.findByReferedCountGreaterThanAndOffsetGreaterThanAndIsAccessProvidedOrderByReferedCountDesc(0, 0,
							"N");
			List<WaitingListDetailsEntity> updatedUsers = null;
			log.info("Refered Users Count " + referedUsers.size());
			for (WaitingListDetailsEntity user : referedUsers) {

				if ((user.getWaitingListNumber() - user.getOffset()) > 1) {
					List<WaitingListDetailsEntity> optionalEntity = waitingListRepository
							.findByWaitingListNumberGreaterThanEqual(user.getWaitingListNumber() - user.getOffset());
					if (optionalEntity != null && optionalEntity.size() > 0) {
						updatedUsers = new ArrayList<WaitingListDetailsEntity>();
						if (optionalEntity.get(0).getWaitingListNumber()
								.equals(user.getWaitingListNumber() - user.getOffset())) {
							int index = 1;
							boolean gap_flag = false;
							for (WaitingListDetailsEntity refUser : optionalEntity) {
								if (index <= optionalEntity.size() && (optionalEntity.get(index).getWaitingListNumber()
										- refUser.getWaitingListNumber()) > 1) {
									gap_flag = true;
								}
								/* Waiting list number increment to the next position */
								refUser.setWaitingListNumber(refUser.getWaitingListNumber() + 1);
								updatedUsers.add(refUser);
								if (gap_flag)
									break;
								index++;
							}
							/**/
							user.setWaitingListNumber(user.getWaitingListNumber() - user.getOffset());
							user.setOffset(0);
							updatedUsers.add(user);
							waitingListRepository.saveAll(updatedUsers);
						} else {
							log.info("waiting list number is empty inserting directly " + referedUsers.size());
							user.setWaitingListNumber(user.getWaitingListNumber() - user.getOffset());
							user.setOffset(0);
							waitingListRepository.save(user);
						}
					}
				} else {
					user.setWaitingListNumber(1);
					user.setOffset(0);
					waitingListRepository.save(user);
				}
			}
			
		} catch (Exception e) {
			log.error("error in updating waitlist", e);
		}
	}
	
	
	
}

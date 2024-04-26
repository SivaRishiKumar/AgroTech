package com.jsp.agro_springBoot.dao;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro_springBoot.dto.Image;
import com.jsp.agro_springBoot.dto.User;
import com.jsp.agro_springBoot.repo.Repo;
import com.jsp.agro_springBoot.repo.Repo2;

@Repository
public class ImageCrud {

	@Autowired
	private Repo2 irepo;
	@Autowired
	private Image image;
	@Autowired
	private UserCrud userdao;
	@Autowired
	private Repo userrepo;
	
	
	public Image saveImage(Image image) {
		Optional<Image> db = irepo.findByName(image.getName());
		if (db.isEmpty())
			return irepo.save(image);
		return null;
	}
	
	public Image saveImages( MultipartFile file, String name)throws IOException {
		image.setData(file.getBytes());
		image.setName(name);
	return irepo.save(image);
}

//	public Image fetchById(int id) {
//		Optional<Image> db = irepo.findById(id);
//		if(db.isPresent())
//			return db.get();
//		else
//			return null;
//	
//	}
	public Image fetchById(int id) {
		Optional<Image> db = irepo.findById(id);
		if (db.isPresent())
			return db.get();
		else
			throw null;
	}

	// @Transactional
	public Image deleteById(int id) {
		Optional<Image> db = irepo.findById(id);
		if (db.isPresent()) {
			Image imageDb = db.get();
			User userdb = userrepo.fetchByImage(imageDb);
			if (userdb != null) {
				userdb.setImage(null);
				userdao.updateUser(userdb);
			}
			irepo.deleteById(id);
			return imageDb;
		} else {
			return null;
		}
	}

//	public Image updateImage(int id, String description, MultipartFile file) throws IOException {
//		Optional<Image> db = irepo.findById(id);
//		if (db.isPresent()) {
//			Image imagedb = db.get();
//			if (description != null) {
//				imagedb.setDescription(description);
//			}
//			if (file != null) {
//				imagedb.setImage(imagedb.getImage());
//			}
//			return irepo.save(imagedb);
//
//		}
//		return null;
//	}

	public Image updateImage(int id,  String name, MultipartFile file) {
		Optional<Image> db = irepo.findById(id);
		if (db.isPresent()) {
			Image imagedb = db.get();
			if (name != null) {
				imagedb.setName(name);;
			}else if (file != null) {
				imagedb.setData(imagedb.getData());
				
			}
			return irepo.save(imagedb);

		}
		return null;
	}

	public Image uploadProfile(MultipartFile file, String name) throws IOException{
		image.setName(name);
		image.setData(file.getBytes());
		return irepo.save(image);
	}
	
	
	
	
}

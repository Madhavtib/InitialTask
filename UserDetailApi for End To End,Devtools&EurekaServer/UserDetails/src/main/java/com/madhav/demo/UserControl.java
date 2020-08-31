package com.madhav.demo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.madhav.demo.dao.UserRepo;
import com.madhav.demo.model.User;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class UserControl {

	@Autowired
	UserRepo repo;

	@GetMapping("/users")
	public List<User> getUsers() {
		return repo.findAll();
	}

	@GetMapping("/users/{name}")
	public ResponseEntity<Optional<User>> getUser(@PathVariable int aid) {
		if (repo.existsById(aid))
			return new ResponseEntity<Optional<User>>(repo.findById(aid), HttpStatus.OK);
		else
			return new ResponseEntity<Optional<User>>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User u1) {
		User saveuser = repo.save(u1);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveuser.getUid())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/users/{aid}")
	public ResponseEntity<User> SaveOrUpdateUser(@PathVariable int aid, @RequestBody User u1) {
		if (!repo.findById(aid).isPresent())
			return ResponseEntity.notFound().build();
		repo.save(u1);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/users/{aid}")
	public ResponseEntity<Void> deleteUser(@PathVariable int aid) {

		if (repo.existsById(aid)) {
			repo.deleteById(aid);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}

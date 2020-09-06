package vn.t3h.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.t3h.dao.UserDao;
import vn.t3h.model.Config;
import vn.t3h.model.User;
import vn.t3h.repositorys.ConfigRepository;
import vn.t3h.services.ConfigService;

@RestController
@RequestMapping("/api-config")
public class ConfigController {
	
	@Autowired ConfigService configService;
	@Autowired ConfigRepository configRepository;
	@Autowired UserDao userDao;
	
	@GetMapping(value="/by-key")
	public List<Config> listByKey(@RequestParam String key,
			@RequestParam(name = "value" , required = true, defaultValue = "wibu")String value) {
		return configService.findByKeyAndValue(key, value);
	}
	
	@PostMapping("/create-config")
	public Config createConfig(@RequestBody Config config) {
		configRepository.save(config);
		return config;
	}
	@PostMapping("/update-config")
	public Config updateConfig(@RequestParam Integer id,
			@RequestBody Config input) {
		var config2 = configRepository.findById(id).get();
		if (config2 != null) {
			config2.setKey(input.getKey());
			config2.setValue(input.getValue());
			config2.setNote(input.getNote());
			configRepository.save(config2);
		}
		return config2;
	}
	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		var config = configRepository.findById(id).get();
		if(config == null) {
			return "False";
		}
		configRepository.delete(config);
		return "success";
	}
	
	@GetMapping(value="/user-id")
	public User findUserById(@RequestParam Integer id) {
		return userDao.findById(id);
	}
}
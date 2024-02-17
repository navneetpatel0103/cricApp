package com.players.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.MappingApplication;
import com.players.dto.TeamDTO;
import com.players.entity.Player;
import com.players.entity.Team;
import com.players.response.ResponseObject;
import com.players.service.playerService;
import com.players.service.teamService;


@RestController
public class ControllerClass {
	@Configuration
	@EnableWebMvc
	public class WebConfig implements WebMvcConfigurer {

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	            .allowedOrigins("http://localhost:3000") // Allow requests from your React app
	            .allowedMethods("GET", "POST", "PUT", "DELETE")
	            .allowCredentials(true);
	    }
	}
	final static Logger LOG = Logger.getLogger(MappingApplication.class);

	@Autowired
	teamService svc;

	@Autowired
	playerService svc2;

	@RequestMapping(value = "/createTeam", method = RequestMethod.POST)
	public ResponseObject addTeam(@RequestBody TeamDTO msg) {
		ResponseObject ros = new ResponseObject();
		Team t = new Team();
		t.setTeam_id(msg.getTeamId());
		t.setTeamName(msg.getTeamName());
		t.setContact(msg.getContact());
		t.setEmail(msg.getEmail());
		t.setTeamFormationDate(msg.getTeamFormationDate());
		t.setTeamLocation(msg.getTeamLocation());
		t.setTeamOwner(msg.getTeamOwner());
		ros = svc.insert(t);
		return ros;
	}

	@RequestMapping(value = "/updateTeam", method = RequestMethod.POST)
	public ResponseObject updateTeam(@RequestBody TeamDTO msg) {
		ResponseObject ros = new ResponseObject();
		Team t = new Team();
		t.setTeam_id(msg.getTeamId());
		t.setTeamName(msg.getTeamName());
		t.setContact(msg.getContact());
		t.setEmail(msg.getEmail());
		t.setTeamFormationDate(msg.getTeamFormationDate());
		t.setTeamLocation(msg.getTeamLocation());
		t.setTeamOwner(msg.getTeamOwner());
		ros = svc.updateTeam(t);
		return ros;
	}

	@RequestMapping(value = "/fetchTeamById", method = RequestMethod.GET)
	public ResponseObject fetchTeam(@RequestParam(value="id", defaultValue="0") int id) {
		ResponseObject ros = new ResponseObject();
		ros = svc.findById(id);
		return ros;
	}

	@RequestMapping(value = "/fetchTeamGreaterThanId", method = RequestMethod.GET)
	public ResponseObject fetchTeamGreaterThanId(Integer id) {
		ResponseObject ros = new ResponseObject();
		ros = svc.findTeamById(id);
		return ros;
	}

	@RequestMapping(value = "/fetchAllTeams", method = RequestMethod.GET)
	public ResponseObject fetchAllTeams() {
		ResponseObject ros = svc.findAll();
		return ros;
	}

	@RequestMapping(value = "/fetchAllTeamWithPlayerCustomize", method = RequestMethod.GET)
	public ResponseObject fetchAllTeamWithPlayerCustomize() {
		ResponseObject ros = new ResponseObject();
		ros = svc.findTeamWithPlayerCustomize();
		return ros;
	}



	@RequestMapping(value = "/countTotalTeams", method = RequestMethod.GET)
	public String countTotalTeams() {
		int cnt = svc.countTotalTeams();
		String str = "Total Teams are :" + cnt;
		return str;
	}

	@RequestMapping(value = "/createPlayer", method = RequestMethod.POST)
	public ResponseObject addPlayer(@RequestBody Player msg) {
		ResponseObject ros = new ResponseObject();
		ros = svc2.insert(msg);
		return ros;
	}

	@RequestMapping(value = "/updatePlayer", method = RequestMethod.POST)
	public ResponseObject updatePlayer(@RequestBody Player msg) {
		ResponseObject ros = new ResponseObject();
		ros = svc2.updatePlayer(msg);
		return ros;
	}

	@RequestMapping(value = "/fetchPlayerById", method = RequestMethod.GET)
	public ResponseObject fetchPlayer(@RequestParam(value="id", defaultValue="0")Integer id) {
		ResponseObject ros = new ResponseObject();
		ros = svc2.findById(id);
		return ros;
	}

	@RequestMapping(value = "/fetchAllPlayers", method = RequestMethod.GET)
	public ResponseObject fetchAllPlayers() {
		ResponseObject ros = svc2.findAll();
		System.out.println(ros.getListP());
		return ros;
	}

	@RequestMapping(value = "/fetchCustomPlayerById", method = RequestMethod.GET)
	public ResponseObject fetchCustomPlayerById(Integer id) {
		ResponseObject ros = new ResponseObject();
		ros = svc2.findPlayerById(id);
		return ros;
	}

	@RequestMapping(value = "/findByName")
	public ResponseObject findByName(String name) {
		ResponseObject ros = new ResponseObject();
		ros = svc2.findByName(name);
		return ros;

	}

	@RequestMapping(value = "/convertNameToUpperCase", method = RequestMethod.GET)
	public ResponseObject convertNameToUpperCase() {
		ResponseObject ros = new ResponseObject();
		ros = svc2.convertNameToUpperCase();
		return ros;
	}

}

//@RequestMapping("/sayHello")
//public String helloWorld() {
//	return "Welcome to world of CODING.....";
//}
//
//@RequestMapping("/sayHelloUser")
////localhost:8080/sayHelloUser?Name=Jack&DOB=12-03-2000
//public String sayHelloUser(@RequestParam(value="Name", defaultValue="Giri") String name,
//		@RequestParam(value="DOB", required=true) String dob)
//{
//	return "My name is " + name + " and my birth date is " + dob;
//}
//
//@RequestMapping("/sayHelloFolks/{Name}/{DOB}")
////localhost:8080/sayHelloFolks/{Joey}/{10-02-1978}
//public String sayHelloFolks(@PathVariable("Name") String name, @PathVariable("DOB") String dob) {
//	return "Name: " + name + " Date of Birth: " + dob;
//}
//
//@RequestMapping("/greetPerson")
////localhost:8080/greetPerson?Greet=Happy Birthday&Message=Wishing you prosperous life
//public Greetings greetPerson(@RequestParam(value="Greet") String greet,
//							  @RequestParam(value="Name", required=false) String name,
//							   @RequestParam(value="Message") String mssg) {
//	Greetings g=new Greetings();
//	g.setGreet(greet);
//	g.setName(name);
//	g.setMessage(mssg);
//	return g;
//}
//
//
//@RequestMapping("/searchUsers")
//public List<Users> searchUsers(){
//	List<Users> l=new ArrayList<Users>();
//	Users u1=new Users();
//	u1.setName("Ross");
//	u1.setAge(28);
//	
//	Users u2=new Users();
//	u2.setName("Mike");
//	u2.setAge(25);
//	
//	l.add(u1);
//	l.add(u2);
//	return l;
//}
//
//
//@RequestMapping("/searchUsersMap")
//public Map<String, Users> searchUserMap(){
//	Map<String, Users> m=new HashMap<String, Users>();
//	Users u1=new Users();
//	u1.setName("Ross");
//	u1.setAge(28);
//	Users u2=new Users();
//	u2.setName("Mike");
//	u2.setAge(25);
//	m.put(u1.getName(), u1);
//	m.put(u2.getName(), u2);
//	return m;
//}
//
//@PostMapping("/searchUserPost")
//public List<Users> searchUserPost(@RequestBody String s){
//	System.out.println("The list is posted with string " + s);
//	List<Users> l=new ArrayList<Users>();
//	Users u1=new Users();
//	u1.setName("Joey");
//	u1.setAge(29);
//	
//	Users u2=new Users();
//	u2.setName("Ross");
//	u2.setAge(32);
//	l.add(u1);
//	l.add(u2);
//	return l;
//}

//@RequestMapping(value="/createTeam", method=RequestMethod.POST)
//public TeamDTO addTeam(@RequestBody TeamDTO msg) {
//	ResponseObject ros=new ResponseObject();
//	System.out.println("TeamName: " + msg.getTeamName());
//	Team t=new Team();
//	t.setTeamName(msg.getTeamName());
//	Team t1=svc.insert(t);
//	msg.setTeamId(t1.getTeam_id());
//	msg.setCreatedAt(t1.getCreatedAt());
//	msg.setUpdatedAt(t1.getUpdatedAt());
//	return msg;
//}

//@RequestMapping(value="/fetchTeamById", method=RequestMethod.GET)
//public TeamDTO fetchTeam(int id) {
//	System.out.println("Team ID: "+ id);
//	Team t=svc.findById(id);
//	TeamDTO dto=new TeamDTO();
//	dto.setTeamId(t.getTeam_id());
//	dto.setTeamName(t.getTeamName());
//	dto.setCreatedAt(t.getCreatedAt());
//	dto.setUpdatedAt(t.getUpdatedAt());
//	return dto;
//}

//@RequestMapping(value="/fetchTeamGreaterThanId", method=RequestMethod.GET)
//public List<TeamDTO> fetchTeamGreaterThanId(int id) {
//	System.out.println("Team ID: "+ id);
//	List<Team> l=svc.findTeamById(id);
//	List<TeamDTO> lDto=new ArrayList<TeamDTO>();
//	for(Team it:l) {
//		TeamDTO d=new TeamDTO();
//		d.setTeamId(it.getTeam_id());
//		d.setTeamName(it.getTeamName());
//		d.setCreatedAt(it.getCreatedAt());
//		d.setUpdatedAt(it.getUpdatedAt());
//		lDto.add(d);
//	}
//	return lDto;
//}

//@RequestMapping(value="/fetchAllTeamWithPlayerCustomize", method=RequestMethod.GET)
//public List<TeamWithPlayerCustomizeDTO> fetchAllTeamWithPlayerCustomize() {
//	List<TeamWithPlayerCustomize> l=svc.findTeamWithPlayerCustomize();
//	List<TeamWithPlayerCustomizeDTO> lDto=new ArrayList<TeamWithPlayerCustomizeDTO>();
//	for(TeamWithPlayerCustomize it: l) {
//		TeamWithPlayerCustomizeDTO d=new TeamWithPlayerCustomizeDTO();
//		d.setTeamNameDTO(it.getTeamName());
//		d.setTeamIdDTO(it.getTeamId());
//		d.setPlayerNameDTO(it.getPlayerName());
//		d.setPlayerRoleDTO(it.getPlayerRole());
//		d.setPlayerCountryDTO(it.getPlayerCountry());
//		lDto.add(d);
//	}
//	return lDto;
//}

//@RequestMapping(value="/createPlayer", method=RequestMethod.POST)
//public Player addPlayer(@RequestBody Player msg) {
//	Team t=svc.findById(msg.getTeam().getTeam_id());
//	System.out.println("Team is :" + t);
//	Player p=new Player();
//	p.setPlayerName(msg.getPlayerName());
//	p.setPlayerCountry(msg.getPlayerCountry());
//	p.setPlayerRole(msg.getPlayerRole());
//	msg.setTeam(t);
//	Player response=svc2.insert(msg);
//	response.setTeam_ID(response.getTeam().getTeam_id());
//	System.out.println("Player Added successfully...");
//	return response;
//}

//@RequestMapping(value="/findByName")
//public List<String> findByName(String name){
//	List<Player> l=svc2.findByName(name);
//	List<String> lDto=new ArrayList<String>();
//	for(Player it:l) {
//		String s=new String();
//		s+=it.getPlayerName();
//		lDto.add(s);
//	}
//	return lDto;
//
//}

//@RequestMapping(value="/convertNameToUpperCase", method=RequestMethod.GET)
//public List<String> convertNameToUpperCase() {
//	List<String> l=svc2.convertNameToUpperCase();
//	return l;
//}
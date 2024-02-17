package com.example.demo;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.players.dao.CustomPlayer;
import com.players.dao.TeamWithPlayerCustomize;
import com.players.entity.Player;
import com.players.entity.Team;
import com.players.response.ResponseObject;
import com.players.service.playerService;
import com.players.service.teamService;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = { "com.players.entity" })
@EnableJpaRepositories("com.players.dao")
@ComponentScan("com.players")
public class MappingApplication {
	final static Logger LOG = Logger.getLogger(MappingApplication.class);

	public static String takeInput() {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		while (str.isBlank()) {
			str = sc.nextLine();
		}
		return str;
	}

	public static boolean isInt(String entry) {
		try {
			Integer.parseInt(entry);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Kindly enter the integer value");
			return false;
		}
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MappingApplication.class, args);
		LOG.info("The system has started");
		teamService t = ctx.getBean(teamService.class);
		playerService p = ctx.getBean(playerService.class);
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("Select now:");
			System.out.println("1.Add Team");
			System.out.println("2.Update Team");
			System.out.println("3.Fetch Team details by id");
			System.out.println("4.Fetch all Teams");
			System.out.println("5.Add Player");
			System.out.println("6.Update Player");
			System.out.println("7.Fetch player details by id");
			System.out.println("8.Fetch all Players");
			System.out.println("9.Fetch teams greater than given id");
			System.out.println("10.Fetch custom info of team with players");
			System.out.println("11.Find total number of teams");
			System.out.println("12.Fetch custom fields of player with given id");
			System.out.println("13.Fetch players having given string in their names");
			System.out.println("14.Convert players name to upper case");
			System.out.println("15. Exit");
			int menuSelected = 0;
			boolean check = true;
			while (check) {
				String str = takeInput();
				String stringWithoutSpaces = str.replaceAll("\\s", "");
				try {
					menuSelected = Integer.parseInt(stringWithoutSpaces);
					check = false;
				} catch (NumberFormatException e) {
					check = true;
					System.out.println("Kindly enter the valid option.");
				}
			}

			switch (menuSelected) {
			case 1:
				LOG.info("Add team function from main is called");
				Team teamObj = new Team();
				System.out.println("Enter the team name:");
				String str1 = sc.nextLine();
				str1=str1.trim();
				teamObj.setTeamName(str1);
				ResponseObject ros = new ResponseObject();
				ros = t.insert(teamObj);
				if (ros.getFailureMessage() != null) {
					System.out.println(ros.getFailureMessage());
				} else {
					System.out.println(ros.getSuccessMessage());
				}
				break;
			case 2:
				LOG.info("Update team function from main is called");
				Team teamObj2 = new Team();
				System.out.println("Enter the team id");
				String str2 = sc.nextLine();
				str2 = str2.trim();
				if (isInt(str2)) {
					int id = Integer.parseInt(str2);
					ResponseObject ros2 = new ResponseObject();
					teamObj2.setTeam_id(id);
					System.out.println("Enter the team name:");
					String s = sc.nextLine();
					s=s.trim();
					teamObj2.setTeamName(s);
					ros2 = t.updateTeam(teamObj2);
					if (ros2.getSuccessMessage() != null) {
						System.out.println(ros2.getSuccessMessage());
					} else {
						System.out.println(ros2.getFailureMessage());
					}
				}
				break;
			case 3:
				System.out.println("Enter the team id");
				String str3 = sc.nextLine();
				str3 = str3.trim();
				if (isInt(str3)) {
					int n = Integer.parseInt(str3);
					LOG.info("Find by id team function from main is called for: " + n);
					ResponseObject ros3 = new ResponseObject();
					ros3 = t.findById(n);
					if (ros3.getFailureMessage() != null) {
						System.out.println(ros3.getFailureMessage());
					} else {
						Team teamObj3 = ros3.getT();
						System.out.println(ros3.getSuccessMessage());
						System.out.println("==============================================================");
						System.out.printf("%-5s%-10s%-30s%-20s\n", "ID", "Team", "createdAt", "updatedAt");
						System.out.println("===============================================================");
						System.out.printf("%-5s%-10s%-30s%-20s\n", teamObj3.getTeam_id(), teamObj3.getTeamName(),
								teamObj3.getCreatedAt(), teamObj3.getUpdatedAt());
					}
				}
				break;
			case 4:
				LOG.info("Find all teams function from main is called");
				ResponseObject ros4 = new ResponseObject();
				ros4 = t.findAll();
				List<Team> list4 = ros4.getListT();
				if (!list4.isEmpty()) {
					System.out.println(ros4.getSuccessMessage());
					System.out.println("==============================================================");
					System.out.printf("%-5s%-10s%-30s%-20s\n", "ID", "Team", "createdAt", "updatedAt");
					System.out.println("===============================================================");
					for (Team it : list4) {
						System.out.printf("%-5s%-10s%-30s%-20s\n", it.getTeam_id(), it.getTeamName(), it.getCreatedAt(),
								it.getUpdatedAt());
					}
				} else {
					System.out.println(ros4.getFailureMessage());
				}
				break;
			case 5:
				LOG.info("Add player function from main is called");
				ResponseObject ros5 = new ResponseObject();
				Team t5 = new Team();
				Player p5 = new Player();
				System.out.println("Enter the player name");
				String pName = sc.nextLine();
				p5.setPlayerName(pName);
				System.out.println("Enter the player role");
				String pRole = sc.nextLine();
				p5.setPlayerRole(pRole);
				System.out.println("Enter the player country");
				String pCountry = sc.nextLine();
				p5.setPlayerCountry(pCountry);
				System.out.println("Enter the team id");
				String str5 = sc.nextLine();
				str5 = str5.trim();
				if (isInt(str5)) {
					int id5 = Integer.parseInt(str5);
					t5.setTeam_id(id5);
					p5.setTeam(t5);
					ros5 = p.insert(p5);
					if (ros5.getSuccessMessage() != null) {
						System.out.println(ros5.getSuccessMessage());
					} else {
						System.out.println(ros5.getFailureMessage());
					}
				}
				break;
			case 6:
				LOG.info("Update player function from main is called");
				ResponseObject ros6 = new ResponseObject();
				Team t6 = new Team();
				Player p6 = new Player();
				System.out.println("Enter the player id");
				String str6 = sc.nextLine();
				str6 = str6.trim();
				if (isInt(str6)) {
					int pid6 = Integer.parseInt(str6);
					p6.setPlayer_id(pid6);
					System.out.println("Enter the player name");
					String pName6 = sc.nextLine();
					pName6=pName6.trim();
					p6.setPlayerName(pName6);
					System.out.println("Enter the player role");
					String pRole6 = sc.nextLine();
					pRole6=pRole6.trim();
					p6.setPlayerRole(pRole6);
					System.out.println("Enter the player country");
					String pCountry6 = sc.nextLine();
					pCountry6=pCountry6.trim();
					p6.setPlayerCountry(pCountry6);
					System.out.println("Enter the team id");
					String str6b = sc.nextLine();
					str6b = str6b.trim();
					if (isInt(str6)) {
						int id6 = Integer.parseInt(str6b);
						t6.setTeam_id(id6);
						p6.setTeam(t6);
						ros6 = p.updatePlayer(p6);
						if (ros6.getSuccessMessage() != null) {
							System.out.println(ros6.getSuccessMessage());
						} else {
							System.out.println(ros6.getFailureMessage());
						}
					}
				}
				break;
			case 7:
				ResponseObject ros7 = new ResponseObject();
				System.out.println("Enter the player id");
				String str7 = sc.nextLine();
				str7 = str7.trim();
				if (isInt(str7)) {
					int pid7 = Integer.parseInt(str7);
					LOG.info("Find by id player function from main is called for: " + pid7);
					ros7 = p.findById(pid7);
					if (ros7.getFailureMessage() != null) {
						System.out.println(ros7.getFailureMessage());
					} else {
						Player p7 = ros7.getP();
						System.out.println("===================================================================");
						System.out.printf("%-5s%-20s%-20s%-20s%-10s\n", "ID", "Name", "Role", "Country", "Team");
						System.out.println("===================================================================");
						System.out.printf("%-5s%-20s%-20s%-20s%-10s\n", p7.getPlayer_id(), p7.getPlayerName(),
								p7.getPlayerRole(), p7.getPlayerCountry(), p7.getTeam().getTeamName());
					}
				}
				break;
			case 8:
				LOG.info("Find all players function from main is called");
				ResponseObject ros8 = new ResponseObject();
				ros8 = p.findAll();
				List<Player> list8 = ros8.getListP();
				if (!list8.isEmpty()) {
					System.out.println(ros8.getSuccessMessage());
					System.out.println("============================================================================");
					System.out.printf("%-5s%-20s%-20s%-20s%-10s\n", "ID", "Name", "Role", "Country", "Team");
					System.out.println("============================================================================");
					for (Player it : list8) {
						System.out.printf("%-5s%-20s%-20s%-20s%-10s\n", it.getPlayer_id(), it.getPlayerName(),
								it.getPlayerRole(), it.getPlayerCountry(), it.getTeam().getTeamName());
					}
				} else {
					System.out.println(ros8.getFailureMessage());
				}
				break;
			case 9:
				LOG.info("Find all teams greater than given id function from main is called");
				ResponseObject ros9 = new ResponseObject();
				System.out.println("Enter the id");
				String str9 = sc.nextLine();
				str9 = str9.trim();
				if (isInt(str9)) {
					int id9 = Integer.parseInt(str9);
					ros9 = t.findTeamById(id9);
					if (ros9.getFailureMessage() != null) {
						System.out.println(ros9.getFailureMessage());
					} else {
						System.out.println(ros9.getSuccessMessage());
						List<Team> list10 = ros9.getListT();
						System.out.println("==============================================================");
						System.out.printf("%-5s%-10s%-30s%-20s\n", "ID", "Team", "createdAt", "updatedAt");
						System.out.println("===============================================================");
						for (Team it : list10) {
							System.out.printf("%-5s%-10s%-30s%-20s\n", it.getTeam_id(), it.getTeamName(),
									it.getCreatedAt(), it.getUpdatedAt());
						}
					}
				}
				break;
			case 10:
				LOG.info("Find custom info of teams with their players function from main is called");
				ResponseObject ros10 = new ResponseObject();
				ros10 = t.findTeamWithPlayerCustomize();
				if (ros10.getFailureMessage() != null) {
					System.out.println(ros10.getFailureMessage());
				} else {
					System.out.println(ros10.getSuccessMessage());
					List<TeamWithPlayerCustomize> list10 = ros10.getListCustomized();
					System.out.println("====================================================================");
					System.out.printf("%-5s%-10s%-25s%-20s%-20s\n", "ID", "Team", "Name", "Role", "Country");
					System.out.println("=====================================================================");
					for (TeamWithPlayerCustomize it : list10) {
						System.out.printf("%-5s%-10s%-25s%-20s%-20s\n", it.getTeamId(), it.getTeamName(),
								it.getPlayerName(), it.getPlayerRole(), it.getPlayerCountry());
					}
				}
			case 11:
				LOG.info("Count total teams function from main is called");
				int cnt = t.countTotalTeams();
				System.out.println("The total number of teams are:" + cnt);
				break;
			case 12:
				LOG.info("Fetch custom player function from main is called");
				ResponseObject ros12 = new ResponseObject();
				System.out.println("Enter the player id");
				String str12 = sc.nextLine();
				str12 = str12.trim();
				if (isInt(str12)) {
					int id12 = Integer.parseInt(str12);
					ros12 = p.findPlayerById(id12);
					if (ros12.getFailureMessage() != null) {
						System.out.println(ros12.getFailureMessage());
					} else {
						System.out.println(ros12.getSuccessMessage());
						List<CustomPlayer> list12 = ros12.getListCustomizedPlayer();
						System.out.println(
								"============================================================================");
						System.out.printf("%-20s%-20s%-20s%-10s\n", "Name", "Role", "Country", "Team");
						System.out.println(
								"============================================================================");
						for (CustomPlayer it : list12) {
							System.out.printf("%-20s%-20s%-20s%-10s\n", it.getPlayerName(), it.getPlayerRole(),
									it.getPlayerCountry(), it.getIplTeam().getTeamName());
						}
					}
				}
				break;
			case 13:
				LOG.info("fetch player with given string function from main is called");
				ResponseObject ros13 = new ResponseObject();
				System.out.println("Enter the string");
				String str13 = sc.nextLine();
				ros13 = p.findByName(str13.trim());
				if (ros13.getFailureMessage() != null) {
					System.out.println(ros13.getFailureMessage());
				} else {
					System.out.println(ros13.getSuccessMessage());
					List<Player> list13 = ros13.getListP();
					System.out.println("============================================================================");
					System.out.printf("%-20s%-20s%-20s%-10s\n", "Name", "Role", "Country", "Team");
					System.out.println("============================================================================");
					for (Player it : list13) {
						System.out.printf("%-20s%-20s%-20s%-10s\n", it.getPlayerName(), it.getPlayerRole(),
								it.getPlayerCountry(), it.getTeam().getTeamName());
					}
				}
				break;
			case 14:
				LOG.info("Convert name to upper case function from main is called");
				ResponseObject ros14 = new ResponseObject();
				ros14 = p.convertNameToUpperCase();
				if (ros14.getFailureMessage() != null) {
					System.out.println(ros14.getFailureMessage());
				} else {
					System.out.println(ros14.getSuccessMessage());
					List<String> list14 = ros14.getListWithUpperCase();
					System.out.println("  Player Name in upperCase  ");
					System.out.println("============================");
					int temp = 1;
					for (String it : list14) {
						System.out.println(temp + ". " + it);
						temp++;
					}
				}
				break;
			case 15:
				System.out.println("Thank you for using");
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a valid option.");
				break;
			}

		}

//		while(!isInt(str2)) {
//		str2=takeInput();
//		str2=str2.trim();
//	}

//		Player p1=new Player();
//		ResponseObject ros=new ResponseObject();
//		p1.setPlayer_id(7);
//		p1.setPlayerName("Mitchell Santner");
//		p1.setPlayerCountry("New Zealand");
//		p1.setPlayerRole("Bowler");
//		Team t2=new Team();
//		t2.setTeam_id(1);;
//		p1.setTeam(t2);
//		ros=p.updatePlayer(p1);
//		if(ros.getSuccessMessage()!=null) {
//		System.out.println(ros.getSuccessMessage());
//	}else {
//		System.out.println(ros.getFailureMessage());
//	}

//		Team t1=new Team();
//		ResponseObject ros=new ResponseObject();
//		t1.setTeam_id(6);
//		t1.setTeamName("DC");
//		ros=t.updateTeam(t1);
//		if(ros.getSuccessMessage()!=null) {
//			System.out.println(ros.getSuccessMessage());
//		}else {
//			System.out.println(ros.getFailureMessage());
//		}

//		t.deleteTeamById(5);

		/*
		 * Team t1=new Team(); ResponseObject ros=new ResponseObject();
		 * t1.setTeam_id(6); t1.setTeamName(""); ros=t.insert(t1);
		 * if(ros.getSuccessMessage()!=null) {
		 * System.out.println(ros.getSuccessMessage()); }else {
		 * System.out.println(ros.getFailureMessage()); }
		 */

//		ResponseObject ros=new ResponseObject();
//		ros=t.findById(4);
//		if(ros.getFailureMessage()!=null) {
//			System.out.println(ros.getFailureMessage());
//		}else {
//			Team t1=ros.getT();
//			System.out.println(ros.getSuccessMessage());
//			System.out.println("==============================");
//			System.out.println("ID "+" Team Name "+ " createdAt "+ " updatedAt ");
//			System.out.println("==============================");
//			System.out.println(t1.getTeam_id() + " "+ t1.getTeamName()+ " " +
//			t1.getCreatedAt()+ " " + t1.getUpdatedAt());
//		}

//		ResponseObject ros=new ResponseObject();
//		ros=t.findTeamById(-8);
//		if(ros.getFailureMessage()!=null) {
//			System.out.println(ros.getFailureMessage());
//		}else {
//			System.out.println(ros.getSuccessMessage());
//			System.out.println("==============================");
//			List<Team> list=ros.getListT();
//			System.out.println("ID "+" Team Name "+ " createdAt "+ " updatedAt ");
//			System.out.println("==============================");
//			for(Team i: list) {
//				System.out.println(i.getTeam_id() + " "+ i.getTeamName()+ " " +
//						i.getCreatedAt()+ " " + i.getUpdatedAt());
//			}
//			
//		}

//		ResponseObject ros=new ResponseObject();
//		ros=t.findTeamWithPlayerCustomize();
//		if(ros.getFailureMessage()!=null) {
//			System.out.println(ros.getFailureMessage());
//		}else {
//			System.out.println(ros.getSuccessMessage());
//			System.out.println("==============================");
//			List<TeamWithPlayerCustomize> list=ros.getListCustomized();
//			for(TeamWithPlayerCustomize i: list) {
//				System.out.println("Team_id: "+ i.getTeamId() + "| Team_Name " + i.getTeamName()
//				+ "| Player_Name: "+ i.getPlayerName() + "| PlayerCountry: " +
//						i.getPlayerCountry()+ "| PlayerRole: " + i.getPlayerRole());
//		}

//		List<TeamWithPlayerCustomize> l5=t.findTeamWithPlayerCustomize();
//		System.out.println("The size is: " + l5.size());
//		for(TeamWithPlayerCustomize i: l5) {
//			System.out.println("Team_id: "+ i.getTeamId() + "| Team_Name " + i.getTeamName()
//			+ "| Player_Name: "+ i.getPlayerName() + "| PlayerCountry: " +
//					i.getPlayerCountry()+ "| PlayerRole: " + i.getPlayerRole());

//		Team t1=new Team();
//		t1.setTeamName("CSK");
//		t1.setTeam_id(1);
//		t.insert(t1);

//		Player p1=new Player();
//		p1.setPlayer_id(7);
//		p1.setPlayerName("Dhoni");
//		p1.setPlayerCountry("India");
//		p1.setPlayerRole("WicketKeeper Batsman");
//		Team t2=new Team();
//		t2.setTeam_id(1);;
//		p1.setTeam(t2);
//		p.insert(p1);
//		

//		playerService p=ctx.getBean(playerService.class);
//		Player p1=new Player();
//		p1.setPlayer_id(8);
//		p1.setPlayerName("Jadeja");
//		p1.setPlayerCountry("India");
//		p1.setPlayerRole("All Rounder");
//		Team t2=new Team();
//		t2.setTeam_id(1);;
//		p1.setTeam(t2);
//		p.insert(p1);
//		

//		Team t3=new Team();
//		t3.setTeamName("RCB");
//		t3.setTeam_id(2);
//		t.insert(t3);
//		
//		playerService p=ctx.getBean(playerService.class);
//		Player p2=new Player();
//		p2.setPlayer_id(93);
//		p2.setPlayerName("Maxwell");
//		p2.setPlayerCountry("Australia");
//		Team t2=new Team();
//		t2.setTeamName("RCB");
//		p2.setTeam(t2);
//		p.insert(p2);

//		playerService p=ctx.getBean(playerService.class);
//		Player p2=new Player();
//		p2.setPlayer_id(14);
//		p2.setPlayerName("Conway");
//		p2.setPlayerCountry("New Zealand");
//		Team t2=new Team();
//		t2.setTeamName("CSK");
//		p2.setTeam(t2);
//		p.insert(p2);

//		Team t3=new Team();
//		t3.setTeamName("RR");
//		t3.setTeam_id(3);
//		
//		Player p1=new Player();
//		p1.setPlayer_id(32);
//		p1.setPlayerName("Samson");
//		p1.setPlayerCountry("India");
//		p1.setPlayerRole("WicketKeeper Batsman");
//		
//		Player p2=new Player();
//		p2.setPlayer_id(12);
//		p2.setPlayerName("Buttler");
//		p2.setPlayerCountry("England");
//		p2.setPlayerRole("Batsman");

//		Associating players to team

//		p1.setTeam(t3);
//		p2.setTeam(t3);

//		Associating library to teams

//		List<Player> l=new ArrayList<Player>();
//		l.add(p1);
//		l.add(p2);
//		
//		t3.setPlayer(l);
//		
//		t.insert(t3);

//		p1.setPlayer_id(32l);
//		p1.setPlayerName("Jadeja");
//		p1.setPlayerCountry("India");
//		Team t2=new Team();
//		t2.setTeamName("CSK");
//		p1.setTeam(t2);
//		p.insert(p1);

//		Team t4=new Team();
//		t4.setTeamName("MI");
//		t4.setId(4l);
//		t.insert(t4);

//		Team t1=new Team();
//		t1.setTeamName("DC");
//		t1.setId(5l);
//		t.insert(t1);
//		
//		Player p1=new Player();
//		p1.setPlayerName("David Warner");
//		p1.setPlayerCountry("Australia");
//		Team t2=new Team();
//		t2.setTeamName("DC");
//		p1.setTeam(t1);
//		p.insert(p1);

//		Team t1=new Team();
//		t1.setTeamName("GT");
//		t1.setId(6l);
//		t.insert(t1);
//		
//		Player p1=new Player();
//		p1.setPlayerName("Rashid Khan");
//		p1.setPlayerCountry("Afghanistan");
//		p1.setTeam(t1);
//		p.insert(p1);

//		Team t1=new Team();
//		t1.setTeamName("KKR");
//		t1.setId(5l);
//		t.insert(t1);
//		
//		Player p1=new Player();
//		p1.setPlayerName("Shreyas Iyer");
//		p1.setPlayerCountry("India");
//		p1.setTeam(t1);
//		List<Player> l=new ArrayList<Player>();
//		l.add(p1);
//		t1.setPlayer(l);

//		Team t1=new Team();
//		t1.setTeamName("SRH");
//		t1.setId(8l);

//		Player p1=new Player();
//		p1.setPlayerName("Bhuvneshwar Kumar");
//		p1.setPlayerCountry("India");
//		Team t1=new Team();
//		t1.setTeamName("SRH");
//		p1.setTeam(t1);
//		List<Player> l=new ArrayList<Player>();
//		l.add(p1);
//		t.insert(t1);
//		p.insert(p1);

//		Team t1=new Team();
//		t1.setTeamName("SRH");
//		t1.setTeam_id(2);
//		t.insert(t1);
//		
//		Player p1=new Player();
//		p1.setPlayerName("Rohit Sharma");
//		p1.setPlayerCountry("India");
//		Team t2=new Team();
//		t2.setTeamName("MI");
//		p1.setTeam(t2);
//		p.insert(p1);

//		Team t1=new Team();
//		t1.setTeamName("RPS");
//		t1.setTeam_id(4);
//		t.insert(t1);

//		Player p1=new Player();
//		p1.setPlayerName("Hetmyer");
//		p1.setPlayerCountry("West Indies");
//		p1.setPlayer_id(14);
//		p1.setPlayerRole("Batsman");
//		Team t2=new Team();
//		t2.setTeam_id(3);
//		p1.setTeam(t2);
//		p.insert(p1);

//		Team t1=new Team();
//		t1.setTeamName("RCB");
//		t1.setTeam_id(10);
//		t.insert(t1);

//		Team t1=new Team();
//		t1.setTeamName("RCB");
//		t.insert(t1);

//		t.transactionDemo(t1, p1);

//		Queries

//		INSERT ELEMENT-----------------------

//		Team team1=new Team();
//		team1.setTeamName("CSK");
//		t.insert(team1);
//		
//		Team team2=new Team();
//		team2.setTeamName("RCB");
//		t.insert(team2);		
//		Team team3=new Team();
//		team3.setTeamName("MI");
//		t.insert(team3);

//		Player p1=new Player();
//		p1.setPlayerName("MS Dhoni");
//		p1.setPlayerCountry("India");
//		p1.setPlayerRole("WK Batsman");
//		Team team1=new Team();
////		team1.setTeam_id(1);
//		p1.setTeam(team1);
//		p.insert(p1);

//		Player p2=new Player();
//		p2.setPlayerName("Rohit Sharma");
//		p2.setPlayerCountry("India");
//		p2.setPlayerRole("Batsman");
//		Team team=new Team();
//		team.setTeam_id(3);
//		p2.setTeam(team3);
//		p.insert(p2);

//		Player p3=new Player();
//		p3.setPlayerName("Siraj");
//		p3.setPlayerCountry("India");
//		p3.setPlayerRole("Bowler");
//		Team team=new Team();
//		team.setTeam_id(2);
//		p3.setTeam(team2);
//		p.insert(p3);

//		Player p4=new Player();
//		p4.setPlayerName("Conway");
//		p4.setPlayerCountry("New Zealand");
//		p4.setPlayerRole("Batsman");
//		Team team=new Team();
//		team.setTeam_id(1);
//		p4.setTeam(team);
//		p.insert(p4);

//		Team t1=new Team();
//		t1.setTeamName("PK");
//		t.insert(t1);

//		Player p5=new Player();
//		p5.setPlayerName("Arshdeep Singh");
//		p5.setPlayerCountry("India");
//		p5.setPlayerRole("Bowler");
//		p5.setTeam(t1);
//		List<Player> l=new ArrayList<Player>();
//		l.add(p5);
//		t1.setPlayer(l);
//		t.insert(t1);

//		FIND BY ID-----------------------

//		Team teamName=t.findById(1);
//		System.out.println(teamName);
//		
//		Player playerName=p.findById(4);
//		System.out.println(playerName);

//		FIND ALL-----------------------------

//		System.out.println(t.findAll());
//		System.out.println(p.findAll());

//		EAGER AND LAZY--------------------------

//		Team t1=t.findById(1);
//		System.out.println("Team: " + t1.getTeamName());
//		List<Player> l=t1.getPlayer();
//		for(Player i: l) {
//			System.out.println("Player name: "+ i.getPlayerName());
//			System.out.println("Player IPL team: " + i.getTeam().getTeamName());
//			System.out.println("Player country is: " + i.getPlayerCountry());
//		}

//		Player pl=p.findById(3);
//		System.out.println("Player name: "+ pl.getPlayerName());
//		System.out.println("Player country is: " + pl.getPlayerCountry());
//		System.out.println("Player Role: " + pl.getPlayerRole());
//		System.out.println("Player IPL team: " + pl.getTeam().getTeamName());

//		Team t2=pl.getTeam();
//
//		System.out.println("Player IPL team: " + t2.getTeamName());

//		TRANSACTION-------------------
//		
//		Player p1=new Player();
//		p1.setPlayerName("Shikhar Dhawan");
//		p1.setPlayerCountry("India");
//		p1.setPlayerRole("Batsman");
//		Team t1=new Team();
//		t1.setTeam_id(5);
//		p1.setTeam(t1);
//		
//		Team t2=new Team();
//		t2.setTeamName("DC");
//		
//		Player p2=new Player();
//		p2.setPlayerName("David Warner");
//		p2.setPlayerCountry("Australia");
//		p2.setPlayerRole("Batsman");
//		p2.setTeam(t2);
//		
//		Player p3=new Player();
//		p3.setPlayerName("Mitchell Marsh");
//		p3.setPlayerCountry("Australia");
//		p3.setPlayerRole("All Rounder");
//		p3.setTeam(t2);
//		
//		List<Player> l=new ArrayList<Player>();
//		l.add(p2);
//		l.add(p3);

//		t2.setPlayer(l);
//		t.transactionDemo(t2, p1);

//		QUERIES------------

//		List<Team> list=t.findTeamById(2);
//		System.out.println("Teams are:");
//		System.out.println(list);

//		List<CustomPlayer> l2=p.findPlayerById(4);
//		System.out.println(l2.size());
//		System.out.println("Teams are:");
//		for(CustomPlayer c:l2) {
//			System.out.println("Player: " + c.getPlayerName() + "| Country: " + c.getPlayerCountry()
//			+ "| IPL Team: "+ c.getIplTeam().getTeamName() + "| Role: " + c.getPlayerRole() );
//		}

//		List<Player> l3=p.findByName("w");
//		System.out.println("Size: " + l3.size());
//		for(Player i: l3) {
//			System.out.println(i);
//		}

//		List<Team> l4=t.findTeamWithPlayers();
//		for(Team i:l4) {
//			System.out.println(i);
//		}

//		Team t7=new Team();
//		t7.setTeamName("KKR");
//		t.insert(t7);

//		List<TeamWithPlayerCustomize> l5=t.findTeamWithPlayerCustomize();
//		System.out.println("The size is: " + l5.size());
//		for(TeamWithPlayerCustomize i: l5) {
//			System.out.println("Team_id: "+ i.getTeamId() + "| Team_Name " + i.getTeamName()
//			+ "| Player_Name: "+ i.getPlayerName() + "| PlayerCountry: " +
//					i.getPlayerCountry()+ "| PlayerRole: " + i.getPlayerRole());
//		}

//		NAMED QUERY----------
//		int count=t.countTotalTeams();
//		System.out.println("Total teams are: " + count);

//		List<String> l=p.convertNameToUpperCase();
//		for(String i: l) {
//			System.out.println(i);
//		}
//		List<Team> l=t.findTeamById(5);
//		System.out.println(l);

	}

}

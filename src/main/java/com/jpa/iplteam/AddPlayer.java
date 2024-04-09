package com.jpa.iplteam;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/addPlayer")
public class AddPlayer extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int playerId = Integer.parseInt(req.getParameter("playerId"));
		String playerName = req.getParameter("playerName");
		String playerCountry = req.getParameter("playerCountry");
		String iplTeam = req.getParameter("iplTeam");
		int salary = Integer.parseInt(req.getParameter("salary"));
		int playerAge=Integer.parseInt(req.getParameter("playerAge"));
		
		Player player=new Player();
		player.setPlayerId(playerId);
		player.setPlayerName(playerName);
		player.setPlayerAge(playerAge);
		player.setPlayerCountry(playerCountry);
		player.setIplTeam(iplTeam);
		player.setSalary(salary);
		
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("manikMysql");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction =entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(player);
		entityTransaction.commit();
		entityManager.close();
		
		resp.sendRedirect("Index.jsp");
	}
	
}

package com.jpa.iplteam;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saveUpdate")
public class SaveUpdate extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int playerId = Integer.parseInt(req.getParameter("playerId"));
		String playerName = req.getParameter("playerName");
		String playerCountry = req.getParameter("playerCountry");
		String iplTeam = req.getParameter("iplTeam");
		int salary = Integer.parseInt(req.getParameter("salary"));
		int playerAge=Integer.parseInt(req.getParameter("playerAge"));

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("manikMysql");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaUpdate<Player> criteriaQuery = builder.createCriteriaUpdate(Player.class);
		Root<Player> root = criteriaQuery.from(Player.class);
		criteriaQuery.set("playerName", playerName);
		criteriaQuery.set("playerCountry", playerCountry);
		criteriaQuery.set("iplTeam", iplTeam);
		criteriaQuery.set("salary", salary);
		criteriaQuery.set("playerAge", playerAge);
		criteriaQuery.where(builder.equal(root.get("playerId"), playerId));
		
		Query query=em.createQuery(criteriaQuery);
		query.executeUpdate();
		
		CriteriaQuery<Player> criteriaQuery1=builder.createQuery(Player.class);
		Root<Player> root1=criteriaQuery1.from(Player.class);
		criteriaQuery1.select(root1);
		
		Query query1 = em.createQuery(criteriaQuery1);
		List<Player> list=query1.getResultList();
		
		req.setAttribute("playerList", list);
		RequestDispatcher dispatcher=req.getRequestDispatcher("DisplayAllPlayer.jsp");
		dispatcher.forward(req, resp);
		et.commit();
		em.close();
		
		
		
		
	}

}

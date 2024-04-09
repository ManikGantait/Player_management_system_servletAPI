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
import javax.persistence.criteria.Root;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/update")
public class GetUpdateDetails extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int playerId=Integer.parseInt(req.getParameter("playerId"));
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("manikMysql");
		EntityManager em= emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		CriteriaBuilder builder=em.getCriteriaBuilder();
		CriteriaQuery<Player> criteriaQuery=builder.createQuery(Player.class);
		Root<Player> root =criteriaQuery.from(Player.class);
		criteriaQuery.select(root);
		criteriaQuery.where(builder.equal(root.get("playerId"), playerId));
		
		Query query=em.createQuery(criteriaQuery);
		List<Player> list= query.getResultList();
		req.setAttribute("player", list);
		RequestDispatcher dispatcher=req.getRequestDispatcher("SaveUpdate.jsp");
		dispatcher.forward(req, resp);
		et.commit();
		em.close();
		
	}
	
}

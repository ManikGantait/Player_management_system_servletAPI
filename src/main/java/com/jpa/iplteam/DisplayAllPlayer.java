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

@WebServlet("/display")
public class DisplayAllPlayer extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("manikMysql");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Player> criteriaQuery=builder.createQuery(Player.class);
		Root<Player> root=criteriaQuery.from(Player.class);
		criteriaQuery.select(root);
		
		Query query = entityManager.createQuery(criteriaQuery);
		List<Player> list=query.getResultList();
		req.setAttribute("playerList", list);
		RequestDispatcher dispatcher=req.getRequestDispatcher("DisplayAllPlayer.jsp");
		dispatcher.forward(req, resp);
		entityTransaction.commit();
		entityManager.close();
		
	}

}

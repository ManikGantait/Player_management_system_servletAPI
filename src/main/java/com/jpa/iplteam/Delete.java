package com.jpa.iplteam;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class Delete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int playerId=Integer.parseInt(req.getParameter("playerId"));
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("manikMysql");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaDelete<Player> criteriaDelete=builder.createCriteriaDelete(Player.class);
		Root<Player> root=criteriaDelete.from(Player.class);
		criteriaDelete.where(builder.equal(root.get("playerId"), playerId));
		Query query=em.createQuery(criteriaDelete);
		query.executeUpdate();
		
		
		
		
		CriteriaBuilder builder1=em.getCriteriaBuilder();
		CriteriaQuery<Player> criteriaQuery1=builder1.createQuery(Player.class);
		Root<Player> root1=criteriaQuery1.from(Player.class);
		criteriaQuery1.select(root1);
		
		Query query1 = em.createQuery(criteriaQuery1);
		List<Player> list=query.getResultList();
		req.setAttribute("playerList", list);
		RequestDispatcher dispatcher=req.getRequestDispatcher("DisplayAllPlayer.jsp");
		dispatcher.forward(req, resp);
		et.commit();
		em.close();
		
		
				
	}

}

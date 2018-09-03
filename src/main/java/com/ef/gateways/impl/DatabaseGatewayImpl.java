package com.ef.gateways.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ef.entities.BlockedIps;
import com.ef.entities.InputArgs;
import com.ef.entities.Ip;
import com.ef.entities.LogLine;
import com.ef.gateways.DatabaseGateway;
import com.ef.gateways.impl.model.BlockedComments;
import com.ef.gateways.impl.model.Request;

public class DatabaseGatewayImpl implements DatabaseGateway{

	private final EntityManagerFactory entityManagerFactory;
	
	public DatabaseGatewayImpl(){
		entityManagerFactory = Persistence.createEntityManagerFactory( "com.ef.gateways.impl.DatabaseGatewayImpl" );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BlockedIps findBlockedIps(InputArgs inputArgs) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createNativeQuery(
				"SELECT ip \n" + 
				"FROM (\n" + 
				"    SELECT ip, COUNT(1) count\n" + 
				"    FROM `Request` \n" + 
				"    WHERE `requestDate` BETWEEN :startDate AND :endDate \n" + 
				"    GROUP BY ip\n" + 
				") requestCount \n" + 
				"WHERE requestCount.count >= :threshold");
		
		query.setParameter("startDate", inputArgs.getStartDateString());
		query.setParameter("endDate", inputArgs.getEndDateString());
		query.setParameter("threshold", inputArgs.getThreshold());
			
        List<String> blockedIpsStrings = query.getResultList();
		
        List<Ip> blockedIpsList = blockedIpsStrings.parallelStream().map(ipString -> new Ip(ipString)).collect(Collectors.toList());
        
        BlockedIps blockedIps = new BlockedIps(blockedIpsList, inputArgs.getThreshold(), inputArgs.getStartDate(), inputArgs.getEndDate());
        
        blockedIps.toList().stream().forEach(blockedIp -> entityManager.persist(new BlockedComments(blockedIp)));
        
        entityManager.getTransaction().commit();
        entityManager.close();
                       
        
		return blockedIps;
	}


	@Override
	public void saveLog(Stream<LogLine> logLineStream) {
		
		
		logLineStream.sequential().forEach(logLine -> {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			
			entityManager.persist( new Request(logLine.getIp().getIp(), logLine.getRequestDate(), logLine.getMethod(), logLine.getStatusCode(), logLine.getUserAgent()));
			
			entityManager.getTransaction().commit();
			entityManager.close();
		});
		
		
	}
	
}

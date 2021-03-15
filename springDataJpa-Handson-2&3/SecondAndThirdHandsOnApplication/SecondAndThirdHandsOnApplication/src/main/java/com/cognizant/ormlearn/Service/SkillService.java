package com.cognizant.ormlearn.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.Repository.SkillRepository;
import com.cognizant.ormlearn.model.Skill;

@Service
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;
	
	@Transactional
	public Skill get(int id)
	{
		//LOGGER.info("Start");
		
		return skillRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Skill skill)
	{
		
		skillRepository.save(skill);
		
	}
}

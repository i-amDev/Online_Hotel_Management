package com.department.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceService {
	
	@Autowired
	private Repo repo;
	public long getNextSequence(String sequenceName) {
		Sequence sequence = repo.findById(sequenceName).orElse(null);
		if(sequence == null) {
			sequence = new Sequence();
			sequence.setId(sequenceName);
			sequence.setSeq(1);
		}
		else {
			sequence.setSeq(sequence.getSeq()+1);
		}
		repo.save(sequence);
		return sequence.getSeq(); 
	}

}

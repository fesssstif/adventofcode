package fr.sma.adventofcode.resolve.day18;

import fr.sma.adventofcode.resolve.DataFetcher;
import fr.sma.adventofcode.resolve.ExSolution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Day18Ex1 implements ExSolution {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataFetcher dataFetcher;
	
	@Override
	public void run() throws Exception {
		System.out.println("Day18Ex1");
		
		String values = dataFetcher.fetch(18).trim();
		
	}
}
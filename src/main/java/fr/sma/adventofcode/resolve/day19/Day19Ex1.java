package fr.sma.adventofcode.resolve.day19;

import fr.sma.adventofcode.resolve.ExSolution;
import fr.sma.adventofcode.resolve.processor.Cpu;
import fr.sma.adventofcode.resolve.processor.asm.CpuAsmBuilder;
import fr.sma.adventofcode.resolve.util.DataFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * I solve the problem by dynamically creating a java class by writing the bytecode directly from the inputs.
 */
@Component
public class Day19Ex1 implements ExSolution {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataFetcher dataFetcher;
	
	@Override
	public void run() throws Exception {
		System.out.println("Day19Ex1");
		
		String values = dataFetcher.fetch(19).trim();
		
		//Cpu cpu = new CpuLambda(Cpu.readPointer(values), Cpu.readCode(values));
		Cpu cpu = CpuAsmBuilder.buildDynamic(Cpu.readPointer(values), Cpu.readCode(values), true);

		
		long result = cpu.calculate(0, 0, 0, 0, 0, 0);
		
		System.out.println("result = " + result);
	}
}
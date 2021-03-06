package fr.sma.adventofcode.resolve.day12;

import fr.sma.adventofcode.resolve.ExSolution;
import fr.sma.adventofcode.resolve.util.DataFetcher;
import one.util.streamex.StreamEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * parse input into a potline, a potline is constituted of an infinity of empty pot,
 * followed by an arbitrary number of pot, followed by an infinity of empty pots
 * we represent that as string of pot, with an offset.
 * calculate the next potline for 20 iterations
 */
@Component
public class Day12Ex1 implements ExSolution {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final static Pattern POT_LINE_PATTERN = Pattern.compile("initial state: ([.#]+)");
	private final static Pattern RULE_LINE_PATTERN = Pattern.compile("([.#]{5}) => ([.#])");
	
	
	@Autowired
	private DataFetcher dataFetcher;
	
	@Override
	public void run() throws Exception {
		System.out.println("Day12Ex1");
		
		String values = dataFetcher.fetch(12).trim();
		
		String pots = StreamEx.split(values, "\n")
				.map(POT_LINE_PATTERN::matcher)
				.filter(Matcher::matches)
				.map(m -> m.group(1))
				.findFirst().get();
		
		Map<String, String> rules = StreamEx.split(values, "\n")
				.map(RULE_LINE_PATTERN::matcher)
				.filter(Matcher::matches)
				.mapToEntry(m -> m.group(1), m -> m.group(2))
				.toMap();
		
		StreamEx.iterate(new PotLine(pots, 0, 0), s -> s.grow(rules))
				.peek(System.out::println)
				.findFirst(potLine -> potLine.getYear() == 20)
				.map(PotLine::potSum)
				.ifPresent(System.out::println);
	}
}
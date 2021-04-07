package wordcountsample.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wordcountsample.samples.wordcount.WordCountSample;

public class WordCountSampleMain {
	private static final Logger log = LoggerFactory
			.getLogger(WordCountSampleMain.class);

	public static void main(String[] args) throws Exception {
		try {
			WordCountSample wordCountSample = WordCountSample.getInstance();
			wordCountSample.runWordCount(args);
		} catch (Exception exception) {
			log.error("Exception : ", exception);
			throw new Exception(exception);
		}
	}
}

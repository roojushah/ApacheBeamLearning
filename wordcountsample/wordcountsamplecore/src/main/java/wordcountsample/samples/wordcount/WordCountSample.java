package wordcountsample.samples.wordcount;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wordcountsample.utils.WordCountSampleOptions;

public class WordCountSample {
	private static Logger logger = LoggerFactory
			.getLogger(WordCountSample.class);
	private static WordCountSample wordCountSample = null;

	private WordCountSample() {
	}

	public static WordCountSample getInstance() {
		if (wordCountSample == null) {
			logger.debug("WordCountSample object created");
			wordCountSample = new WordCountSample();
		}
		return wordCountSample;
	}

	public void runWordCount(String args[]) throws Exception {
		WordCountSampleOptions options = PipelineOptionsFactory.fromArgs(args)
						.withValidation().as(WordCountSampleOptions.class);
						
		Pipeline pipeline = Pipeline.create(options);
		logger.info("Word count started, Input - " + options.getInput());
		pipeline.apply("ReadLines", TextIO.read().from(options.getInput()))
				.apply("SplitWords", ParDo.of(new ExtractWordsFunction()))
				.apply("CountWords", Count.perElement())
				.apply("FormatOutputs",
						MapElements.via(new FormatAsTextFunction()))
				.apply("WriteOutput", TextIO.write().to(options.getOutput()));
		pipeline.run().waitUntilFinish();
		logger.info("Word count ended, Output - " + options.getOutput());
	}
}

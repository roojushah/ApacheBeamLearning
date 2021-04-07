package wordcountsample.samples.wordcount;

import org.apache.beam.sdk.metrics.Counter;
import org.apache.beam.sdk.metrics.Distribution;
import org.apache.beam.sdk.metrics.Metrics;
import org.apache.beam.sdk.transforms.DoFn;

import wordcountsample.constants.Constants;

public class ExtractWordsFunction extends DoFn<String, String> {
	private static final long serialVersionUID = -1068221924349060674L;
	private final Counter emptyLines = Metrics.counter(
			ExtractWordsFunction.class, "emptyLines");
	private final Distribution lineLenDist = Metrics.distribution(
			ExtractWordsFunction.class, "lineLenDistro");

	@ProcessElement
	public void processElement(@Element String element,
			OutputReceiver<String> receiver) {
		lineLenDist.update(element.length());
		if (element.trim().isEmpty()) {
			emptyLines.inc();
		}
		// Split the line into words.
		String[] words = element.toLowerCase().split(Constants.TOKENIZER_PATTERN, -1);

		// Output each word encountered into the output PCollection.
		for (String word : words) {
			if (!word.isEmpty()) {
				receiver.output(word);
			}
		}
	}
}

package wordcountsample.samples.wordcount;

import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;

/** A SimpleFunction that converts a Word and Count into a printable string. */
public class FormatAsTextFunction extends
		SimpleFunction<KV<String, Long>, String> {
	private static final long serialVersionUID = -8575490032678855180L;

	@Override
	public String apply(KV<String, Long> input) {
		return input.getKey() + " : " + input.getValue();
	}
}

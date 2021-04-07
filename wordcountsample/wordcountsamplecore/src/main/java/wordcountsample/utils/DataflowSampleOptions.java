package wordcountsample.utils;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation.Required;

public interface DataflowSampleOptions extends DataflowPipelineOptions {
	@Description("Path of the file to read from")
	@Required
	String getInput();

	void setInput(String value);

	@Description("Path of the file to write to")
	@Required
	String getOutput();

	void setOutput(String value);
}
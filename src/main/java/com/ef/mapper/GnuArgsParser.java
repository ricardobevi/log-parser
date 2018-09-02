package com.ef.mapper;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.ef.mapper.exceptions.BadArgumentsException;

public class GnuArgsParser implements ArgsParser {

	
	private static final String ACCESS_LOG = "accesslog";
	private static final String START_DATE = "startDate";
	private static final String DURATION = "duration";
	private static final String THRESHOLD = "threshold";

	public GnuArgsParser() {}

	public InputArgsDto parse(String[] args){

		Options options = createOptions();

		CommandLineParser parser = new DefaultParser();
		
		try {
			
			CommandLine cmd = parser.parse( options, args);
			
			return new InputArgsDto(
					cmd.getOptionValue(ACCESS_LOG), 
					cmd.getOptionValue(START_DATE), 
					cmd.getOptionValue(DURATION), 
					cmd.getOptionValue(THRESHOLD)
					);
			

		} catch (ParseException e) {
			throw new BadArgumentsException(e.getMessage() + "\n\n" + createFormatedHelpMessage(options));
		}
	}

	private String createFormatedHelpMessage(Options options) {
		HelpFormatter formatter = new HelpFormatter();

	    StringWriter out = new StringWriter();
	    PrintWriter pw = new PrintWriter(out);

	    formatter.printHelp(
	    		pw, 
	    		120, 
	    		"parser.jar", 
	    		"", 
	    		options,
	            formatter.getLeftPadding(), 
	            formatter.getDescPadding(), 
	            "", 
	            true);
	    
	    pw.flush();
	    
		return out.toString();
	}

	private Options createOptions() {
		Options options = new Options();
		
		options.addOption( Option.builder().longOpt(ACCESS_LOG).argName("path").hasArg().desc("Path to the access.log file").required().build() );
		options.addOption( Option.builder().longOpt(START_DATE).argName("date").hasArg().desc("Stating point to meassure in \"yyyy-MM-dd.HH:mm:ss\" format").required().build() );
		options.addOption( Option.builder().longOpt(DURATION).argName("hourly,daily").hasArg().desc("'hourly' or 'daily'").required().build() );
		options.addOption( Option.builder().longOpt(THRESHOLD).argName("int").hasArg().desc("Number of request per IP to filter").required().build() );
		
		return options;
	}
 
}

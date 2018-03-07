package io.github.victorhugonf.javaee.ejb.utils;

public class CONSTANTS {
	
	private CONSTANTS(){}
	
	public class DATA_BASE{
		
		private DATA_BASE(){}
		
		public class TABLES{
			
			private TABLES(){}
			
			public class INDUSTRIES{
				
				private INDUSTRIES(){}
				
				public static final String NAME = "industries";
				public static final String SEQUENCE = "industries_sequence";
				
				public class COLUMNS{
					
					private COLUMNS(){}
					
					public static final String NAME = "name";
				}
			}
			
			public class AIRCRAFTS{
				
				private AIRCRAFTS(){}
				
				public static final String NAME = "aircrafts";
				public static final String SEQUENCE = "aircrafts_sequence";
				
				public class COLUMNS{
					
					private COLUMNS(){}
					
					public static final String MODEL = "model";
					public static final String ID_INDUSTRY = "id_industry";
				}
				
				public class REFERENCES{
					
					private REFERENCES(){}
					
					public static final String INDUSTRY = "industry";
				}
			}
			
			public class LOG_ERRORS{
				
				private LOG_ERRORS(){}
				
				public static final String NAME = "log_errors";
				public static final String SEQUENCE = "log_errors_sequence";
				
				public class COLUMNS{
					
					private COLUMNS(){}
					
					public static final String STACK_TRACE = "stack_trace";
				}
			}
		}
	
	}
	
}

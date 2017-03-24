# SIMS

### Complete build 
	mvn clean install

### Run Instance in Jetty container
	mvn jetty:run

### Code Coverage Reports(reports path - target/site/cobertura)
	mvn cobertura:cobertura
	
### File name convention
	Corresponding HTML, CSS, JS files name should be same follwed with their extension
	include in the respective directories
### HTML elements ID and class format
	pppp_CCC_EEE
	ppp - first 4 letters of html page name
	ccc - (short form or unique word) purpose of the element verbal task description
	EEE - HTML element type
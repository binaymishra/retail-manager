How to run ?
============

	Step (1)
	
		Download/clone project from git and extract the zip as folder.
		
		Navigate to the folder and open it in to command prompt.
		
		issue command, [Note:gradle must be installed.]
		
		c:\retail-manager>gradle build
		
		it will create an executable java inside 
		
		c:\retail-manager\build\libs\retail-manager-<VERSION>.jar
		
		RUN the jar via java command line.
		
		c:\retail-manager\build\libs> java -jar retail-manager-<VERSION>.jar
		
	
		
	Step (2)
	
		Once the executable is running, Use a RESTfult client to run the end points of the RESTful Service
		
		Base URL = http://localhost:3000/retail-manager/v1
		
			There are two endpoints :
			
				1) /shops 
				
						* Operation(Method) POST
						* Content-Type	application/json;charset=UTF-8
						* Request Body Should be one shop per request. List of shop is not supported.
						* Body : 
									{ 
										"shopName":"Home Town", 
											"shopAddress": { 
												"number":1600, 
												"postCode":700156 
												} 
									}
		
				2) /shops?customerLatitude=22.5818985&customerLongitude=88.4529769
				
						* Operation(Method) GET
						* Expected Status code : 200 OK
						* Response Body(Nearest Shop to customer)

	    						{
	        						"shopName": "Home Town",
	        							"shopAddress":
	        								{
	           								 "number": 1600,
	            								 "postCode": 700156
	        								},
	        							"shopLongitude": 88.4586321,
	        							"shopLatitude": 22.5829933
	    						}

								
						
Basic Steps to run the program:

1). Run the script file to generate the db.
2). Open the folder QAManagement and in that open solution QAManagement.sln.
3). Install Entity framework from the NuGet Package manager
4). Import the db from DBFirst method.
5). Add constraint for email and required feild for password in the model for validation.
      	
	[Required]
	[DisplayName("Password")]
	public string PasswordHash { get; set; }
	[Required]
	[EmailAddress(ErrorMessage = "Enter Valid EmailAddress")]
	public string Email { get; set; }
6). after that you can run the Project named Question Villa.
7). after Role based sign in you can navigate home page via home button in the navbar. 